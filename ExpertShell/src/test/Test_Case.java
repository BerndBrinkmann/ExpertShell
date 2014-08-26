package test;

import java.util.ArrayList;

import datatypes.Antecedent;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.KnowledgeBase;
import datatypes.LinguisticVariable;
import datatypes.Rule;
import datatypes.KBSettings;
import datatypes.Value;
import datatypes.Variable;

public class Test_Case {

	

	//hard coded knowledge base for testing purposes
	public KnowledgeBase createBoatKnowlegeBase()
	{
		KnowledgeBase boat_kb = new KnowledgeBase("Boat Knowlege Base");
		
		//define the variables
		
		Variable numberOfMasts = new Variable("number of masts");
		boat_kb.addVariable(numberOfMasts);
		Value one = new Value("one");
		Value two = new Value("two");
		numberOfMasts.addPossibleValue(one);
		numberOfMasts.addPossibleValue(two);
		numberOfMasts.setUserInput(true);
		
		Variable shapeOfMain = new Variable("shape of main-sail");
		boat_kb.addVariable(shapeOfMain);
		Value tri = new Value("triangular");
		Value quad = new Value("quadrilateral");
		Value triw2fore = new Value("triangular with two foresails");
		shapeOfMain.addPossibleValue(tri);
		shapeOfMain.addPossibleValue(quad);
		shapeOfMain.addPossibleValue(triw2fore);
		shapeOfMain.setUserInput(true);
		
		Variable mainMastPosition = new Variable("main mast position");
		boat_kb.addVariable(mainMastPosition);
		Value fwrdShortMast = new Value("forward of the short mast");
		Value aftShortMast = new Value("aft of the short mast");
		mainMastPosition.addPossibleValue(fwrdShortMast);
		mainMastPosition.addPossibleValue(aftShortMast);
		mainMastPosition.setUserInput(true);
		
		Variable shortMastPosition = new Variable("short mast position");
		boat_kb.addVariable(shortMastPosition);
		Value fwrdHelm = new Value("forward of the helm");
		Value aftHelm = new Value("aft of the  helm");
		shortMastPosition.addPossibleValue(fwrdHelm);
		shortMastPosition.addPossibleValue(aftHelm);
		shortMastPosition.setUserInput(true);
		
		Variable boat = new Variable("boat");
		boat_kb.addVariable(boat);
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
			boat_kb.AddRule(rules[i]);
		}

		boat_kb.setTarget(boat);
		//boat_kb.setUncertaintyMethod(boat_kb);
		
		return boat_kb;

	}
	
	
	
	
}
