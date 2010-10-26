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
		buildingBase = new StaticRect(p.getWidth()/3 - 150, p.getHeight() - 30, 300, 60 );
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
		//displays tutorial until the user has finished with it
		//snaps to grid and all that stuff.
		//hard coding platform location and staging area location.
		
		//this moves a shape up against its nearest neighbour. Also need to implement snap to within a certain radius
		for(int i = 0; i < shapes.length; i++){
			Shape s = shapes[i];
			if(!s.isInStagingArea() && !s.held){
				Thing[] closestTo = s.findClosest();
				s.moveTowards(closestTo[0], 20); //needs implementing (by alex :-) )
				s.moveTowards(closestTo[1], 20); //needs implementing (by alex :-) )
			}
		}
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
