import game.*;
import java.awt.*;
import cityblock.*;
public class CityBlockGame extends Platform {
	
	SceneManager mManager;
	
	public void setup(){
		//_controller = new LevelController();
		Platform.platform = this;
		//this stuff is just setting up the staging area on the right side of the screen.
		IScene menu = new MenuScene();
		IScene game = new GameScene(menu);
		menu.addChild(game);
		mManager = new SceneManager(menu);
	}
	
	public void update(){
		IScene current = mManager.getCurrentScene();
		current.update();
	}
	

	public void overlay(Graphics g) {
		mManager.getCurrentScene().updateOverlay(g);
	}
}