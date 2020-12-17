import java.io.File;
import java.util.ArrayList;

/**
 * ShipRegistry is a Data Manager that keeps a list of ships.  Note that this list is not precise in its naval jargon.
 * @author ralexander
 */
public interface ShipRegistryInterface {
	
	/**
	 * shipList is an ArrayList holding Ship references
	 */
	ArrayList<Ship> shipList = null;
	
	/**
	 * The getShips method returns the current shipList
	 * @return ArrayList<Ship> shipList
	 */
	public ArrayList<Ship> getShips();
	
	/**
	 * The addShip method adds ships to the ShipRegistry's ArrayList, distinguishing by type, 
	 * creating a ship instance of the correct type, and specifying the correct parameters according to its type. 
	 * This method does not add in any sorted order - the ships are held in the order they are added
	 * @param name A string representing the name of the ship
	 * @param type A string representing one of "Cargo", "Cruise", "Warship", "Carrier", "Cruiser","Destroyer","Mine Sweeper",or "Submarine"
	 * @param year A string representing the year launched
	 * @param tons A string representing the number of tons of cargo (net register tonnage (NRT)) the ship can carry
	 * @param pax A string representing the number of passengers a cruise ship can carry
	 * @param guns A string representing the number of guns a warship can carry.  "Guns" is loosely defined, not according to naval jargon
	 * @param torpedoes A string representing the number of torpedoes a warship can carry.
	 * @param aircraft A string representing the number of aircraft a warship can nominally carry.
	 */
	public void addShip(String name, String type, String year, int tons, int pax, int guns, int torpedoes, int aircraft);

	/**
	 * The readFile method reads from the input file, one line at a time, assuming each line represents data for one ship.
	 * It assumes that the second field is a string representing the ship type, 
	 * one of "Cargo", "Cruise", "Warship", "Carrier", "Cruiser","Destroyer","Mine Sweeper",or "Submarine".
	 * It further assumes specific formats for each type of ship, as follows:
	 *    Cargo: name,"Cargo",year,tons
	 *    Cruise: name,"Cruise",year,passengers
	 *    Warship: name,"Warship",year,guns,aircraft,torpedoes
	 *    Carrier: name,"Carrier",year,aircraft
	 *    Cruiser, Destroyer, and Mine Sweeper: name,type,year,guns
	 *    Submarine: name,"Submarine",year,torpedoes
	 * It then calls addShip to instantiate it and add it to the ShipRegistry's ArrayList
	 * @param file the file of type File to read from, assumed to be a csv file (comma-delimited) in the above order.
	 */
	public void readFile(File file);
	
	/**
	 * The writeFile method writes to a specified file, either creating a new file or appending to the end of an existing file.
	 * It iterates through ShipRegistry's ArrayList<Ship>, writes one line at a time representing data for each ship.
	 * It writes the second field as a string representing the ship type, 
	 * one of "Cargo", "Cruise", "Warship", "Carrier", "Cruiser","Destroyer","Mine Sweeper",or "Submarine".
	 * It further writes specific formats for each type of ship, as follows:
	 *    Cargo: name,"Cargo",year,tons
	 *    Cruise: name,"Cruise",year,passengers
	 *    Warship: name,"Warship",year,guns,aircraft,torpedoes
	 *    Carrier: name,"Carrier",year,aircraft
	 *    Cruiser, Destroyer, and Mine Sweeper: name,type,year,guns
	 *    Submarine: name,"Submarine",year,torpedoes
	 * @param file the file of type File to write to
	 */
	public void writeFile(File file);
	
	/**
	 * The getShipDescriptions method is used in the GUI to set the radio button labels for basic ship types
	 * @return the string array {ShipType.CARGO.toString(), ShipType.CRUISE.toString(), ShipType.WARSHIP.toString()};
	 */
	public String[] getShipDescriptions();
	
	/**
	 * The getWarshipDescriptions method is used in the GUI to set the radio button labels for the five warship types
	 * @return the string array {ShipType.CARRIER.toString(), ShipType.CRUISER.toString(), ShipType.DESTROYER.toString(), ShipType.MINE_SWEEPER.toString(), ShipType.SUBMARINE.toString()};
	 */
	public String[] getWarshipDescriptions();
}
