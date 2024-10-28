package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Adam Hmaddi
 */
class ResellerTest {

	@Test
	void testCellIdentity() {
		Town tTown = new Town(4, 4);
		Reseller resellerCell = new Reseller(tTown, 1, 2);
		assertEquals(State.RESELLER, resellerCell.who());
	}

	@Test
	void testNextCellState() {
		Town someTown = new Town(2, 2);
		someTown.grid[0][0] = new Reseller(someTown, 0, 0);
		someTown.grid[0][1] = new Casual(someTown, 0, 1);
		someTown.grid[1][0] = new Streamer(someTown, 1, 0);
		someTown.grid[1][1] = new Outage(someTown, 1, 1);
		assertEquals(State.EMPTY, someTown.grid[0][0].next(someTown).who());
	}
}
