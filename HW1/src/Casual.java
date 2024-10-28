package edu.iastate.cs2280.hw1;

/**
 * It represents a casual cell in the town grid It inherits from the TownCell
 * class
 * 
 * @author Adam Hmaddi
 */
public class Casual extends TownCell {

	/**
	 * It constructs a casual cell at the specified row and col in the given town
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns the state of the cell
	 */
	@Override
	public State who() {
		return State.CASUAL;
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
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			return new Reseller(tNew, row, col);
		}
		// Rule 1a: If there’s a reseller neighbor, the Casual (C) cell changes to
		// Outage (O).
		else if (nCensus[State.RESELLER.ordinal()] > 0) {
			return new Outage(tNew, row, col);
		}
		// Rule 1b: If there’s a streamer neighbor, the Casual (C) becomes a Streamer
		// (S).
		// OR
		// Rule 6b: Any cell with more than 5 casual neighbors becomes Streamer (S).
		else if (nCensus[State.STREAMER.ordinal()] > 0 || nCensus[State.CASUAL.ordinal()] >= 5) {
			return new Streamer(tNew, row, col);
		}
		// Rule 7: If no rules apply, the cell state stays the same.
		else {
			return new Casual(tNew, row, col);
		}
	}

}
