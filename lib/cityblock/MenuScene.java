package cityblock;

import java.util.*;
import java.awt.*;
import game.*;

public class MenuScene implements IScene{
	IScene mChild = null;
	IScene mParent = null;
	ArrayList mButtons;
	ImageThing mBackground;
	MenuThing mStart;
	String title = "CITYBLOCK";
	Font mFont;

	public MenuScene(){
		mButtons = new ArrayList();
		mParent = null;
	}

	public void setup(){
		mFont = new Font("Helvetica", Font.PLAIN, 90);
		initializeBackground();
		initializeButtons();
	}

	public void addChild(IScene child){
		mChild = child;
	}

	//This is the simplest implementation of a finish method.
	public void finish(){
		for(int i = 0; i < mButtons.size(); i++){
			Thing t = (Thing)mButtons.get(i);
			Platform.platform.removeThing(t);
		}
		Platform.platform.removeThing(mBackground);
	}

	public void update(){
		//dont have to call update on the things, that's done by the platform
		//TODO: Update shape positions if needed
	}

	public void updateOverlay(Graphics g){
		g.setColor(Color.white);
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing m = (MenuThing)mButtons.get(i);
			m.updateOverlay(g);
		}
		//nothing
	}

	public boolean childReady(){
		//some condition
		return mStart.clicked();
	}

	public IScene getChild(){
		mStart.unClick();
		return mChild;
	}
	
	public void addParent(IScene p){
		mParent = p;
	}

	public IScene getParent(){
		return null;
	}

	public boolean done(){
		return false;
	}

//private

	private void initializeBackground(){
		mBackground = new ImageThing("menubackground.png", Platform.platform.getWidth(), Platform.platform.getHeight());
		Platform.platform.addThing(mBackground);
	}

	private void initializeButtons(){
		Image m = Platform.platform.getImage(Platform.platform.getBase(), "playbutton.png");
		MenuThing start = new MenuThing(Platform.platform.getWidth() / 2.0, 300, 200, 100, m);
		Platform.platform.addThing(start);
		mButtons.add(start);
		mStart = start;
	}
}
