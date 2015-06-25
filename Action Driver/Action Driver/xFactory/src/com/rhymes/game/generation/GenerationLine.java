package com.rhymes.game.generation;

import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.Constants;
import com.rhymes.helpers.Helper;

public abstract class GenerationLine {
	protected Array<GenerationSet> genSets;
	protected Array<Vector2> scoreCurve; // distance, score
	protected String scoreType;
	
	protected static HashMap<String, Float> genPointList ;

	public abstract void initScoreCurve();
	public abstract void initGenSets();
	public abstract void initGenPoints();
	
	protected float startingOffset = 0;
	protected boolean firstTime = true;
	
	public float getStartingOffset() {
		return startingOffset;
	}
	public void setStartingOffset(float startingOffset) {
		this.startingOffset = startingOffset;
	}
	
	public Array<Vector2> getScoreCurve() {
		return scoreCurve;
	}

	public void setScoreCurve(Array<Vector2> scoreCurve) {
		this.scoreCurve = scoreCurve;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	
	public GenerationLine(String scoreType){
		genSets = new Array<GenerationSet>();
		scoreCurve = new Array<Vector2>();
		if(genPointList == null)
			genPointList = new HashMap<String, Float>();
		this.scoreType = scoreType;
		initScoreCurve();
		initGenSets();
		initGenPoints();
	}
	
	public float getTargetScore(float distance){
//		Helper.println("Calculating target score distance: " + distance + " for generation line: " + this.getClass());
		float d = 0;
		for(int i = 0; i < scoreCurve.size; i++)
		{
			d = scoreCurve.get(i).x;
			if(d >= distance)
			{
				if(i == 0)
					return scoreCurve.get(i).y;
				else
					return scoreCurve.get(i-1).y;
			}
		}
		if(scoreCurve != null)
			return scoreCurve.get(scoreCurve.size-1).y;
		return 0;
	}
	
	
	Array<GenerationSet> retSets;
	Random rand = new Random();
	
	private float getSmallestValue()
	{
		float s = -1;
		for(int i = 0; i < genSets.size; i++)
			if(genSets.get(i).getValue(scoreType) < s || s < 0)
				s = genSets.get(i).getValue(scoreType);
		
		return s;
	}
	private GenerationSet getRandomGenSet(float maxScore)
	{
		if(maxScore < getSmallestValue())
			return null;
		
		int r = rand.nextInt(this.genSets.size);
		int count = 0;
		while(true)
		{
			if(genSets.get(r).getValue(this.scoreType) < maxScore)
				return genSets.get(r);
			r = rand.nextInt(this.genSets.size);

			count++;
			if(count>15)
				return null;
		}
		
	}
	GenerationSet g ;
	public Array<GenerationSet> getGenerationSets(float targetScore){
		retSets = new Array<GenerationSet>();
		
		while(true){
				g = getRandomGenSet(targetScore);				
				if(g != null)
				{
					if(g.getValue(scoreType) == 0)
						continue;
					retSets.add(g);
					targetScore-=g.getValue(scoreType);
					if(targetScore < 0)
						break;
				}
				else break;
				
		}
		return retSets;
	}

	public abstract void setGenPoint(float value);
	public abstract void setGenPointToNext();
	public abstract float getGenPoint();
	public abstract float getCurrentPlayPoint();
	public abstract float getPeriodLength();
	
	

	Array<GenerationSet> gSets;
	float prevGenPoint;
	float s;
	public void checkGeneration()
	{
		if(firstTime)
		{
			setGenPoint(this.startingOffset);
			firstTime = false;
		}
		
		prevGenPoint = getGenPoint();
		while(getCurrentPlayPoint() > getGenPoint())
		{
			Helper.println("\n\n\nGenereating for: " + this.getClass());
			Helper.println("\nChecking Generation: current playpoint: " + getCurrentPlayPoint() + " Gen point: " + getGenPoint());
			gSets = getGenerationSets(getTargetScore(getGenPoint()));
		    Helper.println("Gen selected for value: " + getTargetScore(getGenPoint()) + " at gen point: " + getGenPoint() + " gSets.size: " + gSets.size) ;	
		    
//		    s = getPeriodLength() / gSets.size;
		    s = 0;
		    for(GenerationSet g:gSets){
//		    	Helper.println("G value: " + g.getValue(scoreType));
		    	g.generate(getGenPoint() /*+ rand.nextInt((int) s)*/, Constants.generationLowerBoundary 
		    			+ rand.nextInt((int) (Constants.generationUpperBoundary - Constants.generationLowerBoundary)));
		    	setGenPointToNext();
		    	s += getGenPoint() - prevGenPoint;
//		    	this.setGenPoint(this.getGenPoint() + s);
		    }
		    if(s < getPeriodLength())
		    	this.setGenPoint(getGenPoint() + getPeriodLength() - s);
//		    prevGenPoint = getGenPoint();
		}
	}
	
	public void tick(float stepTime){
		this.checkGeneration();
	}
}
