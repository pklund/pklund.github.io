package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Environment implements Drawer{ //Uses the Drawer interface 

	public double[] buildingWidth = new double[10]; //Keeps track of the width of buildings in the skyline
	private double windy; //Keeps track of the double value of the wind 
	private double[] buildingHeights = new double[10]; //Keeps track of thw heights of the buildings in the skyline

	/**
	 * Constructs an Environment to take advantage of Environment methods (found below)
	 */
	public Environment() {
		//This is only used to access the below methods, so no code is needed here
	}

	
	
	//Getters and Setters 
	/**
	 * Gets the wind value that is used to draw the wind vector
	 * @return
	 */
	public double getWindy() {
		return windy;
	}

	/**
	 * Sets the wind value
	 * @param windy
	 */
	public void setWindy(double windy) {
		this.windy = windy;
	}

	/**
	 * Gets the array buildingHeights that is used to tell other objects about the building heights
	 * @return
	 */
	public double[] getBuildingHeights() {
		return buildingHeights;
	}

	/**
	 * Sets the array buildingHeights 
	 * @param buildingHeights
	 */
	public void setBuildingHeights(double[] buildingHeights) {
		this.buildingHeights = buildingHeights;
	}

	/**
	 * Gets array buildingWidth that is used to tell other objects about the widths of the buildings 
	 * @return
	 */
	public double[] getBuildingWidth() {
		return buildingWidth;
	}

	/**
	 * Sets the buildingWidth array 
	 * @param buildingWidth
	 */
	public void setBuildingWidth(double[] buildingWidth) {
		this.buildingWidth = buildingWidth;
	}




	/**
	 * Randomly generates a skyline; the height of each building is stored in an array which is 
	 * returned by this method
	 * @return an array of building heights called heightSto
	 */
	public double[] builder(){
		StdDraw.setXscale(0.0, 2.0); //Sets the appropriate scale for drawing 
		StdDraw.setYscale(0.0, 1.0);

		double[] heightSto = new double[10];

		for (int i = 0; i < 10; i++){ //Chooses building heights from a number of possible heights 
			double xHeight = 0.0;		//(because the height differences can't be too large)
			double chance = Math.random();
			if (chance < 1.0/7.0){
				xHeight = 0.6;
			}
			else if (chance < 2.0/7.0){
				xHeight = 0.65;
			}
			else if (chance < 3.0/7.0){
				xHeight = 0.7;
			}
			else if (chance < 4.0/7.0){
				xHeight = 0.55;
			}
			else if (chance < 5.0/7.0){
				xHeight = 0.5;
			}
			else if (chance < 6.0/7.0){
				xHeight = 0.45;
			}
			else{
				xHeight = 0.75;
			}

			heightSto[i] = xHeight; //Stores these height values in an array

		}
		return heightSto;
	}


	/**
	 * Part 2 of random skyline generation; the width of each building is selected and
	 * stored in an array 
	 * @return an array called lengthSto that holds building widths 
	 */
	public double[] builderX(){
		double remainingLength = 2.0;
		double[] lengthSto = new double[10];
		
		for (int j = 0; j < 10; j++){ //The widths are selected from a number of possibilities 
			double xLength = 0.0;
			double random = Math.random();

			if (random < 1.0/6.0){
				xLength = 0.25;
			}
			else if (random < 2.0/6.0){
				xLength = 0.35;
			}
			else if (random < 3.0/6.0){
				xLength = 0.45;
			}
			else if (random < 4.0/6.0){
				xLength = 0.2;
			}
			else if (random < 5.0/6.0){
				xLength = 0.15;
			}
			else{
				xLength = 0.1;
			}

			lengthSto[j] = xLength; //the widths are stored in an array
			remainingLength -= xLength;
		}
		if (remainingLength == 0.0){ 
			return lengthSto;		
		}
		else{
			return builderX();	//if the 10 widths don't sum to 2.0 (the length of the screen),
		}						//try again using recursion 

	}


	/**
	 * Sketches the randomly generated skyline (using the methods that create widths and heights above)
	 * This uses the sketch interface 
	 */
	public void Sketch(double time){
		//Set the correct drawing scale
		StdDraw.setXscale(0.0, 2.0);
		StdDraw.setYscale(0.0, 1.0);

		//Draw a nice backdrop -- a light blue background with a cloud in the sky
		Color lightBlue = new Color(0,0,182,155); //Creates custom light blue color 
		StdDraw.setPenColor(lightBlue); //Create the sky--a light blue backdrop
		StdDraw.filledRectangle(1.0, 0.5, 1.5, 1.0);
		StdDraw.picture(1.8, 0.9, "labs/project/images.png", 0.3, 0.1); //import cloud image 
		
		//Draw the skyscrapers using the arrays generated above 
		double distSoFar = 0.0;
		for (int i = 0; i < 10; i++){
			if (i % 2 == 0){ //Alternate color of the buildings for visual appeal
				StdDraw.setPenColor(Color.GRAY);
			}
			else{
				StdDraw.setPenColor(Color.DARK_GRAY);
			}
			//Draw the skyscrapers using height and width arrays!
			StdDraw.filledRectangle(distSoFar + this.buildingWidth[i]/2.0, this.buildingHeights[i]/2.0, this.buildingWidth[i]/2.0, this.buildingHeights[i]/2.0);
			distSoFar += this.buildingWidth[i];
		}
		
		//Sketch the wind vector over the skyline
		if (this.windy > 0){ //changes based on the sign of windy--can either point in the plus or minus x direction 
			double distance = (2.0 - this.windy - 0.05) / 2.0;
			double[] jimX = {distance, distance + 0.05, distance + 0.05}; //These two arrows are for the triangle
			double[] jimY = {0.15, 0.15 - 0.025, 0.15 + 0.025};			//tip of the wind vector 
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledPolygon(jimX, jimY);
			StdDraw.setPenRadius(0.02); //Make the pen radius thicker 
			StdDraw.line(distance + 0.05, 0.15, distance + 0.05 + this.windy, 0.15);
		}
		else if (windy < 0){
			double distance = (2.0 + this.windy - 0.05) / 2.0;
			double[] seanX = {2.0 - distance, 2.0 - distance - 0.05, 2.0 - distance - 0.05};
			double[] seanY = {0.15, 0.15 - 0.025, 0.15 + 0.025};
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledPolygon(seanX, seanY);
			StdDraw.setPenRadius(0.02);
			StdDraw.line(2.0 - distance - 0.05, 0.15, distance, 0.15);
		}
	}
}
