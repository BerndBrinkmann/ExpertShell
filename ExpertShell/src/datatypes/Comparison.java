package datatypes;

import java.io.Serializable;

public enum Comparison implements Serializable 
{
	EQ("=",0),
	NEQ("!=",1),
	GT(">",2),
	LT("<",3),
	LTEQ("<=",4),
	GTEQ(">=",5),
	IS("is",6),
	ISNT("is not",7);
	
	
	String name;
	int index;
	
	private Comparison(String s, int i)
	{
		index = i;
		name = s;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public int getIndex() {
		return index;
	}
}
