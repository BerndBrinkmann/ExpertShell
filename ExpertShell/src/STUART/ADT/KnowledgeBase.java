package STUART.ADT;

import java.io.Serializable;
import java.util.ArrayList;

public class KnowledgeBase implements Serializable
{
	protected String name;
	protected String description;
	protected ArrayList<Rule> rules;
	protected Variable target;
	protected InferenceMethod inferenceMethod;
	protected UncertaintyMethod uncertaintyMethod = UncertaintyMethod.NONE;
	protected ConflictResolutionMethod conflictResolutionMethod = ConflictResolutionMethod.NONE;
	
	public KnowledgeBase(String n)
	{
		name = n;
		rules = new ArrayList<Rule>();
	}
	
	public void addRule(Rule r)
	{
		rules.add(r);
		r.setUncertaintyMethod(uncertaintyMethod);
	}
	
	public Variable getTarget()
	{
		return target;
	}
	
	public int getNumberOfRules()
	{
		return rules.size();
	}
	
	public Rule getRule(int i)
	{
		return rules.get(i);
	}
	
	public void removeRule(Rule r)
	{	
		rules.remove(r);	
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public InferenceMethod getInferenceMethod()
	{
		return inferenceMethod;
	}

	public void setInferenceMethod(InferenceMethod inferenceMethod)
	{
		this.inferenceMethod = inferenceMethod;
	}

	public UncertaintyMethod getUncertaintyMethod()
	{
		return uncertaintyMethod;
	}

	public void setUncertaintyMethod(UncertaintyMethod uncertaintyMethod)
	{
		this.uncertaintyMethod = uncertaintyMethod;
		for(Rule r : rules)
		{
			r.setUncertaintyMethod(uncertaintyMethod);
		}
	}

	public ConflictResolutionMethod getConflictResolutionMethod()
	{
		return conflictResolutionMethod;
	}

	public void setConflictResolutionMethod(ConflictResolutionMethod conflictResolutionMethod)
	{
		this.conflictResolutionMethod = conflictResolutionMethod;
	}

	public Rule[] getRuleArray()
	{
		return rules.toArray(new Rule[rules.size()]);
	}
	
	public void shiftRuleUp(Rule r)
	{
		int index = rules.indexOf(r);
		
		if(index > 0)
		{
			rules.remove(r);
			rules.add(index - 1, r);
		}
	}
	
	public void shiftRuleDown(Rule r)
	{
		int index = rules.indexOf(r);
		
		if(index < rules.size() - 1)
		{
			rules.remove(r);
			rules.add(index + 1, r);
		}
	}
	
	public Variable[] getVariablesArray()
	{
		//search through all the rules and find all the variables
		ArrayList<Variable> variables = new ArrayList<Variable>();
		
		for(Rule r : getRuleArray())
		{
			for(Antecedent a : r.getAntecedentArray())
			{
				if(!variables.contains(a.getVariable()))
					variables.add(a.getVariable());
			}
			for(Consequent c : r.getConsequentArray())
			{
				if(!variables.contains(c.getVariable()))
					variables.add(c.getVariable());
			}
		}
		return variables.toArray(new Variable[variables.size()]);
	}
	
	public Variable[] getConsequentVariablesArray()
	{
		ArrayList<Variable> variables = new ArrayList<Variable>();
		
		for(Rule r : getRuleArray())
		{
			for(Consequent c : r.getConsequentArray())
			{
				if(!variables.contains(c.getVariable()))
					variables.add(c.getVariable());
			}
		}
		return variables.toArray(new Variable[variables.size()]);
	}
	
	public void autoSetUserInput()
	{
		//search through all the rules and find all the variables that aren't set in consequents and assign them to be user asked.
		ArrayList<Variable> conVariables = new ArrayList<Variable>();
		ArrayList<Variable> antVariables = new ArrayList<Variable>();
		
		for(Rule r : getRuleArray())
		{
			for(Antecedent a : r.getAntecedentArray())
			{
				if(!antVariables.contains(a.getVariable()))
					antVariables.add(a.getVariable());
			}
			
			for(Consequent c : r.getConsequentArray())
			{
				if(!conVariables.contains(c.getVariable()))
					conVariables.add(c.getVariable());
			}
		}
		
		antVariables.removeAll(conVariables);
		
		// auto setting of user input iff no ants have been set manually (there are problems with this. what if new rules are added?)
		boolean updateAsks = true;
		for(Variable v : antVariables)
		{
			if(v.isUserInput())
				updateAsks = false;
		}
		if(updateAsks)
		{
			for(Variable v : antVariables)
			{
				v.setUserInput(true);
			}
		}
	}
	
	public void validate()
	{
		//TODO three items
		// trim unused values from kb
		// check rule completeness; valid ant/con >1 of each and has required uncertainty values
		// actually, could probably implement this one in rule and antecedent (isValid() ?) and use in rule wizard instead

		autoSetUserInput();
		
		/////
		// part 2 is trimming of unused variables
		
		// for each variable, find all values paired with it in ants/cons and remove unused variables from the possible variables list
		// also adds an 'other' option to each variable, to allow for unknown values
		
		
		ArrayList<Value> valuesUsed = new ArrayList<Value>();
		
		for(Variable var : getVariablesArray())
		{
			for(Rule r : getRuleArray())
			{
				for(Antecedent a : r.getAntecedentArray())
				{
					if(a.getVariable() == var && !valuesUsed.contains(a.getValue()))
						valuesUsed.add(a.getValue());
				}
				for(Consequent c : r.getConsequentArray())
				{
					if(c.getVariable() == var && !valuesUsed.contains(c.getValue()))
						valuesUsed.add(c.getValue());
				}
			}
			
			for(Value pVal : var.getArrayOfPossibleValues())
			{
				if(!valuesUsed.contains(pVal))
					var.removePossibleValue(pVal);
			}
			
			if(!(uncertaintyMethod == UncertaintyMethod.CERTAINTY_FACTOR))
				var.addPossibleValue(new Value("Other"));
		}
	}
	
	public void setTarget(Variable tgt)
	{
		target = tgt;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Knowlege Base : "+name+"\n\n");
		sb.append("Rule List:\n\n");
		for(int i = 0; i < rules.size(); i++)
		{
			sb.append(rules.get(i).toString()+"\n");
		}
		return sb.toString();
	}
	
	
}