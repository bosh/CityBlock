package cityblock;

public class Goal {
	public String name;
	public String text;
	public Shape[] shapes;
	public long time;
	public int bonus;

	public Goal(String name){
		this.name = name;
		text = "";
		bonus = 0;
	}

	public boolean includesShape(String type){
		for(int i = 0; i < shapes.length; i++){
			if (shapes[i].name == type){ return true; }
		}
		return false;
	}

	public void evaluate(long timeTaken, Shape[] shapesUsed){
		this.shapes = shapesUsed;
		this.time = timeTaken;
		if (name == "minute"){
			if (time < 1000 * 60){
				bonus = 25;
				text = "Wow, fast! +" + bonus + " points";
			} else {
				text = "You took " + (int)(time/1000) + " seconds.";
			}
		} else if (name == "easy") {
			bonus = 75;
			text = "Level complete! 75 points";
		} else if (name == "normal") {
			bonus = 100;
			text = "Level complete! 100 points";
		} else if (name == "hard") {
			bonus = 125;
			text = "Level complete! 125 points";
		} else if (name == "square") {
			if (includesShape("square")) {
				bonus = 15;
				text = "Square used! +" + bonus + " points";
			} else {
				text = "Use a square: Incomplete";
			}
		} else if (name == "rectangle") {
			if (includesShape("rectangle")) {
				bonus = 20;
				text = "Rectangle used! +" + bonus + " points";
			} else {
				text = "Use a rectangle that isn't also a square: Incomplete";
			}
		} else if (name == "triangle") {
			if (includesShape("triangle")) {
				bonus = 25;
				text = "Triangle used! +" + bonus + " points";
			} else {
				text = "Use a triangle: Incomplete";
			}
		}
	}
}