package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Adam Hmaddi
 */
class TownTest {

	@Test
	void testConstructorSize() {
		Town town = new Town(4, 3);
		assertEquals(4, town.getLength());
		assertEquals(3, town.getWidth());
	}

	@Test
	void testRandomInitialization() {
		Town town = new Town(4, 4);
		town.randomInit(10); // Setting seed for consistency
		assertNotNull(town.grid[0][0]); // Check if grid cells are initialized
	}

	@Test
	void testInitializationFromFile2x2() throws FileNotFoundException {
		Town town = new Town("ISP2x2.txt");
		assertEquals(2, town.getLength());
		assertEquals(2, town.getWidth());

		// Assertions for the 2x2 grid based on file contents
		assertEquals(State.CASUAL, town.grid[0][0].who()); // Checks if it's Casual 'C'
		assertEquals(State.STREAMER, town.grid[0][1].who()); // Checks if it's Streamer 'S'
		assertEquals(State.RESELLER, town.grid[1][0].who()); // Checks if it's Reseller 'R'
		assertEquals(State.OUTAGE, town.grid[1][1].who()); // Checks if it's Outage 'O'
	}

	@Test
	void testInitializationFromFile3x3() throws FileNotFoundException {
		Town town = new Town("ISP3x3.txt");

		assertEquals(3, town.getLength());
		assertEquals(3, town.getWidth());

		// Check the state of each cell in the grid based on the file

		// Assertions for the 3x3 grid based on file contents
		assertEquals(State.RESELLER, town.grid[0][0].who()); // Checks if it's Reseller 'R'
		assertEquals(State.OUTAGE, town.grid[0][1].who()); // Checks if it's Outage 'O'
		assertEquals(State.CASUAL, town.grid[0][2].who()); // Checks if it's Casual 'C'

		assertEquals(State.STREAMER, town.grid[1][0].who()); // Checks if it's Streamer 'S'
		assertEquals(State.CASUAL, town.grid[1][1].who()); // Checks if it's Casual 'C'
		assertEquals(State.RESELLER, town.grid[1][2].who()); // Checks if it's Reseller 'R'

		assertEquals(State.OUTAGE, town.grid[2][0].who()); // Checks if it's Outage 'O'
		assertEquals(State.EMPTY, town.grid[2][1].who()); // Checks if it's Empty 'E'
		assertEquals(State.EMPTY, town.grid[2][2].who()); // Checks if it's Empty 'E'
	}

	@Test
	void testInitializationFromFile4x4() throws FileNotFoundException {
		Town town = new Town("ISP4x4.txt");
		assertEquals(4, town.getLength());
		assertEquals(4, town.getWidth());

		// Assertions for the 4x4 grid based on file contents
		assertEquals(State.OUTAGE, town.grid[0][0].who()); // Checks if it's Outage 'O'
		assertEquals(State.RESELLER, town.grid[0][1].who()); // Checks if it's Reseller 'R'
		assertEquals(State.OUTAGE, town.grid[0][2].who()); // Checks if it's Outage 'O'
		assertEquals(State.RESELLER, town.grid[0][3].who()); // Checks if it's Reseller 'R'

		assertEquals(State.EMPTY, town.grid[1][0].who()); // Checks if it's Empty 'E'
		assertEquals(State.EMPTY, town.grid[1][1].who()); // Checks if it's Empty 'E'
		assertEquals(State.CASUAL, town.grid[1][2].who()); // Checks if it's Casual 'C'
		assertEquals(State.OUTAGE, town.grid[1][3].who()); // Checks if it's Outage 'O'

		assertEquals(State.EMPTY, town.grid[2][0].who()); // Checks if it's Empty 'E'
		assertEquals(State.STREAMER, town.grid[2][1].who()); // Checks if it's Streamer 'S'
		assertEquals(State.OUTAGE, town.grid[2][2].who()); // Checks if it's Outage 'O'
		assertEquals(State.STREAMER, town.grid[2][3].who()); // Checks if it's Streamer 'S'

		assertEquals(State.EMPTY, town.grid[3][0].who()); // Checks if it's Empty 'E'
		assertEquals(State.OUTAGE, town.grid[3][1].who()); // Checks if it's Outage 'O'
		assertEquals(State.RESELLER, town.grid[3][2].who()); // Checks if it's Reseller 'R'
		assertEquals(State.RESELLER, town.grid[3][3].who()); // Checks if it's Reseller 'R'
	}

	/**
	 * Returns a string representation of the town's grid
	 *
	 * @return a string displaying the current state of the town's grid
	 */
	@Test
	void testStringRepresentation2x2() {
		Town town = new Town(2, 2);
		town.grid[0][0] = new Casual(town, 0, 0);
		town.grid[0][1] = new Streamer(town, 0, 1);
		town.grid[1][0] = new Reseller(town, 1, 0);
		town.grid[1][1] = new Outage(town, 1, 1);

		String expectedOutput = "C S \nR O \n";
		assertEquals(expectedOutput, town.toString());
	}

	/**
	 * Returns a string representation of the town's grid
	 *
	 * @return a string displaying the current state of the town's grid
	 */
	@Test
	void testStringRepresentation3x3() {
		Town town = new Town(3, 3);
		town.grid[0][0] = new Reseller(town, 0, 0);
		town.grid[0][1] = new Outage(town, 0, 1);
		town.grid[0][2] = new Casual(town, 0, 2);
		town.grid[1][0] = new Streamer(town, 1, 0);
		town.grid[1][1] = new Casual(town, 1, 1);
		town.grid[1][2] = new Reseller(town, 1, 2);
		town.grid[2][0] = new Outage(town, 2, 0);
		town.grid[2][1] = new Empty(town, 2, 1);
		town.grid[2][2] = new Empty(town, 2, 2);

		String expectedOutput = "R O C \nS C R \nO E E \n";
		assertEquals(expectedOutput, town.toString());
	}

	/**
	 * Returns a string representation of the town's grid
	 *
	 * @return a string displaying the current state of the town's grid
	 */
	@Test
	void testStringRepresentation4x4() {
		Town town = new Town(4, 4);
		town.grid[0][0] = new Outage(town, 0, 0);
		town.grid[0][1] = new Reseller(town, 0, 1);
		town.grid[0][2] = new Outage(town, 0, 2);
		town.grid[0][3] = new Reseller(town, 0, 3);
		town.grid[1][0] = new Empty(town, 1, 0);
		town.grid[1][1] = new Empty(town, 1, 1);
		town.grid[1][2] = new Casual(town, 1, 2);
		town.grid[1][3] = new Outage(town, 1, 3);
		town.grid[2][0] = new Empty(town, 2, 0);
		town.grid[2][1] = new Streamer(town, 2, 1);
		town.grid[2][2] = new Outage(town, 2, 2);
		town.grid[2][3] = new Streamer(town, 2, 3);
		town.grid[3][0] = new Empty(town, 3, 0);
		town.grid[3][1] = new Outage(town, 3, 1);
		town.grid[3][2] = new Reseller(town, 3, 2);
		town.grid[3][3] = new Reseller(town, 3, 3);

		String expectedOutput = "O R O R \nE E C O \nE S O S \nE O R R \n";
		assertEquals(expectedOutput, town.toString());
	}

}
