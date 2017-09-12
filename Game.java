package project;

import java.awt.Color;
import java.awt.Font;

import cse131.ArgsProcessor;
import sedgewick.StdDraw;

public class Game {
	// Create the original gorillas and environment; initialize important
	// variables
	static ArgsProcessor ap;
	
	/**
	 * Initializes some of the basic variables/objects in the game
	 */
	static GameMethodRunner gameRunner = new GameMethodRunner(); // Creates the method runner object that helps
																//run many of the important game methods
		
	
	
	/**
	 * The MAIN METHOD--takes in ArgsProcessor and executes game via runIt method 
	 * @param args
	 */
	public static void main(String[] args) {
		ap = new ArgsProcessor(args);
		runIt(); //Main runs below runIt method
	}

	
	
	
	/**
	 * A static method that allows the main method to run--uses methods from the GameMethodRunner class
	 * and calls on classes. It's really the "brains" of the game 
	 */
	public static void runIt() {
		int playaOneScore = 0; // Will keep track of the score of player 1
		int playaTwoScore = 0; // Will keep track of the score of player 2
		String playaOneName = ""; // Stores the name of player 1
		String playaTwoName = ""; // Stores the name of player 2
		double gravity = 0.0; // This variable is set at a later time (with the ArgsProcessor)
		int numPoints = 0; 	// This variable adds the score of each player and
							// compares this to the desired number of total
							// points--this is set by the ArgsProcessor
		
		for (int w = 1; w > -1; w++) { //This external loop creates an infinite loop, but it breaks when
			if (w > 1) {				//the score gets to the desired number of points 
				if (playaOneScore + playaTwoScore == numPoints) { // When the desired number of
																//total points is reached,the game stops
					
					gameRunner.drawHomeScreen(2); // Draw the end screen that thanks players for playing
					break; //If the total number of points has been reached, break the loop and end the game 
				}
			}

			// Create the original gorillas and environment; initialize important variables
			Environment one = new Environment();
			double[] stoBuilder = new double[10];
			double[] stoBuilderX = new double[10];
			stoBuilder = one.builder();
			stoBuilderX = one.builderX();
			int whoWon = 0;

			Gorilla playerOne = new Gorilla(1); // Create Player 1
			Gorilla playerTwo = new Gorilla(2); // Create Player 2

			double timey = 0.0; // Initialize time for game

			playerOne.setHeightMan(stoBuilder); // Tell gorillas about the
												// heights of the buildings
			playerTwo.setHeightMan(stoBuilder);

			one.setBuildingHeights(stoBuilder); // Tell environment drawer about the heights of the buildings
			one.setBuildingWidth(stoBuilderX); // Similarly, set building widths

			// Set the positions of the two players using the above method
			gameRunner.determinePositions(one, playerOne, playerTwo, 1);
			gameRunner.determinePositions(one, playerOne, playerTwo, 2);

			// Original Start Screen--only applies for first iteration
			if (w == 1) {
				gameRunner.drawHomeScreen(1); // Calls the home screen method above to set the home screen
				// Ask the player for the necessary information via the
				// ArgsProcessor--but only once (at the beginning of the game)
				playaOneName = ap.nextString("What is the name of Player 1?");
				playaTwoName = ap.nextString("What is the name of Player 2?");
				numPoints = ap.nextInt("To how many total points should the game play?");
				gravity = ap.nextDouble("What value would you like to use for gravity for the entire game (9.8 is normal)?");
			}

			StdDraw.clear();

			double windFactor = gameRunner.setTheWind(one); // Set the wind with a random factor

			// Redraw everything 
			gameRunner.drawItAll(one, playerOne, playerTwo, timey);
			gameRunner.scoresAndNames(playaOneName, playaTwoName, playaOneScore, playaTwoScore, w);

			for (int q = 1; q > -1; q++) { // Yes, this is an infinite loop, but it will break when one of the
											// players is hit
				
				// Create the "win" screen--depends on who won
				if (whoWon == 1) {
					gameRunner.whoWonGameScreen(playerOne, playerTwo, one, timey, playaOneName, playaTwoName,
							playaOneScore, playaTwoScore, 1);
					playaOneScore++; //Add to the winner's score
					whoWon = 0;
					break;
				} else if (whoWon == 2) {
					gameRunner.whoWonGameScreen(playerOne, playerTwo, one, timey, playaOneName, playaTwoName,
							playaOneScore, playaTwoScore, 2);
					playaTwoScore++; //Add to the winner's score 
					whoWon = 0;
					break;
				}
				StdDraw.clear(); //clear all drawings

				gameRunner.drawItAll(one, playerOne, playerTwo, timey); // Sketch everything
				gameRunner.scoresAndNames(playaOneName, playaTwoName, playaOneScore, playaTwoScore, q);
				StdDraw.show();

				// Prompt the players for specific information regarding the
				// angle and velocity of the projectile
				int angle = ap.nextInt("At what angle should the projectile be fired?");
				int velocity = ap.nextInt("At what speed (NO DECIMALS) should the projectile be fired?");

				// Create the banana
				Banana banana = gameRunner.createBanana(velocity, gravity, angle, playerOne, playerTwo, windFactor, q);

				gameRunner.setGravity(banana);

				timey = 0.0; // Sets important time variable--this variable
							// increments up and makes animation possible

				whoWon = gameRunner.internalLoop(banana, one, playerOne, playerTwo, whoWon, timey, playaOneName,
						playaTwoName, q, playaOneScore, playaTwoScore); // Creates internal loop that runs until a collision
			}
		}
	}
}
