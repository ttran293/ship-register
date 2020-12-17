/**
 * abtract class ship
 * @author ttran293
 *
 */
public abstract class Ship 
{
	
private String name; //name of the ship
private String year; //year commissioned
protected ShipType type; //string represents enum

//getters and setters for each

/**
 * no arg contructor
 */
public Ship()
{
	name="";
	year="";
	type=null;
}

/**
 * Ship contructor with parameter name, type, and year
 * @param name - name of the ship
 * @param type - type of the ship
 * @param year - year built
 */
public Ship(String name, String type, String year)
{
	this.name=name;
	this.year=year;
	//set type depend on the String provide
	if(type.equalsIgnoreCase(ShipType.CARGO.toString()))
		this.type=ShipType.CARGO;
	if(type.equalsIgnoreCase(ShipType.CRUISE.toString()))
		this.type=ShipType.CRUISE;
	if(type.equalsIgnoreCase(ShipType.WARSHIP.toString()))
		this.type=ShipType.WARSHIP;
	if(type.equalsIgnoreCase(ShipType.CARRIER.toString()))
		this.type=ShipType.CARRIER;
	if(type.equalsIgnoreCase(ShipType.CRUISER.toString()))
		this.type=ShipType.CRUISER;
	if(type.equalsIgnoreCase(ShipType.DESTROYER.toString()))
		this.type=ShipType.DESTROYER;
	if(type.equalsIgnoreCase(ShipType.MINE_SWEEPER.toString()))
		this.type=ShipType.MINE_SWEEPER;
	if(type.equalsIgnoreCase(ShipType.SUBMARINE.toString()))
		this.type=ShipType.SUBMARINE;
	
	
}

/**
 * setter for name
 * @param name- name of the ship
 */
public void setName(String name)
{
	this.name=name;
}
/**
 * getter for name
 * @return name- name of the ship
 */
public String getName()
{
	return name;
}

/**
 * setter for year
 * @param year- year ship built
 */
public void setYear(String year)
{
	this.year=year;
}

/**
 * getter for year
 * @return year-year built
 */
public String getYear()
{
	return year;
}

/**
 * setter for type
 * @param type- type of the ship depending on the tostring
 */
public void setType(String type)
{
	if(type.equalsIgnoreCase(ShipType.CARGO.toString()))
		this.type=ShipType.CARGO;
	if(type.equalsIgnoreCase(ShipType.CRUISE.toString()))
		this.type=ShipType.CRUISE;
	if(type.equalsIgnoreCase(ShipType.WARSHIP.toString()))
		this.type=ShipType.WARSHIP;
	if(type.equalsIgnoreCase(ShipType.CARRIER.toString()))
		this.type=ShipType.CARRIER;
	if(type.equalsIgnoreCase(ShipType.CRUISER.toString()))
		this.type=ShipType.CRUISER;
	if(type.equalsIgnoreCase(ShipType.DESTROYER.toString()))
		this.type=ShipType.DESTROYER;
	if(type.equalsIgnoreCase(ShipType.MINE_SWEEPER.toString()))
		this.type=ShipType.MINE_SWEEPER;
	if(type.equalsIgnoreCase(ShipType.SUBMARINE.toString()))
		this.type=ShipType.SUBMARINE;
}

/**
 * getter for type
 * @return type- the String format of the type
 */
public String getType()
{
	return type.toString();
}

/**
 * write data method 
 * @return a string format required
 */
public String writeData(){
	return name+","+type.toString()+","+year+",";
}

/**
 * @return generate a string method
 */
public String toString()
{
	return name+" built in "+ year+ ", "+type+" class ";
	
}




















}
