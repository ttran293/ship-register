/**
 * warship class implemented ship
 * @author ttran293
 *
 */
public class WarShip extends Ship {

private int guns;
private int tor;
private int aircraft;
	
/**
 * no-arg contructor
 */
	public WarShip()
	{
		
	}
	
	/**
	 * warship contructor with parameters name, type, year, guns, tor, aircraft
	 * @param name - name of the ship
	 * @param type - type of the ship
	 * @param year - year of the ship
	 * @param guns - guns carrying
	 * @param tor - torpedoes
	 * @param aircraft - aircraft carrying
	 */
	public WarShip(String name, String type, String year, int guns, int tor,int aircraft)
	{
		super(name, type, year);
		this.guns=guns;
		this.tor=tor;
		this.aircraft=aircraft;
	}
	/**
	 * getter for guns 
	 * @return guns - number of guns carrying
	 */
	public int getNumGuns()
	{
		return guns;
	}
	/**
	 * getter for torpedos
	 * @return tor - number of torpedoes
	 */
	public int getNumTorpedos()
	{
		return tor;
	}
	/**
	 * getter for aircraft
	 * @return aircraft - number of aircraft
	 */
	public int getAircraft()
	{
		return aircraft;
	}
	
	/**
	 * setter for guns
	 * @param guns -number of guns
	 */
	public void setGuns(int guns)
	{
		this.guns=guns;
	}
	/**
	 * setter for torpedos
	 * @param tor -number of torpedos
	 */
	public void setTorpedos(int tor)
	{
		this.tor=tor;
	}
	/**
	 * setter for aircraft
	 * @param airCraft - number of aircraft
	 */
	public void setAirCraft(int airCraft)
	{
		this.aircraft=airCraft;
	}
	/**
	 * @return write a string format
	 */
	public String writeData()
	{
		return super.writeData()+guns+","+tor+","+aircraft;
	}
	
	/**
	 * @return generate a string format
	 */
	public String toString()
	{
		
			return super.toString()+"with "+ guns +" Guns, "
										+tor      +"Torpedoes, and"
										+aircraft +" aircraft";
		
	}
	
	
	
	
	
}
