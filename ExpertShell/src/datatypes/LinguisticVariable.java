package datatypes;

import java.util.ArrayList;

public class LinguisticVariable extends Variable {

	protected String name;
	protected String description = "";
	protected ArrayList<Value> possibleValues;
	protected ArrayList<Integer> numOfValueInstances;
	protected Value currentValue;
	protected Rule changedBy;
	protected String queryPrompt = "";
	
	protected Boolean isNumeric = false;
	protected Double numVal = null;
	
	protected ArrayList<Double> certaintyFactors;  //by our convention certainty factors are stored as 0-1
	protected ArrayList<Double> prior;
		
	
	protected Boolean askUser = false;
	protected Boolean userDerived = false;
	protected Value defaultValue;
	
	public LinguisticVariable()
	{
		
	}
	
}
