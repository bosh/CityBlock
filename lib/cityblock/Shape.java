package cityblock;

import game.*;
import java.awt.*;

public class Shape extends Thing{
	int width;
	int height;
	
	//we should not be using ACTUAL width and height for the numbers we display cus they'll be far too high.
	
	public Shape(int w, int h){
		this.width = w;
		this.height = h;
		this.movable = true;
	}
	
	public void setup(int x, int y){
		//override me!
	}
	
	public void renderOverlay(Graphics g){
		
	}
	public boolean inStagingArea(){
		return false;
	}
	
	public void moveTowards(game.Thing other, int movementRate){
		//this should move closer to other at a rate of min(this.distanceTo(other), movementRate)
	}
	
	
}