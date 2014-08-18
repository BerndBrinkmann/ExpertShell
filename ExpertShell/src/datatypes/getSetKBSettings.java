package datatypes;

public class getSetKBSettings {

	protected KBSettings.InferenceType inferenceType;
	protected KBSettings.UncertaintyManagement uncertaintyType = KBSettings.UncertaintyManagement.NONE;
	protected KBSettings.ConflictResolution conflictResolution = KBSettings.ConflictResolution.NONE;
	
	
	
	public KBSettings.ConflictResolution getConflictResolutionMethod()
	{
		return conflictResolution;
	}

	public void setConflictResolutionMethod(KBSettings.ConflictResolution resolutionMethod)
	{
		this.conflictResolution = resolutionMethod;
	}
	
	
	
	public KBSettings.InferenceType getInferenceMethod()
	{
		return inferenceType;
	}
	
	public void setInferenceMethod(KBSettings.InferenceType inference)
	{
		this.inferenceType = inference;
	}
	
	
	
	public KBSettings.UncertaintyManagement getUncertaintyMethod()
	{
		return uncertaintyType;
	}


	public void setUncertaintyMethod(KBSettings.UncertaintyManagement uncertainty)
	{
		this.uncertaintyType = uncertainty;
	}
	
	
}
