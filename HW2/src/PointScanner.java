package edu.iastate.cs2280.hw2;

/**
 * 
 * @author Adam Hmaddi
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException("Input array of points can't be null or empty");
		}

		this.points = new Point[pts.length];
		System.arraycopy(pts, 0, this.points, 0, pts.length);
		this.sortingAlgorithm = algo;

	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {

		Scanner scnr = null;
		try {
			File file = new File(inputFileName);
			scnr = new Scanner(file);
			int count = 0;
			while (scnr.hasNextInt()) {
				count++;
				scnr.nextInt();
			}

			if (count % 2 != 0) {
				throw new InputMismatchException("The input file has an odd number of integers");
			}
			scnr.close();

			scnr = new Scanner(file);
			this.points = new Point[count / 2];
			int index = 0;

			while (scnr.hasNextInt()) {
				int x = scnr.nextInt();
				int y = scnr.nextInt();
				points[index++] = new Point(x, y);
			}

			this.sortingAlgorithm = algo;
		} finally {
			if (scnr != null) {
				scnr.close();
			}
		}
	}

	/**
	 * Returns the sorted array of points.
	 * 
	 * @return sorted array of points
	 */
	public Point[] getPoints() {
		return points; // Returns the array of points
	}

	/**
	 * Returns the median coordinate point.
	 * 
	 * @return median point
	 */
	public Point getMedian() {
		return medianCoordinatePoint; // Returns the median point
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		// create an object to be referenced by aSorter according to sortingAlgorithm.
		// for each of the two
		// rounds of sorting, have aSorter do the following:
		//
		// a) call setComparator() with an argument 0 or 1.
		//
		// b) call sort().
		//
		// c) use a new Point object to store the coordinates of the
		// medianCoordinatePoint
		//
		// d) set the medianCoordinatePoint reference to the object with the correct
		// coordinates.
		//
		// e) sum up the times spent on the two sorting rounds and set the instance
		// variable scanTime.

		// Creates the right sorter based on the sorting algorithm
		switch (sortingAlgorithm) {
		case SelectionSort:
			aSorter = new SelectionSorter(points);
			break;
		case InsertionSort:
			aSorter = new InsertionSorter(points);
			break;
		case MergeSort:
			aSorter = new MergeSorter(points);
			break;
		case QuickSort:
			aSorter = new QuickSorter(points);
			break;
		default:
			throw new IllegalArgumentException("Sorting algorithm unknown");
		}

		long startTime = System.nanoTime();

		// Sort by x-coordinate to find the median x
		aSorter.setComparator(0);
		aSorter.sort();
		int medianX = aSorter.getMedian().getX();

		// Sort by y-coordinate to find the median y
		aSorter.setComparator(1);
		aSorter.sort();
		int medianY = aSorter.getMedian().getY();

		// Creates the median coordinate point
		medianCoordinatePoint = new Point(medianX, medianY);

		// Calculate and set the total scan time
		long endTime = System.nanoTime();
		scanTime = endTime - startTime;
	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 * 
	 * @return a formatted string containing the algorithm, size, and execution time
	 */
	public String stats() {
		return String.format("%-15s %5d %10d", sortingAlgorithm.toString(), points.length, scanTime);
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 * 
	 * @return A string representation of the median coordinate point
	 */
	@Override
	public String toString() {
		return "MCP: " + medianCoordinatePoint.toString();
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException if the file can't be found created or written
	 *                               to
	 */
	public void writeMCPToFile() throws FileNotFoundException {

		PrintWriter out = new PrintWriter("MCP.txt");
		out.println(this.toString());
		out.close();
	}

}
