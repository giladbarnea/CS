/*Intro
Description:    This library contains implementation of binary semaphores
Student Name:   Amit Abecasis
Student ID:     205480072
 */

#include "binsem.h"
#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <unistd.h>

#define LOCKED 0
#define UNLOCKED 1

void binsem_init(sem_t *s, int init_val)
{
    if(!s)
    {
        fprintf(stderr, "Invalid pointer to semaphore.\n");
        return ;
    }
	xchg(s,init_val != LOCKED);
}

void binsem_up(sem_t *s)
{
	xchg(s, UNLOCKED);
}

int binsem_down(sem_t *s)
{
    int num = LOCKED;

    //If the semaphore was locked before, continue lock it and send alarm signal in order to unlock it.
    while( (num = xchg(s, num)) == LOCKED)
        if(kill(getpid(), SIGALRM) < 0)
        {
            fprintf(stderr, "Error in sending SIGALRM signal.\n");
            return -1;
        }

    //Else - quit the function.
	return 0;
}