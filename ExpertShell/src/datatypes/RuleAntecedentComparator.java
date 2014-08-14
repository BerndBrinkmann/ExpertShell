package datatypes;

import java.util.Comparator;


// call this comparator with the following
// Arrays.sort(arrayOfRules, new RuleAntecedentComparator());

// in order to sort the rule array by the number of antecedents each rule has

public class RuleAntecedentComparator implements Comparator<Rule>
{

	public int compare(Rule r1, Rule r2)
	{
		
		if(r1.getSpecificity() < r2.getSpecificity())
			return -1;
		else if (r1.getSpecificity() == r2.getSpecificity())
			return 0;
		else if(r1.getSpecificity() > r2.getSpecificity())
			return 1;
		
		return 0;	
	}
}