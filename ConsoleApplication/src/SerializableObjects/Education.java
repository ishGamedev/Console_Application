package SerializableObjects;

import java.io.Serializable;

public class Education implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String degree;
	public String school;
	
	public Education(String _degree,String _school) {
		degree = _degree;
		school = _school;
	}
}
