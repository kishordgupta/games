package com.rhymes.game.entity.elements.solitaire.scoring;

import com.rhymes.ge.core.stage.level.GameState;

public class ResultVegas extends ResultSolitaire{
	private final int player_to_dealer = - 52;
	private final int card_to_final = 5;
	private final int max_profit = 208;
	
	private int total_profit;
	
	
	public ResultVegas(){
		setTotal_profit(- 52);
	}
	
	@Override
	public void reset() {
		setTotal_profit(- 52);
		this.setState(GameState.PLAYING);
	}

	@Override
	public String toString() {
		return null;
	}

	public int getPlayer_to_dealer() {
		return player_to_dealer;
	}

	public int getCard_to_final() {
		return card_to_final;
	}

	public int getMax_profit() {
		return max_profit;
	}

	public void setTotal_profit(int total_profit) {
		this.total_profit = total_profit;
		
		if(total_profit > max_profit){
			this.total_profit = max_profit;
		}	
	}

	public int getTotal_profit() {
		return total_profit;
	}

}
