package STUART.ADT;

import java.io.Serializable;

public class Value implements Serializable
{
	protected String name;
	
	public Value(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}
}
