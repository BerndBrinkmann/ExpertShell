package test;

import java.io.Serializable;
import java.util.ArrayList;

import datatypes.Antecedent;
import datatypes.Comparison;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.KnowledgeBase;
import datatypes.Rule;
import datatypes.KBSettings;
import datatypes.Value;
import datatypes.Variable;
import gui.*;

public class test_CF implements Serializable  {
	MainScreen mainsc;
 
		//hard coded knowledge base for testing purposes
		public KnowledgeBase createCFKB(MainScreen mains)
		{
		mainsc = mains;
		KnowledgeBase boat_kb = new KnowledgeBase("Boat KB (Certainty Factor Example)");
		
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
		
				// Rule Set
		
				Rule[] rules = new Rule[11];
				
				rules[0] = new Rule();
				rules[0].setConnective(Connectives.AND);
				rules[0].addAntecedent(new Antecedent(numberOfMasts,one));
				rules[0].addConsequent(new Consequent(boat,jibCutter));
				rules[0].addConsequent(new Consequent(boat,gaffSloop));
				rules[0].getConsequent(0).setCertaintyFactor(0.4);
				rules[0].getConsequent(1).setCertaintyFactor(0.4);
				
				rules[1] = new Rule();
				rules[1].setConnective(Connectives.AND);
				rules[1].addAntecedent(new Antecedent(numberOfMasts,one));
				rules[1].addAntecedent(new Antecedent(shapeOfMain,tri));
				rules[1].addConsequent(new Consequent(boat,jibCutter));
				rules[1].getConsequent(0).setCertaintyFactor(1.0);
				
				rules[2] = new Rule();
				rules[2].setConnective(Connectives.AND);
				rules[2].addAntecedent(new Antecedent(numberOfMasts,one));
				rules[2].addAntecedent(new Antecedent(shapeOfMain,quad));
				rules[2].addConsequent(new Consequent(boat,gaffSloop));
				rules[2].getConsequent(0).setCertaintyFactor(1.0);

				rules[3] = new Rule();
				rules[3].setConnective(Connectives.AND);
				rules[3].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[3].addConsequent(new Consequent(boat,jibKetch));
				rules[3].addConsequent(new Consequent(boat,gaffKetch));
				rules[3].addConsequent(new Consequent(boat,jibYawl));
				rules[3].addConsequent(new Consequent(boat,gaffYawl));
				rules[3].addConsequent(new Consequent(boat,gaffScooner));
				rules[3].addConsequent(new Consequent(boat,staySchooner));
				rules[3].getConsequent(0).setCertaintyFactor(0.1);
				rules[3].getConsequent(1).setCertaintyFactor(0.1);
				rules[3].getConsequent(2).setCertaintyFactor(0.1);
				rules[3].getConsequent(3).setCertaintyFactor(0.1);
				rules[3].getConsequent(4).setCertaintyFactor(0.1);
				rules[3].getConsequent(5).setCertaintyFactor(0.1);

				rules[4] = new Rule();
				rules[4].setConnective(Connectives.AND);
				rules[4].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[4].addAntecedent(new Antecedent(mainMastPosition,fwrdShortMast));
				rules[4].addConsequent(new Consequent(boat,jibKetch));
				rules[4].addConsequent(new Consequent(boat,gaffKetch));
				rules[4].addConsequent(new Consequent(boat,jibYawl));
				rules[4].addConsequent(new Consequent(boat,gaffYawl));
				rules[4].getConsequent(0).setCertaintyFactor(0.2);
				rules[4].getConsequent(1).setCertaintyFactor(0.2);
				rules[4].getConsequent(2).setCertaintyFactor(0.2);
				rules[4].getConsequent(3).setCertaintyFactor(0.2);

				rules[5] = new Rule();
				rules[5].setConnective(Connectives.AND);
				rules[5].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[5].addAntecedent(new Antecedent(mainMastPosition,aftShortMast));
				rules[5].addConsequent(new Consequent(boat,gaffScooner));
				rules[5].addConsequent(new Consequent(boat,staySchooner));
				rules[5].getConsequent(0).setCertaintyFactor(0.4);
				rules[5].getConsequent(1).setCertaintyFactor(0.4);
				
				rules[6] = new Rule();
				rules[6].setConnective(Connectives.AND);
				rules[6].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[6].addAntecedent(new Antecedent(shortMastPosition,fwrdHelm));
				rules[6].addConsequent(new Consequent(boat,jibKetch));
				rules[6].addConsequent(new Consequent(boat,gaffKetch));
				rules[6].getConsequent(0).setCertaintyFactor(0.4);
				rules[6].getConsequent(1).setCertaintyFactor(0.4);

				rules[7] = new Rule();
				rules[7].setConnective(Connectives.AND);
				rules[7].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[7].addAntecedent(new Antecedent(shortMastPosition,aftHelm));
				rules[7].addConsequent(new Consequent(boat,jibYawl));
				rules[7].addConsequent(new Consequent(boat,gaffYawl));
				rules[7].addConsequent(new Consequent(boat,gaffScooner));
				rules[7].addConsequent(new Consequent(boat,staySchooner));
				rules[7].getConsequent(0).setCertaintyFactor(0.2);
				rules[7].getConsequent(1).setCertaintyFactor(0.2);
				rules[7].getConsequent(2).setCertaintyFactor(0.2);
				rules[7].getConsequent(3).setCertaintyFactor(0.2);
				
				rules[8] = new Rule();
				rules[8].setConnective(Connectives.AND);
				rules[8].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[8].addAntecedent(new Antecedent(shapeOfMain,tri));
				rules[8].addConsequent(new Consequent(boat,jibKetch));
				rules[8].addConsequent(new Consequent(boat,jibYawl));
				rules[8].getConsequent(0).setCertaintyFactor(0.4);
				rules[8].getConsequent(1).setCertaintyFactor(0.4);
				
				rules[9] = new Rule();
				rules[9].setConnective(Connectives.AND);
				rules[9].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[9].addAntecedent(new Antecedent(shapeOfMain,quad));
				rules[9].addConsequent(new Consequent(boat,gaffKetch));
				rules[9].addConsequent(new Consequent(boat,gaffYawl));
				rules[9].addConsequent(new Consequent(boat,gaffScooner));
				rules[9].getConsequent(0).setCertaintyFactor(0.3);
				rules[9].getConsequent(1).setCertaintyFactor(0.3);
				rules[9].getConsequent(2).setCertaintyFactor(0.3);

				rules[10] = new Rule();
				rules[10].setConnective(Connectives.AND);
				rules[10].addAntecedent(new Antecedent(numberOfMasts,two));
				rules[10].addAntecedent(new Antecedent(shapeOfMain,triw2fore));
				rules[10].addConsequent(new Consequent(boat,gaffSloop));
				rules[10].getConsequent(0).setCertaintyFactor(1.0);		
				
				
				
				for(int i = 0; i < 11; i++)
				{
					rules[i].setRuleNum(i);
					boat_kb.AddRule(rules[i]);
				}

				boat_kb.setTarget(boat);
				boat_kb.setUncertaintyMethod(KBSettings.UncertaintyManagement.CF);
				boat_kb.setInferenceMethod(KBSettings.InferenceType.F_CHAINING);
				return boat_kb;	
		
}
}