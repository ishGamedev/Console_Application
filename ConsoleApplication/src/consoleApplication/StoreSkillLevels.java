package consoleApplication;

import java.util.HashMap;
import java.util.Scanner;

import SerializableObjects.Skills;

public class StoreSkillLevels {
	public static void storeSkillLevelsFn(User tempProfile,String[] skills) {
		
		Scanner scString = new Scanner(System.in);
		Scanner scInt = new Scanner(System.in);
		
		HashMap<String,String> hm = new HashMap<String,String>();
		
		int skillsChosen = 0;//number of skills chosen in total by the user
		String stringHolder = "";
		int userChoice = 0;//index to choose skills from the array
		
		do {
			System.out.println("Choose the Skills you possess ");
			//Printing all the skills for the job
			for(int i = 0;i<skills.length;i++) {
				System.out.println(i+1+". "+skills[i]);
			}
			System.out.println("Enter Skill Number: ");
			userChoice = scInt.nextInt();
			//Iterating to check whether the skill is already chosen by the user
			if(hm.containsKey(skills[userChoice-1])) {
				System.out.println("\n\nSkill already entered!\n\n");
				continue;
			}
			System.out.println("How good are you in "+skills[userChoice-1]+" out of Five: ");
			stringHolder = scString.nextLine();
			stringHolder += "/5";// '/5' will be added to level added
			
			hm.put(skills[userChoice-1], stringHolder);
			//User can add 5 skills continuously after that for each skill a message will be shown whether they want to add further
			if(skillsChosen>=5) {
				System.out.println("Do you want to enter more skills (Yes/No): ");
				stringHolder = scString.nextLine();
			}
			//incrementing the skills added number
			skillsChosen++;
		}while(!stringHolder.equalsIgnoreCase("No"));
			
		copySkillsFromHashmap(tempProfile,hm);
	}
	
	private static void copySkillsFromHashmap(User tempProfile,HashMap<String,String> hm){
		hm.entrySet().forEach(entry ->{
			tempProfile.skillsObjects.add(new Skills(entry.getKey(),entry.getValue()));
		});
	}
}
