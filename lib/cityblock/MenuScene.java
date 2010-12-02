package cityblock;

import game.*;


public class MenuScene implements IScene{
	IScene mChild = null;
	IScene mParent = null;
	ArrayList mButtons;
	Image mBackground;
	
	public MenuScene(IScene daddy){
		mButtons = new ArrayList();
		mParent = daddy;
		initializeBackground();
		initializeButtons();
	}
	
	
	public void addChild(IScene child){
		mChild = child;
	}
	
	
	//This is the simplest implementation of a finish method.
	public void finish(){
		for(int i = 0; i < mButtons.size(); i++){
			Thing t = (Thing)mMyThings.get(i);
			Platform.platform.removeThing(t);
		}
		Platform.platform.removeThing(mBackground);
	}
	public void update(){
		//dont have to call update on the things, that's done by the platform
		//TODO: Update shape positions if needed
	}
	public void updateOverlay(Graphics g){
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing m = (MenuThing)mButtons.get(i);
			m.updateOverlay(g);
		}
		//nothing
	}
	public boolean childReady(){
		//some condition
		return false;
	}
	public IScene getChild(){
		return mChild;
	}
	public IScene getParent(){
		return mParent;
	}
	public boolean done(){
		//some condition
		return false;
	}
	
	
//private

	private void initializeBackground(){
		mBackground = new ImageThing("menubackground.png", Platform.platform.getWidth(), Platform.platform.getHeight());
		Platform.platform.addThing(mBackground);
	}
	
	private void initializeButtons(){
		MenuThing start = new MenuThing(Platform.platform.getWidth() / 2.0, 10, "Start!");
		Platform.platform.addThing(start);
		mButtons.add(start);
	}
}



