	.text

/* Switch from current_thread to next_thread. Make next_thread
 * the current_thread, and set next_thread to 0.
 * Use eax as a temporary register, which should be caller saved.
 */
	.globl thread_switch
thread_switch:
        
	/* push registers in current_thread stack */
	pushal

	/* save sp of current_thread */
	movl current_thread, %eax
	movl %esp, (%eax)

	/* switch stack to next_thread */
	movl next_thread, %eax
	movl (%eax), %esp
	
	/* set current_thread to next_thread */
	movl %eax, current_thread

	/* pop next_thread registers */
	popal

	/* next_thread = 0 */
	movl $0, next_thread
	
	/* pop return address from stack */
	ret				

