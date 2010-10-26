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

	public boolean mouseUp(int x, int y) {
		if (!isInStagingArea() && !isInPlayArea()) {
			returnToStaging();
		} else {
			snapToNearest();
			Shape[] shapes = level.shapes;
			for(int i = 0; i < shapes.length; i++){
				if (!this.isInStagingArea() && shapes[i].isInPlayArea() && getPlatform().colliding(this, shapes[i])) {
					returnToStaging();
				}
			}
		}

		this.held = false;
		return false;
	}

	public void snapToNearest(){
		double snapToDistance = 35;
		Thing[] closest = findClosest();
		for(int xy = 0; xy < 2; xy++) {
			if (closest[xy] != null) {
				double distance = distanceTo(closest[xy])[xy];
				if (Math.abs(distance) < snapToDistance) {
					if(xy == 0) { setX(getX() - distance); }
					if(xy == 1) { setY(getY() - distance); }
				}
			}
		}
	}

	public void returnToStaging(){
		setX(getPlatform().getWidth() - 150);
		setY(getPlatform().getHeight() - 150);
	}

	public Thing[] findClosest(){
		Thing[] currentClosest = new Thing[] {null, this.getLevel().buildingBase}; //null because it should not snap horizontally by default
		Shape[] shapes = level.shapes;
		for(int i = 0; i < shapes.length; i++){
			Shape other = shapes[i];
			if(this != other && !other.isInStagingArea() && this.isInPlayArea()) {
				double[] distance = this.distanceTo(other);
				for(int xy = 0; xy < 2; xy++){
					if (currentClosest[xy] == null || Math.abs(distance[xy]) < Math.abs(currentClosest[xy].distanceTo(this)[xy])) {
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
		String bottom = ""+ getGameWidth();
		String side = "" + getGameHeight();
		LineSegment bottomEdge = this.getBottomEdge();
		LineSegment leftEdge = this.getLeftEdge();
		double bottomWidth = Math.abs(bottomEdge.x1 - bottomEdge.x2);
		double sideHeight = Math.abs(leftEdge.y1 - leftEdge.y2);
		g.drawString(bottom, (int)(bottomEdge.xMin() + bottomWidth/2), (int)(bottomEdge.yMin() + 20));
		g.drawString(side, (int)(leftEdge.xMin() - 20), (int)(leftEdge.yMin() + sideHeight/2) );
		
	}

	public void moveTowards(game.Thing other, int movementRate){
		//initial implementation - immediately snaps to
		// this.setthis.distanceTo(other)
		//this should move closer to other at a rate of min(this.distanceTo(other), movementRate)
	}

}