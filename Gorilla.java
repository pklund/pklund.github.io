package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Gorilla implements Drawer{ //Uses the Drawer interface 

	private double xPos1 = 0.0; //Set the x position of the player initially equal to zero
	private double[] heightMan = new double[10]; //Array that keeps track of building heights 
	private double yBottom = 0.0; //Originally set the y position of the player to zero 
	int number; //Depending what this is, the gorilla will spawn on a different half of the screen

	/**
	 * Constructs a Gorilla--either player 1 or player 2--depending on an integer, number
	 * @param number an integer that helps determine which player is going 
	 */
	public Gorilla(int number) {
		this.number = number;
		this.heightMan = heightMan;
		this.yBottom = yBottom;
		this.xPos1 = xPos1;
	}



	//Getters and Setters 
	/**
	 * Gets the values of double array heightMan
	 * @return
	 */
	public double[] getHeightMan() {
		return heightMan;
	}

	/**
	 * Sets the values of double array heightMan
	 * @param heightMan
	 */
	public void setHeightMan(double[] heightMan) {
		this.heightMan = heightMan;
	}

	/**
	 * Gets the x position of a player (from the left side of the screen) 
	 * @return
	 */
	public double getxPos1() {
		return xPos1;
	}

	/**
	 * Sets the x position of a player 
	 * @param xPos1
	 */
	public void setxPos1(double xPos1) {
		this.xPos1 = xPos1;
	}

	/**
	 * Gets the value of yBottom--the height at which a player sits
	 * @return
	 */
	public double getyBottom() {
		return yBottom;
	}

	/**
	 * Sets the value of yBottom
	 * @param yBottom
	 */
	public void setyBottom(double yBottom) {
		this.yBottom = yBottom;
	}

	/**
	 * Gets the integer number (that corresponds to what player is being dealt with)
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the integer number 
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}


	/**
	 * Uses the Sketch interface to draw a Gorilla (one of the players in the game)
	 */
	public void Sketch(double time){
		if (this.number == 1){ //Depending on the number of the player, the color will be different 
			StdDraw.setPenColor(Color.BLUE);
		}
		else{
			StdDraw.setPenColor(Color.RED);
		}

		StdDraw.filledRectangle(xPos1, yBottom + 0.05, 0.02, 0.05); //Draw the gorilla as simple rectangle
	}

}
