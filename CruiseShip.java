/**
 * cruiseship class implement from Ship
 * @author ttran293
 *
 */
public class CruiseShip extends Ship{

private int passen;
	
	/**
	 * no-arg contructor
	 */
	public CruiseShip()
	{
		//no-arg contructor
	}
	
	/**
	 * Cruiseship contructor with parameters name, type, year, and passengers
	 * @param name - name of the ship
	 * @param type - type of the ship
	 * @param year  - year built
	 * @param passenger - passenger carrying
	 */
	public CruiseShip(String name, String type, String year, int passenger)
	{
		super(name, type, year);
		this.passen=passenger;
	}
	
	/**
	 * gettler for passenger
	 * @return passen - the number of passengers
	 */
	public int getPassenger()
	{
		return passen;
	}
	
	/**
	 * setter for passgeners
	 * @param passenger - the number of passgeners
	 */
	public void setPassenger(int passenger)
	{
		this.passen=passenger;
	}
	
	/**
	 * @return write a string format
	 */
	public String writeData()
	{
		return super.writeData()+passen+" ";
	}
	
	/**
	 * @return generate a String format required
	 */
	public String toString()
	{
		return super.toString()+"carrying "+passen+" passengers";
	}
	
	
	
	
	
}
