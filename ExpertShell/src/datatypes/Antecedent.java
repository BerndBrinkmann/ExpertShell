package datatypes;

import java.io.Serializable;

public class Antecedent extends getSetKBSettings implements Serializable
{
	protected Variable variable;
	protected Value value;
//	protected double likelihoodOfNecessity = 1;
//	protected double likelihoodOfSufficiency = 1;
	
	protected Comparison comparison;
	protected Boolean isNumeric = false;
	public Double numVal = null;
	protected KBSettings.UncertaintyManagement uncertaintyType =KBSettings.UncertaintyManagement.NONE;
	
	public Antecedent()
	{
		variable = new Variable("default");
		comparison = Comparison.IS;
		value = new Value("default",this);
		
	}
	
	public Antecedent(Variable var,Comparison comp ,Double val)
	{
		
		numVal = val;
		comparison = comp;
		variable = var;
		
	}
	
	public Antecedent(Variable var, Value val)
	{
		variable = var;
		comparison = Comparison.IS;
		value = val;
	}
	
	public Antecedent(Variable var, Double val)
	{
		variable = var;
		comparison = Comparison.EQ;
		numVal = val;
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
	
	/*public double getLikelihoodOfNecessity()
	{
		return likelihoodOfNecessity;
	}
	*/
	public Comparison getComparison()
	{
		return comparison;
	}

/*	public double getLikelihoodOfNecessity()
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
*/
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
		if(!(variable instanceof NumericVariable))
		{
			
			//System.out.println(variable.getCurrentValue().toString() + "  =?  " + value.toString());
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
		if(variable instanceof NumericVariable)
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
		if(variable instanceof NumericVariable)
		{
			return variable.getName() + " " + comparison.toString() + " " + numVal;
		}
		else
		{
			/*if(uncertaintyType == KBSettings.UncertaintyManagement.BAYESIAN
					&& (likelihoodOfSufficiency != 1 || likelihoodOfNecessity != 1))
			{
				return variable.getName() + " is " + value.getName() + " {ls = "+likelihoodOfSufficiency+"} {ln = "+likelihoodOfNecessity+"}";
			}
			else*/
				return variable.getName() + " is " + value.getName();
		}
	}
	
	public String getVariableAsString() {
		return variable.getName();
	}
	
	public String getValueAsString() {
		if(variable instanceof NumericVariable) {
			return numVal.toString();
		} else {
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
//		if(this.variable instanceof NumericVariable)
//		{
//			this.comparison = comparison;
//		}
		
		//see what type of comparison we have
		boolean comparisonIsNumeric = comparison.numeric;
		
		//assign it
		this.comparison = comparison;
		
		//if antecedent is different then set it
		if((variable instanceof NumericVariable)!= comparisonIsNumeric)
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

