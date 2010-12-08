package cityblock;

import game.*;
import java.util.*;
import cityblock.*;

public class LevelController {
	//private, dont make public!!
	LevelSpec[] _levels;
	Level[] _generated_levels;

	public LevelController(){
		_levels = new LevelSpec[8];
		_levels[0] = new LevelSpec(3, true, false, false, new String[] {"easy", "minute"});
		_levels[1] = new LevelSpec(6, true, false, false, new String[] {"normal","minute"});
		_levels[2] = new LevelSpec(3, false, true, false, new String[] {"easy", "minute"});
		_levels[3] = new LevelSpec(6, true, true, false, new String[]  {"normal", "rectangle", "minute"});
		_levels[4] = new LevelSpec(3, false, false, true, new String[]  {"easy", "minute"});
		_levels[5] = new LevelSpec(6, true, false, true, new String[]  {"normal", "triangle", "minute"});
		_levels[6] = new LevelSpec(6, false, true, true, new String[]  {"normal", "triangle", "minute"});
		_levels[7] = new LevelSpec(8, true, true, true, new String[]  {"hard", "square", "triangle", "minute"});
		//read in levels file and create hash
		_generated_levels = new Level[_levels.length];
	}

	public Level getLevel(int i){
		if(i >= _levels.length){ return null; }
		Level l;
		if (_generated_levels[i] == null) {
			l = generate(_levels[i]);
			l.levelNumber = i;
			_generated_levels[i] = l;
		} else {
			l = _generated_levels[i];
		}
		return l;
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
		// Get one of each desired shape
		for(int i = 0; i < spec.desiredShapes.length; i++){
			if (spec.desiredShapes[i]){
				shapes[shapesIndex] = this.getShape(i);
				shapesIndex++;
			}
		}
		// Fill in with other random shapes
		Random r = new Random();
		while(shapesIndex < spec.numShapes){
			int sType = r.nextInt(3);
			if(spec.desiredShapes[sType]){
				shapes[shapesIndex] = this.getShape(sType);
				shapesIndex++;
			}
		}

		Goal[] goals = new Goal[spec.goalTypes.length];
		for(int i = 0; i < spec.goalTypes.length; i++){
			goals[i] = new Goal(spec.goalTypes[i]);
		}

		Level result = new Level(shapes, goals);

		int totalShapes = shapes.length;
		HashMap totalAreas = new HashMap();

		double sum = 0.0;
		for(int i = 0; i < shapes.length; i++){
			sum += shapes[i].getArea();
		}
		totalAreas.put(Double.valueOf(sum), Integer.valueOf(1));

		for(int i = 0; i < shapes.length; i++){
			double newSum = sum - shapes[i].getArea();
			incrementArea(totalAreas, newSum);
			for(int j = i+1; j < shapes.length; j++){
				double crazySum = newSum - shapes[j].getArea();
				incrementArea(totalAreas, crazySum);
			}
		}

		Object[] keys = totalAreas.keySet().toArray();
		int maxCount = 0;
		for(int i = 0; i < keys.length; i++){
			int currentCount = ((Integer)totalAreas.get((Double)keys[i])).intValue();
			if(currentCount > maxCount) {
				result.targetArea = ((Double)keys[i]).doubleValue();
				maxCount = currentCount;
			}
			System.out.println("area total " + (Double)keys[i] + ": " + totalAreas.get(((Double)keys[i])));
		}
		System.out.println("using: " + result.targetArea);
		return result;
	}

	private void incrementArea(HashMap hash, double sum){
		Integer count = (Integer)hash.get(Double.valueOf(sum));
		if(count == null) count = Integer.valueOf(0);
		int rawCount = count.intValue() + 1;
		hash.put(Double.valueOf(sum), Integer.valueOf(rawCount));
	}
}
