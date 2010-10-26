package cityblock;
import cityblock.*;
import game.*;

public class Level {
	public Shape[] shapes;
	public int targetArea;
	public Goal[] goals;
	public int id;
	public String description;
	public Tutorial tutorial;
	public StaticRect buildingBase;
	
	public void start(Platform p){
		buildingBase = new StaticRect(p.getWidth()/3 - 150, p.getHeight() - 30, 300, 30 );
		buildingBase.setColor(java.awt.Color.gray);
		p.addThing(buildingBase);
		
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

	public void render(){
		//displays tutorial.
	}
	public void renderOverlay(java.awt.Graphics g){
		//renders tutorial text until its finished
		//renders numbers next to the shapes
	}
	
	public int startTime;
	public int stackedBlocks(){return 0;}
	public int getCurrentArea(){return 0;}
	public int getElapsedTime(){return 0;}
	public boolean submit(){return false;}
	
}
