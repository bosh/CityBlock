package cityblock;

public class LevelSpec{
	public int numShapes; //must accommodate 1 of each of the desired shapes;
	public boolean[] desiredShapes = new boolean[3]; //0 is square, 1 is rectangle, 2 is triangle
	public String[] goalTypes;

	public LevelSpec(int numShapes, boolean square, boolean rect, boolean triangle, String[] goalTypes){
		this.numShapes = numShapes;
		desiredShapes[0] = square;
		desiredShapes[1] = rect;
		desiredShapes[2] = triangle;
		this.goalTypes = goalTypes;
	}
}
