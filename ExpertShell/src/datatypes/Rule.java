package datatypes;

import gui.MainScreen;

import java.io.Serializable;

import datatypes.KBSettings;
import datatypes.KBSettings.UncertaintyManagement;
import datatypes.getSetKBSettings;
import java.util.ArrayList;
import java.util.Stack;

import removedClasses.UncertaintyMethod;

public class Rule extends getSetKBSettings implements Serializable 
{
	protected ArrayList<Antecedent> antecedents;
	protected ArrayList<Consequent> consequents;
	Connectives connective = Connectives.AND;
	protected Boolean fired = false;
	protected int priority = 1;
	protected int ruleNum;
	protected MainScreen mainsc;
	
	UncertaintyManagement uncertaintyType = UncertaintyManagement.NONE;
	
	@Override
	public void setUncertaintyMethod(KBSettings.UncertaintyManagement uncertainty)
	{
		this.uncertaintyType = uncertainty;
		
		//set all the antecedents and consequents also
		for(Antecedent a : antecedents)
		{
			a.setUncertaintyMethod(uncertainty);
		}
		for(Consequent c : consequents)
		{
			c.setUncertaintyMethod(uncertainty);
		}
	}

	public Rule(MainScreen mains)
	{
		mainsc = mains;
		antecedents = new ArrayList<Antecedent>();
		consequents  = new ArrayList<Consequent>();
	}
	
	public void addAntecedent(Antecedent ant)
	{
		antecedents.add(ant);
		ant.setUncertaintyMethod(uncertaintyType);
	}
	
	public void addConsequent(Consequent cons)
	{
		consequents.add(cons);
		cons.setUncertaintyMethod(uncertaintyType);
	}
	
	public void setConnective(Connectives con)
	{
		connective = con;
	}
	
	public void setRuleNum(int num)
	{
		ruleNum = num;
	}
	
	public int getRuleNum()
	{
		return ruleNum;
	}
	
	public Antecedent getAntecedent(int i)
	{
		return antecedents.get(i);
	}
	
	public Consequent getConsequent(int i)
	{
		return consequents.get(i);
	}
	
	public void removeConditional(Object conditional)
	{	
		// would be better if we implemented a Conditional class and had Antecedent and Consequent extend that
		if(conditional instanceof Antecedent)
		{
			antecedents.remove(conditional);
		}
		else if(conditional instanceof Consequent)
		{
				consequents.remove(conditional);
		}
	}
	
	public Antecedent[] getAntecedentArray()
	{
		return antecedents.toArray(new Antecedent[antecedents.size()]);
	}
	
	public Consequent[] getConsequentArray()
	{
		return consequents.toArray(new Consequent[consequents.size()]);
	}
	
	public int getNumberOfAntecedents()
	{
		return antecedents.size();
	}
	
	public int getNumberOfConsequents()
	{
		return consequents.size();
	}
	
	public Connectives getConnective()
	{
		return connective;
	}
	
	//Executes all the consequents in the rule
	public void fire()
	{
		fired = true;
		
		for(int i = 0; i < consequents.size(); i++)
		{
			consequents.get(i).execute();
			//set that the variable was set by this rule
			consequents.get(i).getVariable().setDerivedFrom(this);
		}	
	}
	
	public Boolean hasFired()
	{
		return fired;
	}
	
	public int getSpecificity()
	{
		if(antecedents.size() == 1)
		{
			return 1;
		}
		else if(connective == Connectives.AND)
		{
			return antecedents.size();
		}
		else //the case that the connective is an OR
		{
			return antecedents.size()*-1;
		}
	}
	
	//returns the result of evaluating the required antecedents in a rule and also fires if required
	public Boolean evaluate(UncertaintyManagement umethod)
	{
		//evaluate all antecedents in the case of Bayesian and Certainty factor reasoning. Otherwise minimise user interaction
		switch(umethod)
		{
			case BAYESIAN:
				return evaluateAll(umethod);
			case CF:
				return evaluateAll(umethod);
			default:
				break;
		}
		
		
		// there must be at least a single antecedent to evaluate
		Boolean result = false;

		if(getNumberOfAntecedents() < 1)
		{
			System.err.println("ERROR: Rule must have at least one antecedent to evaluate");
			return false;
		}
		
		
		switch(connective)
		{
		case AND:
			//In an AND connective, test rules until a false or undefinable is found. If none found then fire.
			for(int i = 0; i < getNumberOfAntecedents(); i++)
			{
				//if the antecedent variable is undefined
				Variable var = getAntecedent(i).getVariable();
				 if(!var.hasValue())
				 {
					 //if the value can be obtained from the user
					 if(var.isUserInput())
					 {
						//get the input from the user
						var = mainsc.AskUserForInput(var,this);
						
						//get the certainty if required
						if(umethod == KBSettings.UncertaintyManagement.CF)
						{
							var.setCertaintyFactor(var.getCurrentValue(), ImportExport.getCertainty("Input a certainty for "+var.toString()));
						}
					 }
					 else
					 {
						 //if it can't be obtained then do not fire
						 return false;
					 }
				 }
				 
				 //if the variable has a value
				 if(!getAntecedent(i).evaluate())
				 {
					 // and evaluates to false then return
					 return false;
				 }		 
			}
			
			fire();
	
			
			return true;
		case OR:
			//In an OR connective, test rules until a true is found then fire
			for(int i = 0; i < getNumberOfAntecedents(); i++)
			{
				//if the antecedent variable is undefined
				Variable var = getAntecedent(i).getVariable();
				 if(!var.hasValue())
				 {
					 //if the value can be obtained from the user
					 if(var.isUserInput())
					 {
						//get the input from the user
						var = mainsc.AskUserForInput(var,this);
						
						//get the certainty if required
						if(umethod == UncertaintyManagement.CF)
						{
							var.setCertaintyFactor(var.getCurrentValue(), ImportExport.getCertainty("Input a certainty for "+var.toString()));
						}
					 }
					 else
					 {
						 continue;
					 }
				 }
				
				if(getAntecedent(i).evaluate())
				{
					fire();
					return true;
				}
				 
			}

		
		}
		return false;
	}
	
