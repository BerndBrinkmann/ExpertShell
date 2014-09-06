package datatypes;

import gui.IO;
import gui.runGUI;

import java.io.IOException;

import datatypes.getSetKBSettings;
import datatypes.KnowledgeBase;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.io.Serializable;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.SerializationUtils;

import datatypes.*;

public class InferenceEngine implements Serializable {

    KnowledgeBase KBase;
    runGUI theRunGUI;
    public Boolean stopFlag = false;

	protected ArrayList<Rule> howList;
	

	public InferenceEngine(KnowledgeBase kb)

	{
		 
		//create a deep copy of the knowledge base object to operate on
		//KBase = (KnowledgeBase) SerializationUtils.clone(kb);
		KBase = kb;
	}
		
	public Variable solveForwardChaining(Variable selectedVariable)
	{			
		// obtain the target variable
		howList = new ArrayList<Rule>();
		Variable target = selectedVariable;
		if(target == null)
		{
		System.out.println("Operation Cancelled by User");
			return null;
		}
		
		Rule rule;
		Rule[] rulesList = KBase.getRuleArray();		
		
		while(true)
		{
			//consider all the rules 
			for(int i = 0; i < rulesList.length; i++)
			{
				if(!stopFlag)
				{
				//grab the current rule in a cyclic way
				rule = rulesList[i];
				Boolean conseq = false;
				setRelevant(target);
				//checks whether the target can still be adjusted 
				for( int n=i; n<rulesList.length;n++)
				{
					if(!rulesList[n].hasFired())
					{
						for(int m=0; m<rulesList[n].getConsequentArray().length;m++)
						{
							//if target is in a consequent and that rule is fireable
							if(rulesList[n].getConsequent(m).getVariable() == target)
							{
								conseq = true;
								System.out.println(conseq);
							}
						}
					}
				}
				if(!conseq)
				{
					return target;
				}
				
				//has the rule been evaluated? If it has then grab the next rule
				if(rule.hasFired() == true)
				{
					continue;
				}
				else
				{


					if(rule.evaluate(KBase.getUncertaintyMethod(),this,KBase))

					{
						howList.add(rule);
					}
					else
					{

					}
				}
			}
			}
				return target;
		}
	}    
	
	
	public ArrayList<Rule> getHowList()
	{
		return howList;
	}
	
	public Variable solveBackwardChaining(Variable selectedVariable)
	{
		
		Variable targetVar = selectedVariable;
		Value targetVal = IO.getValue("Input a value to search for111", targetVar.getArrayOfPossibleValues());
		if(targetVar == null || targetVal == null)
		{
			System.out.println("Operation Cancelled by User");
			return null;
		}
		return solveBackwardChaining(targetVar, targetVal);
	}
	
	public Variable solveBackwardChaining(Variable targetVar, Value targetVal)
	{
		
		if(targetVar == null || targetVal == null)
		{
			System.out.println("Operation Cancelled by User");
			return null;
		}
		
		howList = new ArrayList<Rule>();
		
		Consequent cons = new Consequent(targetVar,targetVal);
		
		Stack<Rule> stack = new Stack<Rule>();
		ArrayList<Rule> stacked = new ArrayList<Rule>();
		
		//find the first lot of containing rules and push to the stack
		Rule[] containingRules = findContainingRules(cons);
		
		if(containingRules.length > 0)
		{			
			for(Rule r : containingRules)
			{
				if(!stacked.contains(r))
				{
					stack.push(r);
					stacked.add(r);
				}
			}
		}
		
		//loop while the stack contains rules
		while(!stack.isEmpty())
		{
			//get the set of rule at the top of the stack
			Rule rule = stack.peek();
								

			if(rule.evaluate(KBase.getUncertaintyMethod(),this,KBase))
			{
				//if a rule is evaluated then drop the set off the stack and look at the rule set below
				howList.add(stack.pop());
				continue;
			}	
			else
			{
				System.out.println("could not evaluate");
				//else stack the rules that can cause this rule to trigger
				int nStacked = 0;
				for(int j = 0; j < rule.getNumberOfAntecedents(); j++)
				{
					if(!rule.getAntecedent(j).getVariable().hasValue() || rule.getAntecedent(j).evaluate() == false)
					{
						containingRules = findContainingRules(rule.getAntecedent(j).convertToConsequent());
						if(containingRules.length > 0)
						{		
							for(Rule r : containingRules)
							{
								if(!stacked.contains(r))
								{
									stack.push(r);
									stacked.add(r);
									nStacked++;
								}
							}
						}
						
					}
				}
				//if no rules got stacked this time around then we have reached a dead end. go back down the stack.
				if(nStacked == 0)
				{
					stack.pop();
				}
			}
			
			
		}
		return targetVar;
	}
	
