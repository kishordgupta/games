package com.rhymes.game.generation;

import java.util.HashMap;

public abstract class GenerationSet {
	HashMap<String, Float> scoreValues;
	
	public GenerationSet(){
		scoreValues = new HashMap<String, Float>();
		initScoreValues();
	}
	public abstract void generate(float startX, float startY);
	public abstract void initScoreValues();
	
	public float getValue(String scoreType){
		if(scoreValues.containsKey(scoreType))
			return scoreValues.get(scoreType).floatValue(); 
		return 0;
	}
	public void setValue(String scoreType, float value)
	{
		if(scoreValues.containsKey(scoreType))
			scoreValues.remove(scoreType);
		scoreValues.put(scoreType, new Float(value));
	}
	
}
