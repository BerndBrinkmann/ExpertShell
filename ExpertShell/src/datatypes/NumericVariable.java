package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class NumericVariable extends Variable implements Serializable  {

	protected String name;
	protected String description = "";
	protected ArrayList<Value> possibleValues;
	protected ArrayList<Integer> numOfValueInstances;
	protected ArrayList<String> numericOperators;
	protected Double currentValue = null;
	protected Rule changedBy;
	protected String queryPrompt = "";
	
	protected Boolean isNumeric = true;
	protected Double numVal = null;
	
	protected ArrayList<Double> certaintyFactors;  //by our convention certainty factors are stored as 0-1
	protected ArrayList<Double> prior;
		
	
	protected Boolean askUser = false;
	protected Boolean userDerived = false;
	protected Value defaultValue;
	

	public NumericVariable(String n)
	{
		name = n;
		possibleValues = new ArrayList<Value>();
		certaintyFactors = new ArrayList<Double>();
		beliefs = new ArrayList<Double>();
	}
	
	public NumericVariable(String n, String descr, ArrayList<Double> pr,
			ArrayList<Double> cf, String query,ArrayList<Integer> vInstances,
			Boolean askUsr,Boolean userDer, ArrayList<Value> posValues)
	{
		name = n;
		description = descr;
		queryPrompt = query;
		askUser = askUsr;
		userDerived = userDer;
		
		certaintyFactors = cf;
		prior = pr;
		
		possibleValues = posValues;
		numOfValueInstances = vInstances;
		
	}
	
	
	public void setIsNumeric(Boolean isNumeric)
	{
		this.isNumeric = isNumeric;
	}


	public Double getCurrentVal()
	{
		return currentValue;
	}

	public void setCurrentValue(Double value)
	{
		currentValue = value;	
	}
	
	public void userSetCurrentValue(Double value)
	{
		userDerived = true;
		currentValue = value;	
	}

	
	public void addPossibleValue(Value val)
	{
		if(!possibleValues.contains(val))
		{
			possibleValues.add(val);
		}
		//certainty factors should be initialised to zero
		certaintyFactors.add(new Double(0));
		
		//beliefs can be initialised to 0.5
		beliefs.add(new Double(0.5));
	}
		
	public Value getPossibleValue(int i)
	{
		return possibleValues.get(i); 
	}
	
	//checks user input against list of possible values. (checks for val in list of p.values)
	public int getValueIndex(Value val)
	{
		int index = -1;
		
		for(int i = 0; i < getNumberOfPossibleValues(); i++)
		{
			if(val.equals(getArrayOfPossibleValues()[i]))
			{
				index = i;
			}
		}
		
		if(index == -1)
		{
			System.err.println("Variable.getValueIndex(): Value was not found in variable!");
		}
		
		return index;
	}
	
	public Value[] getArrayOfPossibleValues()	//should be able to just use get
	{
		return possibleValues.toArray(new Value[possibleValues.size()]);
	}
	//Implement add possible value
	public void removePossibleValue(Value v)
	{
		possibleValues.remove(v);
	}
	
	public int getNumberOfPossibleValues()
	{
		return possibleValues.size();
	}
	
	public String getValuesString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < possibleValues.size(); i++)
		{
			sb.append("'"+possibleValues.get(i).getName()+"'["+i+"],");
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	public void clearVariable()
	{
		for(int i=0; i< certaintyFactors.size() ; i++)
		{
			certaintyFactors.set(i,0.0);
			beliefs.set(i, 0.5);
		}
		
		//beliefs = new ArrayList<Double>();
		numVal = (Double) null;
		currentValue = (Double) null;
	}

	public String getName()
	{
		return name;
	}
	
}
