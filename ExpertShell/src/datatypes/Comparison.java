package datatypes;

import java.io.Serializable;

public enum Comparison implements Serializable 
{
	EQ("=",0,true),
	NEQ("!=",1,true),
	GT(">",2,true),
	LT("<",3,true),
	LTEQ("<=",4,true),
	GTEQ(">=",5,true),
	IS("is",6,false),
	ISNT("is not",7,false);
	
	
	String name;
	int index;
	boolean numeric;
	
	private Comparison(String s, int i, boolean n)
	{
		index = i;
		name = s;
		numeric = n;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isNumeric() {
		return numeric;
	}
}
