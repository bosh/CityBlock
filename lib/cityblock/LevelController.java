package cityblock;
import game.*;

public class LevelController{
	
	//private, dont make public!!
	LevelController.LevelSpec[] _levels;
	int _top;
	
	public class LevelSpec{
		public int maxShapes;
		public boolean[] desiredShapes; //0 is suqare, 1 is rectangle, 2 is triangle
		//more stuff here
	}
	
	
	public LevelController(LevelController.LevelSpec[] levels){
		//read in levels file and create hash
		_top = 100;
		_levels = levels;
	}
	
	public Level getLevel(int i){
		if(i > _top-1) return null;
		return generate(_levels[i]);
	}
	
	public Level generate(LevelController.LevelSpec spec){
		Level result = new Level();
		//flow:
		/*
			> generate a random number of each shape (at least one for all shapes specified)
			> randomly generate dimensions for each shape, within hard-set boundaries
			> work out all the different areas combining a certain number of shapes (n, n-1, n-2)
			> pick any combination that appears twice (makes it more interesting), rank them by the
				number of different shapes it takes to make that area. IE if area 42 can be made with 3, and 4 blocks,
				thats better to pick than area 35 that can only be made with different combos of 3 blocks.
				
			> Then randomly pick a goal, exclude certain goals under certain conditions, for example
			 if there is only one solution you can't have a 'use minimum blocks' goal.
		
		
		
		*/
		
		return result;
	}
	
	
}