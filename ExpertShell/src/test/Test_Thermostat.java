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

public class Test_Thermostat implements Serializable {
	MainScreen mainsc;

	//hard coded knowledge base for testing purposes
	public KnowledgeBase createThermostat(MainScreen mains)
	{
	mainsc = mains;
	KnowledgeBase Thermo = new KnowledgeBase("Thermostat Knowledge Base");
	
	Variable day = new Variable("day");
	day.setIsNumeric(false);
	Thermo.addVariable(day);
	
	Value mon = new Value("Monday");
	Value tues = new Value("Tuesday");
	Value wed = new Value("Wednesday");
	Value thurs = new Value("Thursday");
	Value fri = new Value("Friday");
	Value sat = new Value("Saturday");
	Value sun = new Value("Sunday");
	day.addPossibleValue(mon);
	day.addPossibleValue(tues);
	day.addPossibleValue(wed);
	day.addPossibleValue(thurs);
	day.addPossibleValue(fri);
	day.addPossibleValue(sat);
	day.addPossibleValue(sun);
	day.setUserInput(true);
	
	Variable it = new Variable("it");
	it.setIsNumeric(false);
	Thermo.addVariable(it);
	it.setUserInput(false);
	
	Value end = new Value("weekend");
	Value work = new Value("workday");
	it.addPossibleValue(end);
	it.addPossibleValue(work);
	
	Variable time = new Variable("time");
	time.setIsNumeric(true);
	Thermo.addVariable(time);
	time.setUserInput(true);
	
	Variable month = new Variable("month");
	month.setIsNumeric(false);
	Thermo.addVariable(month);
	
	Value jan = new Value("January");
	Value feb = new Value("February");
	Value mar = new Value("March");
	Value apr = new Value("April");
	Value may = new Value("May");
	Value jun = new Value("June");
	Value jul = new Value("July");
	Value aug = new Value("August");
	Value sep = new Value("September");
	Value oct = new Value("October");
	Value nov = new Value("November");
	Value dec = new Value("December");
	month.addPossibleValue(jan);
	month.addPossibleValue(feb);
	month.addPossibleValue(mar);
	month.addPossibleValue(apr);
	month.addPossibleValue(may);
	month.addPossibleValue(jun);
	month.addPossibleValue(jul);
	month.addPossibleValue(aug);
	month.addPossibleValue(sep);
	month.addPossibleValue(oct);
	month.addPossibleValue(nov);
	month.addPossibleValue(dec);
	
	month.setUserInput(true);
	
	Variable season = new Variable("season");
	season.setIsNumeric(false);
	Thermo.addVariable(season);
	season.setUserInput(false);
	
	Value spring = new Value("Spring");
	Value summer = new Value("Summer");
	Value autumn = new Value("Autumn");
	Value winter = new Value("winter");
	season.addPossibleValue(spring);
	season.addPossibleValue(summer);
	season.addPossibleValue(autumn);
	season.addPossibleValue(winter);
	
	Variable op = new Variable("operation");
	op.setIsNumeric(false);
	Thermo.addVariable(op);
	op.setUserInput(false);
	
	Value closed = new Value("outside business hours");
	Value open = new Value("during business hours");
	op.addPossibleValue(closed);
	op.addPossibleValue(open);
	
	Variable settings = new Variable("thermostat_settings");
	settings.setIsNumeric(true);
	Thermo.addVariable(settings);
	settings.setUserInput(false);
	
	//rules
	Rule[] rules = new Rule[18];
	int i = 0;
	
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(day,mon));
	rules[i].addAntecedent(new Antecedent(day,tues));
	rules[i].addAntecedent(new Antecedent(day,wed));
	rules[i].addAntecedent(new Antecedent(day,thurs));
	rules[i].addAntecedent(new Antecedent(day,fri));
	rules[i].addConsequent(new Consequent(it, work));
	
	i++;
	
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(day,sat));
	rules[i].addAntecedent(new Antecedent(day,sun));
	rules[i].addConsequent(new Consequent(it, end));
	
	i++;
	
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(it,work));
	rules[i].addAntecedent(new Antecedent(time, Comparison.GT,0900.00));
	rules[i].addAntecedent(new Antecedent(time,Comparison.LT,1700.00));
	rules[i].addConsequent(new Consequent(op, open));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(it,work));
	rules[i].addAntecedent(new Antecedent(time, Comparison.LT,0900.00));
	rules[i].addConsequent(new Consequent(op, closed));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(it,work));
	rules[i].addAntecedent(new Antecedent(time, Comparison.GT,1700.00));
	rules[i].addConsequent(new Consequent(op, closed));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(it,end));
	rules[i].addConsequent(new Consequent(op, closed));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(month,jan));
	rules[i].addAntecedent(new Antecedent(month,feb));
	rules[i].addAntecedent(new Antecedent(month,dec));
	rules[i].addConsequent(new Consequent(season, summer));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(month,mar));
	rules[i].addAntecedent(new Antecedent(month,apr));
	rules[i].addAntecedent(new Antecedent(month,may));
	rules[i].addConsequent(new Consequent(season, autumn));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(month,jun));
	rules[i].addAntecedent(new Antecedent(month,jul));
	rules[i].addAntecedent(new Antecedent(month,aug));
	rules[i].addConsequent(new Consequent(season, winter));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.OR);
	rules[i].addAntecedent(new Antecedent(month,sep));
	rules[i].addAntecedent(new Antecedent(month,oct));
	rules[i].addAntecedent(new Antecedent(month,nov));
	rules[i].addConsequent(new Consequent(season, spring));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,spring));
	rules[i].addAntecedent(new Antecedent(op, open));
	rules[i].addConsequent(new Consequent(settings, 20.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,spring));
	rules[i].addAntecedent(new Antecedent(op,closed));
	rules[i].addConsequent(new Consequent(settings, 15.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,summer));
	rules[i].addAntecedent(new Antecedent(op, open));
	rules[i].addConsequent(new Consequent(settings, 24.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,summer));
	rules[i].addAntecedent(new Antecedent(op, closed));
	rules[i].addConsequent(new Consequent(settings, 27.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,autumn));
	rules[i].addAntecedent(new Antecedent(op, open));
	rules[i].addConsequent(new Consequent(settings, 20.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,autumn));
	rules[i].addAntecedent(new Antecedent(op, closed));
	rules[i].addConsequent(new Consequent(settings, 16.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,winter));
	rules[i].addAntecedent(new Antecedent(op, open));
	rules[i].addConsequent(new Consequent(settings, 18.0));
	
	i++;
	rules[i] = new Rule();
	rules[i].setConnective(Connectives.AND);
	rules[i].addAntecedent(new Antecedent(season,winter));
	rules[i].addAntecedent(new Antecedent(op, closed));
	rules[i].addConsequent(new Consequent(settings, 14.0));
	
	for(int n = 0; n < 18; n++)
	{
		rules[n].setRuleNum(n);
		Thermo.AddRule(rules[n]);
	}

	Thermo.setTarget(settings);
	Thermo.setUncertaintyMethod(KBSettings.UncertaintyManagement.NONE);
	Thermo.setInferenceMethod(KBSettings.InferenceType.F_CHAINING);
	return Thermo;
}
}

