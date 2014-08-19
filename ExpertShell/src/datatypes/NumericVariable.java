package datatypes;

import java.util.ArrayList;

public class NumericVariable extends Variable {

	protected String name;
	protected String description = "";
	protected ArrayList<Value> possibleValues;
	protected ArrayList<Integer> numOfValueInstances;
	protected ArrayList<String> numericOperators;
	protected Value currentValue;
	protected Rule changedBy;
	protected String queryPrompt = "";
	
	protected Boolean isNumeric = true;
	protected Double numVal = null;
	
	protected ArrayList<Double> certaintyFactors;  //by our convention certainty factors are stored as 0-1
	protected ArrayList<Double> prior;
		
	
	protected Boolean askUser = false;
	protected Boolean userDerived = false;
	protected Value defaultValue;
	
	public void setIsNumeric(Boolean isNumeric)
	{
		this.isNumeric = isNumeric;
	}

	public Double getNumVal()
	{
		return numVal;
	}

	public void setNumVal(Double numVal)
	{
		this.numVal = numVal;
	}

	
	public NumericVariable(String n)
	{
		name = n;
		possibleValues = new ArrayList<Value>();
		certaintyFactors = new ArrayList<Double>();
		beliefs = new ArrayList<Double>();
	}
	
	
	public Value getCurrentValue()
	{
		return currentValue;
	}
	
	public void setCurrentValue(Value val)
	{
		currentValue = val;
	}
	
	public void setCurrentValue(Double d)
	{
		numVal = d;	
		setIsNumeric(true);
	}
	
	public void userSetCurrentValue(Value val)
	{
		userDerived = true;
		currentValue = val;		
	}
	
	public void userSetCurrentValue(Double d)
	{
		userDerived = true;
		numVal = d;	
		setIsNumeric(true);
	}
	
	public void addPossibleValue(Value val)
	{
		if(!possibleValues.contains(val))
		{
			possibleValues.add(val);
		}
		//certainty factors should be initialised to zero
		certaintyFactors.add(new Double(0));
		
		//beliefs can be initilised to 0.5
		beliefs.add(new Double(0.5));

	}
	
	public void setCertaintyFactor(int i, Double certainty)
	{
		certaintyFactors.set(i, certainty);
	}
	
	public void setCertaintyFactor(Value val, Double certainty)
	{
		certaintyFactors.set(getValueIndex(val), certainty);
	}
	
	public void setBelief(Value val, Double belief)
	{
		beliefs.set(getValueIndex(val), belief);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setDerivedFrom(Rule r)
	{
		derivedFrom = r;
	}
	
	public Boolean isUserInput()
	{
		return askUser;
	}
	
	public void setUserInput(Boolean b)
	{
		askUser = b;
	}
	
	public Value getPossibleValue(int i)
	{
		return possibleValues.get(i); 
	}
	
	public Double getCertaintyFactor(int i)
	{
		return certaintyFactors.get(i);
	}
	
	public Double getCertaintyFactor(Value val)
	{
		return getCertaintyFactor(getValueIndex(val));
		
	}
	
	public Double getBelief(int i)
	{
		return beliefs.get(i);
	}
	
	public Double getBelief(Value val)
	{
		return beliefs.get(getValueIndex(val));
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
	
	public String getCertaintyValuesString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for(int i = 0; i < possibleValues.size(); i++)
		{
			sb.append("'"+possibleValues.get(i).getName()+"'("+ String.format("%.2f", getCertaintyFactor(i)*100) +"%), \n");
		}
		
		
		return sb.toString();
	}
	
	public String getBeliefValuesString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		
		//normalise the beliefs?
		for(int i = 0; i < possibleValues.size(); i++)
		{
			
			sb.append("'"+possibleValues.get(i).getName()+"'("+ String.format("%.2f", getBelief(i)*100)  +"%), \n");
		}
		
		
		return sb.toString();
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String s)
	{
		description = s;
	}
	
	public String getQueryPrompt()
	{
		return queryPrompt;
	}
	
	public void setQueryPrompt(String s)
	{
		queryPrompt = s;
	}
	
	public String toString()
	{
		return name;
	}
}