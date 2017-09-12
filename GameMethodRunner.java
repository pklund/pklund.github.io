package project;

import java.awt.Color;

import sedgewick.StdDraw;

//This class takes in many static methods that are run in the Game class through the runIt method that accesses
//the ArgsProcessor 
public class GameMethodRunner { 
	/**
	 * Takes in the names of players and the scores of players and displays them based on whether 
	 * parameter turn is even or odd
	 * @param n1 name of player 1 (String)
	 * @param n2 name of player 2 (String)
	 * @param s1 score of player 1 (integer)
	 * @param s2 score of player 2 (integer)
	 * @param turn the integer that determines how the names will be highlighted on the screen
	 */
	public static void scoresAndNames(String n1, String n2, int s1, int s2, int turn){
		if (turn % 2 != 0){
			StdDraw.setPenColor(Color.CYAN); //The player who is up has his/her name highlighted in Cyan for clarity
			StdDraw.text(0.2, 0.08, n1);
			StdDraw.text(0.2, 0.04, "" + s1);

			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(1.8, 0.08, n2);
			StdDraw.text(1.8, 0.04, "" + s2);
		}
		else{
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0.2, 0.08, n1);
			StdDraw.text(0.2, 0.04, "" + s1);

			StdDraw.setPenColor(Color.CYAN);
			StdDraw.text(1.8, 0.08, n2);
			StdDraw.text(1.8, 0.04, "" + s2);
		}
	}


	/**
	 * Draws the original and final display screens of the game (includes an imported gorilla image)
	 * @param q the integer that determines whether the home or end screen is to be shown
	 */
	public static void drawHomeScreen(int q){
		if (q == 1){ //the home screen
			StdDraw.setPenColor(Color.MAGENTA);
			StdDraw.filledRectangle(1.0, 0.5, 1.0, 0.5); //draw colored rectangle to serve as backdrop
			StdDraw.setPenColor(Color.DARK_GRAY);
			StdDraw.text(1.0, 0.8, "PLAY SUPER GORILLA!");
			StdDraw.text(1.0, 0.75, "~ Please Enter the Requested Information to Begin ~");
			StdDraw.text(1.0, 0.70, "Note: The Red Arrow on the Next Screen Indicates");
			StdDraw.text(1.0, 0.65, "Relative Wind Speed and Direction");
			StdDraw.picture(1.0, 0.4, "labs/project/gorillaimage.png", 1, 0.5); //Take in gorilla image
			StdDraw.show();
		}
		if (q == 2){ //the end screen 
			StdDraw.clear();
			StdDraw.setPenColor(Color.ORANGE); //draw colored rectangle to serve as backdrop
			StdDraw.filledRectangle(1.0, 0.5, 1.0, 0.5);
			StdDraw.setPenColor(Color.DARK_GRAY);
			StdDraw.text(1.0, 0.8, "THANKS FOR PLAYING!");
			StdDraw.picture(1.0, 0.4, "labs/project/gorillaimage.png", 1, 0.5); //Take in gorilla image
			StdDraw.show();
		}
	}

	/**
	 * When one of the players is hit, a screen displays for 3 seconds telling you which player won
	 * After the three seconds end, the game either restarts or goes to the end screen (depending on how
	 * many total points the game goes to)
	 * @param p1 player 1 (a Gorilla)
	 * @param p2 player 2 (a Gorilla)
	 * @param envy the background Environment 
	 * @param time a double that gives the game animation 
	 * @param s1 name of player 1 (String)
	 * @param s2 name of player 2 (String)
	 * @param score1 score of player 1 (integer)
	 * @param score2 score of player 2 (integer)
	 * @param parameter an integer that keeps track of which player won the game 
	 */
	public static void whoWonGameScreen(Gorilla p1, Gorilla p2, Environment envy, double time, String s1, String s2, int score1, int score2, int parameter){
		if (parameter == 1){
			StdDraw.clear();
			score1++;
			drawItAll(envy, p1, p2, time); //Draw backdrop 
			scoresAndNames(s1, s2, score1, score2, 2); //include player names 
			StdDraw.setPenColor(Color.RED);
			StdDraw.text(1.0, 0.9, "GAME OVER - " + s1 + " WINS"); //Display win state 
																	//reports the player that won
			StdDraw.show(3000); //Pause for three seconds 
		}
		else{
			StdDraw.clear();
			score2++; 
			drawItAll(envy, p1, p2, time);
			scoresAndNames(s1, s2, score1, score2, 1); 
			StdDraw.setPenColor(Color.RED);
			StdDraw.text(1.0, 0.9, "GAME OVER - " + s2 + " WINS"); //Display win state

			StdDraw.show(3000); //Pause for three seconds 
		}
	}


	/**
	 * Draws the backdrop of the game board (players and the environment) to make it easy to redraw them when animating the 
	 * bullet
	 * @param envy the background Environment that draws a backdrop
	 * @param g1 player 1 (a Gorilla)
	 * @param g2 player 2 (a Gorilla)
	 * @param time the double that helps achieve animation  
	 */
	public static void drawItAll(Environment envy, Gorilla g1, Gorilla g2, double time){
		envy.Sketch(time);
		g1.Sketch(time); 
		g2.Sketch(time);
	}

	/**
	 * Determines the position of the Gorilla player using an random variable called chance
	 * @param envy the Environment this takes in (to get building heights, widths, and other info)
	 * @param g1 the first player (a Gorilla)
	 * @param g2 the second player (a Gorilla)
	 * @param parameter the integer that keeps track of which player we're referencing 
	 */
	public static void determinePositions(Environment envy, Gorilla g1, Gorilla g2, int parameter){
		double chance = Math.random(); 

		if (parameter == 1){
			if (chance < 1.0/2.0){ //Spawns players randomly in one of two places 
				g1.setxPos1(envy.buildingWidth[0] + (envy.buildingWidth[1] / 2.0));
				g1.setyBottom(g1.getHeightMan()[1]);
			}
			else{
				g1.setxPos1(envy.buildingWidth[0] + envy.buildingWidth[1] + (envy.buildingWidth[2] / 2.0));
				g1.setyBottom(g1.getHeightMan()[2]); 
			}
		}
		else{
			if (chance < 1.0/2.0){
				g2.setxPos1(2.0 - envy.buildingWidth[9] - (envy.buildingWidth[8] / 2.0));
				g2.setyBottom(g2.getHeightMan()[8]);
			}
			else{
				g2.setxPos1(2.0 - envy.buildingWidth[9] - envy.buildingWidth[8] - (envy.buildingWidth[7] / 2.0));
				g2.setyBottom(g2.getHeightMan()[7]);
			}
		}
	}


	/**
	 * Creates a brand new banana and feeds it information from the user (gravity, velocity, etc.)
	 * @param vel the user imputed projectile velocity (double)
	 * @param gravity the user imputed vertical acceleration on the projectile (double) 
	 * @param angle integer angle the user inputed 
	 * @param p1 player 1 (a Gorilla)
	 * @param p2 player 2 (a Gorilla)
	 * @param wind the double value of the wind (will affect x velocity components) 
	 * @param q an integer--if it is even, player one gets to go and the converse is true for player 2 
	 * @return
	 */
	public static Banana createBanana(int vel, double gravity, int angle, Gorilla p1, Gorilla p2, double wind, int q){
		Banana banana;
		if (q % 2 != 0){
			banana = new Banana(vel, gravity, angle, p1.getxPos1(), 1); //uses the position of a the Gorilla
			banana.setStartHeight1(p1.getyBottom());					//players to create a Banana object
			banana.setWindman(wind);
		}
		else{
			banana = new Banana(vel, gravity, angle, p2.getxPos1(), 2);
			banana.setStartHeight1(p2.getyBottom());
			banana.setWindman(wind);
		}
		return banana;

	}

	/**
	 * Creates an internal for loop that will run when the as long as the prokectile has not collided with a building or another
	 * Gorilla 
	 * @param banana the main projectile of the game (a Banana)
	 * @param one the Environment that serves as a backdrop for the game (houses buildings, etc.)
	 * @param playerOne a Gorilla that represents the first player
	 * @param playerTwo a Gorilla that represents the second player 
	 * @param whoWon an integer that keeps track of which player won (either a 1 or a 2)
	 * @param timey the double that allows for animation by incrementating up and drawing the new position of the projectile
	 * @param playaOneName the name of the first player (String)--inputed by user
	 * @param playaTwoName the name of the second player (String)--inputed by the user 
	 * @param q an integer that keeps track of which player is going based on whether it is even or odd
	 * @param playaOneScore the score of the first player (integer)
	 * @param playaTwoScore the score of the second player (integer)
	 * @return whoWon, an integer that reports which player won the game (eg. either player 1 or player 2)
	 */
	public static int internalLoop(Banana banana, Environment one,  Gorilla playerOne, Gorilla playerTwo, 
			int whoWon, double timey, String playaOneName, 
			String playaTwoName, int q, int playaOneScore, int playaTwoScore){
		while (banana.collisionsAndBounds(one) == true){ //Breaks for loop if one of the players is hit 
			if (q % 2 != 0){							//(after a certain period of time after ball is launched
				if (banana.isGameOver(playerTwo) == true){
					System.out.println("Player 2 hit");
					whoWon = 1;
					return whoWon;
				}
				else if (timey > 0.05){
					if (banana.isGameOver(playerOne) == true){
						whoWon = 2;
						return whoWon;
					}
				}
			}
			else{
				if (banana.isGameOver(playerOne) == true){
					System.out.println("Player 1 hit");
					whoWon = 2;
					return whoWon;
				}
				else if (timey > 0.1){
					if (banana.isGameOver(playerTwo) == true){
						whoWon = 1;
						return whoWon;
					}
				}
			}
			StdDraw.clear();

			//Draw the ball at the given time
			banana.Sketch(timey);

			//UNCOMMENT to Print out important variables to show what's going on internally
			//System.out.println("x: " + banana.getxPos() + ", y: " + banana.getyPos() + ", collisions: " + banana.collisionsAndBounds(one) + ", time: " + timey);

			//Draw everything  
			drawItAll(one, playerOne, playerTwo, timey);
			scoresAndNames(playaOneName, playaTwoName, playaOneScore, playaTwoScore, q);
			StdDraw.show(10); //Show all of the drawings for the given frame

			timey += 0.001; //Add to the time before you repeat; added time must be small to achieve small 
							//on screen progressions
		}
		return whoWon; //reports who won the game in a return statement 
	}

	/**
	 * Makes sure that both negative and positive gravities work; magnifies the size of the gravity for better effect
	 * @param a Banana called banana
	 */
	public static void setGravity(Banana banana){
		//Make sure that gravity functions as it should if user inputs a negative gravity or a positive gravity
		if (banana.getGravity() > 0){
			banana.setGravity(-1*banana.getGravity());
		}
		banana.setGravity(3.2*banana.getGravity()); //Magnify gravity for greater effects 
	}

	/**
	 * Reports the value of a randomly generated "windfactor" that will affect the x velocity of the projectile 
	 * @param one an Environment that houses information about the skyline 
	 * @return the value of the newly generated windfactor (double)
	 */
	public static double setTheWind(Environment one){
		double windFactor = Math.random(); //Creates a randomized value wind factor
		//Randomly generate wind speed & direction for each game--uses randomly generated "windFactor"
		if (windFactor < 0.5){
			one.setWindy((-5*windFactor));
		}
		else{
			one.setWindy((5*windFactor));
		}
		if (windFactor < 0.32){
			return windFactor;
		}
		else{
			return setTheWind(one); //Recursive--the wind can't be too big or else the drawn vector that draws 
									//the wind on the game board will be far too long!
		}
	}
	
	
	
}
