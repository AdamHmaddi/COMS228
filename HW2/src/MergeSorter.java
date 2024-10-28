package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Adam Hmaddi
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {

		super(pts);
		this.algorithm = "merge sort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {

		// Call the recursive merge sort Method
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		if (pts.length < 2) {
			return;
		}

		int midPoint = pts.length / 2;

		Point[] left = new Point[midPoint];
		Point[] right = new Point[pts.length - midPoint];

		System.arraycopy(pts, 0, left, 0, midPoint);
		System.arraycopy(pts, midPoint, right, 0, pts.length - midPoint);

		mergeSortRec(left);
		mergeSortRec(right);

		merge(pts, left, right);
	}

	// Other private methods if needed ...

	/**
	 * It merges two sorted sub arrays into one single sorted array
	 * 
	 * @param pts   array to merge into
	 * @param left  left sub array
	 * @param right right sub array
	 */
	private void merge(Point[] pts, Point[] left, Point[] right) {

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {
			if (pointComparator.compare(left[i], right[j]) <= 0) {
				pts[k++] = left[i++];
			} else {
				pts[k++] = right[j++];
			}
		}

		while (i < left.length) {
			pts[k++] = left[i++];
		}

		while (j < right.length) {
			pts[k++] = right[j++];
		}
	}

}
