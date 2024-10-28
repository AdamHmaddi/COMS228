package edu.iastate.cs2280.hw1;

/**
 * 
 * @author Adam Hmaddi
 * 
 *         This is an abstract class that represents a cell in the town grid All
 *         of the subclasses are going to implement the methods here to identify
 *         cell's state and behavior
 */
public abstract class TownCell {

	/*
	 * Town the cell belongs to
	 */
	protected Town plain;
	/*
	 * Row index of the cell
	 */
	protected int row;
	/*
	 * Column index of the cell
	 */
	protected int col;

	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;

	public static final int NUM_CELL_TYPE = 5;

	// Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	/**
	 * This method constructs a TownCell object
	 * 
	 * @param p the town the cell belongs to
	 * @param r the row of the cell in the town grid
	 * @param c the col of the cell in the town grid
	 */
	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}

	/**
	 * Checks all neigborhood cell types in the neighborhood. Refer to homework pdf
	 * for neighbor definitions (all adjacent neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 * 
	 * @param nCensus counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0;
		nCensus[EMPTY] = 0;
		nCensus[CASUAL] = 0;
		nCensus[OUTAGE] = 0;
		nCensus[STREAMER] = 0;

		// This is a loop through the cell neighbors and update census counts
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++) {
				if ((i != row || j != col) && i > -1 && i < plain.getLength() && j > -1 && j < plain.getWidth()) {
					State neighborState = plain.grid[i][j].who();
					if (neighborState == State.CASUAL) {
						nCensus[CASUAL]++;
					} else if (neighborState == State.STREAMER) {
						nCensus[STREAMER]++;
					} else if (neighborState == State.RESELLER) {
						nCensus[RESELLER]++;
					} else if (neighborState == State.OUTAGE) {
						nCensus[OUTAGE]++;
					} else if (neighborState == State.EMPTY) {
						nCensus[EMPTY]++;
					}
				}
			}
		}

	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State state of the cell
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
