package datatypes;

import java.io.Serializable;

public class KBSettings implements Serializable  {

	public enum ConflictResolution
	{
		NONE, SPECIFICITY_BASED
	}
	
	public enum InferenceType
	{
		F_CHAINING, B_CHAINING
	}
	
	public enum UncertaintyManagement
	{
		NONE, CF, BAYESIAN
	}
	
	
}
