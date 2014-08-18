package datatypes;

import java.io.Serializable;

public class Consequent extends Conditional implements Serializable
{
	protected Variable variable;
	protected Value value;
	protected double certaintyFactor = 1;
	
	protected Boolean isNumeric = false;
	protected Double numVal;
	protected KBSettings.UncertaintyManagement uncertaintyType = KBSettings.UncertaintyManagement.NONE;
	protected Boolean antecedentFlag = false;
	
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
	
	public String toString()
	{
		if(!getIsNumeric())
		{
			if(uncertaintyType == KBSettings.UncertaintyManagement.CF)
			{
				return variable.getName() + " is " + value.toString() + " {cf = "+certaintyFactor+"}";
			}
			else if(uncertaintyType == KBSettings.UncertaintyManagement.BAYESIAN && !value.getName().equals("default") && !value.getName().equals(""))
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

	
}
