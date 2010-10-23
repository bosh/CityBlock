package cityblock;
import cityblock.*;
import java.awt.*;


public class TriangleShape extends cityblock.Shape{
	//simple at first, just makes right-angle triangles only
	public TriangleShape(int w, int h){
		super(w, h);
		this.setColor(Color.green);
		this.setLineColor(Color.green);
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