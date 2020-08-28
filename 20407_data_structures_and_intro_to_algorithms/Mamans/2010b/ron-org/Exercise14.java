import java.util.Scanner;

/**
 * Author: Ron Sne
 * i.d: 305840571
 * cell phone: 050-4751814
 * Maman - 14
 * This is the running class
 * contains the MMI
**/

public class Exercise14 {

	public static void main(String[] args) {

	int option;
	do // looped menu 
	{
		System.out.println("Please choose one of the following options:");
		System.out.println("-1 = ends the program");
		System.out.println("1 = builds a structure");
		System.out.println("2 = inserts a new value to the structure (if such exists)");
		System.out.println("3 = finds the maximum value");
		System.out.println("4 = finds the minimum value");
		System.out.println("5 = deletes the maximum value");
		System.out.println("6 = deletes the minimum value");
		Scanner optionsList = new Scanner (System.in);
		option = optionsList.nextInt();
		if (option > 6 || option <-1 || option ==0 )
		{
			System.out.println("The number is incorrect");
		}
	
		switch (option) // switch between the options (looping MMI)
		{
		case 1:
			final int MAXLENGTH = 100;
			int tempArrayOccupied=0; // counter of the real tempArray length
			int[] tempArray = new int [MAXLENGTH]; // Creates the temporary array
			System.out.println("Please enter the integer, each number followed by the ENTER button (maximum 100 numbers)");
			System.out.println("at the end, enter: -1");
			Scanner scan = new Scanner (System.in);
			for (int i=0; i<MAXLENGTH; i++) // builds the tempArray array from the given numbers
			{
				int number = scan.nextInt();
				if (number == -1)
				{
					break;
				}
				else
				{
					tempArray[i] = number; // adding the number into the array
					tempArrayOccupied++;
				}
			}
			Structure s = new Structure (tempArray, tempArrayOccupied);
			break;

		case 2:
			Scanner scanAdd = new Scanner (System.in);
			System.out.println("Please enter the number");
			int additional = scanAdd.nextInt();
			Structure.Insert(additional);
			break;
	
		case 3:
			Structure.FindMax();
			break;
		
		case 4:
			Structure.FindMin();
			break;
		
		case 5:
			Structure.DeleteMax();
			break;
		
		case 6:
			Structure.DeleteMin();
			break;
		}
	}
	while (option != -1);


}
}
