package cityblock;

import cityblock.*;
import game.*;
import java.awt.*;
import java.util.*;

public class SquareShape extends cityblock.RectShape{
	public SquareShape(int hw){
		super(hw, hw, Color.red);
		name = "square";
		this.rotatedImages = new String[] {"square-up.png", "square-right.png", "square-down.png", "square-left.png"};
	}

	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		SquareShape result = new SquareShape(dimension);
		return result;
	}
}
