package com.rhymes.game.entity.elements.solitaire.scoring;

import com.rhymes.ge.core.stage.level.GameState;

public class ResultStandard extends ResultSolitaire{
	
	private final int deck_to_table = 5;
	private final int deck_to_final = 10;
	private final int table_to_final = 10;
	private final int turn_visible = 5;
	private final int final_table = - 15;
	
	private int total_points;
	
	public ResultStandard(){
		setTotal_points(0);
	}
	

	@Override
	public void reset() {
		setTotal_points(0);
		this.setState(GameState.PLAYING);

	}

	@Override
	public String toString() {
		return null;
	}
	

	public int getDeck_to_table() {
		return deck_to_table;
	}

	public int getDeck_to_final() {
		return deck_to_final;
	}

	public int getTable_to_final() {
		return table_to_final;
	}

	public int getTurn_visible() {
		return turn_visible;
	}

	public int getFinal_table() {
		return final_table;
	}

	public void setTotal_points(int total_points) {
		this.total_points = total_points;
	}

	public int getTotal_points() {
		return total_points;
	}

}