	//returns the result of evaluating the required antecedents in a rule and also fires if required
	public Boolean evaluateAll(UncertaintyManagement umethod)
		{
			// there must be at least a single antecedent to evaluate
			Boolean result = false;

			if(getNumberOfAntecedents() < 1)
			{
				System.err.println("ERROR: Rule must have at least one antecedent to evaluate");
				return false;
			}
			
			
			
				//In an AND connective, test rules until a false or undefinable is found. If none found then fire.
				for(int i = 0; i < getNumberOfAntecedents(); i++)
				{
					//if the antecedent variable is undefined
					Variable var = getAntecedent(i).getVariable();
					 if(!var.hasValue())
					 {
						 //if the value can be obtained from the user
						 if(var.isUserInput())
						 {
							//get the input from the user
							 var = mainsc.AskUserForInput(var,this);
							//var = ImportExport.userSetVaraible(var,this);
							
							//get the certainty if required				TODO added this condition
							if(umethod == UncertaintyManagement.CF && !(var instanceof NumericVariable))
							{
								var.setCertaintyFactor(var.getCurrentValue(), ImportExport.getCertainty("Input a certainty for "+var.toString()));
							}
						 }

					 }
					 	 
				}
				
				switch(umethod)
				{
					case BAYESIAN:
						fireBayesian();
						break;
					case CF:
						fireCertainty();
						break;
					default:
						break;
				}
				
				return true;
				
				
		
		}

	public void fireCertainty()
	{
		Double antecedentCertaintyProd = 1.0;
		fired = true;
		
		
		if(connective == Connectives.AND)
		{
			//compute the product of all the antecedents
			double min = Double.MAX_VALUE;
			for(int i = 0; i < getNumberOfAntecedents(); i++)
			{
				if(getAntecedent(i).variable instanceof NumericVariable)
				{
					Double numMin = 1.0;
					// if an expression evaluates to true then it's local cf is 1, which is then compared with all other ants in the rule
					// essentially nulls a rule is the numerical evaluation returns false
					if(!getAntecedent(i).evaluate())
						numMin = 0.0;
					
					if(numMin < min)
						min = numMin;
				}
				else
				{
					//TODO this is the original chunk
					Variable var = getAntecedent(i).getVariable();
					Value val = getAntecedent(i).getValue();
					if(var.getCertaintyFactor(val) < min)
						min = var.getCertaintyFactor(val);
				}
			}
			
			antecedentCertaintyProd = min;

			
		}
		else if (connective == Connectives.OR)
		{
			//compute the product of all the antecedents
			double max = 0;
			for(int i = 0; i < getNumberOfAntecedents(); i++)
			{
				if(getAntecedent(i).variable instanceof NumericVariable)
				{
					Double numMax = 0.0;
					if(getAntecedent(i).evaluate())
						numMax = 1.0;
					
					if(numMax > max)
						max = numMax;
				}
				else
				{
					// TODO this was the original chunk that just doesn't get run if the antecedent is numeric
					Variable var = getAntecedent(i).getVariable();
					Value val = getAntecedent(i).getValue();
					if(var.getCertaintyFactor(val) > max)
						max = var.getCertaintyFactor(val);
				}
			}
			
			antecedentCertaintyProd = (max);

		}
		
		//fire all the consequents with the certainty data
		for(int i = 0; i < getNumberOfConsequents(); i++)
		{
			if(connective == Connectives.AND)
			{
				consequents.get(i).executeCertainty(antecedentCertaintyProd);
			}
			else if (connective == Connectives.OR)
			{
				consequents.get(i).executeCertainty(antecedentCertaintyProd);

			}
		}
		
	}
	
	public void fireBayesian()
	{
		fired = true;

		//consider antecedents in order
		for(int i = 0; i < getNumberOfAntecedents(); i++)
		{
			
			Antecedent currentAnt = getAntecedent(i);
			//evaluate the current antecedent. 
			if(currentAnt.evaluate())
			{
				//if it is true then execute consequents using LS
				for(int j = 0; j < getNumberOfConsequents(); j++)
				{
					getConsequent(j).executeBayesian(currentAnt.getLikelihoodOfSufficiency());
				}
			}
			else
			{
				//if it is false then execute consequents using LN
				for(int j = 0; j < getNumberOfConsequents(); j++)
				{
					getConsequent(j).executeBayesian(currentAnt.getLikelihoodOfNecessity());
				}
			}
			
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("IF ");
		for(int i = 0; i < antecedents.size(); i++)
		{
			sb.append(antecedents.get(i).toString() + "\n");
			
			if(i < antecedents.size() - 1)
			{
				sb.append(connective.toString() + " ");
			}
		}
		sb.append("THEN\n");
		for(int i = 0; i < consequents.size(); i++)
		{
			sb.append(consequents.get(i).toString() + "\n");
		}
		return sb.toString();
	}
}
