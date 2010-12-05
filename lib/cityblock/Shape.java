package cityblock;
import java.util.*;
import game.*;
import java.awt.*;
import java.awt.image.*;

public class Shape extends Thing implements ImageObserver{
	public String name;
	Level level;
	public Image img;
	public static int maxDimension = 5;
	public static int dimMultiplier = 20;
	protected Color defaultColor = Color.gray;
	
	public void reset(){
		setColor(defaultColor);
		setLineColor(defaultColor);
	}

	public double getArea(){return this.getGameHeight()*this.getGameWidth();}
	public int getGameHeight(){return height / dimMultiplier;}
	public int getGameWidth(){return width / dimMultiplier;}

	//we should not be using ACTUAL width and height for the numbers we display cus they'll be far too high.
	public Shape(int w, int h, String imgpath, Color color){
		this.width = w;
		this.height = h;
		this.img = Platform.platform.getImage(Platform.platform.getCodeBase(), imgpath); 
		this.defaultColor = color;
		this.movable = true;
	}

	public void setLevel(Level level){
		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}
	public void highlight(){
		setColor(Color.black);
		setLineColor(Color.black);
	}

	public boolean mouseUp(int x, int y) {
		if(isInStagingArea()){
			if (!dragged) { rotate(); }
			return false;
		}
		if (!isInStagingArea() && !isInPlayArea()) {
			returnToStaging();
		} else {
			if (snapToNearest()) {
				Shape[] shapes = level.shapes;
				updateShape();
				for(int i = 0; i < shapes.length; i++){
					if (!this.isInStagingArea() && shapes[i].isInPlayArea() && getPlatform().colliding(this, shapes[i])) {
						returnToStaging();
					}
				}
			} else {
				returnToStaging();
			}
		}
		this.held = false;
		return false;
	}

	public boolean snapToNearest(){
		boolean snapped = false;
		double snapToDistance = dimMultiplier*2;
		Thing[] closest = findClosest();
		for(int xy = 0; xy < 2; xy++) {
			if (closest[xy] != null) {
				double distance = distanceTo(closest[xy])[xy];
				if (Math.abs(distance) < snapToDistance) {
					if(xy == 0 && distance > 0) { setX(getX() - distance + 1); snapped = true; }
					if(xy == 0 && distance < 0) { setX(getX() - distance - 1); snapped = true; }
					if(xy == 1 && distance > 0) { setY(getY() - distance + 1); snapped = true; }
					if(xy == 1 && distance < 0) { setY(getY() - distance - 1); snapped = true; }
				}
			}
		}
		return snapped;
	}

	public void returnToStaging(){ //TODO make this target a specific place for objects to come back
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
					if (currentClosest[xy] == null || Math.abs(distance[xy]) < Math.abs(distanceTo(currentClosest[xy])[xy])) {
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
		Font font = new Font("Helvetica", Font.BOLD, 16);
		g.setFont(font);
		g.setColor(new Color(85, 85, 85));
		if(this.isInPlayArea() && !this.held) return;
		String bottom = ""+ getGameWidth();
		String side = "" + getGameHeight();
		LineSegment bottomEdge = this.getBottomEdge();
		LineSegment leftEdge = this.getLeftEdge();
		double bottomWidth = Math.abs(bottomEdge.x1 - bottomEdge.x2);
		double sideHeight = Math.abs(leftEdge.y1 - leftEdge.y2);
		if (rotation == 0 || rotation == 180) {
			g.drawString(bottom, (int)(bottomEdge.xMin() + bottomWidth/2), (int)(bottomEdge.yMin() + 15));
			g.drawString(side, (int)(leftEdge.xMin() - 15), (int)(leftEdge.yMin() + 7 + sideHeight/2) );
		} else if (rotation == 90 || rotation == 270) {
			g.drawString(bottom, (int)(bottomEdge.xMin() + bottomWidth/2), (int)(bottomEdge.yMin() + 15));
			g.drawString(side, (int)(leftEdge.xMin() - 15), (int)(leftEdge.yMin() + 7 + sideHeight/2) );
		}
	}

	public void update(Graphics g) {
		super.update(g);
		g.drawImage(img, (int) getX() - width/2, (int) getY() - height/2, width, height, this);
	}

	public boolean imageUpdate(Image img, int x, int y, int width, int height, int fudge){
		return true;
	}
}
