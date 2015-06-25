package com.rhymes.game.stage.result;

import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ResultBTRClassic extends Result {
	private int levelStarNumber = 3;
	private int starCollected = 0;
	

	public void consumeStar(){
		this.starCollected++;
		Helper.println("Start Collected: " + starCollected + " LevelstarNubm: " + this.levelStarNumber ) ;
		if(this.starCollected == this.levelStarNumber)
		{
			this.setState(GameState.OVER);
		}
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

	
	
	@Override
	public void reset() {
		starCollected = 0;
		this.setState(GameState.PLAYING);
	}


	@Override
	public String toString() {
		return "Star Collected: " + this.starCollected ;
	}
}
