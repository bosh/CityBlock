package cityblock;
import game.*;

public class LevelController{
	
	//private, dont make public!!
	LevelController.LevelSpec[] _levels;
	int _top;
	
	public class LevelSpec{
		public int maxShapes;
		public int maxSolutions;
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
		return new Level();
	}
	
	
}