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
	public StaticRect buildingBase;
	public Platform platform;
	public boolean completed = false;
	public long startTime;
	public boolean frozen = false;
	public int score;
	public boolean locked = false;
	public int levelNumber = -1;
	SoundController sounds = SoundController.active;

	int action = 0;
	String[] screenText = new String[]{"", "", ""};
	String[] goalText;

	cityblock.Button button;
	Button resetButton = null;
	Button completedButton = null;
	boolean doEndLevel = false;
	
	public Level(Shape[] shapes, Goal[] goals){
		this.shapes = shapes;
		this.goals = goals;
		for(int i = 0; i < shapes.length; i++) {
			shapes[i].setLevel(this);
		}

	}

	public void start(Platform p){
		platform = p;
		buildingBase = new StaticRect("base.png", p.getWidth()/3 - 150, p.getHeight() - 40, 300, 30 );
		buildingBase.setColor(java.awt.Color.gray);
		button = new Button(p.getWidth()*2/3 - 62, p.getHeight() - 22, 120, 40, this, p.getImage(p.getBase(), "Submit.png"), 0);
		resetButton = new Button(62, p.getHeight() - 22, 120, 40, this, p.getImage(p.getBase(), "ResetButton.png"), 1);
		p.addThing(resetButton);
		p.addThing(button);
		p.addThing(buildingBase);
		startTime = System.currentTimeMillis();

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
		frozen = false;
		score = 0;
		screenText = new String[]{"", "", ""};
		goalText = null;
		total = "";
		action = 0;
		nextAction = 0;
		notePlayed = 0;
		if(completedButton != null) platform.removeThing(completedButton);
		completedButton = null;
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
		Color t = g.getColor();
		g.setColor(Color.white);
		g.drawString(txt, platform.getWidth() - platform.stringWidth(txt, g) - 20 , platform.getHeight() - 20);
		g.setColor(t);
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

		int currX = 100;
		int currY = 100;
		for(int i = 0; i < screenText.length; i++){
			g.drawString(screenText[i], currX, currY);
			currY += 25;
		}
		currY += 25;
		if ( frozen && goalText != null ){
			for(int i = 0; i < goalText.length; i++){
				g.drawString(goalText[i], currX, currY);
				currY += 25;
			}
			g.drawString("Score: " + score, currX, currY);
		}
		//renders tutorial text until its finished
		//renders numbers next to the shapes
	}

	public Shape[] getShapesInPlay(){
		Shape[] inPlay = new Shape[100];
		int next = 0;
		for(int i = 0; i < shapes.length; i++){
			if(shapes[i].isInPlayArea()){
				inPlay[next] = shapes[i];
				next++;
			}
		}
		Shape[] results = new Shape[next];
		for(int i = 0; i < results.length; i++){
			results[i] = inPlay[i];
		}
		return results;
	}

	public double getCurrentArea(){
		double result = 0.0;
		Shape[] inPlay = getShapesInPlay();
		for(int i = 0; i < inPlay.length; i++){
			result += inPlay[i].getArea();
		}
		return result;
	}

	public long getElapsedMillis(){
		return System.currentTimeMillis() - startTime;
	}

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
	int notePlayed = 0;
	private void renderEndLevel(){
		Date current = new Date();
		if(nextAction == 0) nextAction = current.getTime()+750;//milliseconds

		if(current.getTime() > nextAction){
			nextAction = current.getTime()+750;
			while(action < shapes.length && !shapes[action].isInPlayArea()) action++;
			if(action < shapes.length){
				shapes[action].highlight();
				if( !total.equals("") ){ total += " + "; }
				total += shapes[action].getArea();
				sounds.playNote(notePlayed);
				notePlayed++;
				action++;
			} else {
				if (!total.contains("=") ){
					total += " = " + getCurrentArea();
				}
				if (targetArea == getCurrentArea()){
					screenText[2] = "Good Job! Level Complete";
					if ( completedButton == null ) {
						completedButton = new Button(400, 30, 300, 50, this, platform.getImage(platform.getBase(), "NextLevel.png"), 2);
						sounds.playNote(11);
						platform.addThing(completedButton);
					}
					if ( !frozen ){
						score = 0;
						long millisTaken = getElapsedMillis();
						goalText = new String[goals.length];
						int[] goalBonuses = new int[goals.length];
						for(int i = 0; i < goals.length; i++){
							goals[i].evaluate(millisTaken, getShapesInPlay());
							goalText[i] = goals[i].text;
							score += goals[i].bonus;
						}
						frozen = true;
					}
				} else {
					if( screenText[2].equals("") ){  sounds.playNote(0); }
					screenText[2] = "Not quite! Click reset to try again.";
				}
			}
		}
		screenText[0] = "Target area: " + targetArea;
		screenText[1] = "You have: " + total;
	}
}
