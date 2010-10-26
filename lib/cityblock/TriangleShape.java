package cityblock;
import cityblock.*;
import java.awt.*;
import java.util.*;

public class TriangleShape extends cityblock.Shape{
	//simple at first, just makes right-angle triangles only
	public TriangleShape(int w, int h){
		super(w, h);
		this.setColor(Color.green);
		this.setLineColor(Color.green);
	}
	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		int dimension2 = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		TriangleShape result = new TriangleShape(dimension, dimension2);
		return result;
	}
	public double getArea(){
		return this.getGameHeight()*((double)this.getGameWidth()*0.5);
	}
	
	public void setup(int x, int y){
		X[0] = x;
		Y[0] = y;
		Y[1] = y + this.height;
		X[1] = x;
		X[2] = x + this.width;
		Y[2] = y + this.height;
		//this should make a triangle
		setShape(X, Y, 3);
	}
	
	
}