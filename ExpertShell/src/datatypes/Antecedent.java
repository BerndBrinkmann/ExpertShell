package datatypes;

import java.io.Serializable;

public class Antecedent implements Serializable
{
	//test comment by arie
	//
	
	protected Variable variable;
	protected Value value;
	protected double likelihoodOfNecessity = 1;
	protected double likelihoodOfSufficiency = 1;
	
	protected Comparison comparison;
	protected Boolean isNumeric = false;
	protected Double numVal = null;
	protected KBSettings.UncertaintyManagement uncertaintyType =KBSettings.UncertaintyManagement.NONE;
	
	public Antecedent()
	{
		variable = new Variable("default");
		value = new Value("default");
	}
	
	public Antecedent(Variable var,Comparison comp ,Double val)
	{
		setIsNumeric(true);
		numVal = val;
		comparison = comp;
		variable = var;
		
	}
	
	public Antecedent(Variable var, Value val)
	{
		variable = var;
		value = val;
	}
	
	public Antecedent(Variable var, Double val)
	{
		variable = var;
		setIsNumeric(true);
		numVal = val;
	}

	public KBSettings.UncertaintyManagement getUncertaintyMethod()
	{
		return uncertaintyType;
	}


	public void setUncertaintyMethod(KBSettings.UncertaintyManagement uncertainty)
	{
		this.uncertaintyType = uncertainty;
	}


	public double getLikelihoodOfNecessity()
	{
		return likelihoodOfNecessity;
	}
	
	public Comparison getComparison()
	{
		return comparison;
	}

	public void setComparison(Comparison comparison)
	{
		this.comparison = comparison;
		if(!isNumeric)
			setIsNumeric(true);
	}

	public void setLikelihoodOfNecessity(double likelihoodOfNecessity)
	{
		this.likelihoodOfNecessity = likelihoodOfNecessity;
	}

	public double getLikelihoodOfSufficiency()
	{
		return likelihoodOfSufficiency;
	}

	public void setLikelihoodOfSufficiency(double likelihoodOfSufficiency)
	{
		this.likelihoodOfSufficiency = likelihoodOfSufficiency;
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

	
	public Variable getVariable()
	{
		return variable;
	}
	
	public void setVariable(Variable v)
	{
		// will null this object's value field on setting of variable - it will no longer be valid
		
		variable = v;
		
		if(variable.getIsNumeric())
		{
			setValue(0.0);
			setComparison(Comparison.EQ);
		}
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
	
	public Boolean evaluate()
	{
		if(!isNumeric)
		{
			if(variable.getCurrentValue() == value)
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
//				return variable.getNumVal() == numVal;
				return Double.compare(variable.getNumVal(), numVal) == 0;
			case NEQ:
				return variable.getNumVal() != numVal;
			case GT:
				return variable.getNumVal() > numVal;
			case LT:
				return variable.getNumVal() < numVal;
			case LTEQ:
				return variable.getNumVal() <= numVal;
			case GTEQ:
				return variable.getNumVal() >= numVal;
			default:
				return false;
			}
		}
				
	}
	
	public Consequent convertToConsequent()
	{
		if(isNumeric)
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
		if(isNumeric)
		{
			return variable.getName() + " " + comparison.toString() + " " + numVal;
		}
		else
		{
			if(uncertaintyType == KBSettings.UncertaintyManagement.BAYESIAN
					&& (likelihoodOfSufficiency != 1 || likelihoodOfNecessity != 1))
			{
				return variable.getName() + " is " + value.toString() + " {ls = "+likelihoodOfSufficiency+"} {ln = "+likelihoodOfNecessity+"}";
			}
			else
				return variable.getName() + " is " + value.toString();
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
