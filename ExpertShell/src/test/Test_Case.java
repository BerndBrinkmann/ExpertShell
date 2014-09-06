package test;

import java.io.Serializable;
import java.util.ArrayList;

import datatypes.Antecedent;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.KnowledgeBase;
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
		mainsc = mains;
		KnowledgeBase forecast = new KnowledgeBase("Forecast KB (Bayesian Example)");
		
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
