package cityblock;

import cityblock.*;
import game.*;
import java.awt.*;
import java.util.*;

public class TriangleShape extends cityblock.Shape{
	//simple at first, just makes right-angle triangles only
	public TriangleShape(int w, int h){
		super(w, h, "right-triangle-lower-left.png", Color.green);
	}

	public static Shape randomize(){
		Random r = new Random();
		int dimension = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		int dimension2 = cityblock.Shape.dimMultiplier * (r.nextInt(cityblock.Shape.maxDimension) + 1); //between 1 and maxDimension
		TriangleShape result = new TriangleShape(dimension, dimension2);
		return result;
	}

	public double getArea(){
		return this.getGameHeight()*((double)this.getGameWidth()*0.5);
	}

	public void setup(int x, int y){
		this.x = x + width / 2;
		this.y = y + height / 2;
		X[0] = x;
		Y[0] = y;
		Y[1] = y + this.height;
		X[1] = x;
		X[2] = x + this.width;
		Y[2] = y + this.height;
		//this should make a triangle
		setShape(X, Y, 3);
	}

	public game.LineSegment getSingleEdge(String location) {
		LineSegment[] segments = getLineSegments();
		LineSegment result = null;
		for(int i = 0; i < segments.length; i++){
			LineSegment seg = segments[i];
			if(location == "left" && seg.isVertical()){
				if(seg.x1 < x) { result = seg; }
			} else if(location == "right" && seg.isVertical()){
				if(seg.x1 > x) { result = seg; }
			} else if(location == "bottom" && seg.isHorizontal()){
				if(seg.y1 > y) { result = seg; }
			} else if(location == "top" && seg.isHorizontal()){
				if(seg.y1 < y) { result = seg; }
			}
		}
		return result;
	}

	public double[] distanceTo(Thing other){ //Assumes that there will always be parallel lines between things
		double left = 999, right = 999, top = 999, bottom = 999;
		if (getLeftEdge() != null)	{ left		= getLeftEdge().distanceTo( other.getRightEdge())[0]; }
		if (getRightEdge() != null) { right 	= getRightEdge().distanceTo( other.getLeftEdge())[0]; }
		if (getTopEdge() != null) 	{ top		= getTopEdge().distanceTo( other.getBottomEdge())[1]; }
		if (getBottomEdge() != null){ bottom	= getBottomEdge().distanceTo( other.getTopEdge())[1]; }
		
		double[] minimumDistance = new double[] {left, top};
		if (Math.abs(right) < Math.abs(left)){ minimumDistance[0] = right; }
		if (Math.abs(bottom) < Math.abs(top)){ minimumDistance[1] = bottom; }
		return minimumDistance;
	}

	public void renderOverlay(Graphics g){
		Font font = new Font("Helvetica", Font.BOLD, 16);
		g.setFont(font);
		g.setColor(new Color(85, 85, 85));
		if(this.isInPlayArea() && !this.held) return;
		String base = ""+ getGameWidth();
		String height = "" + getGameHeight();
		LineSegment bottomEdge = this.getBottomEdge();
		LineSegment leftEdge = this.getLeftEdge();
		LineSegment topEdge = this.getTopEdge();
		LineSegment rightEdge = this.getRightEdge();
		if (rotation == 0) {
			double bottomWidth = Math.abs(bottomEdge.x1 - bottomEdge.x2);
			double leftHeight = Math.abs(leftEdge.y1 - leftEdge.y2);
			g.drawString(base, (int)(bottomEdge.xMin() + bottomWidth/2), (int)(bottomEdge.yMin() + 15));
			g.drawString(height, (int)(leftEdge.xMin() - 15), (int)(leftEdge.yMin() + 7 + leftHeight/2) );
		} else if (rotation == 90) {
			double leftHeight = Math.abs(leftEdge.y1 - leftEdge.y2);
			double topWidth = Math.abs(topEdge.x1 - topEdge.x2);
			g.drawString(height, (int)(topEdge.xMin() + topWidth/2), (int)(topEdge.yMin() - 7));
			g.drawString(base, (int)(leftEdge.xMin() - 15), (int)(leftEdge.yMin() + 7 + leftHeight/2) );
		} else if (rotation == 180) {
			double topWidth = Math.abs(topEdge.x1 - topEdge.x2);
			double rightHeight = Math.abs(rightEdge.y1 - rightEdge.y2);
			g.drawString(base, (int)(topEdge.xMin() + topWidth/2), (int)(topEdge.yMin() - 7));
			g.drawString(height, (int)(rightEdge.xMin() + 7), (int)(rightEdge.yMin() + 7 + rightHeight/2) );
		} else if (rotation == 270) {
			double bottomWidth = Math.abs(bottomEdge.x1 - bottomEdge.x2);
			double rightHeight = Math.abs(rightEdge.y1 - rightEdge.y2);
			g.drawString(height, (int)(bottomEdge.xMin() + bottomWidth/2), (int)(bottomEdge.yMin() + 15));
			g.drawString(base, (int)(rightEdge.xMin() + 7), (int)(rightEdge.yMin() + 7 + rightHeight/2) );
		}
	}
}