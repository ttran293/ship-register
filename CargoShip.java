/**
 * CargoShip implemented from class Ship
 * @author ttran293
 *
 */
public class CargoShip extends Ship 
{
	private int ton;
	
	/**
	 * no-arg contructor
	 */
	public CargoShip()
	{
		//no-arg contructor
	}
	
	/**
	 * CargoShip contructor with parameter name, type, year, and ton
	 * @param name - name of the ship
	 * @param type - type of the ship
	 * @param year - year built
	 * @param ton - ton carrying
	 */
	public CargoShip(String name, String type, String year, int ton)
	{
		super(name, type, year);
		this.ton=ton;
	}
	
	/**
	 * Getter for ton
	 * @return ton - the amount
	 */
	public int getTon()
	{
		return ton;
	}
	
	/**
	 * setter for ton
	 * @param ton - the amount
	 */
	public void setTon(int ton)
	{
		this.ton=ton;
	}
	
	/**
	 * @return return a write data format
	 */
	public String writeData()
	{
		return super.writeData()+ton+" ";
	}
	
	/**
	 * @return generate a String format require
	 */
	public String toString()
	{
		return super.toString()+"with "+ton+" tons capacity";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
