package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Adam Hmaddi
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		//
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new
		// PointScanner objects, which are created using four different values
		// of the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		//
		//

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();

		Scanner scnr = new Scanner(System.in);
		PointScanner[] scanners = new PointScanner[4];

		// For each input of points, do the following.
		//
		// a) Initialize the array scanners[].
		//
		// b) Iterate through the array scanners[], and have every scanner call the
		// scan()
		// method in the PointScanner class.
		//
		// c) After all four scans are done for the input, print out the statistics
		// table from
		// section 2.
		//
		// A sample scenario is given in Section 2 of the project description.

		while (true) {
			// Display Menu Options
			System.out.println("Keys: 1 (random numbers) 2 (file input) 3 (exit)");
			System.out.print("Trial: ");

			int input = scnr.nextInt();

			if (input == 1) {
				// Handles random point generation
				System.out.print("Enter number of random points: ");
				int numPts = scnr.nextInt();

				Random rand = new Random();
				Point[] points = generateRandomPoints(numPts, rand);

				runAndPrintStatistics(points, null);

			} else if (input == 2) {
				// Handles file input
				System.out.print("Enter file name: ");
				String fileName = scnr.next();

				runAndPrintStatistics(null, fileName);

			} else if (input == 3) {
				// Exits the program
				break;
			} else {
				// Handles invalid input
				System.out.println("Error: Invalid option. Please enter 1, 2, or 3.");
				continue;
			}

			System.out.println();

		}
		scnr.close();
	}

	/**
	 * This method runs sorting algorithms on a set of points and prints the
	 * statistics. It also creates PointScanner objects for each sorting algorithm,
	 * then it runs the scan on each one of them, and then prints the performance
	 * statistics.
	 * 
	 * @param points   Array of Point objects to be sorted
	 * @param fileName The name of the file to read the points
	 * @throws FileNotFoundException If the file isn't found
	 */
	private static void runAndPrintStatistics(Point[] points, String fileName) throws FileNotFoundException {
		PointScanner[] scanners = new PointScanner[4];

		if (points != null) {
			// Creates scanners for point array input
			scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(points, Algorithm.MergeSort);
			scanners[3] = new PointScanner(points, Algorithm.QuickSort);
		} else {
			// Creates scanners for file input
			scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
			scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
		}

		// The following prints table's header
		System.out.println();
		System.out.printf("%-15s %9s %12s%n", "algorithm", "size", "time (ns)");
		System.out.println("----------------------------------");

		for (PointScanner scanner : scanners) {
			scanner.scan();
			System.out.println(scanner.stats());
		}

		System.out.println("----------------------------------");
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {

		if (numPts < 1) {
			throw new IllegalArgumentException("Error: The number of points should be at least 1");
		}

		Point[] points = new Point[numPts];
		// Generates random points
		for (int i = 0; i < numPts; i++) {
			// Generate x and y coordinates in the range of -50, 50
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;
			points[i] = new Point(x, y);
		}
		return points;
	}

}
