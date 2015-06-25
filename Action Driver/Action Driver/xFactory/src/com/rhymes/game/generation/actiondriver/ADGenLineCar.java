package com.rhymes.game.generation.actiondriver;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.rhymes.game.data.Constants;
import com.rhymes.game.generation.GenerationLine;
import com.rhymes.game.generation.GenerationSet;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class ADGenLineCar extends GenerationLine{
	float stepDistance = 4000 * Constants.rx;
	float genPointStep = 3000* Constants.rx;
	
	protected final String genPoint = "car";

	public ADGenLineCar(String scoreType) {
		super(scoreType);
	}

	@Override
	public void initScoreCurve() {
		Helper.println("initializing score curve: " + this.getClass());
		this.scoreCurve.add(new Vector2(1, 120));
		this.scoreCurve.add(new Vector2(6000* Constants.rx, 180));
//		this.scoreCurve.add(new Vector2(1500, 200));
	}
	
	
	Random rand = new Random();
	private float[] lanes = {Constants.roadLowerBoundary + 30 * Constants.ry, Helper.getScreenHeight()/2f - 30 * Constants.ry, Constants.roadUpperBoundary - 30 * Constants.ry};
	public float getLaneRandomY()
	{
		return lanes[rand.nextInt(lanes.length)];
	}


	
	@Override
	public void initGenSets() {
		Helper.println("Initializing generation line: " + this.getClass());
//		this.genSets.add(new ADGenSetScore());

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeCAR, 100);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				Helper.println("1 Generating Enemey at: " + 	startX + " Cam x: " + Helper.getCameraX()) ;
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addEnemey(startX, getLaneRandomY(), Constants.carStartingSpeedX , 80);
			}
		});

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeCAR, 150);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				float s = getLaneRandomY();
//				Helper.println("2 Generating Enemey Car at: " + 	startX + " Cam x: " + Helper.getCameraX()) ;
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addEnemey(startX, s, Constants.carStartingSpeedX , 80);
//				Helper.println("2 Generating Enemey Car at: " + 	startX + " Cam x: " + Helper.getCameraX()) ;
				float r = getLaneRandomY();
				while(r == s )
					r = getLaneRandomY();
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addEnemey(startX , r, Constants.carStartingSpeedX , 80);
//				setGenPointToNext();
			}
		});
	}

	@Override
	public void initGenPoints() {
		this.genPointList.put(this.genPoint, new Float(0));
	}

	Float f ;
	@Override
	public void setGenPointToNext() {
		f = new Float(this.genPointList.get(this.genPoint) + this.genPointStep);
		this.genPointList.put(this.genPoint, f);
	}

	@Override
	public float getGenPoint() {
		return this.genPointList.get(this.genPoint).floatValue();
	}

	@Override
	public float getCurrentPlayPoint() {
		return Helper.getCameraX() + Helper.getScreenWidth() * 2f;
	}

	@Override
	public float getPeriodLength() {
		return this.stepDistance;
	}

	@Override
	public void setGenPoint(float value) {
		f = new Float(value);
		this.genPointList.put(this.genPoint, f);
	}

}
