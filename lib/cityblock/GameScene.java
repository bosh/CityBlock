package cityblock;

import java.awt.*;
import game.*;

public class GameScene implements IScene{
	IScene mChild;
	IScene mDaddy;
	Level _currentLevel = null;
	boolean _showMenu;
	LevelController _controller;
	LevelSpec[] _levelSpecs;
	int current = 0;
	MenuThing mBack;

	public GameScene(){
		_controller = new LevelController();
		mBack = new MenuThing(75, 35, 100, 50, "Back");
	}

	public void setup(){
		int bWidth = Platform.platform.getWidth();
		int bHeight = Platform.platform.getHeight();

		Platform.platform.playArea = new StaticRect(0, 0, (2*bWidth)/3, bHeight);
		StaticRect playArea = Platform.platform.playArea;
		playArea.setColor(Color.white);
		playArea.setLineColor(Color.white);
		Platform.platform.addThing(playArea);

		Platform.platform.stagingArea = new StaticRect(bWidth - bWidth/3, 0, bWidth/3, bHeight);
		StaticRect stagingArea = Platform.platform.stagingArea;
		stagingArea.setColor(new Color(240,255,240));
		stagingArea.setLineColor(Color.darkGray);
		Platform.platform.addThing(stagingArea);
		

		Platform.platform.addThing(mBack);

		//_currentLevel = _controller.getLevel(1);
		if(_currentLevel == null) nextLevel();
		_currentLevel.start(Platform.platform);
	}

	public void nextLevel(){
		loadLevel(current);
	}
	
	public void loadLevel(int num){
		System.out.println("GameScene, Loading level: " + num);
		if(_currentLevel != null){
			_currentLevel.destroy();
		}
		_currentLevel = _controller.getLevel(num);
		current = num + 1;
	}

	public void addChild(IScene child){
		mChild = child;
	}

	public void finish(){
	}

	public void update(){
		if(!_currentLevel.completed) {
			_currentLevel.update();
		} else {
			nextLevel();
			_currentLevel.start(Platform.platform);
		}
		SoundController.active.housekeeping();
	}

	public void updateOverlay(Graphics g){
		mBack.updateOverlay(g);
		_currentLevel.renderOverlay(g);
	}

	public boolean childReady(){
		return false;
	}

	public IScene getChild(){
		return null;
	}

	public void addParent(IScene p){
		mDaddy = p;
	}

	public IScene getParent(){
		return mDaddy;
	}

	public boolean done(){
		if(mBack.clicked()){
			mBack.unClick();
			return true;
		}
		return false;
	}
}
