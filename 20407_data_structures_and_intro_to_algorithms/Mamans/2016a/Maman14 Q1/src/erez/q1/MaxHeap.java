/*
  MaxHeap.java
  20407 Data Structures and Introduction to Algorithms
  Maman 14
*/
package erez.q1;

/* This class represents a d-ary MAX-HEAP.
 * Based on the algorithms in the book, adapted to d-ary heaps */
public class MaxHeap {
	private int d;
	private int[] data;
	private int heapSize;
	
	public MaxHeap(int d, int[] array, Statistics stat) {
		this.d = d;
		BUILD_MAX_HEAP(array, stat);
	}

	private void BUILD_MAX_HEAP(int[] sourceArray, Statistics stat) {
		heapSize = sourceArray.length;
		data = sourceArray;
		int lastNonLeaf = PARENT(heapSize - 1);
		for (int i = lastNonLeaf; i >= 0; i--) {
			MAX_HEAPIFY(i, stat);
		}
	}

	private void MAX_HEAPIFY(int i, Statistics stat) {
		int largest = i;
		int firstChild = CHILD(i, 0);
		int lastChild = Math.min(firstChild + d - 1, heapSize - 1);
		
		for (int currChild = firstChild; currChild <= lastChild; currChild++) {
			if (data[currChild] > data[largest])
				largest = currChild;
			stat.comparisons++;
		}
		
		if (largest != i) {
			swap(data, i, largest);
			stat.copies += 3;
			MAX_HEAPIFY(largest, stat);
		}
	}

	/* Sorts the array in-place using d-ary heap
	 * Returns the number of comparisons and copies done to the array elements */
	public static Statistics HEAPSORT(int d, int[] array) {
		Statistics stat = new Statistics();
		MaxHeap heap = new MaxHeap(d, array, stat);
		for (int i = array.length - 1; i >= 1; i--) {
			swap(array, 0, i);
			stat.copies += 3;
			heap.heapSize--;
			heap.MAX_HEAPIFY(0, stat);
		}
		
		return stat;
	}

	private int PARENT(int i) {
		return (i - 1) / d;
	}

	private int CHILD(int i, int k) {
		return d * i + 1 + k;
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static class Statistics {
		public int copies = 0;
		public int comparisons = 0;
	}
}
