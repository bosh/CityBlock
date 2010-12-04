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

	public GameScene(IScene daddy){
		mDaddy = daddy;
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

		//ths is setting up the first level, but hacked at the moment for testing
		_controller = new LevelController();
		//_currentLevel = _controller.getLevel(1);
		nextLevel();
	}

	public void nextLevel(){
		if(_currentLevel != null){
			_currentLevel.destroy();
		}
		_currentLevel = _controller.getLevel(current);
		current++;
		_currentLevel.start(Platform.platform);
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
		}
		SoundController.active.housekeeping();
	}

	public void updateOverlay(Graphics g){
		_currentLevel.renderOverlay(g);
	}

	public boolean childReady(){
		return false;
	}

	public IScene getChild(){
		return null;
	}

	public IScene getParent(){
		return mDaddy;
	}

	public boolean done(){
		return false;
	}
}
