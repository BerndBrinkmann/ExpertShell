package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class KnowledgeBase extends getSetKBSettings implements Serializable 
{
	protected String Name;
	protected String Description;
	protected ArrayList<Rule> RuleList;
	protected ArrayList<Variable> VariableList = new ArrayList<Variable>();
	protected KBSettings.InferenceType inferenceType;
	protected KBSettings.UncertaintyManagement uncertaintyType = KBSettings.UncertaintyManagement.NONE;
	protected KBSettings.ConflictResolution conflictResolution = KBSettings.ConflictResolution.NONE;
	protected Variable VarTemp;
	
	

	public KnowledgeBase(String name)
	{
		Name = name;
		RuleList = new ArrayList<Rule>();
	}
	
	public void setDescription(String description)
	{
		this.Description = description;
	}

	public String getDescription()
	{
		return Description;
	}

	public String getName()
	{
		return Name;
	}
			
	public void SetName(String name)
	{
		this.Name = name;
	}
			
	public void AddRule(Rule rule)
	{
		RuleList.add(rule);
		rule.setUncertaintyMethod(uncertaintyType);
	}
	
	public int getNumberOfRules()
	{
		return RuleList.size();
	}
	
	public Rule getRule(int i)
	{
		return RuleList.get(i);
	}
	
	public void removeRule(Rule rule)
	{	
		RuleList.remove(rule);	
	}

	public Rule[] getRuleArray()
	{
		return RuleList.toArray(new Rule[RuleList.size()]);
	}
	
	public void shiftRuleUp(Rule rule)
	{
		int index = RuleList.indexOf(rule);
		
		if(index > 0)
		{
			RuleList.remove(rule);
			RuleList.add(index - 1, rule);
		}
	}
	
	public void shiftRuleDown(Rule rule)
	{
		int index = RuleList.indexOf(rule);
		
		if(index < RuleList.size() - 1)
		{
			RuleList.remove(rule);
			RuleList.add(index + 1, rule);
		}
	}
	
	public ArrayList<Variable> getVariablesArray()
	{
		//search through all the rules and find all the variables
	//	ArrayList<Variable> variables = new ArrayList<Variable>();
		
		for(Rule rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(!VariableList.contains(a.getVariable()))
					VariableList.add(a.getVariable());
			}
			for(Consequent c : rule.getConsequentArray())
			{
				if(!VariableList.contains(c.getVariable()))
					VariableList.add(c.getVariable());
			}
		}
		return VariableList;
	}
	
	public Variable getVariable(String VarString)
	{
		int x =0;
		
		for (int i =0 ; i < VariableList.size() ; i +=1)
		{
			
			if(VariableList.get(i).name.equals(VarString))
			{
				x = i;
			}
		}
		return VariableList.get(x);
	}
	
	public int addVariableToArray( Variable Var)
	{
		if(!VariableList.contains(Var))
		{
			VariableList.add(Var);
			return 1;
		}	
		else
		{
			return -1;
		}
	}
	
	public int DeleteVariableFromArray( Variable Var)
	{
		if(VariableList.contains(Var))
		{
			VariableList.remove(Var);
			return 1;
		}	
		else
		{
			return -1;
		}
	}
	
	public Variable[] getConsequentVariablesArray()
	{
		ArrayList<Variable> variables = new ArrayList<Variable>();
		
		for(Rule rule : getRuleArray())
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
		
		for(Rule rule : getRuleArray())
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
			for(Rule rule : getRuleArray())
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
			
			if(!(uncertaintyType == KBSettings.UncertaintyManagement.CF))
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
	
	public Boolean isVariable(Variable v)
	{
		int inList = -1;
		for(int i=0; i<VariableList.size();i++)
		{
			//if(variable typed into rule editor box exists in list)
			inList = i;
		}
		if(inList != -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void addVariable(Variable v)
	{
		VariableList.add(v);
	}
	
	public void removeVariable(Variable v)
	{
		VariableList.remove(v);
	}

	
}