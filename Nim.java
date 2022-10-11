/*
Project: ICS3U
Package: methods
Class: Nim
Programmer: Shaan Banday.
Teacher: Mr. Elliott.
Date Created: 5th October, 2020.
Program Description: A game called Nim, programmed in Java. Nim is a mathematically based game of strategy which is said to originate
from China. The rules of the game are as follows, there are a number of stones to start with. This can range anywhere from 15-30 stones.
Then the user must select 1, 2, or 3 stones to take from the pile. The computer then goes and selects 1, 2, or 3 randomly, except when 
there is 4, 3, or 2 stones left. In that case, the computer will play smart, and try to win the game. The game is won if the opposing
player takes the final stone.
*/

package methods; //launch package

import java.util.Scanner; //import scanner method

public class Nim //beginning of class
{
	public static int getComputerChoice(int stonesLeft) //create integer method to generate the computers randomly generated choice
	{
		int choice; //declare the variable to be returned		
		if (stonesLeft > 4) //if the number of stones left is greater than 4
		{
			choice = (int) ((Math.random() * 3) + 1); //randomly generate a choice between 1 and 3
		}
		else if (stonesLeft == 1) //if stones left is 1
		{
			choice = stonesLeft; //The computer cannot do anything, so it must loose
		}
		else // if the number of stones left is 4, 3, or 2
		{
			choice = stonesLeft - 1; //the computer plays smart, and puts the player into a losing position
		}
		return choice; //return whatever the choice may be
	} //end of integer method
	public static boolean isValidSelection(int amountTaken, int numLeft) //created boolean method, used to check if the selection is valid
	{
		boolean isValid = true; //set boolean value to true
		if ((amountTaken >= 1) && (amountTaken <= 3) && (amountTaken <= numLeft)) //if the amount taken is between 1 and 3, as well as greater than the number left
		{
			isValid = true; //keep isValid to true, meaning that this is a valid selection, either by the computer or user
		}
		else //if it does not meet the aforementioned parameters
		{
			isValid = false; //set isValid to false, meaning that this is not a valid selection
		}
		return isValid; //return isValid as a boolean variable
	} //end of boolean method
	public static void checkForWinner(boolean lastTurn) //create boolean void method to print who won. 
	{
		if (!lastTurn) // if lastTurn is false, which means that the computer went last
		{
			System.out.println("Congratulations, you WON! :)"); //print that user won
		}
		else //if lastTurn is true, which means that the user went last
		{
			System.out.println("Sorry, but the computer won. :("); //print that computer won
		}
	} //end of void method
	public static void main(String[] args) //start of main method
	{
		Scanner pressPlay = new Scanner(System.in); //create new scanner called play
		
		//Declare all variables
		int stonesLeft = (int) ((Math.random() * 16) + 15); //variable to generate a random number for the starting number of stones
		int takeIn = 0, computerOut = 0; //variable for user input, and computer output
		boolean lastTurn = true; //declare the variable last turn as true, since the user is going first

		//Introductory statements to inform the user
		System.out.println("Hello! Welcome to Nim in Java. Your goal is to not take the last stone.\nPress ENTER to play."); //prompt user to press enter
		pressPlay.nextLine(); //when the user presses enter, execute the next line of code
		//loops
		while (stonesLeft > 0) // run loop as long as the number of stones left is greater than 0
		{
			if (lastTurn) //if lastTurn is true (which means it is the users turn)
			{
				System.out.println(); //print line space
				System.out.println("There are " + stonesLeft + " stones. How many will you take? (Make sure it is Between 1 and 3 and that there are sufficient stones left): "); //tell user how many stones are left, then prompt user for number of stones to be taken
				takeIn = pressPlay.nextInt(); //store whatever user input in variable takeIn
				while (!isValidSelection(takeIn, stonesLeft)) //run nested loop while the selection is invalid
				{
					System.out.println("This is not a valid selection. Please enter a valid number, between 1 and 3, and make sure that there are sufficent stones left: "); //tell user their selection was invlaid, and tell them the parameters
					takeIn = pressPlay.nextInt(); //store whatever user input in variable takeIn
				} //loop breaks when selection is valid
				stonesLeft -= takeIn; //subtract the amount taken by the user, from the number of stones
				if (stonesLeft == 0) //if the number of stones reaches 0
				{
					lastTurn = true; //set the last turn as the user
					System.out.println("GAME OVER"); //print that the game is over
				} 
				else //if the number of stones is not 0
				{
					lastTurn = false; //continue game, and move onto the computer
				}
			} 
			else //if last turn is false (which means it is now the computers turn)
			{
				System.out.println(); //print line space
				System.out.print("There are " + stonesLeft + " stones."); //print how many stones are left
				computerOut = getComputerChoice(stonesLeft); //get the random choice from the computer
				while (!isValidSelection(computerOut, stonesLeft)) //run nested loop while the selection is invalid
				{
					computerOut = getComputerChoice(stonesLeft); //if selection is invalid, get a new choice
				}
				stonesLeft -= computerOut;
				System.out.println(" The computer takes " + computerOut + " stone(s)."); //tell the user how many stones the computer takes
				if (stonesLeft == 0) //if the number of stones reaches 0 
				{
					lastTurn = false; //set the last turn as the computer
					System.out.println("GAME OVER"); //print that the game is over
				}
				else //if the number of stones is not 0
				{
					lastTurn = true; //continue game, and move onto the computer
				}
			}
		} //end of loop
		checkForWinner(lastTurn); //check for winner, and print corresponding message
		pressPlay.close(); //close the scanner to avoid a leak
	} //end of main method
} //end of class