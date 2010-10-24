package game;

public class LineSegment {
	public double x1, x2, y1, y2;

	public LineSegment(double[] start, double[] end){
		this.x1 = Math.min(start[0], end[0]); //x1 < x2
		this.y1 = Math.min(start[1], end[1]); //y1 < y2
		this.x2 = Math.max(start[0], end[0]);
		this.y2 = Math.max(start[1], end[1]);
	}
	
	public double[] distanceTo(LineSegment other){ //Only returns parallel line distances
		double[] distance = new double[] {999,999};
		if (this.x1 == this.x2 && other.x1 == other.x2) {		//If the segments are vertically parallel
			if (this.x2 < other.x1 || this.x1 > other.x2){		//any of the Xs are between the Xs of the other shape
				distance[0] = this.x1 - other.x1;				//simple difference (as though parallel lines)
			}
		} else if (this.y1 == this.y2 && other.y1 == other.y2){	//If the segments are horizontally parallel
			if (this.y2 < other.y1 || this.y1 > other.y2){		//any of the Ys are between the Ys of the other shape
				distance[1] = this.y1 - other.y1;				//simple difference (as though parallel lines)
			}
		}
		return distance;
	}
}