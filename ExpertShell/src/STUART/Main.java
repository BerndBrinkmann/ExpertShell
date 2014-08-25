package STUART;

import STUART.ADT.*;

import java.io.*;

import javax.swing.JFileChooser;

import STUART.GUI.*;

public class Main 
{
	
//	public static void main(String[] args)
//	{
//		//set up the static file chooser.
//		KnowledgeBaseFileManager.fileChooser = new JFileChooser();
//		
//		SplashScreen s = new SplashScreen();
////		RuleWizard ruleWizard = new RuleWizard();
//
//		System.out.println("S.T.U.A.R.T is GO!\n");
//	}
	
	
	//hard coded knowledge base for testing purposes
	public static KnowledgeBase createBoatKnowlegeBase()
	{
		KnowledgeBase boat_kb = new KnowledgeBase("Boat Knowlege Base");
		
		//define the variables
		Variable numberOfMasts = new Variable("number of masts");
		Value one = new Value("one");
		Value two = new Value("two");
		numberOfMasts.addPossibleValue(one);
		numberOfMasts.addPossibleValue(two);
		numberOfMasts.setUserInput(true);
		
		Variable shapeOfMain = new Variable("shape of main-sail");
		Value tri = new Value("triangular");
		Value quad = new Value("quadrilateral");
		Value triw2fore = new Value("triangular with two foresails");
		shapeOfMain.addPossibleValue(tri);
		shapeOfMain.addPossibleValue(quad);
		shapeOfMain.addPossibleValue(triw2fore);
		shapeOfMain.setUserInput(true);
		
		Variable mainMastPosition = new Variable("main mast position");
		Value fwrdShortMast = new Value("forward of the short mast");
		Value aftShortMast = new Value("aft of the short mast");
		mainMastPosition.addPossibleValue(fwrdShortMast);
		mainMastPosition.addPossibleValue(aftShortMast);
		mainMastPosition.setUserInput(true);
		
		Variable shortMastPosition = new Variable("short mast position");
		
		Value fwrdHelm = new Value("forward of the helm");
		Value aftHelm = new Value("aft of the  helm");
		shortMastPosition.addPossibleValue(fwrdHelm);
		shortMastPosition.addPossibleValue(aftHelm);
		shortMastPosition.setUserInput(true);
		
		Variable boat = new Variable("boat");
		Value jibCutter = new Value("jib headed cutter");
		Value gaffSloop = new Value("gaff headed sloop");
		Value jibKetch = new Value("jib headed ketch");
		Value gaffKetch = new Value("gaff headed ketch");
		Value jibYawl = new Value("jib heaeded yawl");
		Value gaffYawl = new Value("gaff headed yawl");
		Value gaffScooner = new Value("gaff headed schooner");
		Value staySchooner = new Value("staysail schooner");
		boat.addPossibleValue(jibCutter);
		boat.addPossibleValue(gaffSloop);
		boat.addPossibleValue(jibKetch);
		boat.addPossibleValue(gaffKetch);
		boat.addPossibleValue(jibYawl);
		boat.addPossibleValue(gaffYawl);
		boat.addPossibleValue(gaffScooner);
		boat.addPossibleValue(staySchooner);


		
		
		
		//create the rule set
		Rule[] rules = new Rule[8];
		
		rules[0] = new Rule();
		rules[0].setConnective(Connectives.AND);
		rules[0].addAntecedent(new Antecedent(numberOfMasts,one));
		rules[0].addAntecedent(new Antecedent(shapeOfMain,tri));
		rules[0].addConsequent(new Consequent(boat,jibCutter));
		rules[0].getConsequent(0).setCertaintyFactor(0.4);
		
		rules[1] = new Rule();
		rules[1].setConnective(Connectives.AND);
		rules[1].addAntecedent(new Antecedent(numberOfMasts,one));
		rules[1].addAntecedent(new Antecedent(shapeOfMain,quad));
		rules[1].addConsequent(new Consequent(boat,gaffSloop));
		rules[1].getConsequent(0).setCertaintyFactor(0.9);

		
		rules[2] = new Rule();
		rules[2].setConnective(Connectives.AND);
		rules[2].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[2].addAntecedent(new Antecedent(mainMastPosition,fwrdShortMast));
		rules[2].addAntecedent(new Antecedent(shortMastPosition,fwrdHelm));
		rules[2].addAntecedent(new Antecedent(shapeOfMain,tri));
		rules[2].addConsequent(new Consequent(boat,jibKetch));
		rules[2].getConsequent(0).setCertaintyFactor(0.9);

		
		rules[3] = new Rule();
		rules[3].setConnective(Connectives.AND);
		rules[3].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[3].addAntecedent(new Antecedent(mainMastPosition,fwrdShortMast));
		rules[3].addAntecedent(new Antecedent(shortMastPosition,fwrdHelm));
		rules[3].addAntecedent(new Antecedent(shapeOfMain,quad));
		rules[3].addConsequent(new Consequent(boat,gaffKetch));
		rules[3].getConsequent(0).setCertaintyFactor(0.8);

		
		rules[4] = new Rule();
		rules[4].setConnective(Connectives.AND);
		rules[4].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[4].addAntecedent(new Antecedent(mainMastPosition,fwrdShortMast));
		rules[4].addAntecedent(new Antecedent(shortMastPosition,aftHelm));
		rules[4].addAntecedent(new Antecedent(shapeOfMain,tri));
		rules[4].addConsequent(new Consequent(boat,jibYawl));
		rules[4].getConsequent(0).setCertaintyFactor(0.7);

		
		rules[5] = new Rule();
		rules[5].setConnective(Connectives.AND);
		rules[5].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[5].addAntecedent(new Antecedent(mainMastPosition,fwrdShortMast));
		rules[5].addAntecedent(new Antecedent(shortMastPosition,aftHelm));
		rules[5].addAntecedent(new Antecedent(shapeOfMain,quad));
		rules[5].addConsequent(new Consequent(boat,gaffYawl));
		rules[5].getConsequent(0).setCertaintyFactor(0.6);

		
		rules[6] = new Rule();
		rules[6].setConnective(Connectives.AND);
		rules[6].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[6].addAntecedent(new Antecedent(mainMastPosition,aftShortMast));
		rules[6].addAntecedent(new Antecedent(shapeOfMain,quad));
		rules[6].addConsequent(new Consequent(boat,gaffScooner));
		rules[6].getConsequent(0).setCertaintyFactor(0.5);

		
		rules[7] = new Rule();
		rules[7].setConnective(Connectives.AND);
		rules[7].addAntecedent(new Antecedent(numberOfMasts,two));
		rules[7].addAntecedent(new Antecedent(mainMastPosition,aftShortMast));
		rules[7].addAntecedent(new Antecedent(shapeOfMain,triw2fore));
		rules[7].addConsequent(new Consequent(boat,staySchooner));
		rules[7].getConsequent(0).setCertaintyFactor(0.9);

		
		
		for(int i = 0; i < 8; i++)
		{
			rules[i].setRuleNum(i);
			boat_kb.addRule(rules[i]);
		}

		boat_kb.setTarget(boat);
		boat_kb.setUncertaintyMethod(UncertaintyMethod.BAYESIAN);
		
		return boat_kb;

	}
	
	
	public static void testADTs()
	{
		//follow a possible workflow for the RuleWizard
		
		//create a knowledge base
		KnowledgeBase kn = new KnowledgeBase("test knowlege base");
		
		//create a rule and add
		Rule rule = new Rule();
		kn.addRule(rule);

		//specify the variables in the rule
		Variable var1 = new Variable("Rain");
		Value val1 = new Value("heavy");
		var1.setCurrentValue(val1);
		var1.addPossibleValue(val1);
		
		Variable var2 = new Variable("Wetness");
		Value val2 = new Value("very");
		
		var1.addPossibleValue(val2);
		var2.addPossibleValue(val2);
		
		Antecedent ant = new Antecedent(var1,val1);
		Consequent cons = new Consequent(var2,val2);
		
		rule.addAntecedent(ant);
		rule.addConsequent(cons);
		
		//specify another rule
		Rule rule2 = new Rule();
		kn.addRule(rule2);
		
		Variable var3 = new Variable("Sun");
		Value val3 = new Value("shining");
		var1.setCurrentValue(val3);
		var1.addPossibleValue(val3);
		
		Variable var4 = new Variable("Sunburn");
		Value val4 = new Value("red");
		
		var1.addPossibleValue(val4);
		var2.addPossibleValue(val4);
		
		Antecedent ant2 = new Antecedent(var3,val3);
		Consequent cons2 = new Consequent(var4,val4);
		
		rule2.addAntecedent(ant2);
		rule2.addConsequent(cons2);

		System.out.println(var1);

		
		System.out.println(kn);
		System.out.println(rule.toString());

		
		System.out.println(kn);

	}

}
