package cityblock;
import cityblock.*;
import java.awt.*;
import java.util.*;

public class SquareShape extends cityblock.RectShape{

	public SquareShape(int hw){
		super(hw, hw);
		defaultColor = Color.red;
	}
	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		SquareShape result = new SquareShape(dimension);
		return result;
	}
	
	
}
