package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * @author Adam Hmaddi
 */
class TownCellTest {

	@Test
	void testNeighborCensus() {
		Town Town = new Town(3, 3);
		Town.grid[0][0] = new Casual(Town, 0, 0);
		Town.grid[0][1] = new Streamer(Town, 0, 1);
		Town.grid[0][2] = new Reseller(Town, 0, 2);
		Town.grid[1][0] = new Outage(Town, 1, 0);
		Town.grid[1][1] = new Empty(Town, 1, 1);
		Town.grid[1][2] = new Casual(Town, 1, 2);
		Town.grid[2][0] = new Streamer(Town, 2, 0);
		Town.grid[2][1] = new Casual(Town, 2, 1);
		Town.grid[2][2] = new Outage(Town, 2, 2);

		int[] counts = new int[TownCell.NUM_CELL_TYPE];
		Town.grid[1][1].census(counts);

		assertEquals(3, counts[TownCell.CASUAL]);
		assertEquals(2, counts[TownCell.STREAMER]);
		assertEquals(1, counts[TownCell.RESELLER]);
		assertEquals(2, counts[TownCell.OUTAGE]);
		assertEquals(0, counts[TownCell.EMPTY]);
	}
}
