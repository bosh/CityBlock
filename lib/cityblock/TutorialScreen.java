package cityblock;
import game.*;
import java.awt.*;

public class TutorialScreen{
	
	public static boolean active;
	MenuThing square;
	MenuThing rectangle;
	MenuThing triangle;
	MenuThing activeTutorial = null;
	MenuThing stop;
	ImageThing banner;
	ImageThing background;
	
	public TutorialScreen(){
		background = new ImageThing("WhiteBox.png", 800, 600, 0, 0);
		double startX = 800.0 / 2;
		double startY = 200;
		banner = new ImageThing("tutorials/banner.png", 500, 100, 150, 50);
		square = new MenuThing(startX, startY, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Square.png"));
		rectangle = new MenuThing(startX, startY + 110, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Rectangle.png"));
		triangle = new MenuThing(startX, startY + 220, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Triangle.png"));
		stop = new MenuThing(startX, startY + 330, 150, 75, Platform.platform.getImage(Platform.platform.getBase(), "BackButton.png"));
		//2 things: tutorial menu
		// 					+ scrren for each tutorial
	}
	
	public void start(){	
		active = true;
		Platform.platform.addThing(background);
		Platform.platform.addThing(square);
		Platform.platform.addThing(rectangle);
		Platform.platform.addThing(triangle);
		Platform.platform.addThing(banner);
		Platform.platform.addThing(stop);
	}
	
	
	public void update(){
		if(activeTutorial != null){
			if(activeTutorial.clicked()){
				Platform.platform.removeThing(activeTutorial);
				activeTutorial.unClick();
				activeTutorial = null;
			}
		}
		else if(square.clicked()){
			System.out.println("square clicked");
			activeTutorial = new MenuThing(400, 300, 800, 600, Platform.platform.getImage(Platform.platform.getBase(), "tutorials/square.png"));
			Platform.platform.addThing(activeTutorial);
			square.unClick();
		}
		else if(rectangle.clicked()){
						System.out.println("square clicked");
			activeTutorial = new MenuThing(400, 300, 800, 600, Platform.platform.getImage(Platform.platform.getBase(), "tutorials/rectangle.png"));
			Platform.platform.addThing(activeTutorial);
			rectangle.unClick();
		}
		else if(triangle.clicked()){
			activeTutorial = new MenuThing(400, 300, 800, 600, Platform.platform.getImage(Platform.platform.getBase(), "tutorials/triangle.png"));
			Platform.platform.addThing(activeTutorial);
			triangle.unClick();
		}
		else if (stop.clicked()){
			stop.unClick();
			this.stop();
		}
	}
	
	public void stop(){
		active = false;
		Platform.platform.removeThing(background);
		Platform.platform.removeThing(square);
		Platform.platform.removeThing(rectangle);
		Platform.platform.removeThing(triangle);
		Platform.platform.removeThing(banner);
		Platform.platform.removeThing(stop);
	}
	
	
	
	
	
}