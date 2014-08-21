package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Value implements Serializable
{
	protected String name;
	protected Conditional conditionalPointer;
	
	
	
	
	public Value(String n, Conditional cond)
	{
		name = n;
		conditionalPointer = cond;
		
	}
	
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
