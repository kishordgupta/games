package com.rhymes.game.interactions.actiondriver;

public interface ICCarController {
	public void changeSpeed(float dx, float dy);
	public void setSpeed(float speedX, float speedY);
	public float getSpeedX();
	public float getSpeedY();
}
