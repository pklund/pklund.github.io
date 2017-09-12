package project;

//Defines a java interface that groups all elements that draw using Sedgwick's drawing API
//all objects that accept this interface must use the below sketch method that takes in a time (this
//time makes animation possible)
public interface Drawer {
	public void Sketch(double time);
}
