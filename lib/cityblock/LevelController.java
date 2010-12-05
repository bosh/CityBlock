package cityblock;

import game.*;
import java.util.*;
import cityblock.*;

public class LevelController {
	//private, dont make public!!
	LevelSpec[] _levels;

	public LevelController(){
		_levels = new LevelSpec[4];
		_levels[0] = new LevelSpec(3, true, true, false, new String[] {"square", "minute"});
		_levels[1] = new LevelSpec(6, true, true, false, new String[] {"rectangle", "minute"});
		_levels[2] = new LevelSpec(4, false, true, true, new String[] {"triangle", "minute"});
		_levels[3] = new LevelSpec(6, true, true, true, new String[]  {"square", "triangle", "minute"});
		//read in levels file and create hash
	}

	public Level getLevel(int i){
		if(i >= _levels.length){ return null; }
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
		}	// end getting of initial shapes
			// now need to continue getting shapes until no more to get
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
