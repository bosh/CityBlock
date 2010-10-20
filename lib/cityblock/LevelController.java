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
	
	
	public LevelGenerator(LevelController.LevelSpec[] levels){
		//read in levels file and create hash
		_top = 100;
		_levels = new Level[_top]; //some arbitrary limit so that I don't have to cast objects from a hashmap.
		
	}
	
	public Level getLevel(int i){
		if(i > _top-1) return null;
		result = _levels[i];
		return generate(_levels[i]);
	}
	
	public Level generate(LevelController.LevelSpec spec){
		
	}
	
	
}