package test;

import java.io.Serializable;
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
import gui.*;

public class Test_Case implements Serializable {


	MainScreen mainsc;

	//hard coded knowledge base for testing purposes
	public KnowledgeBase createBoatKnowlegeBase(MainScreen mains)
	{
		/*mainsc = mains;
		KnowledgeBase boat_kb = new KnowledgeBase("Boat Knowledge Base");
		
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
/*		Rule[] rules = new Rule[8];
		
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
*/
/*		Rule[] rules = new Rule[11];
		
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
		*/
		
		mainsc = mains;
		KnowledgeBase forecast = new KnowledgeBase("Forecast Knowledge Base");
		
		//define the variables
		
		Variable today = new Variable("today");
		forecast.addVariable(today);
		Value rain = new Value("rain");
		Value dry = new Value("dry");
		today.addPossibleValue(rain);
		today.addPossibleValue(dry);
		today.setUserInput(true);
		
		Variable tomorrow = new Variable("tomorrow");
		forecast.addVariable(tomorrow);
		Value raintom = new Value("rain");
		Value drytom = new Value("dry");
		tomorrow.addPossibleValue(raintom);
		tomorrow.addPossibleValue(drytom);
		tomorrow.setUserInput(false);
		
		Variable rainfall = new Variable("rainfall");
		forecast.addVariable(rainfall);
		Value low = new Value("low");
		Value high = new Value("high");
		rainfall.addPossibleValue(low);
		rainfall.addPossibleValue(high);
		rainfall.setUserInput(true);
		
		Variable temperature = new Variable("temperature");
		forecast.addVariable(temperature);
		Value warm = new Value("warm");
		Value cold = new Value("cold");
		temperature.addPossibleValue(warm);
		temperature.addPossibleValue(cold);
		temperature.setUserInput(true);
		
		Variable sky = new Variable("sky");
		forecast.addVariable(sky);
		Value overc = new Value("overcast");
		Value clear = new Value("clear");
		sky.addPossibleValue(overc);
		sky.addPossibleValue(clear);
		sky.setUserInput(true);
	
		Rule[] rules = new Rule[6];
		
		rules[0] = new Rule();
		rules[0].setConnective(Connectives.AND);
		rules[0].addAntecedent(new Antecedent(today,rain));
		rules[0].addConsequent(new Consequent(tomorrow,raintom));
		rules[0].setLikelihoodOfSufficiency(2.5);
		rules[0].setLikelihoodOfNecessity(0.6);
		rules[0].getConsequent(0).getVariable().setBelief(raintom, 0.5);
		
		rules[1] = new Rule();
		rules[1].setConnective(Connectives.AND);
		rules[1].addAntecedent(new Antecedent(today,dry));
		rules[1].addConsequent(new Consequent(tomorrow,drytom));
		rules[1].setLikelihoodOfSufficiency(1.6);
		rules[1].setLikelihoodOfNecessity(0.4);
		rules[1].getConsequent(0).getVariable().setBelief(drytom, 0.5);
		
		rules[2] = new Rule();
		rules[2].setConnective(Connectives.AND);
		rules[2].addAntecedent(new Antecedent(today,rain));
		rules[2].addAntecedent(new Antecedent(rainfall,low));
		rules[2].addConsequent(new Consequent(tomorrow,drytom));
		rules[2].setLikelihoodOfSufficiency(10);
		rules[2].setLikelihoodOfNecessity(1);
		rules[2].getConsequent(0).getVariable().setBelief(drytom, 0.5);
		
		rules[3] = new Rule();
		rules[3].setConnective(Connectives.AND);
		rules[3].addAntecedent(new Antecedent(today,rain));
		rules[3].addAntecedent(new Antecedent(rainfall,low));
		rules[3].addAntecedent(new Antecedent(temperature,cold));
		rules[3].addConsequent(new Consequent(tomorrow,drytom));
		rules[3].setLikelihoodOfSufficiency(1.5);
		rules[3].setLikelihoodOfNecessity(1);
		rules[3].getConsequent(0).getVariable().setBelief(drytom, 0.5);
		
		rules[4] = new Rule();
		rules[4].setConnective(Connectives.AND);
		rules[4].addAntecedent(new Antecedent(today,dry));
		rules[4].addAntecedent(new Antecedent(temperature,warm));
		rules[4].addConsequent(new Consequent(tomorrow,raintom));
		rules[4].setLikelihoodOfSufficiency(2);
		rules[4].setLikelihoodOfNecessity(0.9);
		rules[4].getConsequent(0).getVariable().setBelief(raintom, 0.5);
		
		rules[5] = new Rule();
		rules[5].setConnective(Connectives.AND);
		rules[5].addAntecedent(new Antecedent(today,dry));
		rules[5].addAntecedent(new Antecedent(temperature,warm));
		rules[5].addAntecedent(new Antecedent(sky,overc));
		rules[5].addConsequent(new Consequent(tomorrow,raintom));
		rules[5].setLikelihoodOfSufficiency(5);
		rules[5].setLikelihoodOfNecessity(1);
		rules[5].getConsequent(0).getVariable().setBelief(raintom, 0.5);
		
		for(int i = 0; i < 6; i++)
		{
			rules[i].setRuleNum(i);
			forecast.AddRule(rules[i]);
		}

		forecast.setTarget(tomorrow);
		forecast.setUncertaintyMethod(KBSettings.UncertaintyManagement.BAYESIAN);
		forecast.setInferenceMethod(KBSettings.InferenceType.F_CHAINING);
		return forecast;
		
		
		
	}
	
	
	
	
}
