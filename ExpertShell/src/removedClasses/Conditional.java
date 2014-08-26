package removedClasses;

import datatypes.Antecedent;
import datatypes.Comparison;
import datatypes.Consequent;
import datatypes.LinguisticVariable;
import datatypes.NumericVariable;
import datatypes.Value;
import datatypes.Variable;
import datatypes.getSetKBSettings;

public class Conditional extends getSetKBSettings {

	protected Variable variable;
	protected Value value;
	protected Double numVal = null;
	protected Boolean isNumeric = false;
	protected Boolean antecedentFlag = true;
	protected Comparison comparison;

	public Conditional convert()
	{
		if(antecedentFlag)
		{
			if(this.variable instanceof NumericVariable)
			{
				return new Antecedent(this.variable, Comparison.EQ, this.numVal);
			}
			else
			{
				return new Antecedent(this.variable, this.value);
			}	
		}
		else
		{
			if(this.variable instanceof NumericVariable)
			{
				return new Consequent(this.variable, this.numVal);
			}
			else
			{
				return new Consequent(this.variable, this.value);
			}
		}
	}
	
	
	public Variable getVariable()
	{
		return variable;
	}

	public void setVariable(Variable v)
	{
		variable = v;
		if(variable instanceof NumericVariable)
		{
			if(antecedentFlag)
			{
				setValue(0.0);
				setComparison(Comparison.EQ);
			}
			else
			{
				setValue(0.0);	
			}
		}
		else
		{
			setValue(new Value("", this));
		}
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
		isNumeric = false;
	}
	
	public void setValue(Double x)
	{
		numVal = x;
		isNumeric = true;
	}
	
	
	public void setIsNumeric(Boolean isNumeric)
	{
		this.isNumeric = isNumeric;
		if(variable != null)
			variable.setIsNumeric(isNumeric);
		if(isNumeric)
			setValue(0.0);
		else
			setValue(new Value("",this));
	}

	public Comparison getComparison()
	{
		return comparison;
	}

	public void setComparison(Comparison comparison)
	{
		if(this.variable instanceof NumericVariable)
		{
			this.comparison = comparison;
		}
		else if(this.variable instanceof LinguisticVariable)
		{
			this.comparison = comparison;	
		}
		else
		{
			
		}
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