package cityblock;

import game.*;



public class MenuThing extends StaticRect{
	
	Color mTextColor = Color.darkBlue; // change me!!
	int mFontSize = 26;
	double mWidth = 300;
	double mHeight = 150;
	boolean mClicked = false;
	String mText;
	
	public MenuThing(double centerX, double centerY, String text){
		super(x - mWidth/2.0, y - mHeight/2.0, mWidth, mHeight);
		this.setColor(Color.gray);
		mText = text;
	}
	public setText(String t){
		mText = t;
	}
	
	public updateOverlay(Graphics g){
		Font oldFont = g.getFont();
		Font font = new Font("Helvetica", mFontSize);
		Color oldColor = g.getColor();
		g.setColor(mTextcolor);
		g.setFont(font);
		g.drawString(mText, textX(g), textY(g));
	}
	
	public boolean mouseDown(){
		mClicked = true;
		super.mouseDown();
	}
	
	public boolean clicked(){
		return mClicked;
	}
	public void unClick(){
		mClicked = false;
	}
	
	private int textX(Graphics g){
		return (int)(this.getX() - Platform.getStringWidth(mText, g) / 2.0);
	}
	private int textY(Graphics g){
		return (int)(this.getY() - mFontSize / 2.0);
	}
	
	
	
	
	
	
}