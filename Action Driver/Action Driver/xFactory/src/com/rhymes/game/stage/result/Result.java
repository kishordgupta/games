package com.rhymes.game.stage.result;

import com.rhymes.ge.core.stage.level.GameState;

public abstract class Result {
	protected int state = GameState.PLAYING;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Result()
	{
		
	}
	public abstract void reset();
	public abstract String toString();
}
