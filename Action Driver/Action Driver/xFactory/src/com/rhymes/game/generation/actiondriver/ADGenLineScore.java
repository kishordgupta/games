package com.rhymes.game.generation.actiondriver;

import java.util.Random;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.badlogic.gdx.math.Vector2;
import com.rhymes.game.data.Constants;
import com.rhymes.game.generation.GenerationLine;
import com.rhymes.game.generation.GenerationSet;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class ADGenLineScore extends GenerationLine{
	float stepDistance = 1500* Constants.rx;
	float genPointStep = 400 * Constants.rx;

	public ADGenLineScore(String scoreType) {
		super(scoreType);
	}

	@Override
	public void initScoreCurve() {
		Helper.println("initializing score curve: " + this.getClass());
		this.scoreCurve.add(new Vector2(0, 200));
		this.scoreCurve.add(new Vector2(2000, 300));
		this.scoreCurve.add(new Vector2(4000, 400));
		this.scoreCurve.add(new Vector2(6000, 600));
		this.scoreCurve.add(new Vector2(8000, 800));
		this.scoreCurve.add(new Vector2(10000, 1000));
		this.scoreCurve.add(new Vector2(12000, 1200));
	}


	
	@Override
	public void initGenSets() {
		Helper.println("Initializing generation line: " + this.getClass());
//		this.genSets.add(new ADGenSetScore());
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 30);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addPeeble(startX, startY, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addPeeble(startX + Constants.boxWidth + 5 * Constants.rx, startY + 30 * Constants.ry, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addPeeble(startX+ Constants.boxWidth * 2 + 5 * Constants.rx, startY, Constants.boxWidth, Constants.boxHeight);
//				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX + Constants.cashWidth + 10 * Constants.rx, 
//						startY+Constants.cashHeight + 10 * Constants.ry);
			}
		});
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 20);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addPeeble(startX, startY, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addPeeble(startX+ Constants.boxWidth + 10 * Constants.rx, startY, Constants.boxWidth, Constants.boxHeight);
//				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX + Constants.cashWidth + 10 * Constants.rx, 
//						startY+Constants.cashHeight + 10 * Constants.ry);
			}
		});
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 10);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addDrum(startX, startX % 2 == 0 ? Constants.roadLowerBoundary : Constants.roadUpperBoundary- Constants.drumHeight, 
						Constants.drumWidth, Constants.drumHeight);
//				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX + Constants.cashWidth + 10 * Constants.rx, 
//						startY+Constants.cashHeight + 10 * Constants.ry);
			}
		});
		
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 200);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX, startY, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX + Constants.boxWidth + 20 * Constants.rx, startY + 20 * Constants.ry, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX + Constants.boxWidth * 2 + 20 * Constants.rx, startY + 20 * Constants.ry, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX + Constants.boxWidth * 3 + 20 * Constants.rx, startY , Constants.boxWidth, Constants.boxHeight);
			}
		});
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 100);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX, startY, Constants.boxWidth, Constants.boxHeight);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addBox(startX + Constants.boxWidth + 50 * Constants.rx, startY + 30 * Constants.ry, Constants.boxWidth, Constants.boxHeight);
//				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX + Constants.cashWidth + 10 * Constants.rx, 
//						startY+Constants.cashHeight + 10 * Constants.ry);
			}
		});

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 20);
				
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX, startY);
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCone(startX + Constants.cashWidth + 20 * Constants.rx, 
						startY+Constants.cashHeight + 20 * Constants.ry);
			}
		});
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 150);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY+Constants.cashHeight + 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth * 2f+ 30 * Constants.rx,
						startY, Constants.COLLIDABLE_CASH_1);
			}
		});
		
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 350);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY+Constants.cashHeight + 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth * 2f+ 30 * Constants.rx,
						startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY-Constants.cashHeight - 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);

				startX += (Constants.cashWidth + 20 * Constants.rx) * 2f;
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY+Constants.cashHeight + 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth * 2f+ 30 * Constants.rx,
						startY, Constants.COLLIDABLE_CASH_1);
			}
		});
		
		

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 200);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY+Constants.cashHeight + 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth * 2f+ 30 * Constants.rx,
						startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY-Constants.cashHeight - 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
			}
		});
		
		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 200);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY+Constants.cashHeight + 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth * 2f+ 30 * Constants.rx,
						startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 20 * Constants.rx,
						startY-Constants.cashHeight - 20 * Constants.ry, Constants.COLLIDABLE_CASH_1);
			}
		});

		this.genSets.add(new GenerationSet() {
			
			@Override
			public void initScoreValues() {
				this.setValue(ADGenerationController.scoreTypeScore, 100);
			}
			
			@Override
			public void generate(float startX, float startY) {
//				Helper.println("Gen 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX, startY, Constants.COLLIDABLE_CASH_1);
				
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCash(startX+Constants.cashWidth + 10 * Constants.rx,
						startY+Constants.cashHeight + 10 * Constants.ry, Constants.COLLIDABLE_CASH_1);				
			}
		});
	}

	@Override
	public void initGenPoints() {
		this.genPointList.put("def", new Float(0));
	}

	Float f ;
	@Override
	public void setGenPointToNext() {
		f = new Float(this.genPointList.get("def") + this.genPointStep);
		this.genPointList.put("def", f);
	}

	@Override
	public float getGenPoint() {
		return this.genPointList.get("def").floatValue();
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
		this.genPointList.put("def", f);
	}

}
