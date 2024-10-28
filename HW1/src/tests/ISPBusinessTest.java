package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Adam Hmaddi
 */
class ISPBusinessTest {

	@Test
	void testUpdateTownState() {
		Town town = new Town(4, 4);
		town.randomInit(10);

		Town expectedTown = new Town(4, 4);
		expectedTown.grid[0][0] = town.grid[0][0].next(expectedTown);
		expectedTown.grid[0][1] = town.grid[0][1].next(expectedTown);
		expectedTown.grid[0][2] = town.grid[0][2].next(expectedTown);
		expectedTown.grid[0][3] = town.grid[0][3].next(expectedTown);
		expectedTown.grid[1][0] = town.grid[1][0].next(expectedTown);
		expectedTown.grid[1][1] = town.grid[1][1].next(expectedTown);
		expectedTown.grid[1][2] = town.grid[1][2].next(expectedTown);
		expectedTown.grid[1][3] = town.grid[1][3].next(expectedTown);
		expectedTown.grid[2][0] = town.grid[2][0].next(expectedTown);
		expectedTown.grid[2][1] = town.grid[2][1].next(expectedTown);
		expectedTown.grid[2][2] = town.grid[2][2].next(expectedTown);
		expectedTown.grid[2][3] = town.grid[2][3].next(expectedTown);
		expectedTown.grid[3][0] = town.grid[3][0].next(expectedTown);
		expectedTown.grid[3][1] = town.grid[3][1].next(expectedTown);
		expectedTown.grid[3][2] = town.grid[3][2].next(expectedTown);
		expectedTown.grid[3][3] = town.grid[3][3].next(expectedTown);

		Town actualTown = ISPBusiness.updatePlain(town);
		assertEquals(expectedTown.toString(), actualTown.toString());
	}

	@Test
	void testGetProfit() {
		Town town = new Town(2, 2);
		town.grid[0][0] = new Casual(town, 0, 0);
		town.grid[0][1] = new Empty(town, 0, 1);
		town.grid[1][0] = new Casual(town, 1, 0);
		town.grid[1][1] = new Reseller(town, 1, 1);

		int profit = ISPBusiness.getProfit(town);
		assertEquals(2, profit);
	}
}