	public Variable solveBackwardChainingNum(Variable targetVar, Double targetVal)
	{
		System.out.println(targetVar +"  "+ targetVal);
		if(targetVar == null || targetVal == null)
		{
			System.out.println("1. Operation Cancelled by User");
			return null;
		}
		
		howList = new ArrayList<Rule>();
		
		Consequent cons = new Consequent(targetVar,targetVal);
		
		Stack<Rule> stack = new Stack<Rule>();
		ArrayList<Rule> stacked = new ArrayList<Rule>();
		
		//find the first lot of containing rules and push to the stack
		Rule[] containingRules = findContainingRules(cons);		
		if(containingRules.length > 0)
		{			
			for(Rule r : containingRules)
			{
				if(!stacked.contains(r))
				{
					stack.push(r);
					stacked.add(r);
				}
			}
		}
		
		//loop while the stack contains rules
		while(!stack.isEmpty())
		{
			//get the set of rule at the top of the stack
			Rule rule = stack.peek();
								

			if(rule.evaluate(KBase.getUncertaintyMethod(),this,KBase))
			{
				//if a rule is evaluated then drop the set off the stack and look at the rule set below
				howList.add(stack.pop());
				continue;
			}	
			else
			{
				System.out.println("could not evaluate");
				//else stack the rules that can cause this rule to trigger
				int nStacked = 0;
				for(int j = 0; j < rule.getNumberOfAntecedents(); j++)
				{
					if(!rule.getAntecedent(j).getVariable().hasValue() || rule.getAntecedent(j).evaluate() == false)
					{
						containingRules = findContainingRules(rule.getAntecedent(j).convertToConsequent());
						if(containingRules.length > 0)
						{		
							for(Rule r : containingRules)
							{
								if(!stacked.contains(r))
								{
									stack.push(r);
									stacked.add(r);
									nStacked++;
								}
							}
						}
						
					}
				}
				//if no rules got stacked this time around then we have reached a dead end. go back down the stack.
				if(nStacked == 0)
				{
					stack.pop();
				}
			}
			
			
		}
		return targetVar;
	}
	
	
	// this method searches the knowledge base to find rules that contain the given consequent
	public Rule[] findContainingRules(Consequent cons)
	{
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		//perform a linear search through the rule list
		for(int i = 0; i < KBase.getNumberOfRules(); i++)
		{
			for(int j = 0; j < KBase.getRule(i).getNumberOfConsequents(); j++)
			{
				if(cons.getValueAsString().equals(KBase.getRule(i).getConsequent(j).getValueAsString()))
				{
					rules.add(KBase.getRule(i));
				}
			}
		}
		
		return rules.toArray(new Rule[rules.size()]);
	}
	
	public void setRelevant(Variable target)
	{
		Rule[] rulesList = KBase.getRuleArray();
		for(int i = 0; i < rulesList.length; i++)
		{
			if(!checkRelevant(target, i))
			{
				rulesList[i].setFired(true);
				System.out.println("Rule " + (i+1) + "setFired");
			}
		}
	}
	
	public Boolean checkRelevant(Variable target, Integer index)
	{
			Rule[] rulesList = KBase.getRuleArray();
			for(int n=0; n < rulesList[index].getConsequentArray().length; n++)
			{
				if(rulesList[index].getConsequentArray()[n].getVariable() == target)
				{
					return true;
				}
				else
				{
					if(checkAntRelevant(rulesList[index].getConsequentArray()[n].getVariable(), target)){
						return true;
					}
				}
			}
		return false;
	}
	public Boolean checkAntRelevant(Variable targetVariable, Variable target)
	{
		Rule[] rulesList = KBase.getRuleArray();
		for(int m=0; m< rulesList.length;m++)
		{
		for(int r=0; r<rulesList[m].getAntecedentArray().length;r++)
		{
			if(rulesList[m].getAntecedentArray()[r].getVariable() == targetVariable)
			{
				return checkConsRelevant(rulesList[m],target);
			}
			
		}
		}
		return false;
	}
	
	public Boolean checkConsRelevant(Rule r, Variable target)
	{
		for(int i=0; i<r.getConsequentArray().length;i++)
		{
			if(r.getConsequentArray()[i].getVariable() == target)
			{
				return true;
			}
			else
			{
				return checkAntRelevant(r.getConsequentArray()[i].getVariable(),target);
			}
		}
		return false;
	}
	
}
