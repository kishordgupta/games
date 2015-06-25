package com.rhymes.game.generation.actiondriver;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.rhymes.game.data.Constants;
import com.rhymes.game.generation.GenerationLine;
import com.rhymes.game.generation.GenerationSet;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class ADGenLineDeath extends GenerationLine{
	float stepDistance = 3500;
	float genPointStep = 1500;
	
	protected final String genPoint = "death";

	public ADGenLineDeath(String scoreType) {
		super(scoreType);
		this.startingOffset = 1900;
	}

	@Override
	public void initScoreCurve() {
		Helper.println("initializing score curve: " + this.getClass());
		this.scoreCurve.add(new Vector2(0, 200));
		this.scoreCurve.add(new Vector2(4000, 300));
		this.scoreCurve.add(new Vector2(8000, 500));
	}


	Random rand = new Random();
	private float[] lanes = {Constants.roadLowerBoundary + 60 * Constants.ry + Constants.carHeight, Helper.getScreenHeight()/2f - 60 * Constants.ry - Constants.carHeight,
			Constants.roadUpperBoundary, Constants.roadLowerBoundary};
	public float getLaneRandomY()
	{
		return lanes[rand.nextInt(lanes.length)];
	}
	
	@Override
	public void initGenSets() {
		Helper.println("Initializing generation line: " + this.getClass());
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeHeroDeath, 100);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				startY = getLaneRandomY();
				Helper.println("Generating Enemey at: " + 	startX);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addFallingRock(startX, startY, Constants.fallingRockWidth, Constants.fallingRockHeight, 10);
			}
		});
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeHeroDeath, 75);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				startY = getLaneRandomY();
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX, startY, Constants.smallCrackWidth * 1.5f, Constants.smallCrackHeight * 1.5f);
			}
		});
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeHeroDeath, 50);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				startY = getLaneRandomY();
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX, startY, Constants.smallCrackWidth, Constants.smallCrackHeight);
			}
		});
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeHeroDeath, 100);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
				startY = getLaneRandomY();
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX, startY, Constants.smallCrackWidth, Constants.smallCrackHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX+Constants.smallCrackWidth * 1.5f, startY+20*Constants.ry, Constants.smallCrackWidth, Constants.smallCrackHeight);
			}
		});

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeHeroDeath, 150);
			}
			
			@Override
			public void generate(float startX, float startY) {
				startY = getLaneRandomY();
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX, startY, Constants.smallCrackWidth, Constants.smallCrackHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX+Constants.smallCrackWidth, startY+20*Constants.ry, Constants.smallCrackWidth, Constants.smallCrackHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCrack(startX+Constants.smallCrackWidth *2f, startY, Constants.smallCrackWidth, Constants.smallCrackHeight);
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
