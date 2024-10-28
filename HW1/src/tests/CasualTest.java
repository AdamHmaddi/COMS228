package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Adam Hmaddi
 */
class CasualTest {

	@Test
	void testCellIdentity() {
		Town tTown = new Town(4, 4);
		Outage outageCell = new Outage(tTown, 1, 2);
		assertEquals(State.OUTAGE, outageCell.who());
	}

	@Test
	void testNextCellState() {
		Town someTown = new Town(2, 2);
		someTown.grid[0][0] = new Casual(someTown, 0, 0);
		someTown.grid[0][1] = new Streamer(someTown, 0, 1);
		someTown.grid[1][0] = new Casual(someTown, 1, 0);
		someTown.grid[1][1] = new Casual(someTown, 1, 1);
		assertEquals(State.RESELLER, someTown.grid[0][0].next(someTown).who());
	}
}