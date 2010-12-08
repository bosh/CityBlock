import game.*;
import java.awt.*;
import cityblock.*;
import java.util.*;
public class CityBlockGame extends Platform {

	SceneManager mManager;

	public void setup(){
		//_controller = new LevelController();
		Platform.platform = this;
		//this stuff is just setting up the staging area on the right side of the screen.

		//heirarchy => menu::world_selector::[level-selectors]::gameScene
		try {
			IScene menu = new MenuScene();
			GameScene game = new GameScene();
			IScene world1 = new SelectorScene("world1", "world/world1.spec", game);
			ArrayList worlds = new ArrayList();
			worlds.add(world1);

			SelectorScene worldSelect = new SelectorScene("world_selector", "world/world_select.spec", worlds);
			menu.addChild(worldSelect);

			game.addParent(world1);
			world1.addParent(worldSelect);
			worldSelect.addParent(menu);

			mManager = new SceneManager(menu);			
		} catch ( Exception e ) {
			System.out.println(e);
		}

	}
	
	public void update(){
		IScene current = mManager.getCurrentScene();
		current.update();
	}

	public void overlay(Graphics g) {
		mManager.getCurrentScene().updateOverlay(g);
	}
}