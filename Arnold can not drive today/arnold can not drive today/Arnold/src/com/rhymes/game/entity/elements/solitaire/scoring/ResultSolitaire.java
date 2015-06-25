package com.rhymes.game.entity.elements.solitaire.scoring;

import com.rhymes.ge.core.stage.level.GameState;

public abstract class ResultSolitaire {
	protected int state = GameState.PLAYING;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ResultSolitaire()
	{
		
	}
	public abstract void reset();
	public abstract String toString();
}
