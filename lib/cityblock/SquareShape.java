package cityblock;

import cityblock.*;
import game.*;
import java.awt.*;
import java.util.*;

public class SquareShape extends cityblock.RectShape{
	public static Image[] rotatedImages = new Image[] {
		Platform.platform.getImage(Platform.platform.getBase(), "square-up.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "square-right.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "square-down.png"),
		Platform.platform.getImage(Platform.platform.getBase(), "square-left.png")
	};

	public SquareShape(int hw){
		super(hw, hw, Color.red);
		name = "square";
	}

	public Image getRotatedImage(){
		return rotatedImages[(int)(rotation/90)];
	}

	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		SquareShape result = new SquareShape(dimension);
		return result;
	}
}
