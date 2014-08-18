package datatypes;

import java.io.Serializable;

public class Antecedent extends Conditional implements Serializable
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
	protected Boolean antecedentFlag = true;
	
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

	public double getLikelihoodOfNecessity()
	{
		return likelihoodOfNecessity;
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

}
