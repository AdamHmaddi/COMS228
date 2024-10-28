package edu.iastate.cs2280.hw1;

/**
 * It represents an empty cell in the town grid It inherits from the TownCell
 * class
 * 
 * @author Adam Hmaddi
 */
public class Empty extends TownCell {

	/**
	 * It constructs an empty cell at the specified row and col in the given town
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns the state of the cell
	 */
	@Override
	public State who() {
		return State.EMPTY;
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

		// Rule 6a: Non-reseller or outage cells with less than one in (Empty + Outage)
		// total neighbors convert to Reseller (R).
		if ((nCensus[EMPTY] + nCensus[OUTAGE]) <= 1) {
			return new Reseller(tNew, row, col);
		}
		// Rule 5: Empty (E) becomes Casual (C).
		return new Casual(tNew, row, col);
	}
}
