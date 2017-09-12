package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Banana implements Drawer{ //Uses the drawer interface 

	//Instance Variables--please see below for variable descriptions 
	private int vel;
	private double gravity; 
	private int angle;
	private double distPlaya; 
	private double xPos;
	private double yPos;
	private int reverseV;
	private double startHeight1;
	private double windman;


	/**
	 * Constructs a new banana (a.k.a. bullet or projectile) given the velocity, angle, x-location of a given
	 * player, and a parameter called reverseV that serves to reverse the x-component of the projectile's
	 * trajectory depending whose turn it is 
	 * @param vel the velocity of the projectile (integer)
	 * @param gravity the value of gravity that will act on the projectile (double)
	 * @param angle the angle at which the projectile is fired (integer)
	 * @param distPlaya the distance to the player (double) from the left side of the window 
	 * @param reverseV: the integer that determines the sign of the x-velocity 
	 * component (if equal to 1, positive; if equal to 2, negative)
	 */
	public Banana(int vel, double gravity, int angle, double distPlaya, int reverseV) {
		this.vel = vel; 
		this.gravity = gravity; 
		this.angle = angle; 
		this.distPlaya = distPlaya; 
		this.xPos = xPos; //the x position of the projectile 
		this.yPos = yPos; //the y position of the projectile 
		this.reverseV = reverseV; 
		this.startHeight1 = startHeight1;
		this.windman = windman; //Wind affect added to x component of velocity
	} 


	//Getters and setters
	/**
	 * Gets the velocity of the projectile
	 * @return
	 */
	public double getVel() {
		return vel;
	}

	/**
	 * Sets the velocity of the projectile 
	 * @param vel
	 */
	public void setVel(int vel) {
		this.vel = vel;
	}

	/**
	 * Gets the gravity that acts on the projectile 
	 * @return
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * Sets the gravity that acts on the projectile 
	 * @param gravity
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	/**
	 * Gets the angle at which the projectile is fired
	 * @return
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * Sets the angle at which the projectile is fired
	 * @param angle
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}

	/**
	 * Gets the x position of the projectile 
	 * @return
	 */
	public double getxPos() {
		return xPos;
	}

	/**
	 * Gets the y position of the projectile 
	 * @return
	 */
	public double getyPos() {
		return yPos;
	}

	/**
	 * Gets the start height of the projectile 
	 * @return
	 */
	public double getStartHeight1() {
		return startHeight1;
	}

	/**
	 * Sets the start height of the projectile
	 * @param startHeight1
	 */
	public void setStartHeight1(double startHeight1) {
		this.startHeight1 = startHeight1;
	}
	
	/**
	 * Gets the value of the wind (windman)
	 * @return
	 */
	public double getWindman() {
		return windman;
	}

	/**
	 * Sets the value of the wind (windman)
	 * @param windman
	 */
	public void setWindman(double windman) {
		this.windman = windman;
	}



	//Other methods
	/**
	 * Uses Sketch interface to draw the banana projectile at a given time 
	 * @param time--animation is achieved through incrementation of this variable 
	 */
	public void Sketch(double time){ 
		double xVel = 0.0;
		double reducedVel = this.getVel() / 10.0; //The velocities that the user inputs through the 
													//ArgsProcessor are too big, so reduce these 

		//Change the x component of the velocity depending on the player that is up
		if (this.reverseV == 1){
			xVel = ((Math.cos(this.getAngle()*(Math.PI/180)))*(reducedVel)) + 2*this.windman; //Multiply by pi/2 because
		}																						//java uses radians 
		else if (this.reverseV == 2){ 
			xVel = ((-1)*(Math.cos(this.getAngle()*(Math.PI/180)))*(reducedVel)) + 2*this.windman;
		}

		double yVel = 2*((Math.sin(this.getAngle()*(Math.PI/180)))*(reducedVel));

		if (this.reverseV == 1) { //Set the position of the projectile 
			xPos = xVel*time + this.distPlaya;
			yPos = ((0.5*this.getGravity())*(Math.pow(time, 2))) + yVel*time + this.getStartHeight1() + 0.1;
		}
		else if (this.reverseV == 2){ 
			xPos = xVel*time + (this.distPlaya);
			yPos = ((0.5*this.getGravity())*(Math.pow(time, 2))) + yVel*time + this.getStartHeight1() + 0.1;
		}

		//Draw the banana using the imported banana image (UNCOMMENT if you want to use this)
		//StdDraw.picture(xPos, yPos, "labs/project/banana.png", 0.1, 0.05);
		
		//Because it produces better frame rates, use a an StdDraw shape
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledEllipse(xPos, yPos, 0.02, 0.01);
	}

	

	/**
	 * Boolean that checks to see if a gorilla was hit by the projectile
	 * @param gorill, a Gorilla (one of the two players) 
	 */
	public boolean isGameOver(Gorilla gorill) { //Check to see if the banana collides with one of the players
		//Right player in x direction
		if (Math.abs(gorill.getxPos1() - this.getxPos()) < 0.02){
			//Right player in y direction
			if (Math.abs((gorill.getyBottom() + 0.05) - this.getyPos()) < 0.05){
				return true;
			}
		}
		return false; //if a player is not hit, the game goes on
	}


	/**
	 * Takes in an Environment and checks to see if the banana projectile will collide with the buildings
	 * or the background
	 * @param envy an environment 
	 */
	public boolean collisionsAndBounds(Environment envy){
		//Check to see if the ball collides with the right side of the frame or the left side of the frame
		if (this.getxPos() < 0.0 || this.getxPos() > 2.0){
			return false;
		}
		
		//Check to see if the ball collides with the buildings -- check each rectangular building individually 
		else{
			double progressX = 0.0;
			for (int i = 0; i < 10; i++){ 
				//Check the x values 
				if (Math.abs((progressX + (envy.getBuildingWidth()[i]) / 2.0) - this.getxPos()) < (envy.getBuildingWidth()[i] / 2.0)){
					//Check the y values
					if (Math.abs((envy.getBuildingHeights()[i] / 2.0) - this.getyPos()) < (envy.getBuildingHeights()[i] / 2.0)){
						return false;
					}
				}
				progressX += envy.getBuildingWidth()[i];
			}
		} 
		return true; //If none of the above conditions are satisfied, nothing was hit
	}

}
