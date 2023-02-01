package SerializableObjects;

import java.io.Serializable;

public class LanguagesKnown implements Serializable {
	private static final long serialVersionUID = 1L;
	public String language;
	public String proficiency;
	
	//Called from SetupProfileFn in SetupProfile class 
	public LanguagesKnown(String _language,String _proficiency){
		language = _language;
		proficiency = _proficiency;
	}
}
