/*Intro
Description:    This library contains implementation of thread management in the user leve.
Student Name:   Amit Abecasis
Student ID:     205480072
 */

//Includes
#include "ut.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>

//Constants
#define SWAP_TIME 1     //Swapping time between threads
#define TIMER_MIC 100   //Timer interval in milli-seconds

//Globals
static ut_slot trdTble = NULL;              //Thread Table
static short int tbleSize = 0;
static short int lastTrd = 0;               //Number of threads = Last Thread That was initialized
static volatile	tid_t currThreadNum = 0;    //Current Running Thread Id

//Functions
int ut_init(int tab_size)   //Initializes the thread table
{
    if ((MIN_TAB_SIZE > tab_size) || (tab_size > MAX_TAB_SIZE))
        tab_size = MAX_TAB_SIZE;

    trdTble = (ut_slot)calloc(++tab_size, sizeof(ut_slot_t));

    if (!trdTble)
    {
        fprintf(stderr, "Error allocating memory.\n");
        return SYS_ERR;
    }

    tbleSize = tab_size;

    return 0;
}

tid_t ut_spawn_thread(void (*func)(int), int arg)   //Initializes new thread
{
    char *str = NULL;
    tid_t newTrd = lastTrd + 1;

    if (!func)
    {
        fprintf(stderr, "Invalid function.\n");
        return SYS_ERR;
    }

    if (!trdTble)
    {
        fprintf(stderr, "Thread table isnt initialized.\n");
        return SYS_ERR;
    }

    if (newTrd >= tbleSize)
    {
        fprintf(stderr, "Thread table is full.\n");
        return TAB_FULL;
    }

    if (getcontext(&(trdTble[newTrd].uc))<0)
    {
        fprintf(stderr, "Error in getcontext.\n");
        return SYS_ERR;
    }

    str = (char *)calloc(STACKSIZE, sizeof(char));
    if (!str)
    {
        fprintf(stderr, "Error allocating memory.\n");
        return SYS_ERR;
    }

    trdTble[newTrd].uc.uc_link = &(trdTble[0].uc);
    trdTble[newTrd].uc.uc_stack.ss_sp = str;
    trdTble[newTrd].uc.uc_stack.ss_size = STACKSIZE * sizeof(char);
    trdTble[newTrd].vtime = 0;
    trdTble[newTrd].func = func;
    trdTble[newTrd].arg = arg;

    lastTrd++;
    return newTrd;
}

void handler(int signal)    //Handles signals
{
    int oldTrd;

    if (!trdTble)
    {
        fprintf(stderr, "Thread table isnt initialized.\n");
        exit(1);
    }

    switch(signal)
    {
        case SIGALRM:       //Switching threads if alarm signal was sent
            oldTrd = currThreadNum;
            currThreadNum = (currThreadNum % lastTrd) + 1;
            alarm(SWAP_TIME);

            if (swapcontext(&(trdTble[oldTrd].uc), &(trdTble[currThreadNum].uc)) == SYS_ERR)
            {
                fprintf(stderr, "Error in swapping context.\n");
                perror(NULL);
                exit(1);
            }
            break;

        case SIGVTALRM:     //Adding run time if timer signal was sent
            trdTble[currThreadNum].vtime += TIMER_MIC;
            break;
    }
}

int ut_start(void)      //Starting all threads together
{
    struct sigaction sa;
    struct itimerval itv;
    int i;

    if (!trdTble)
    {
        fprintf(stderr, "Thread table isnt initialized.\n");
        return SYS_ERR;
    }

    //Initializing Signal handler and Timer
    sa.sa_flags = SA_RESTART;
    sa.sa_handler = handler;
    sigfillset(&sa.sa_mask);

    itv.it_interval.tv_sec = 0;
    itv.it_interval.tv_usec = TIMER_MIC * 1000; //Convert from mili-seconds to micro-seconds
    itv.it_value = itv.it_interval;

    //Setting the handler and timer
    if (sigaction(SIGVTALRM, &sa, NULL)<0)
    {
        fprintf(stderr, "Error: sigaction for SIGVTALRM in %s\n", __FUNCTION__);
        perror(NULL);
        exit(1);
    }

    if (sigaction(SIGALRM, &sa, NULL)<0)
    {
        fprintf(stderr, "Error: sigaction for SIGALRM in %s\n", __FUNCTION__);
        perror(NULL);
        exit(1);
    }

    if (setitimer(ITIMER_VIRTUAL, &itv, NULL)<0)
    {
        fprintf(stderr, "Error in setting timer.\n");
        perror(NULL);
        exit(1);
    }

    for(i = 1; i <= lastTrd; i++)
        makecontext(&(trdTble[i].uc), (void(*)(void)) trdTble[i].func , 1, trdTble[i].arg);

    //Swapping first thread to start the next thread swapping
    alarm(SWAP_TIME);
    currThreadNum = 1;
    trdTble[currThreadNum].vtime += TIMER_MIC;

    if (swapcontext(&(trdTble[0].uc), &(trdTble[currThreadNum].uc))<0)
    {
        fprintf(stderr, "Error in swaping context.\n");
        perror(NULL);
        return SYS_ERR;
    }
    return 0;
}

unsigned long ut_get_vtime(tid_t tid)   //Returns thread's run time
{
    if (!trdTble)
    {
        fprintf(stderr, "Error: Must call ut_init before using %s\n", __FUNCTION__);
        return SYS_ERR;
    }

    if ((tid < 1) || (tid > lastTrd))
    {
        fprintf(stderr, "Error: Invalid parameter to %s\n", __FUNCTION__);
        return SYS_ERR;
    }
    return trdTble[tid].vtime;
}
