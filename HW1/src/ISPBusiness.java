package edu.iastate.cs2280.hw1;

import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * @author Adam Hmaddi
 *
 *         The ISPBusiness class performs simulation over a grid plain with
 *         cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());

		// The loop updates each cell in the grid by calculating its next state
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}

		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town the current Town object
	 * @return: The profit
	 */
	public static int getProfit(Town town) {
		// TODO: Write/update your code here.
		int profit = 0;

		// THe loop counts the number of casual cells in the grid to increment the
		// profit
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					profit++;
				}
			}
		}

		return profit;
	}

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements
	 * of grid via an input file (option: 1) or wants to generate it randomly
	 * (option: 2).
	 * 
	 * Depending on the user choice, create the Town object using respective
	 * constructor and if user choice is to populate it randomly, then populate the
	 * grid here.
	 * 
	 * Finally: For 12 billing cycle calculate the profit and update town object
	 * (for each cycle). Print the final profit in terms of %. You should print the
	 * profit percentage with two digits after the decimal point: Example if profit
	 * is 35.5600004, your output should be:
	 *
	 * 35.56%
	 * 
	 * Note that this method does not throw any exception, so you need to handle all
	 * the exceptions in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO: Write your code here.
		Scanner scnr = new Scanner(System.in);
		Town town = null;

		// THis is where the user will put the input choice
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		int input = scnr.nextInt();

		if (input == 1) {
			System.out.println("Please enter file path: ");
			String filePath = scnr.next();
			try {
				town = new Town(filePath);
			} catch (FileNotFoundException e) {
				System.out.println("Error: File not found.");
				return;
			}
		} else if (input == 2) {
			System.out.println("Provide rows, cols and seed integer separated by space: ");
			int rows = scnr.nextInt();
			int cols = scnr.nextInt();
			int seed = scnr.nextInt();
			town = new Town(rows, cols);
			town.randomInit(seed);
		} else {
			System.out.println("Invalid input.");
			return;
		}

		double totalProfit = 0;
		final int billCycle = 12;

		// The loop updates town and calculates the profit for each cycle
		for (int cycle = 0; cycle < billCycle; cycle++) {
			System.out.println(town.toString());
			int profit = getProfit(town);
			System.out.println("profit: " + profit);
			town = updatePlain(town);
			totalProfit += profit;
		}

		double percentage = (totalProfit / (town.getLength() * town.getWidth() * 12)) * 100;
		System.out.printf("Percentage output: %.2f%%\n", percentage);
	}
}
