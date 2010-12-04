package cityblock;
import game.*;
import cityblock.*;
import java.awt.*;

public class Button extends StaticRect {
	double width;
	double height;
	Level level;
	String text;
	int type;
	public Button(double x, double y, double w, double h, cityblock.Level level, String txt, int typ){
		super(x, y, w, h);
		this.level = level;
		this.text = txt;
		this.type = typ;
		width = w;
		height = h;
		color = Color.lightGray;
		lineColor = Color.gray;
	}

	public void renderOverlay(Graphics g){
		Font font = new Font("Helvetica", Font.BOLD, 16);
		g.setFont(font);
		g.setColor(Color.darkGray);
		String s = text;
		double xCord = this.getX() - getPlatform().stringWidth(s, g)/2;
		double yCord = this.getY() + 5;// - (double)height/2;
		g.drawString(s, (int)xCord, (int)yCord );
	}

	public boolean mouseDown(int x, int y) {
		System.out.println("click");
		level.submit(this.type);
		setColor(Color.gray);
		return false;
	}

	public boolean mouseUp(int x, int y){
		System.out.println("click-up");		
		setColor(Color.lightGray);
		return false;
	}
}
