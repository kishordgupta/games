package com.rhymes.game.data;

public class Score {
	public int getCashCollected() {
		return cashCollected;
	}


	public void setCashCollected(int cashCollected) {
		this.cashCollected = cashCollected;
	}


	public int getCarCrashed() {
		return carCrashed;
	}


	public void setCarCrashed(int carCrashed) {
		this.carCrashed = carCrashed;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public float getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}


	public int cashCollected = 0;
	public int carCrashed = 0;
	public int score = 0;
	public float totalScore = 0;
	
	
	public Score()
	{
		carCrashed = 0;
		score = 0;
		cashCollected = 0;
		totalScore = 0;
	}
}
