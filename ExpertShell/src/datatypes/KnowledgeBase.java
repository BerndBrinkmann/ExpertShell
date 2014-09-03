package datatypes;

import java.io.Serializable;
import java.util.ArrayList;

import datatypes.Variable;
import gui.runGUI;
//
public class KnowledgeBase extends getSetKBSettings implements Serializable 
{

	protected String Name;
	protected String Description;
	protected ArrayList<Rule> RuleList;
	protected ArrayList<Variable> VariableList = new ArrayList<Variable>();
	protected getSetKBSettings getsetSettings;
	//not needed - enum classes are static - Arie
	//protected KBSettings kbSettings = new KBSettings();
	
	//these are declared in the parent class (getSetKBSettings) - Arie
	//protected KBSettings.InferenceType inferenceType;
	//protected KBSettings.UncertaintyManagement uncertaintyType = KBSettings.UncertaintyManagement.NONE;
	//protected KBSettings.ConflictResolution conflictResolution = KBSettings.ConflictResolution.NONE;
	protected Variable VarTemp;
	protected Variable target;
	public runGUI rungui;
	
	@Override
	public void setUncertaintyMethod(KBSettings.UncertaintyManagement uncertainty) {
		this.uncertaintyType = uncertainty;
		
		//set uncertainty for all its rules
		for(Rule r : RuleList) {
			r.setUncertaintyMethod(uncertainty);
		}
	}
	
	public void setRunGui(runGUI rung)
	{
		rungui=rung;
	}
	
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
		updateVariableList();
		return VariableList;
	}
	
	public String[] getVariablesArrayAsString() {
		updateVariableList();
		int size = VariableList.size();
		String[] s = new String[size];
		
		for (int i = 0; i < size; i++) {
			s[i] = VariableList.get(i).getName();
		}
		
		return s;
	}
	
	public Variable getVariable(String VarString)
	{
		updateVariableList();
		for (int i =0 ; i < VariableList.size() ; i +=1)
		{
			
			if(VariableList.get(i).name.equals(VarString))
			{
				return VariableList.get(i);
			}
		}
		return null;
	}
	
	public void updateVariableList()
	{
		
		VariableList.clear();
		for(Rule rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(!VariableList.contains(a.getVariable())&& a.getVariable() !=null)
					VariableList.add(a.getVariable());
			}
			for(Consequent c : rule.getConsequentArray())
			{
				if(!VariableList.contains(c.getVariable())&& c.getVariable() !=null)
					VariableList.add(c.getVariable());
			}
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
				if(!variables.contains(c.getVariable())&& c.getVariable() !=null)
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
				if(!antVariables.contains(a.getVariable())&& a.getVariable() !=null)
					antVariables.add(a.getVariable());
			}
			
			for(Consequent c : rule.getConsequentArray())
			{
				if(!conVariables.contains(c.getVariable())&& c.getVariable() !=null)
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
				var.addPossibleValue(new Value("Unknown"));
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
	
	//is this used?? - Arie
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
		if(!VariableList.contains(v))
		VariableList.add(v);
	}
	
	public void removeVariable(Variable v)
	{
		VariableList.remove(v);
	}
    
	public void saveVariable(Variable VarNew, Variable VarOld)
	{

		// save the changes to the Knowledgebase
		for(Rule rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(a.getVariable() ==VarOld)
					a.setVariable(VarNew);
			}
			for(Consequent c : rule.getConsequentArray())
			{
				if(c.getVariable() == VarOld)
					c.setVariable(VarNew);
			}
		}
		//this updates the Variable List
		VariableList.clear();
		for(Rule rule : getRuleArray())
		{
			for(Antecedent a : rule.getAntecedentArray())
			{
				if(!VariableList.contains(a.getVariable())&& a.getVariable() !=null)
					VariableList.add(a.getVariable());
			}
			for(Consequent c : rule.getConsequentArray())
			{
				if(!VariableList.contains(c.getVariable())&& c.getVariable() !=null)
					VariableList.add(c.getVariable());
			}
		}
		
		
		
		
		
//		int x=0;
//		for (int i = 0;i< VariableList.size();i++)
//		{
//			if (VariableList.get(i).getName().equals(VarNew.getName()))
//			{
//
//				VariableList.set(i,VarNew);
//				x=1;
//			}
//		}
//		if (x==0)
//		{
//			VariableList.add(VarNew);
//		}	
		
		
		
	}
		
	public Variable getTarget(){
    	
    	return target;
    }
	
	public void setTarget(Variable tgt){
		
		target=tgt;
	}
	
	public Consequent[] getBCConsequents()
	{
	ArrayList<Consequent> consequents = new ArrayList<Consequent>();
	
	for(Rule rule : getRuleArray())
	{
		for(Consequent c : rule.getConsequentArray())
		{
			if(!consequents.contains(c)&& c !=null)
				consequents.add(c);
		}
	}
	return consequents.toArray(new Consequent[consequents.size()]);
	}
}
