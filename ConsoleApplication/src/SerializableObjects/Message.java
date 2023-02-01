package SerializableObjects;

import java.io.Serializable;

import consoleApplication.User;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String title;
	public String sender;
	public String timeDate;
	public String content;
	public User senderProfile;
	
	public void printMessage() {
		System.out.println("\nTitle: "+title+"\n");
		System.out.println("Content \n"+"       "+content);
		System.out.println("\nBy");
		System.out.println("  "+sender);
		System.out.println("  at "+timeDate+"\n");
	}
}
