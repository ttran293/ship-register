
public enum ShipType 
{

	CARGO ("Cargo"), 
	CRUISE ("Cruise"),
	WARSHIP ("Warship"),
	CARRIER ("Carrier"),
	CRUISER ("Cruiser"),
	DESTROYER ("Destroyer"),
	MINE_SWEEPER ("Mind Sweeper"),
	SUBMARINE("Submarine");
	
	private final String type;   
	public String toString()
	{
		return type;
	}

    ShipType(String s)
    {
       this.type = s;
    }

    public boolean equalsName(String otherName)
    {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return type.equals(otherName);
    }

}