package com.rhymes.game.stage.result;

import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ResultBTRTime extends Result {
	private long expireTime = 0;
	private int elapsedTime = 0;

	private int levelStarNumber = 3;
	private int starCollected = 0;

	private int levelPearlNumber = 2;
	
	public int getLevelPearlNumber() {
		return levelPearlNumber;
	}

	public void setLevelPearlNumber(int levelPearlNumber) {
		this.levelPearlNumber = levelPearlNumber;
	}

	public int getPearlCollected() {
		return pearlCollected;
	}

	public void setPearlCollected(int pearlCollected) {
		this.pearlCollected = pearlCollected;
	}

	private int pearlCollected = 0;

	public void consumePearl(){
		this.pearlCollected++;
//		Helper.println("Pearl Collected: " + pearlCollected + " Total Pearl: " + ((XLevel)GlobalVars.ge.getCurrentStage()).totalPearls ) ;
	}

	public void consumeStar(){
		this.starCollected++;
//		Helper.println("Start Collected: " + starCollected + " LevelstarNubm: " + this.levelStarNumber ) ;
	}

	
	public int getStarCollected() {
		return starCollected;
	}

	public void setStarCollected(int starCollected) {
		this.starCollected = starCollected;
	}

	public int getLevelStarNumber() {
		return levelStarNumber;
	}

	public void setLevelStarNumber(int levelStarNumber) {
		this.levelStarNumber = levelStarNumber;
	}

	
	

	
	public long getExpireTime() {
		return expireTime;
	}


	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}


	public int getElapsedTime() {
		return elapsedTime;
	}


	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public void reset() {
		elapsedTime = 0;
		this.state = GameState.PLAYING;
		this.pearlCollected = 0;
		this.starCollected = 0;
	}


	@Override
	public String toString() {
		return "Elapsed time: " + this.elapsedTime + " StarCollected: " + this.starCollected + " PearlCollected: " + this.pearlCollected;
	}
	
	public void step(long stepTime)
	{
		if(this.state == GameState.OVER)
			return;
		this.elapsedTime += stepTime;
		if(this.elapsedTime > this.expireTime)
			this.setState(GameState.OVER);
	}
}