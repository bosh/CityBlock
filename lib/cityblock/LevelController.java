package cityblock;
import game.*;
import java.util.*;
import cityblock.*;



public class LevelController {
	//private, dont make public!!
	LevelSpec[] _levels;
	int _top;
	

	
	
	public LevelController(LevelSpec[] levels){
		//read in levels file and create hash
		_top = 100;
		_levels = levels;
	}
	
	public Level getLevel(int i){
		if(i > _top-1) return null;
		return generate(_levels[i]);
	}
	
	private Shape getShape(int i){
		switch(i){
			case 0: return SquareShape.randomize();
			case 1: return RectShape.randomize();
			case 2: return TriangleShape.randomize();
			default: return SquareShape.randomize();
		}
	}
	
	public Level generate(LevelSpec spec){
		Shape[] shapes = new Shape[spec.numShapes];
		int shapesIndex = 0;
		for(int i = 0; i < spec.desiredShapes.length; i++){
			if (spec.desiredShapes[i]){
				shapes[shapesIndex] = this.getShape(i);
				shapesIndex++;
			}
		}// end getting of initial shapes
		// now need to continue getting shapes until no more to get
		Random r = new Random();
		while(shapesIndex < spec.numShapes){
			int sType = r.nextInt(3);
			if(spec.desiredShapes[sType]){
				shapes[shapesIndex] = this.getShape(sType);
				shapesIndex++;
			}
		}
		Level result = new Level(shapes);
		//work out the target area
		//work out the number of solutions
		//add goals
		
		
		//flow:
		/*
			> generate a random number of each shape (at least one for all shapes specified)
			> randomly generate dimensions for each shape, within hard-set boundaries
			> work out all the different areas combining a certain number of shapes (n, n-1, n-2)
			> pick any combination that appear twice or more (makes it more interesting), rank them by the
				number of different shapes it takes to make that area. IE if area 42 can be made with 3, and 4 blocks,
				thats better to pick than area 35 that can only be made with different combos of 3 blocks.
				
			> Then randomly pick a goal, exclude certain goals under certain conditions, for example
			 if there is only one solution you can't have a 'use minimum blocks' goal.
		
		
		
		*/
		
		
		
		
		
		return result;
	}
	
}