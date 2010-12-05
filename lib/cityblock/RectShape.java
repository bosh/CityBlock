package cityblock;

import java.awt.*;
import game.*;
import java.util.*;

public class RectShape extends cityblock.Shape{
	public static Image[] rotatedImages = new Image[] {
		Platform.platform.getImage(Platform.platform.getBase(), "shapes/rectangle-up.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "shapes/rectangle-right.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "shapes/rectangle-down.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "shapes/rectangle-left.png")
	};

	public RectShape(int w, int h, Color color){
		super(w, h, color);
	}

	public RectShape(int w, int h){
		this(w, h, Color.blue);
		name = "rectangle";
	}

	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		int dimension2 = dimension;
		while(dimension == dimension2) dimension2 = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		RectShape result = new RectShape(dimension, dimension2);
		return result;
	}

	public Image getRotatedImage(){
		return rotatedImages[(int)(rotation/90)];
	}

	public void setup(int x, int y){
		this.x = x + width / 2;
		this.y = y + height / 2;
		X[0] = x;
		Y[0] = y;
		X[1] = x + width;
		Y[1] = y;
		X[2] = x + width;
		Y[2] = y + height;
		X[3] = x;
		Y[3] = y + height;
		setShape(X, Y, 4);
	}
}
