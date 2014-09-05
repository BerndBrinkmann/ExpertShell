package test;

import java.io.Serializable;
import java.util.ArrayList;

import datatypes.Antecedent;
import datatypes.Comparison;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.KnowledgeBase;
import datatypes.LinguisticVariable;
import datatypes.NumericVariable;
import datatypes.Rule;
import datatypes.KBSettings;
import datatypes.Value;
import datatypes.Variable;
import gui.*;

public class Test_Numeric implements Serializable  {
	MainScreen mainsc;

	//hard coded knowledge base for testing purposes
	public KnowledgeBase createNumericKB(MainScreen mains)
	{
	mainsc = mains;
	KnowledgeBase Numerical = new KnowledgeBase("Numerical Knowledge Base");
	
	//values
	Value one = new Value("1");
	Value two = new Value("2");
	Value three = new Value("3");
	Value four = new Value("4");
	Value five = new Value("5");
	Value six = new Value("6");
	Value seven = new Value("7");
	Value eight = new Value("8");
	Value nine = new Value("9");
	Value ten = new Value("10");
	// variables
	Variable x = new Variable("x");
	x.setIsNumeric(true);
	Numerical.addVariable(x);
	x.addPossibleValue(one);
	x.addPossibleValue(two);
	x.addPossibleValue(three);
	x.addPossibleValue(four);
	x.addPossibleValue(five);
	x.setUserInput(true);
	
	Variable y = new Variable("y");
	y.setIsNumeric(true);
	Numerical.addVariable(y);
	y.addPossibleValue(one);
	y.addPossibleValue(two);
	y.addPossibleValue(three);
	y.addPossibleValue(four);
	y.addPossibleValue(five);
	y.setUserInput(true);
	
	Variable z = new Variable("z");
	z.setIsNumeric(false);
	Numerical.addVariable(z);
	z.addPossibleValue(one);
	z.addPossibleValue(two);
	z.addPossibleValue(three);
	z.addPossibleValue(four);
	z.addPossibleValue(five);
	z.addPossibleValue(six);
	z.addPossibleValue(seven);
	z.addPossibleValue(eight);
	z.addPossibleValue(nine);
	z.addPossibleValue(ten);
	z.setUserInput(true);
	
	//rules
	Rule[] rules = new Rule[5];
	
	rules[0] = new Rule();
	rules[0].setConnective(Connectives.AND);
	rules[0].addAntecedent(new Antecedent(x,Comparison.GT,1.5));
	System.out.println(rules[0].getAntecedent(0).numVal);
	rules[0].addAntecedent(new Antecedent(y,Comparison.EQ,1.0));
	rules[0].addConsequent(new Consequent(z,two));
	System.out.println(rules[0].getAntecedent(0).toString());
	
	
	rules[1] = new Rule();
	rules[1].setConnective(Connectives.AND);
	rules[1].addAntecedent(new Antecedent(x,Comparison.EQ,2.0));
	rules[1].addAntecedent(new Antecedent(y,Comparison.GTEQ,1.0));
	rules[1].addConsequent(new Consequent(z,three));
	
	rules[2] = new Rule();
	rules[2].setConnective(Connectives.AND);
	rules[2].addAntecedent(new Antecedent(x,Comparison.EQ,1.0));
	rules[2].addAntecedent(new Antecedent(y,Comparison.GT,2.0));
	rules[2].addConsequent(new Consequent(z,three));


	rules[3] = new Rule();
	rules[3].setConnective(Connectives.AND);
	rules[3].addAntecedent(new Antecedent(x,Comparison.EQ,2.0));
	rules[3].addAntecedent(new Antecedent(y,Comparison.NEQ,2.0));
	rules[3].addConsequent(new Consequent(z,four));
		
	rules[4] = new Rule();
	rules[4].setConnective(Connectives.AND);
	rules[4].addAntecedent(new Antecedent(x,Comparison.LT,3.0));
	rules[4].addAntecedent(new Antecedent(y,Comparison.NEQ,2.0));
	rules[4].addConsequent(new Consequent(z,five));
	
	for(int i = 0; i < 5; i++)
	{
		rules[i].setRuleNum(i);
		Numerical.AddRule(rules[i]);
	}

	Numerical.setTarget(z);
	Numerical.setUncertaintyMethod(KBSettings.UncertaintyManagement.NONE);
	Numerical.setInferenceMethod(KBSettings.InferenceType.F_CHAINING);
	return Numerical;
}
}

