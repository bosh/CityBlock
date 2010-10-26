package game;

public class LineSegment {
	public double x1, x2, y1, y2;

	public LineSegment(double[] start, double[] end){
		this.x1 = start[0];
		this.y1 = start[1];
		this.x2 = end[0];
		this.y2 = end[1];
	}
	
	public double[] distanceTo(LineSegment other){ //Only returns parallel line distances
		double[] distance = new double[] {999,999};
		if (isHorizontal() && other.isHorizontal()) {
			if (true) {
				distance[1] = y1 - other.y1;
			}
		} else if (isVertical() && other.isVertical()){
			if (true) {
				distance[0] = x1 - other.x1;
			}
		}
		return distance;
	}

	public boolean isHorizontal() {
		return (y1 == y2);
	}

	public boolean isVertical() {
		return (x1 == x2);
	}
}