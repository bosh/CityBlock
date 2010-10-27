package cityblock;

public class LevelSpec{
	
	public LevelSpec(int num, boolean square, boolean rect, boolean triangle){
		numShapes = num;
		desiredShapes[0] = square;
		desiredShapes[1] = rect;
		desiredShapes[2] = triangle;
	}
	
	public int numShapes; //must accommodate 1 of each of the desired shapes;
	public boolean[] desiredShapes = new boolean[3]; //0 is suqare, 1 is rectangle, 2 is triangle
	//more stuff here
}