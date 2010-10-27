package cityblock;
import cityblock.*;
import game.*;
import java.awt.*;
import java.util.*;

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
	public int startTime;
	public boolean completed = false;

	int action = 0;
	String[] screenText = new String[]{"", "", ""};
	
	
	cityblock.Button button;
	Button resetButton = null;
	Button completedButton = null;
	boolean doEndLevel = false;
	
	public Level(Shape[] shapes){
		this.shapes = shapes;
		for(int i = 0; i < shapes.length; i++) {
			shapes[i].setLevel(this);
		}
	}
	
	public void start(Platform p){
		platform = p;		
		buildingBase = new StaticRect(p.getWidth()/3 - 150, p.getHeight() - 30, 300, 30 );
		buildingBase.setColor(java.awt.Color.gray);
		button = new Button(p.getWidth()*2/3 - 80, p.getHeight() - 35, 70, 30, this, "Submit", 0);
		resetButton = new Button(10, p.getHeight() - 35, 70, 30, this, "Reset", 1);
		p.addThing(resetButton);
		p.addThing(button);
		p.addThing(buildingBase);

		for(int i = 0; i < this.shapes.length; i++){
			shapes[i].setup(0, 0);
			p.addThing(shapes[i]);
		}
		resetShapes(p);
	}
	public void resetShapes(Platform p){
		int offset = 20;		
		for(int i = 0; i < this.shapes.length; i++){
			Shape s = shapes[i];
			if(i > 0) offset += shapes[i-1].height + 20;
			s.setX(p.getWidth() - p.getWidth()/3 + 50 + s.width/2);
			s.setY(offset + s.height/2);
			s.reset();
		}
		
	}
	public void reset(){
		resetShapes(platform);
		doEndLevel = false;
		screenText = new String[]{"", "", ""};
		total = "";
		action = 0;
		nextAction = 0;
		
	}
	public void destroy(){
		for(int i = 0; i < shapes.length; i++){
			platform.removeThing(shapes[i]);
		}
		platform.removeThing(completedButton);
		platform.removeThing(button);
		platform.removeThing(resetButton);
		platform.removeThing(buildingBase);
	}

	public void update(){
		if(doEndLevel){
			renderEndLevel();
		}
		
		if(lastTime !=getCurrentArea()){
		System.out.println("current area :" + getCurrentArea() + " target: " + targetArea);
		lastTime = getCurrentArea();
		}
	}
	public void renderOverlay(java.awt.Graphics g){
		g.setColor(new Color(85, 85, 85));
		String txt = "Target Area: " + targetArea;
		g.drawString(txt, platform.getWidth() - platform.stringWidth(txt, g) - 20 , platform.getHeight() - 20);
		//1. Render the overlay for each object
		//2. Display the target area
		//3. Display the level number
		for(int i = 0; i < shapes.length; i++){
			Shape s = shapes[i];
			s.renderOverlay(g);
		}
		button.renderOverlay(g);
		if(completedButton != null) completedButton.renderOverlay(g);
		if(resetButton != null) resetButton.renderOverlay(g);
		Font font = new Font("Helvetica", Font.BOLD, 26);
		g.setColor(Color.darkGray);
		g.setFont(font);
		for(int i = 0; i < screenText.length; i++){
			int dasX = 100;
			int dasY = 50 + i*25;
			g.drawString(screenText[i], dasX, dasY);
		}

		
		//renders tutorial text until its finished
		//renders numbers next to the shapes
	}
	
	public double getCurrentArea(){
		double result = 0.0;
		for(int i = 0; i < shapes.length; i++){
			if(shapes[i].isInPlayArea()) result += shapes[i].getArea();
		}
		
		return result;
	}
	
	
	public int getElapsedTime(){return 0;}
	long nextAction = 0;

	public void submit(int type){
		switch(type){
			case 0:
				doEndLevel = true;
				return;
			case 1:
				reset();
				doEndLevel = false;
				return;
			case 2:
				completed = true;
				return;
		}
	}

	String total = "";
	
	private void renderEndLevel(){
		Date current = new Date();
		if(nextAction == 0) nextAction = current.getTime()+750;//milliseconds
		
		if(current.getTime() > nextAction){
			nextAction = current.getTime()+750;
			while(action < shapes.length && !shapes[action].isInPlayArea()) action++;
			if(action < shapes.length){
				shapes[action].highlight();
				if(!total.equals("")) total += " + ";
				total += shapes[action].getArea();
				action++;
			}else{
				if(!total.contains("=")) total += " = " + getCurrentArea();
				if(targetArea == getCurrentArea()){
					screenText[2] = "Good Job! Level Complete";
					if(completedButton == null) {
						completedButton = new Button(300, 140, 70, 30, this, "Next", 2);
						platform.addThing(completedButton);
					}
					
				}else{
					screenText[2] = "Not quite! Click reset to try again.";
				}
			}
			
			
		}
		screenText[0] = "Target area: " + targetArea;
		screenText[1] = "You have: " + total;
	}
	
	
	
	
}
