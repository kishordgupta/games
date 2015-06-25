package com.rhymes.game.stage.result;

import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ResultBTRMAP extends Result{
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


	public ResultBTRMAP()
	{
		pearlCollected = 0;
	}
	
	
	@Override
	public void reset() {
		pearlCollected = 0;
		
		this.setState(GameState.PLAYING);
	}

	public void consumePearl(){
		this.pearlCollected++;
		Helper.println("Pearl Collected: " + pearlCollected + " Total Pearl: " + ((XLevel)GlobalVars.ge.getCurrentStage()).totalPearls ) ;
		if(this.pearlCollected == ((XLevel)GlobalVars.ge.getCurrentStage()).totalPearls)
			this.setState(GameState.OVER);
	}

	@Override
	public String toString() {
		return null;
	}
}
