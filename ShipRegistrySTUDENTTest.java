import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Add your test methods here.  Follow the examples in ShipRegistryTest.java
 * @author
 *
 */
public class ShipRegistrySTUDENTTest {

	private ShipRegistry shipsSTUDENT;
	
	@Before
	public void setUpSTUDENT() throws Exception {
		shipsSTUDENT = new ShipRegistry();
		shipsSTUDENT.addShip("Eestralia (O 195)", "Cargo", "1944", 25000, 0, 0, 0, 0);
		shipsSTUDENT.addShip( "BIDAaura",  "Cruise", "2003", 0, 1300, 0, 0, 0);
		shipsSTUDENT.addShip( "Wontgomery",  "Warship", "1813", 0, 0, 11, 0, 0);
		shipsSTUDENT.addShip( "Ritty Hawk (CV-63)", "Carrier",  "1961", 0, 0, 0, 0, 85);
		shipsSTUDENT.addShip( "Trleigh Burke (DDG-51)", "Destroyer", "1989", 0, 0, 90, 0, 0);
	}

	@After
	public void tearDown() throws Exception {
		shipsSTUDENT=null;
	}

	@Test
	public void testAddShipSTUDENT() {
		//fail("Test not implemented yet.");
		shipsSTUDENT.addShip( "Munker Hill (CG-52)",  "Cruiser",  "1986", 0, 0, 260, 0, 0);
		shipsSTUDENT.addShip( "Onchon (LPH-12)",  "Mine Sweeper", "1970", 0, 0, 10, 0, 0);
		shipsSTUDENT.addShip( "Bond Adams (SSBN-620)",  "Submarine", "1964", 0, 0, 0, 22, 0);
		ArrayList<Ship> shipList = shipsSTUDENT.getShips();
		assertEquals("Munker Hill",shipList.get(5).toString().substring(0,11));
		assertEquals(260,((WarShip) shipList.get(5)).getNumGuns());
		assertEquals("Onchon",shipList.get(6).toString().substring(0,6));
		assertEquals("Bond Adams",shipList.get(7).toString().substring(0,10));
		assertEquals(22,((WarShip) shipList.get(7)).getNumTorpedos());
	}

	@Test
	public void testReadFileSTUDENT() {
		//fail("Test not implemented yet.");
		shipsSTUDENT.writeFile(new File("testShipsSTUDENT.csv"));
		shipsSTUDENT = new ShipRegistry();
		shipsSTUDENT.readFile(new File("testShipsSTUDENT.csv"));
		ArrayList<Ship> shipList = shipsSTUDENT.getShips();
		Ship s = shipList.get(1);
		assertEquals("BIDAaura",s.writeData().substring(0,8));	
		s = shipList.get(3);
		assertEquals("Ritty Hawk",s.writeData().substring(0,10));
		s = shipList.get(2);
		assertEquals("Wontgomery",s.writeData().substring(0,10));
	}

	@Test
	public void testWriteFileSTUDENT() {
		//fail("Test not implemented yet.");
		ArrayList<Ship> shipList = shipsSTUDENT.getShips();
		Ship a = shipList.get(2);
		assertEquals("Wontgomery",a.writeData().substring(0,10));
		a = shipList.get(4);
		assertEquals("Trleigh Burke",a.writeData().substring(0,13));
	}

}
