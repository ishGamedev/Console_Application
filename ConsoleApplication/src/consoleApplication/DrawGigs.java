package consoleApplication;

import SerializableObjects.*;

public class DrawGigs {
	public static void drawGigsFn(ServiceOffers allServices,ServiceOffers basic,ServiceOffers standard,ServiceOffers premium){
		
		
		drawHrRule();
		//First row which prints the BASIC STANDARD PREMIUM words at the tob
		System.out.printf("\n| %27s%10s%4s%10s%4s%10s%4s\n","|","Basic","|","Standard","|","Premium","|");
		drawHrRule();
		
		//Responsible for printing contents for the table
		for(int i = 0;i<allServices.typesOfGigs.length;i++) {
			System.out.printf("\n| %25s%s%7s%7s%7s%7s%7s%7s\n",allServices.typesOfGigs[i]," |",basic.getServices()[i],"|",standard.getServices()[i],"|",premium.getServices()[i],"|");
			drawHrRule();
		}
	}
	//draws a horizontal rule
	private static void drawHrRule() {
		int i = 0;
		while(i<70) {
			System.out.print("-");
			i++;
		}
	}
}
