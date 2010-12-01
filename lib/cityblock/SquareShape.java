package cityblock;
import cityblock.*;
import game.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class SquareShape extends cityblock.RectShape implements ImageObserver{
	public Image img;

	public SquareShape(int hw){
		super(hw, hw);
		img = Platform.platform.getImage(Platform.platform.getCodeBase(), "square.jpg");
		defaultColor = Color.red;
	}

	public void update(Graphics g) {
		super.update(g);
		g.drawImage(img, (int) getX() - width/2, (int) getY() - height/2, width, height, this);
	}

	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		SquareShape result = new SquareShape(dimension);
		return result;
	}

	public boolean imageUpdate(Image img, int x, int y, int width, int height, int fudge){
		return true;
	}
}
