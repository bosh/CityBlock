package cityblock;

import game.*;
import java.awt.*;

public class MenuThing extends StaticRect{
	Color mTextColor = Color.blue; // change me!!
	int mFontSize = 40;
	public static double WIDTH = 150;
	public static double HEIGHT = 70;
	boolean mClicked = false;
	String mText;

	public MenuThing(double centerX, double centerY, String text){
		super(centerX - MenuThing.WIDTH/2.0, centerY - MenuThing.HEIGHT/2.0, MenuThing.WIDTH, MenuThing.HEIGHT);
		this.setColor(Color.gray);
		this.setLineColor(Color.gray);
		mText = text;
	}

	public void setText(String t){
		mText = t;
	}

	public void updateOverlay(Graphics g){
		//System.out.println("overlay");
		Font font = new Font("Helvetica", Font.PLAIN, mFontSize);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(mText, textX(g), textY(g));
	}

	public boolean mouseDown(int x, int y){
		mClicked = true;
		return super.mouseDown(x, y);
	}

	public boolean clicked(){
		return mClicked;
	}

	public void unClick(){
		mClicked = false;
	}

	private int textX(Graphics g){
		return (int)(this.getX() - (Platform.getStringWidth(mText, g) / 2.0));
	}

	private int textY(Graphics g){
		return (int)(this.getY()+ mFontSize/2.0 - 5);
	}
}
