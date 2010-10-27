import game.*;
import java.awt.*;
import cityblock.*;
public class CityBlockGame extends Platform {
	Level _currentLevel = null;
	boolean _showMenu;
	LevelController _controller;
	LevelSpec[] _levelSpecs;
	int current = 0;
	
	public void setup(){
		//_controller = new LevelController();
		
		//this stuff is just setting up the staging area on the right side of the screen.
		int bWidth = this.getWidth();
		int bHeight = this.getHeight();
		
		playArea = new StaticRect(0, 0, (2*bWidth)/3, bHeight);
		playArea.setColor(Color.white);
		playArea.setLineColor(Color.white);
		addThing(playArea);

		stagingArea = new StaticRect(bWidth - bWidth/3, 0, bWidth/3, bHeight);
		stagingArea.setColor(new Color(240,255,240));
		stagingArea.setLineColor(Color.darkGray);
		addThing(stagingArea);

		//ths is setting up the first level, but hacked at the moment for testing
		_controller = new LevelController();
		//_currentLevel = _controller.getLevel(1);
		nextLevel();
	}
	
	public void update(){
		if(!_currentLevel.completed)
			{_currentLevel.update();}
		else{
			nextLevel();
		}
		
	}
	
	public void makeLevelSpecs(){

	}
	public void nextLevel(){
		if(_currentLevel != null){
			_currentLevel.destroy();
		}
		_currentLevel = _controller.getLevel(current);
		current++;
		_currentLevel.start(this);
	}

	public void overlay(Graphics g) {
		_currentLevel.renderOverlay(g);
	}

	private Level generateFakeLevel(){
		cityblock.Shape[] shapes = new cityblock.Shape[3];
		shapes[0] = new RectShape(30, 20);
		shapes[1] = new TriangleShape(50, 70);
		shapes[2] = new SquareShape(100);
		Level result = new Level(shapes);
		result.targetArea = 6;
		
		
		return result;
	}

}