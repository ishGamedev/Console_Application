package SerializableObjects;

import java.io.Serializable;

public class Skills implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String skill;
	public String skillLevel;
	
	public Skills(String _skill,String _skillLevel){
		skill = _skill;
		skillLevel = _skillLevel;
	}
}
