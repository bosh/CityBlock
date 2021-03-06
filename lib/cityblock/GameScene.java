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
	MenuThing mEndWorld;
	boolean onEndScreen;
	MenuThing tutorialButton;
	TutorialScreen tutorials;

	public GameScene(){
		mBackground = new ImageThing("staging.png", 800, 600);
		mStaging = new ImageThing("city1.png", 800, 600);
		_controller = new LevelController();
		mEndWorld = new MenuThing(400, 300, 800, 600, Platform.platform.getImage(Platform.platform.getBase(), "end_world.png"));
		mBack = new MenuThing(50, 30, 100, 50, Platform.platform.getImage(Platform.platform.getBase(), "BackButton.png") );
		tutorialButton = new MenuThing(782, 20, 20, 20, Platform.platform.getImage(Platform.platform.getBase(), "question.png") );
		tutorials = new TutorialScreen();
	}

	public void setup(){
		int bWidth = Platform.platform.getWidth();
		int bHeight = Platform.platform.getHeight();
		Platform.platform.addThing(mBackground);
		Platform.platform.addThing(mStaging);
		Platform.platform.addThing(tutorialButton);
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
		Platform.platform.removeThing(tutorialButton);
		Platform.platform.removeThing(mBack);
		Platform.platform.removeThing(mEndWorld);
	}

	public void update(){
		SoundController.active.housekeeping();		
		if(tutorialButton.clicked()){
			tutorials.start();
			tutorialButton.unClick();
			return;
		}
		if(tutorials.active){
			tutorials.update();
			return;
		}
		if(_currentLevel != null && !_currentLevel.completed) {
			_currentLevel.update();
		} else { // TODO: only increment this if the level number of the last completed is high than it previously was
			_lastLevelCompleted++;
			if(_lastLevelCompleted >= _controller.getNumberOfLevels() - 1){
				if(!onEndScreen){
					Platform.platform.addThing(mEndWorld);
					onEndScreen = true;
				}
				
			}else{
				nextLevel();
				_currentLevel.start(Platform.platform);
			}
		}
	}

	public void updateOverlay(Graphics g){
		if(!tutorials.active && !onEndScreen){
			mBack.updateOverlay(g);
			_currentLevel.renderOverlay(g);
		}
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
		if(mBack.clicked() || mEndWorld.clicked()){
			mBack.unClick();
			mEndWorld.unClick();
			return true;
		}
		return false;
	}
}
