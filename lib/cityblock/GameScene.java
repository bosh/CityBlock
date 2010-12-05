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
	int _lastLevelCompleted = -1;
	int current = 0;
	MenuThing mBack;
	ImageThing mBackground;
	ImageThing mStaging;

	public GameScene(){
		mBackground = new ImageThing("staging.png", 800, 600);
		mStaging = new ImageThing("city1.png", 800, 600);
		_controller = new LevelController();
		mBack = new MenuThing(75, 35, 100, 50, "Back");
	}

	public void setup(){
		int bWidth = Platform.platform.getWidth();
		int bHeight = Platform.platform.getHeight();
		Platform.platform.addThing(mBackground);
		Platform.platform.addThing(mStaging);

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
	
	public int firstLockedLevel(){
		return _lastLevelCompleted + 2; //level 0 is never locked.
	}

	public void addChild(IScene child){
		mChild = child;
	}

	public void finish(){
		if(_currentLevel != null){
			_currentLevel.destroy();
		}
		current = 0;
		Platform.platform.removeThing(mBackground);
		Platform.platform.removeThing(mStaging);
	}

	public void update(){
		if(_currentLevel != null && !_currentLevel.completed) {
			_currentLevel.update();
		} else {
			_lastLevelCompleted++;
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
