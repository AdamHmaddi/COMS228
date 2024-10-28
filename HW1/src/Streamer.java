package edu.iastate.cs2280.hw1;

/**
 * It represents a streamer cell in the town grid It inherits from the TownCell
 * class
 * 
 * @author Adam Hmaddi
 */
public class Streamer extends TownCell {

	/**
	 * It constructs a streamer cell at the specified row and col in the given town
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns the state of the cell
	 */
	@Override
	public State who() {
		return State.STREAMER;
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
		// Rule 2a: If there’s a reseller neighbor, the Streamer (S) cell changes to
		// Outage (O).
		else if (nCensus[RESELLER] > 0) {
			return new Outage(tNew, row, col);
		}
		// Rule 2b: If there’s an outage neighbor, the Streamer (S) becomes Empty (E).
		else if (nCensus[OUTAGE] > 0) {
			return new Empty(tNew, row, col);
		}
		// Rule 7: If no rules apply, the cell state stays the same.
		return new Streamer(tNew, row, col);
	}
}