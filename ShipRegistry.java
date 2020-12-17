import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.stage.FileChooser;
/**
 * 
 * @author ttran293
 *
 */
public class ShipRegistry implements ShipRegistryInterface {

	/**
	 * shipList is an ArrayList holding Ship references
	 */
	
	
	private ArrayList<Ship> shipList = new ArrayList<Ship>();

	
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
	public void addShip(String name, String type, String year, int tons, int pax, int guns, int torps, int ac)
	{
		// TODO Auto-generated method stub
		if (type.equalsIgnoreCase("Cargo"))
			shipList.add(new CargoShip(name,type, year, tons));
		
		else if (type.equalsIgnoreCase("Cruise"))
			shipList.add(new CruiseShip(name, type, year, pax));
		
		else if (type.equalsIgnoreCase("Warship"))
			shipList.add(new WarShip(name, type, year, guns, torps, ac));
		
		else if (type.equalsIgnoreCase("Carrier"))
			shipList.add(new WarShip(name, type, year, 0, 0, ac));
		
		else if (type.equalsIgnoreCase("Cruiser"))
			shipList.add(new WarShip(name, type, year, guns, 0, 0));
		
		else if (type.equalsIgnoreCase("Destroyer"))
		    shipList.add(new WarShip(name, type, year,guns, 0 ,0));
		
		else if (type.equalsIgnoreCase("Mine Sweeper"))
			  shipList.add(new WarShip(name, type, year,guns, 0 ,0));
		
		else if(type.equalsIgnoreCase("Submarine"))
			  shipList.add(new WarShip(name, type, year,0, torps ,0));
	}
	
	/**
	 * The getShipDescriptions method is used in the GUI to set the radio button labels for basic ship types
	 * @return the string array {ShipType.CARGO.toString(), ShipType.CRUISE.toString(), ShipType.WARSHIP.toString()};
	 */
	public String[] getShipDescriptions()
	{
		// TODO Auto-generated method stub
		String[] a= {ShipType.CARGO.toString(), ShipType.CRUISE.toString(), ShipType.WARSHIP.toString()};
		return a;
	}

	/**
	 * The getWarshipDescriptions method is used in the GUI to set the radio button labels for the five warship types
	 * @return the string array {ShipType.CARRIER.toString(), ShipType.CRUISER.toString(), ShipType.DESTROYER.toString(), ShipType.MINE_SWEEPER.toString(), ShipType.SUBMARINE.toString()};
	 */
	public String[] getWarshipDescriptions()
	{
		// TODO Auto-generated method stub
		String[] a= {ShipType.CARRIER.toString(), ShipType.CRUISER.toString(), ShipType.DESTROYER.toString(), ShipType.MINE_SWEEPER.toString(), ShipType.SUBMARINE.toString()};
		return a;
	}

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
	public void writeFile(File file) 
	{
		// TODO Auto-generated method stub
		if(file!=null){
			try{
				PrintWriter print = new PrintWriter(file);
				for(int i=0;i<shipList.size();i++)
				{
					print.println(shipList.get(i).writeData());
				}
				print.close();
			}
			catch (IOException event)
			{
				
			}
		}
	}
		
	

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
	public void readFile(File file)
	{
		// TODO Auto-generated method stub
		{
			if(file!=null)
			{
			String[] array;
			try{
				
				Scanner input = new Scanner(file);
				
				while(input.hasNext())
				{
					array =input.nextLine().split(",");
					if(array.length<4)
					{
						JOptionPane.showMessageDialog(null, "There is a error in input file.");
					}
					
					if(array[1].equalsIgnoreCase("Cargo"))
						addShip(array[0], array[1], array[2], Integer.parseInt(array[3].trim()), 0, 0, 0, 0);
					else if (array[1].equalsIgnoreCase("Cruise"))
						addShip(array[0], array[1], array[2], Integer.parseInt(array[3].trim()), 0, 0, 0, 0);
					else if (array[1].equalsIgnoreCase("Warship"))
						addShip(array[0], array[1], array[2], 0, 0, Integer.parseInt(array[3].trim()), Integer.parseInt(array[4].trim()), Integer.parseInt(array[5].trim()));
					else if (array[1].equalsIgnoreCase("Carrier"))
						addShip(array[0], array[1], array[2], 0, 0, 0, 0, Integer.parseInt(array[3].trim()));
					else if (array[1].equalsIgnoreCase("Cruiser"))
						addShip(array[0], array[1], array[2], 0, 0, Integer.parseInt(array[3].trim()), 0, 0);
					else if (array[1].equalsIgnoreCase("Destroyer"))
						addShip(array[0], array[1], array[2], 0, 0, Integer.parseInt(array[3].trim()), 0, 0);
					else if (array[1].equalsIgnoreCase("Mine Sweeper"))
						addShip(array[0], array[1], array[2], 0, 0, Integer.parseInt(array[3].trim()), 0, 0);
					else if (array[1].equalsIgnoreCase("Submarine"))
						addShip(array[0], array[1], array[2], 0, 0, 0, Integer.parseInt(array[3].trim()), 0);
					else
						addShip(array[0], array[1], array[2], 0,0,0,0,0);
				}
				input.close();
				}
			catch (IOException event)
			{
				event.printStackTrace();
			}
		}
			}
		
	}

	
	/**
	 * The getShips method returns the current shipList
	 * @return ArrayList<Ship> shipList
	 */
	@Override
	public ArrayList<Ship> getShips() 
	{
		// TODO Auto-generated method stub
		return shipList;
	}

	public String toString()
	{
		List<Ship> a = new ArrayList<Ship>();
		for (int i=0; i<shipList.size();i++)
		{
			a.add(shipList.get(i));
		}
		
		Collections.sort(a,(p1, p2) -> p1.getName().compareTo(p2.getName()) );
		String result="";
		for(int i=0;i<a.size();i++){
			result+=a.get(i).toString()+"\n";
		}
		return result;
		
	}
}