package STUART.ADT;

import java.io.Serializable;

public class Consequent implements Serializable
{
	protected Variable variable;
	protected Value value;
	protected double certaintyFactor = 1;
	
	protected Boolean isNumeric = false;
	protected Double numVal;
	protected UncertaintyMethod uncertaintyMethod = UncertaintyMethod.NONE;
	
	
	

	public UncertaintyMethod getUncertaintyMethod()
	{
		return uncertaintyMethod;
	}


	public void setUncertaintyMethod(UncertaintyMethod uncertaintyMethod)
	{
		this.uncertaintyMethod = uncertaintyMethod;
	}


	public Consequent()
	{
		variable = new Variable("default");
		value = new Value("default");
	}


	public double getCertaintyFactor()
	{
		return certaintyFactor;
	}



	public void setCertaintyFactor(double certaintyFactor)
	{
		this.certaintyFactor = certaintyFactor;
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
		if(isNumeric)
			setValue(0.0);
		else
			setValue(new Value(""));
	}



	public Consequent(Variable var, Value val)
	{
		variable = var;
		value = val;
	}
	
	public Consequent(Variable var, Double d)
	{
		variable = var;
		numVal = d;
		setIsNumeric(true);
		
	}
	
	public void setCertaintyFactor(Double cert)
	{
		certaintyFactor = cert;
	}
	
	public void execute()
	{
		if(!getIsNumeric())
			variable.setCurrentValue(value);
		else
			variable.setCurrentValue(numVal);
	}
	
	public void executeCertainty(Double prod)
	{
		double cf = variable.getCertaintyFactor(value);
		variable.setCertaintyFactor(value, cf + certaintyFactor*prod*(1-cf));
	}
	
	public void executeBayesian(Double ls_ln)
	{	
		double belief = variable.getBelief(value);
		double odds = belief/(1-belief);
		
		odds = odds*ls_ln;
		
		variable.setBelief(value,odds/(1+odds) );		
	}
	
	public Variable getVariable()
	{
		return variable;
	}
	
	public void setVariable(Variable v)
	{
		// will null this object's value field on setting of variable - it will no longer be valid
		
		variable = v;
		
		if(variable.getIsNumeric())
			setValue(0.0);
		else
			setValue(new Value(""));
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
	
	public Antecedent convertToAntecedent()
	{
		if(isNumeric)
		{
			return new Antecedent(this.variable, Comparison.EQ, this.numVal);
		}
		else
		{
			return new Antecedent(this.variable, this.value);
		}
	}

	public String toString()
	{
		if(!getIsNumeric())
		{
			if(uncertaintyMethod == UncertaintyMethod.CERTAINTY_FACTOR)
			{
				return variable.getName() + " is " + value.toString() + " {cf = "+certaintyFactor+"}";
			}
			else if(uncertaintyMethod == UncertaintyMethod.BAYESIAN && !value.getName().equals("default") && !value.getName().equals(""))
			{
				return variable.getName() + " is " + value.toString() + " {prior = "+variable.getBelief(value)+"}";
			}
			else
				return variable.getName() + " is " + value.toString();
		}
		else
		{
			return variable.getName() + " = " + numVal;
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
		Consequent other = (Consequent) obj;
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