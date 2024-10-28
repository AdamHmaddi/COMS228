package edu.iastate.cs2280.hw1;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Adam Hmaddi
 *
 */
public class Town {

	private int length, width; // Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the
	 * given seed. This constructor does not populate each cell of the grid (but
	 * should assign a 2D array to it).
	 * 
	 * @param length number of rows in the grid
	 * @param width  number of cols in the grid
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of
	 * catching it. Ensure that you close any resources (like file or scanner) which
	 * is opened in this function.
	 * 
	 * @param inputFileName input file name
	 * @throws FileNotFoundException throwing exception if file is not found
	 */
	public Town(String inputFileName) throws FileNotFoundException {

		Scanner scnr = null;
		try {
			File file = new File(inputFileName);
			scnr = new Scanner(file);
			this.length = scnr.nextInt();
			this.width = scnr.nextInt();
			grid = new TownCell[length][width];

			// This is a loop through the file to initialize the grid with the right cell
			// type
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					String cellState = scnr.next();
					if (cellState.equals("C")) {
						grid[i][j] = new Casual(this, i, j);
					} else if (cellState.equals("S")) {
						grid[i][j] = new Streamer(this, i, j);
					} else if (cellState.equals("R")) {
						grid[i][j] = new Reseller(this, i, j);
					} else if (cellState.equals("O")) {
						grid[i][j] = new Outage(this, i, j);
					} else if (cellState.equals("E")) {
						grid[i][j] = new Empty(this, i, j);
					}
				}
			}

		} finally {
			if (scnr != null) {
				scnr.close();
			}
		}
	}

	/**
	 * Returns width of the grid.
	 * 
	 * @return the width of the grid
	 */
	public int getWidth() {
		// TODO: Write/update your code here.
		return width;
	}

	/**
	 * Returns length of the grid.
	 * 
	 * @return the length of the grid
	 */
	public int getLength() {
		// TODO: Write/update your code here.
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following
	 * class object: Casual, Empty, Outage, Reseller OR Streamer
	 * 
	 * @param seed the seed for the random number generation
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);

		// THis is a loop to randomly assign the cell types based on random values
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				int cellState = rand.nextInt(5);
				if (cellState == 0) {
					grid[i][j] = new Reseller(this, i, j);
				} else if (cellState == 1) {
					grid[i][j] = new Empty(this, i, j);
				} else if (cellState == 2) {
					grid[i][j] = new Casual(this, i, j);
				} else if (cellState == 3) {
					grid[i][j] = new Outage(this, i, j);
				} else if (cellState == 4) {
					grid[i][j] = new Streamer(this, i, j);
				}
			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell
	 * type. Each letter should be separated either by a single space or a tab. And
	 * each row should be in a new line. There should not be any extra line between
	 * the rows.
	 * 
	 * @return returns a formatted string of the grid
	 */
	@Override
	public String toString() {
		String s = "";
		// THis is a loop through the grid to build the string representation of the
		// grid
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				s += grid[i][j].who().toString().charAt(0) + " ";
			}
			s += "\n";
		}

		return s.toString();
	}
}
