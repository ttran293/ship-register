import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ShipRegistryTest {

	private ShipRegistry ships;
	
	@Before
	public void setUp() throws Exception {
		ships = new ShipRegistry();
		ships.addShip("Westralia (O 195)", "Cargo", "1944", 25000, 0, 0, 0, 0);
		ships.addShip( "AIDAaura",  "Cruise", "2003", 0, 1300, 0, 0, 0);
		ships.addShip( "Montgomery",  "Warship", "1813", 0, 0, 11, 0, 0);
		ships.addShip( "Kitty Hawk (CV-63)", "Carrier",  "1961", 0, 0, 0, 0, 85);
		ships.addShip( "Arleigh Burke (DDG-51)", "Destroyer", "1989", 0, 0, 90, 0, 0);
	}

	@After
	public void tearDown() throws Exception {
		ships=null;
	}

	@Test
	public void testAddShip() {
		ships.addShip( "Bunker Hill (CG-52)",  "Cruiser",  "1986", 0, 0, 130, 0, 0);
		ships.addShip( "Inchon (LPH-12)",  "Mine Sweeper", "1970", 0, 0, 10, 0, 0);
		ships.addShip( "John Adams (SSBN-620)",  "Submarine", "1964", 0, 0, 0, 20, 0);
		ArrayList<Ship> shipList = ships.getShips();
		assertEquals("Bunker Hill",shipList.get(5).toString().substring(0,11));
		assertEquals(130,((WarShip) shipList.get(5)).getNumGuns());
		assertEquals("Inchon",shipList.get(6).toString().substring(0,6));
		assertEquals(20,((WarShip) shipList.get(7)).getNumTorpedos());
	}

	@Test
	public void testGetShipDescriptions() {
		String[] shipDescr = ships.getShipDescriptions();
		assertEquals("Cargo",shipDescr[0]);
		assertEquals("Cruise",shipDescr[1]);
		assertEquals("Warship",shipDescr[2]);
	}

	@Test
	public void testGetWarshipDescriptions() {
		String[] shipDescr = ships.getWarshipDescriptions();
		assertEquals("Carrier",shipDescr[0]);
		assertEquals("Destroyer",shipDescr[2]);
		assertEquals("Submarine",shipDescr[4]);
	}

	@Test
	public void testReadFile() {
		try {
			//this statement just rewrites an empty file over any existing ones
			new PrintWriter("testShips.csv"); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ships.writeFile(new File("testShips.csv"));
		ships = new ShipRegistry();
		ships.readFile(new File("testShips.csv"));
		ArrayList<Ship> shipList = ships.getShips();
		Ship s = shipList.get(1);
		assertEquals("AIDAaura",s.writeData().substring(0,8));	
		s = shipList.get(3);
		assertEquals("Kitty Hawk",s.writeData().substring(0,10));		
	}

	@Test
	public void testWriteFile() {
		ArrayList<Ship> shipList = ships.getShips();
		Ship s = shipList.get(2);
		assertEquals("Montgomery",s.writeData().substring(0,10));	
	}

}
