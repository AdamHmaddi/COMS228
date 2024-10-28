package edu.iastate.cs2280.hw1;

/**
 * It represents an outage cell in the town grid It inherits from the TownCell
 * class
 * 
 * @author Adam Hmaddi
 */
public class Outage extends TownCell {

	/**
	 * It constructs an outage cell at the specified row and col in the given town
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns the state of the cell
	 */
	@Override
	public State who() {
		return State.OUTAGE;
	}

	/**
	 * Determines the cell type in the next cycle, which is empty always
	 * 
	 * @param tNew the town of the next cycle
	 * @return returns an empty cell
	 */
	@Override
	public TownCell next(Town tNew) {
		// Rule 4: Outage (O) becomes Empty (E).
		return new Empty(tNew, row, col);
	}
}
