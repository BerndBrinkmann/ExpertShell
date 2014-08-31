package datatypes;
import java.io.Serializable;
import java.util.ArrayList;

import datatypes.Antecedent;
import datatypes.Consequent;
import datatypes.Rule;
import datatypes.KBSettings;
import datatypes.Value;
import datatypes.Variable;

public class Variable implements Serializable
{
	protected String name="";
	protected String description = "";
	protected ArrayList<Value> possibleValues = new ArrayList<Value>();
	protected ArrayList<Value> allValues = new ArrayList<Value>();
	protected ArrayList<Integer> numOfValueInstances;
	protected ArrayList<String> numericOperators;
	public Value currentValue = null;
	protected Rule derivedFrom;
	protected String queryPrompt = "";
	
	protected Boolean isNumeric = false;
	protected Double numVal = null;
	
	protected ArrayList<Double> certaintyFactors ;  //by our convention certainty factors are stored as 0-1
	protected ArrayList<Double> beliefs = new ArrayList<Double>();
		
	
	protected Boolean askUser = false;
	protected Boolean userDerived = false;
	protected Value defaultValue;
	protected Comparison comparison;
	
	public Variable()
	{
		
	}
	
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

	
	public Variable(String n)
	{
		name = n;
		possibleValues = new ArrayList<Value>();
		certaintyFactors = new ArrayList<Double>();
		beliefs = new ArrayList<Double>();
		allValues  = new ArrayList<Value>();
	}
	
	
	public Value getCurrentValue()
	{
		return currentValue;
	}
	
	public String getValue()
	{
		if(this instanceof NumericVariable)
		{
			return numVal.toString();
		}
		else
		{
			return getCurrentValue().toString();
		}
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
		allValues.add(val);
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
	
	public Boolean hasValue()
	{
		if(!(this instanceof NumericVariable))
			return currentValue != null;
		else
			return numVal != null;
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
	
	public void removePossibleValue(Value v)
	{
		possibleValues.remove(v);
		allValues.remove(v);
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
			if(getCertaintyFactor(i) !=0)
			{
				sb.append("'"+possibleValues.get(i).getName()+"' with "+ String.format("%.2f", getCertaintyFactor(i)*100) +"% Certainty \n");
			}
		}
		sb.append("\n[Note: Results with zero certainty(0%) are not shown]");
		
		
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
	
	//converts the variable from numeric to linguistic and vice versa	
	public Variable convert()
	{
		if(this instanceof NumericVariable)
		{
			for(int i=0;i<allValues.size();i++)
			{
				
				switch(ComparisonGetter(i))
				{
				case NEQ:
						ComparisonSetter(i,comparison.ISNT);
				default:
						ComparisonSetter(i,comparison.EQ);
				}
			}
			return new LinguisticVariable(this.name, this.description, this.beliefs,
					this.certaintyFactors,this.queryPrompt,this.numOfValueInstances,
					this.askUser,this.userDerived,this.possibleValues);	
		}
		else
		{		
			for(int i=0;i<allValues.size();i++)
			{
				switch(ComparisonGetter(i))
				{
				case IS:
						ComparisonSetter(i,comparison.EQ);
				default:
						ComparisonSetter(i,comparison.NEQ);				
				}	
			}	
			return	new NumericVariable(this.name, this.description, this.beliefs,
					this.certaintyFactors,this.queryPrompt,this.numOfValueInstances,
					this.askUser,this.userDerived,this.possibleValues);
		}	
	
	}
	
	//used to shorten the calls in convert to get and set the comparison value
	public void ComparisonSetter(int i, Comparison compare)
	{
		allValues.get(i).antPointer.setComparison(compare);
	}
	
	public Comparison ComparisonGetter(int i)
	{
		return allValues.get(i).antPointer.getComparison();	
	}
	
}
