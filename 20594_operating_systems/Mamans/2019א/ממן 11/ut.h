/*****************************************************************************
                The Open University - OS course

   File:        ut.h

   Written by:  OS course staff

   Description: this file defines a simple library for creating & scheduling 
                user-level threads. 

                           DO NOT CHANGE THIS FILE!
 ****************************************************************************/
#ifndef _UT_H
#define _UT_H

#include <ucontext.h>

#define MAX_TAB_SIZE 128 // the maximal threads table size.
#define MIN_TAB_SIZE 2   // the minimal threads table size.

#define SYS_ERR -1       // system-related failure code
#define TAB_FULL -2      // full threads table failure code

#define STACKSIZE 8192   // the thread stack size.

/* The TID (thread ID) type. TID of a thread is actually the index of the thread in the
   threads table. */
typedef short int tid_t;

/*
This type defines a single slot (entry) in the threads table. Each slot describes a single
thread. Note that we don't need to keep the thread state since every thread is always ready
or running. We also don't have to support adding/stopping thread dynamically, so we also don't
have to manage free slots. 
*/
typedef struct _ut_slot {
  ucontext_t uc;
  unsigned long vtime;  // the CPU time (in milliseconds) consumed by this thread. 
  void (*func)(int);    // the function executed by the thread.
  int arg;              // the function argument.
} ut_slot_t, *ut_slot;

unsigned long ut_get_vtime(tid_t tid);
int ut_init(int tab_size);
tid_t ut_spawn_thread(void (*func)(int), int arg);
void handler(int signal);
int ut_start(void);

#endif
