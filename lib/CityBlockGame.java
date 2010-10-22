import game.*;
import java.awt.*;
import cityblock.*;
public class CityBlockGame extends Platform{
	Level _currentLevel;
	boolean _showMenu;
	LevelController _controller;
	
	public void setup(){
		//_controller = new LevelController();
		
		//this stuff is just setting up the staging area on the right side of the screen.
		int bWidth = this.getWidth();
		int bHeight = this.getHeight();
		StaticRect backgroundPanel = new StaticRect(bWidth - bWidth/3, 0, bWidth/3, bHeight);
		backgroundPanel.setColor(new Color(240,255,240));
		backgroundPanel.setLineColor(Color.darkGray);

		addThing(backgroundPanel);
		
		
		//ths is setting up the first level, but hacked at the moment for testing
	
		//_currentLevel = _controller.getLevel(1);
		_currentLevel = generateFakeLevel();
		_currentLevel.start(this);
	}
	public void update(){
		_currentLevel.render();
		
		
		
	}

   public void overlay(Graphics g) {   
		_currentLevel.renderOverlay(g);
   }

	private Level generateFakeLevel(){
		Level result = new Level();
		result.shapes = new cityblock.Shape[1];
		result.shapes[0] = new RectShape(30, 20);
		result.targetArea = 6;
		
		
		return result;
	}

	
}