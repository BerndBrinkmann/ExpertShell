package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Value implements Serializable
{
	protected String name;
	protected Consequent conPointer;
	protected Antecedent antPointer;
	
	
	
	
	public Value(String n, Consequent con)
	{
		name = n;
		conPointer = con;
		
	}
	
	public Value(String n, Antecedent ant)
	{
		name = n;
		antPointer = ant;
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
