package datatypes;

import java.io.Serializable;

public class Antecedent extends getSetKBSettings implements Serializable
{
	protected Variable variable;
	protected Value value;

	
	protected Comparison comparison;
	protected Boolean isNumeric = false;
	public Double numVal = null;
	protected KBSettings.UncertaintyManagement uncertaintyType =KBSettings.UncertaintyManagement.NONE;
	
	public Antecedent()
	{
		variable = new Variable("default");
		comparison = Comparison.IS;
		value = new Value("default",this);
		isNumeric = false;
	}
	
	public Antecedent(Variable var,Comparison comp ,Double val)
	{
		
		numVal = val;
		comparison = comp;
		variable = var;
		isNumeric = true;
		
	}
	
	public Antecedent(Variable var, Value val)
	{
		variable = var;
		comparison = Comparison.IS;
		value = val;
		isNumeric = false;
	}
	
	public Antecedent(Variable var, Double val)
	{
		variable = var;
		comparison = Comparison.EQ;
		numVal = val;
		isNumeric = true;
	}

	public Boolean getIsNumeric()
	{
		return isNumeric;
	}

	public void setIsNumeric(Boolean isNumeric)
	{
		this.isNumeric = isNumeric;
		if(variable != null)
			variable.setIsNumeric(isNumeric);
		
		if(isNumeric && numVal == null)
			setValue(0.0);
		else if (!isNumeric && value == null)
			setValue(new Value(""));
	}
	

	public Comparison getComparison()
	{
		return comparison;
	}

	public void setVariable(Variable v)
	{
		// will null this object's value field on setting of variable - it will no longer be valid
		
		variable = v;
		
		if(variable.isNumeric)
		{
			setValue(0.0);
			setComparison(Comparison.EQ);
		}
		else
			setValue(new Value("", this));
	}
	
	public Boolean evaluate()
	{
		if(!(variable.isNumeric))
		{
			if(variable.getCurrentValue().toString().equals(value.toString()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			switch(comparison)
			{
			case EQ:
				//System.out.println( variable.getNumVal()+" " +comparison + " "+ numVal);
				//System.out.println(Double.compare(variable.getNumVal(), numVal) == 0);
				return Double.compare(variable.getNumVal(), numVal) == 0;
			case NEQ:
				//System.out.println("(Double.compare(variable.getNumVal(), numVal) gives:" + (Double.compare(variable.getNumVal(), numVal)));
				//System.out.println("(Double.compare(variable.getNumVal(), numVal) != 0)" + (Double.compare(variable.getNumVal(), numVal) != 0));
				return (Double.compare(variable.getNumVal(), numVal) != 0);
			case GT:
				//System.out.println( variable.getNumVal()+" " +comparison + " "+ numVal);
				//System.out.println(variable.getNumVal() > numVal);
				return (Double.compare(variable.getNumVal(), numVal) > 0);
			case LT:
			//	System.out.println( variable.getNumVal()+" " +comparison + " "+ numVal);
			//	System.out.println(variable.getNumVal() < numVal);
				return (Double.compare(variable.getNumVal(), numVal) < 0);
			case LTEQ:
				//System.out.println( variable.getNumVal()+" " +comparison + " "+ numVal);
				//System.out.println(variable.getNumVal() <= numVal);
				return (Double.compare(variable.getNumVal(), numVal) == 0 || Double.compare(variable.getNumVal(), numVal) < 0);
			case GTEQ:
				//System.out.println( variable.getNumVal()+" " +comparison + " "+ numVal);
				//System.out.println(variable.getNumVal() >= numVal);
				return (Double.compare(variable.getNumVal(), numVal) == 0 || Double.compare(variable.getNumVal(), numVal) > 0);
			default:
				return false;
			}
		}
	}
	
	public Consequent convertToConsequent()
	{
		if(variable.isNumeric)
		{
			return new Consequent(this.variable, this.numVal);
		}
		else
		{
			return new Consequent(this.variable, this.value);
		}
	}
	
	public String toString()
	{
		if(variable.isNumeric)
		{
			return variable.getName() + " " + comparison.toString() + " " + numVal;
		}
		else
		{
				return variable.getName() + " is " + value.getName();
		}
	}
	
	public String getVariableAsString() {
		return variable.getName();
	}
	
	public String getValueAsString() {
		if(variable.isNumeric) {
			if (numVal == null)
				return new String("");
			else
				return numVal.toString();
		} else {
			if (value == null)
				return new String("");
			else
				return value.getName();
		}
	}
	
	public String getComparisonAsString() {
		return comparison.toString();
	}

	public Variable getVariable()
	{
		return variable;
	}

		
	public Value getValue()
	{
		return value;
	}
	
	public Double getNumVal()
	{
		return numVal;
	}
	
	public void setValue(Value v)
	{
		value = v;
		if(!v.getName().equals(""))
		variable.addPossibleValue(v);			
	}
	
	public void setValue(Double v)
	{
		numVal = v;
	}
	
	
	public void setComparison(Comparison comparison)
	{
		//see what type of comparison we have
		boolean comparisonIsNumeric = comparison.numeric;
		
		//assign it
		this.comparison = comparison;
		
		//if antecedent is different then set it
		if(isNumeric != comparisonIsNumeric)
			setIsNumeric(comparisonIsNumeric);
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Antecedent other = (Antecedent) obj;
		if (value == null)
		{
			if (other.value != null)
				return false;
		}
		else if (!value.equals(other.value))
			return false;
		if (variable == null)
		{
			if (other.variable != null)
				return false;
		}
		else if (!variable.equals(other.variable))
			return false;
		return true;
	}
	
}

