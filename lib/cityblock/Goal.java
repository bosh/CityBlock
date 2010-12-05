package cityblock;

public class Goal {
	public String name;
	public String text;
	public int score;

	public Goal(String name){
		this.name = name;
		text = "unevaluated TEMP";
		score = -1;
	}

	public void evaluate(long timeTaken, Shape[] shapesUsed){
		text = "evaluated TEMP";
		score = 999;
	}
}