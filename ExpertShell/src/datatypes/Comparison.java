package datatypes;

public enum Comparison
{
	EQ("="),
	NEQ("!="),
	GT(">"),
	LT("<"),
	LTEQ("<="),
	GTEQ(">=");
	
	String name;
	
	private Comparison(String s)
	{
		name = s;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
