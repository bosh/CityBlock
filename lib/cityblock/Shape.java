package cityblock;
import java.util.*;
import game.*;
import java.awt.*;

public class Shape extends Thing {
	int width;
	int height;
	Level level;
	public static int maxDimension = 6;
	public static int dimMultiplier = 20;
	
	public double getArea(){return this.getGameHeight()*this.getGameWidth();}
	public int getGameHeight(){return height / dimMultiplier;}
	public int getGameWidth(){return width / dimMultiplier;}
	
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

	public boolean mouseDrag(int x, int y) {
		boolean[] canMoveXY = new boolean[] {true, true};
		for(int i = 0; i < level.shapes.length; i++){

			if (!this.isInStagingArea() && level.shapes[i].isInPlayArea() && getPlatform().colliding(this, level.shapes[i])) {
				canMoveXY[0] = false;
				canMoveXY[1] = false;
			}
		}
		if (canMoveXY[0]){ setX(this.x + x - mx); mx = x;} //Yes, yagni for future smarter detection
		if (canMoveXY[1]){ setY(this.y + y - my); my = y;}
		return false;
	}

	public Thing[] findClosest(){
		Thing[] currentClosest = new Thing[] {null, this.getLevel().buildingBase}; //null because it should not snap horizontally by default
		Shape[] shapes = level.shapes;
		for(int i = 0; i < shapes.length; i++){
			Shape other = shapes[i];
			if(this != other && !other.isInStagingArea() && this.isInPlayArea()) {
				double[] distance = this.distanceTo(other);
				for(int xy = 0; xy < 2; xy++){
					if (currentClosest[xy] == null || distance[xy] < currentClosest[xy].distanceTo(this)[xy]) {
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