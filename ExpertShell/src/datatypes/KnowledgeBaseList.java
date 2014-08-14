package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class KnowledgeBaseList implements Serializable
{
	protected String Name;
	protected String Description;
	protected ArrayList<RuleObject> RuleList;
	protected InferenceMethod inferenceMethod;
	protected UncertaintyMethod uncertaintyMethod = UncertaintyMethod.NONE;
	protected ConflictResolutionMethod conflictResolutionMethod = ConflictResolutionMethod.NONE;
	
	public KnowledgeBaseList(String name)
	{
		Name = name;
		RuleList = new ArrayList<RuleObject>();
	}
	
	public void setDescription(String description)
	{
		this.Description = description;
	}

	public String getDescription()
	{
		return Description;
	}

	public InferenceMethod getInferenceMethod()
	{
		return inferenceMethod;
	}
	
	public void setInferenceMethod(InferenceMethod inferenceMethod)
	{
		this.inferenceMethod = inferenceMethod;
	}
	
	public String getName()
	{
		return Name;
	}
			
	public void SetName(String name)
	{
		this.Name = name;
	}
			
	public void AddRule(RuleObject rule)
	{
		RuleList.add(rule);
		rule.setUncertaintyMethod(uncertaintyMethod);
	}
	
	public int getNumberOfRules()
	{
		return RuleList.size();
	}
	
	public RuleObject getRule(int i)
	{
		return RuleList.get(i);
	}
	
	public void removeRule(RuleObject rule)
	{	
		RuleList.remove(rule);	
	}

	public UncertaintyMethod getUncertaintyMethod()
	{
		return uncertaintyMethod;
	}

	public void setUncertaintyMethod(UncertaintyMethod uncertaintyMethod)
	{
		this.uncertaintyMethod = uncertaintyMethod;
		for(RuleObject rule : RuleList)
		{
			rule.setUncertaintyMethod(uncertaintyMethod);
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

	public RuleObject[] getRuleArray()
	{
		return RuleList.toArray(new RuleObject[RuleList.size()]);
	}
	
	public void shiftRuleUp(RuleObject rule)
	{
		int index = RuleList.indexOf(rule);
		
		if(index > 0)
		{
			RuleList.remove(rule);
			RuleList.add(index - 1, rule);
		}
	}
	
	public void shiftRuleDown(RuleObject rule)
	{
		int index = RuleList.indexOf(rule);
		
		if(index < RuleList.size() - 1)
		{
			RuleList.remove(rule);
			RuleList.add(index + 1, rule);
		}
	}
	
	public Variable[] getVariablesArray()
	{
		//search through all the rules and find all the variables
		ArrayList<Variable> variables = new ArrayList<Variable>();
		
		for(RuleObject rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(!variables.contains(a.getVariable()))
					variables.add(a.getVariable());
			}
			for(Consequent c : rule.getConsequentArray())
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
		
		for(RuleObject rule : getRuleArray())
		{
			for(Consequent c : rule.getConsequentArray())
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
		
		for(RuleObject rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(!antVariables.contains(a.getVariable()))
					antVariables.add(a.getVariable());
			}
			
			for(Consequent c : rule.getConsequentArray())
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

		autoSetUserInput();

		ArrayList<Value> valuesUsed = new ArrayList<Value>();
		
		for(Variable var : getVariablesArray())
		{
			for(RuleObject rule : getRuleArray())
			{
				for(Antecedent a : rule.getAntecedentArray())
				{
					if(a.getVariable() == var && !valuesUsed.contains(a.getValue()))
						valuesUsed.add(a.getValue());
				}
				for(Consequent c : rule.getConsequentArray())
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
	
	public String RuleOutput()
	{
		StringBuilder OutputString = new StringBuilder();
		OutputString.append("Knowlege Base : "+Name+"\n");
		OutputString.append("Rules:\n");
		for(int i = 0; i < RuleList.size(); i++)
		{
			OutputString.append(RuleList.get(i).toString()+"\n");
		}
		return OutputString.toString();
	}
	
	
}