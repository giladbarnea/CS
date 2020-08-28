/*
  Main.java
  20407 Data Structures and Introduction to Algorithms
  Maman 14
*/
package erez.q1;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import erez.q1.MaxHeap.Statistics;

public class Main {
	
	private static Random rand = new Random();

	public static void main(String[] args) {
		// Create the input arrays
		// rand.ints requires Java 8
		int[] arrayA = rand.ints(50, 0, 1024).toArray();
		int[] arrayB = rand.ints(100, 0, 1024).toArray();
		int[] arrayC = rand.ints(200, 0, 1024).toArray();
		
		TreeMap<String, int[]> arrays = new TreeMap<>();
		arrays.put("A", arrayA);
		arrays.put("B", arrayB);
		arrays.put("C", arrayC);
		
		// Calculate and print the number of comparisions and copies on each of the input arrays for d = 2, 3, 4, 5
		for (String arrName : arrays.keySet()) {
			int[] currArr = arrays.get(arrName);
			System.out.println("Results for sequence " + arrName + ": (length = " + currArr.length + ")");
			for (int d = 2; d <= 5; d++) {
				int[] tempArray = currArr.clone();
				Statistics stat = MaxHeap.HEAPSORT(d, tempArray);
				
				System.out.println("\td = " + d + " Comparisions: " + stat.comparisons);
				System.out.println("\td = " + d + " Copies:       " + stat.copies);
			}
		}
	}
	
	// This alternative program can be used to get more accurate results for an array of a given length.
	// It runs HEAPSORT many times on random arrays and prints the average results for number of comparisions and copies
	public static void main2(String[] args) {
		final int LENGTH = 50;
		
		ArrayList<Integer> comparisions = new ArrayList<>();
		ArrayList<Integer> copies = new ArrayList<>();
		for (int d = 2; d <= 5; d++) {
			// Repeat 100000 times
			for (int i = 0; i < 100000; i++) {
				int[] array = rand.ints(LENGTH, 0, 1024).toArray();
				Statistics stat = MaxHeap.HEAPSORT(d, array);
				comparisions.add(stat.comparisons);
				copies.add(stat.copies);
			}
			Double comparisionsAvg = comparisions.stream().mapToInt(Integer::intValue).average().getAsDouble();
			Double CopiesAvg = copies.stream().mapToInt(Integer::intValue).average().getAsDouble();
			System.out.println("d = "+d+", size = " + LENGTH + ": comparisions = " + comparisionsAvg + " copies = " + CopiesAvg);
		}
		
	}

}
