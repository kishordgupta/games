package com.rhymes.game.entity.elements.nonphysical;

import javax.print.attribute.standard.MediaSize.Other;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.physical.Ball;
import com.rhymes.game.entity.elements.physical.CollisionListener;
import com.rhymes.game.entity.elements.physical.PhysicsBody;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ResultBounce extends Result {
	private int shotNumber = 3;
	public int bounceCollected = 0;
	private int totalBounceCollected = 0;
	private int shotTaken=0;
	private int totalBounceCollectedPoint = 0;
	private Ball ball;
//	boolean isImageGroundScore = true;
//	boolean isImageBallStickScore = true;
	private int levelScore=0;
	private static int prevShotScore=0;
	
	private int targetScore = 2000;
	private int basketScore = 999;
	private int highScore = 0;

	/**
	 * @param highScore the highScore to set
	 */
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getTargetScore() {
		return targetScore;
	}
	public void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}
	public void consumeBounce(int bounceCollected){
		this.setBounceCollected(bounceCollected);
//		this.totalBounceCollected = totalBounceCollected+bounceCollected;
//		this.setTotalBounceCollected(bounceCollected);
//		prevShotScore = totalBounceCollected;
//		this.totalBounceCollected = bounceCollected+totalBounceCollected;
		Helper.println("Bounce Collected: " + bounceCollected ) ;
		
	}
	public void scoreCollectionBasket() {
		// TODO Auto-generated method stub
		int scr = this.getBounceCollected();
		scr = scr + basketScore;
//		this.setTotalBounceCollected(scr);
//		this.consumeBounce(scr);
		this.setBounceCollected(scr);
	}
	public void scoreCollectionBallStick() {
		// TODO Auto-generated method stub
		int scr = this.getBounceCollected();
		scr = scr + 50;
//		this.setBounceCollected(scr);
//		this.consumeBounce(scr);
		this.bounceCollected = bounceCollected -100;
	}
	public void scoreCollectionGround() {
		// TODO Auto-generated method stub
		int scr = this.getBounceCollected();
		scr = scr + 100;
//		this.setBounceCollected(scr);
//		this.consumeBounce(scr);
		this.setBounceCollected(scr);
	}
	public void countShot(){
		
		this.shotTaken++;
		
//		Helper.println("Shot count " + shotTaken) ;
		
//		this.calculateScore(shotTaken,totalBounceCollected);
//		levelScore = levelScore + prevShotScore;
//		this.setTotalBounceCollected(bounceCollected);
		levelScore = levelScore + bounceCollected;
		this.setLevelScore(levelScore);
		this.setTotalBounceCollectedPoint(levelScore);
		((BounceTest)GlobalVars.ge.getCurrentStage()).loadTopElements();
		Helper.println( "TotalScore"+totalBounceCollectedPoint);

		if(this.shotTaken > this.shotNumber-1)
		{
//			levelScore =  prevShotScore;
			this.reset();
//			if(Constants.levelSelected==1)
			((BounceTest)GlobalVars.ge.getCurrentStage()).gameOver();
//			else if(Constants.levelSelected==2)
//				((BounceTestLevel2)GlobalVars.ge.getCurrentStage()).gameOver();
		}
//		else
//			levelScore = levelScore + prevShotScore;
		
	}
	public int getTotalBounceCollectedPoint() {
		return totalBounceCollectedPoint;
	}
	public void setTotalBounceCollectedPoint(int totalBounceCollectedPoint) {
		this.totalBounceCollectedPoint = totalBounceCollectedPoint;
	}

	public int getBounceCollected() {
		return bounceCollected;
	}

	public void setBounceCollected(int bounceCollected) {
		this.bounceCollected = bounceCollected;
	}

	public int getTotalBounceCollected() {
		return totalBounceCollected;
	}

	public void setTotalBounceCollected(int totalBounceCollected) {
		this.totalBounceCollected = totalBounceCollected;
	}

	public int getShotNumber() {
		return shotNumber;
	}

	public void setShotNumber(int shotNumber) {
		this.shotNumber = shotNumber;
	}
	
	@Override
	public void reset() {
		bounceCollected = 0;
//		totalBounceCollected = 0;
		shotTaken = 0; 
		if(highScore<levelScore)
		{
		this.setHighScore(levelScore);	
		}
		else {
			this.setHighScore(highScore);
		}
//		levelScore = 9999;
		this.setState(GameState.PLAYING);
	}

	@Override
	public String toString() {
		return "Bounce Collected: " + this.bounceCollected ;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public int getShotTaken() {
		return shotTaken;
	}

	public int getLevelScore() {
		return levelScore;
	}

	public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

	public void setShotTaken(int shotTaken) {
		this.shotTaken = shotTaken;
	}
	public int getHighScore() {
		// TODO Auto-generated method stub
		return highScore;
	}



}
