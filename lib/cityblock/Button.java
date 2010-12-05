package cityblock;
import game.*;
import cityblock.*;
import java.awt.*;

public class Button extends MenuThing {
	double width;
	double height;
	Level level;
	int type;

	public Button(double x, double y, double w, double h, cityblock.Level level, Image img, int typ){
		super(x, y, w, h, img);

		this.level = level;
		this.type = typ;
		width = w;
		height = h;
	}

	public void renderOverlay(Graphics g){
		// no more text!
	}

	public boolean mouseDown(int x, int y) {
		System.out.println("click on " + text );
		level.submit(this.type);
		return false;
	}

	public boolean mouseUp(int x, int y){

		System.out.println("click-up");
		return false;
	}
}
