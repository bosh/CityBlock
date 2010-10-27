package cityblock;
import cityblock.*;
import game.*;
import java.awt.*;

public class Level {
	public Shape[] shapes;
	public double targetArea;
	public Goal[] goals;
	public int id;
	public double lastTime = 0.0;
	public String description;
	public Tutorial tutorial;
	public StaticRect buildingBase;
	public Platform platform;
	
	
	public void start(Platform p){
		buildingBase = new StaticRect(p.getWidth()/3 - 150, p.getHeight() - 30, 300, 60 );
		buildingBase.setColor(java.awt.Color.gray);
		p.addThing(buildingBase);
		platform = p;
		
		for(int i = 0; i < this.shapes.length; i++){
			Shape s = shapes[i];
			s.setup(p.getWidth() - p.getWidth()/3 + 10, 10 + 30*i);
			p.addThing(shapes[i]);
		}
	}
	
	public Level(Shape[] shapes){
		this.shapes = shapes;
		for(int i = 0; i < shapes.length; i++) {
			shapes[i].setLevel(this);
		}
	}

	public void update(){
		if(lastTime !=getCurrentArea()){
		System.out.println("current area :" + getCurrentArea() + " target: " + targetArea);
		lastTime = getCurrentArea();
		}
	}
	public void renderOverlay(java.awt.Graphics g){
		g.setColor(new Color(85, 85, 85));
		g.drawString("Target Area: " + targetArea,platform.getWidth()*2/3 + 20 , platform.getHeight() - 20);
		//1. Render the overlay for each object
		//2. Display the target area
		//3. Display the level number
		for(int i = 0; i < shapes.length; i++){
			Shape s = shapes[i];
			s.renderOverlay(g);
		}
		
		
		//renders tutorial text until its finished
		//renders numbers next to the shapes
	}
	
	public int startTime;
	public int stackedBlocks(){return 0;}
	public double getCurrentArea(){
		double result = 0.0;
		for(int i = 0; i < shapes.length; i++){
			if(shapes[i].isInPlayArea()) result += shapes[i].getArea();
		}
		
		return result;
	}
	public int getElapsedTime(){return 0;}
	public boolean submit(){return false;}
	
}
