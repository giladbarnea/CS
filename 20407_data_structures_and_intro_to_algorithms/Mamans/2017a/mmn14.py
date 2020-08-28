'''
Name: mmn14.py
Date: 17.12.2016
Author: Itay Brandes

This is a simple script that creates three random arrays of lengths 50, 100 and 200,
and makes some benchmark tests of d-ary heap sort, according to different d values.
'''
import random

# Global Variables
total_comparisons = 0  # Performance Counter
total_copies = 0  # Performance Counter


def parent(i, d):
	"Function returns the parent of the node i."
	return (i-1)/d


def child(i, k, d):
	"Function returns the child k of the node i. Indexes are 0-indexed, so the first child will be child number 0."
	return d*i + (k+1)
	
	
def max_heapify(A, heapsize, d, i):
	'''Function heapifies the node i in the given heap A.
	The function makes sure that the heap starting from node i is a correct max heap.
	The pseudocode is taken from my Maman12 solution. I also added performance counters.'''
	global total_comparisons, total_copies  # performance counters are global variables.
	
	largest = i  # First we set i to be the largest
	for k in range(d):  # Then we scan the children to see if there's someone larger
		if child(i, k, d) > heapsize-1:  # if we overflow, no such child exist, and we exit the loop
			break
			
		total_comparisons += 1
		if A[child(i, k, d)] > A[largest]:  # if the child's bigger, we set it as the largest so far.
			largest = child(i, k, d)
	
	# If the largest isn't the given node, it's one of the children, and we need to swap them.
	# The swapping may break the heap from the original children index downward, so we have to heapify it as well. 
	if largest != i:
		total_copies += 3
		A[i], A[largest] = A[largest], A[i]
		max_heapify(A, heapsize, d, largest)

		
def build_max_heap(A, d):
	"Function builds a max d-ary heap out of the array A. The solution is based on page 111 on the book."
	heapsize = len(A)
	# We iterate from the bottom to the top, starting from the last non-leaf node.
	# We heapify the node we're iterating over.
	# (len(A)-(d-1))/d is the index of the last non-leaf node.
	# Because it's a division between integers, we're getting the floor value, so there's no need to call floor().
	# The loop is running until -1, not including it, so it's actually running until 0, including.
	for i in range((len(A)-(d-1))/d, -1, -1):
		max_heapify(A, heapsize, d, i)
	return heapsize
	

def heapsort(A, d):
	"Functions builds a max heap out of A and then executes heap sort. The solution is based on page 113 on the book."
	global total_copies  # performance counters are global variables.
	
	heapsize = build_max_heap(A, d)  # First, we build a max heap
	
	for i in range(len(A)-1, 0, -1):  # Loop from the last node to the second node.
		total_copies += 3
		A[0], A[i] = A[i], A[0]
		heapsize -= 1
		max_heapify(A, heapsize, d, 0)
	
	
def get_rand_list(length, start, end):
	"Returns a random list of a specific length from start (starting) to end (excluding)"
	return [random.randrange(start, end) for i in range(length)]

	
def main():
	"Main function of the script."
	global total_comparisons, total_copies  # performance counters are global variables.
	
	print "Creating a 50 elements random array as A."
	A = get_rand_list(50, 0, 1024)
	print "Creating a 100 elements random array as B."
	B = get_rand_list(100, 0, 1024)
	print "Creating a 200 elements random array as C."
	C = get_rand_list(200, 0, 1024)
	print ""
	
	for d in range(2,6):
		# At each iteration, we're going to send a copy of the arrays, and not the arrays themselves,
		# so the next iteration can still run on the original randomized arrays.
		total_comparisons = total_copies = 0  # Cleaning up the counters
		heapsort(A[:], d)
		print "Running heapsort(A, %d); Total Comparisons: %d\tTotal Copies: %d" % (d, total_comparisons, total_copies)
		
		total_comparisons = total_copies = 0  # Cleaning up the counters
		heapsort(B[:], d)
		print "Running heapsort(B, %d); Total Comparisons: %d\tTotal Copies: %d" % (d, total_comparisons, total_copies)
		
		total_comparisons = total_copies = 0  # Cleaning up the counters
		heapsort(C[:], d)
		print "Running heapsort(C, %d); Total Comparisons: %d\tTotal Copies: %d" % (d, total_comparisons, total_copies)
		print ""

if __name__ == "__main__":
	main()
