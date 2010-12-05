package cityblock;


public class TutorialScene{
	
	public static boolean active;
	MenuThing square;
	MenuThing rectangle;
	MenuThing triangle;
	
	public TutorialScreen(){
		ImageThing background = new ImageThing("WhiteBox.png", 800, 600, 0, 0);
		double startX = 800.0 / 2;
		double startY = 600.0 / 2 - 300;
		
		square = new MenuThing(startX, startY, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Square.png"));
		rectangle = new MenuThing(startX, startY + 300, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Rectangle.png"));
		triangle = new MenuThing(startX, startY + 600, 250, 100, Platform.platform.getImage(Platform.platform.getBase(), "Tutorial_Triangle.png"));
		//2 things: tutorial menu
		// 					+ scrren for each tutorial
	}
	
	public void Start(){	
		active = true;
	}
	
	
	public void Update(){
		
	}
	
	public void Stop(){
		active = false;
	}
	
	
	
	
	
}