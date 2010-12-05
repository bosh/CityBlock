package cityblock;

import game.*;
import java.awt.*;
import java.awt.image.*;

public class MenuThing extends StaticRect implements ImageObserver{
	Color mTextColor = Color.blue; // change me!!
	int mFontSize = 40;
	boolean mClicked = false;
	String mText;
	Image mImage;
	double mWidth, mHeight, mTopX, mTopY;

	private MenuThing(double centerX, double centerY, double width, double height){
		super(centerX - width/2.0, centerY - height/2.0, width, height);
		mTopX = centerX - width/2.0;
		mTopY = centerY - height/2.0;
		mWidth = width;
		mHeight = height;
		this.setColor(Color.gray);
		this.setLineColor(Color.gray);		
	}
	public MenuThing(double cX, double cY, double w, double h, String t){
		this(cX, cY, w, h);
		mText = t;
	}

	public MenuThing(double centerX, double centerY, String text){
		this(centerX, centerY, 150, 70, text);
		mText = text;
	}

	
	public MenuThing(double centerX, double centerY, Image image){
		this(centerX, centerY, 150, 150);
		this.mImage = image;
		this.mPolygon = false;
	}

	public void setText(String t){
		mText = t;
	}
	
	public void update(Graphics g){
		g.drawImage(mImage, (int)mTopX, (int)mTopY, (int)mWidth, (int)mHeight, this);
		super.update(g);
	}

	public void updateOverlay(Graphics g){
		if(mText == null) return;
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
	
	public boolean imageUpdate(Image img, int a, int b, int c, int d, int e){
		return true;
	}
}
