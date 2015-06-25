package com.rhymes.game.stage.result;

import com.rhymes.helpers.Helper;

public class ResultActionDriver extends Result {
	private int cashCollected;
	private int carPassed;
	private int carCrashed;
	private float roundDuration;
	private int score;
	
	public float scoreX = 1;
	
	public ResultActionDriver() {
		this.carCrashed = 0;
		this.carPassed = 0;
		this.roundDuration = 0;
		this.score = 0;
	}

	public int getCarPassed() {
		return carPassed;
	}

	public void setCarPassed(int carPassed) {
		this.carPassed = carPassed;
	}

	public int getCarCrashed() {
		return carCrashed;
	}

	public void setCarCrashed(int carCrashed) {
		this.carCrashed = carCrashed;
	}

	public float getRoundDuration() {
		return roundDuration;
	}

	public void setRoundDuration(float roundDuration) {
		this.roundDuration = roundDuration;
	}
	
	public void increaseRoundDuration(float roundDuration) {
		this.roundDuration += roundDuration;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore(int score) {
		this.score+= score* scoreX;
	}



	@Override
	public void reset() {
		this.carCrashed = 0;
		this.carPassed = 0;
		this.roundDuration = 0;
		this.score = 0;
		this.cashCollected = 0;
	}

	@Override
	public String toString() {
		return null;
	}

	public int getCashCollected() {
		return cashCollected;
	}

	public void setCashCollected(int cashCollected) {
		this.cashCollected = cashCollected;
	}
	
	public void increaseCash(int cash) {
		this.cashCollected +=cash * scoreX;
//		Helper.println("New cash colleced, not balance: " + this.cashCollected);
	}

	public float getTotalScore()
	{
		return this.score + this.carCrashed * 200 + this.cashCollected /*+ this.roundDuration*/;
	}
	
}
