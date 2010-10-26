package cityblock;
import java.util.*;
import game.*;
import java.awt.*;

public class Shape extends Thing {

	Level level;
	public static int maxDimension = 8;
	public static int dimMultiplier = 20;
	
	public int getArea(){return this.getGameHeight()*this.getGameWidth();}
	public int getGameHeight(){return height / dimMultiplier;}
	public int getGameWidth(){return width / dimMultiplier;}
	
	//we should not be using ACTUAL width and height for the numbers we display cus they'll be far too high.
	public Shape(int w, int h){
		//System.out.println("foo");
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
	
	public boolean mouseUp(int x, int y){
		this.held = false;
		System.out.println("mouse up");
		Thing other = findClosest();

		this.snapTo(other);


		return false;
	}
	
	public void snapTo(Thing other){
		
		this.setY(other.getTopY() - height/2);
	}
	
	public boolean inStaging(){
		return this.getX() > (Platform.active.getWidth()*2)/3;
	}


	public boolean isTouching(Thing other){
		//sides touching, or top/bottom touching
		
		//sides touching
		return ((int)this.distance(other)) == 0;
//		if(this.getLeftX() == other.getRightX() || this.getRightX() == other.getLeftX())
//			return this.getTopY() < other.getBottomY() && this.getBottomY() > other.getTopY();
	
//		if(this.getTopY() == other.getBottomY() || this.getBottomY() == other.getTopY())
//			return this.getLeftX() < other.getRightX() && this.getRightX() > other.getLeftX();
//		return false;
	}

	public Thing findClosest(){
		if(this.inStaging()) return null;
		if(this.held) return null;
		
		Thing currentClosest = null;

		if(this.distance(level.buildingBase) < 55 && !this.isTouching(level.buildingBase)) currentClosest = level.buildingBase;	
		for(int i = 0; i < level.shapes.length; i++){
			Shape other = level.shapes[i];
			if(this != other && other.getX() != 0.0 && !other.inStaging() && other.distance(this) < 55 && !other.isTouching(this)){
				if(currentClosest == null) currentClosest = other;
				if(currentClosest != null && other.distance(this) < currentClosest.distance(this)) currentClosest = other;	
			}
			
		}
		System.out.print("closest object distance, x, y: ");
		System.out.print(currentClosest.distance(this));
		System.out.print(" " + currentClosest.getX());
		System.out.print(" " + currentClosest.getY());
		System.out.println();
		
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