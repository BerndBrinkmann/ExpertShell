package datatypes;

import java.io.Serializable;

public class Antecedent extends Conditional implements Serializable
{
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
		value = new Value("default",this);
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

	public void setVariable(Variable v)
	{
		// will null this object's value field on setting of variable - it will no longer be valid
		
		variable = v;
		
		if(variable instanceof NumericVariable)
		{
			setValue(0.0);
			setComparison(Comparison.EQ);
		}
		else
			setValue(new Value("", this));
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
				return variable.getName() + " is " + value.getName() + " {ls = "+likelihoodOfSufficiency+"} {ln = "+likelihoodOfNecessity+"}";
			}
			else
				return variable.getName() + " is " + value.getName();
		}
	}
	
	public String getVariableAsString() {
		return variable.getName();
	}
	
	public String getValueAsString() {
		if(isNumeric) {
			return numVal.toString();
		} else {
			return value.getName();
		}
	}
	
	public String getComparisonAsString() {
		return comparison.toString();
	}

}
