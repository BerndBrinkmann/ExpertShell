package datatypes;

public class KBSettings {

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
