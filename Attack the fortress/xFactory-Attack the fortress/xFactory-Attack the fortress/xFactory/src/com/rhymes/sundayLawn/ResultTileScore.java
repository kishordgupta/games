package com.rhymes.sundayLawn;

import com.rhymes.game.entity.elements.testtile.HeroTile;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.stage.level.GameState;

public class ResultTileScore extends Result {
	private int levelStarNumber = 3;
	private int starCollected = 0;
	private int prevTileNumber=0;
	public static int count=0;
	private int score=(int) LevelInfo.totalScore;
	
	

	public void consumeTile(boolean isShit){
		if(!HeroTile.isTimecount){
			score+=count*count;
			count=0;
		}
		
		else if(prevTileNumber!=HeroTile.tileMowedNumber){
			count++;
		}
		else{
		score+=count*count;
		count=0;
		}
		if(isShit){
			score-=250;
			if(score<0)
				score=0;
		}
		this.starCollected = score;
		prevTileNumber=HeroTile.tileMowedNumber;
//		Helper.println("Start Collected: " + starCollected + " LevelstarNubm: " + starCollected );
//		if(this.starCollected == this.levelStarNumber)
//		{
//			this.setState(GameState.OVER);
//		}
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
