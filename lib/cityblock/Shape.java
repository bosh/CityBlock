package cityblock;

import game.*;
import java.awt.*;

public class Shape extends Thing {
	int width;
	int height;
	Level level;
	
	//we should not be using ACTUAL width and height for the numbers we display cus they'll be far too high.
	public Shape(int w, int h){
		this.width = w;
		this.height = h;
		this.movable = true;
	}

	public void setLevel(Level level){
		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}
	
   public Thing[] findClosest(){
      Thing[] currentClosest = new Thing[] {null, this.getLevel().buildingBase}; //null because it should not snap horizontally by default
      Shape[] shapes = level.shapes;
      for(int i = 0; i < shapes.length; i++){
         Shape other = shapes[i];
         if(this != other && !other.isInStagingArea() && this.isInPlayArea()) {
            double[] distance = this.distanceTo(other);
            for(int xy = 0; xy < 2; xy++){
               if (distance[xy] < currentClosest[xy].distanceTo(this)[xy]) {
                  currentClosest[xy] = other;
               }
            }
         }
      }
      return currentClosest;
   }

	public void setup(int x, int y){
		//override me!
	}
	
	public void renderOverlay(Graphics g){
		
	}

	public void moveTowards(game.Thing other, int movementRate){
		//initial implementation - immediately snaps to
		// this.setthis.distanceTo(other)
		//this should move closer to other at a rate of min(this.distanceTo(other), movementRate)
	}

}