package edu.iastate.cs2280.hw1;

/**
 * It represents a reseller cell in the town grid It inherits from the TownCell
 * class
 * 
 * @author Adam Hmaddi
 */
public class Reseller extends TownCell {

	/**
	 * It constructs a reseller cell at the specified row and col in the given town
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns the state of the cell
	 */
	@Override
	public State who() {
		return State.RESELLER;
	}

	/**
	 * Determines the cell type in the next cycle based on who the neighbors are
	 * 
	 * @param tNew the town of the next cycle
	 * @return returns the next TownCell representing the new state
	 */
	@Override
	public TownCell next(Town tNew) {
		int[] nCensus = new int[5];
		census(nCensus);

		// Rule 3a: If there are less than 3 casual neighbors, the Reseller (R) becomes
		// Empty (E).
		// OR
		// Rule 3b: If there are more than 3 empty neighbors, the Reseller (R) becomes
		// Empty (E).
		if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) {
			return new Empty(tNew, row, col);
		}
		// Rule 6b: Any cell with more than 5 casual neighbors becomes Streamer (S).
		else if (nCensus[CASUAL] >= 5) {
			return new Streamer(tNew, row, col);
		}
		// Rule 7: If no rules apply, the cell state stays the same.
		else {
			return new Reseller(tNew, row, col);
		}
	}
}
