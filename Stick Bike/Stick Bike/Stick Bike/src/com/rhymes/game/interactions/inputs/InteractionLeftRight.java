package com.rhymes.game.interactions.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public class InteractionLeftRight extends InteractionBase{
	public static int NONE = -1;
	public static int LEFT = 0;
	public static int RIGHT = 1;
	
	
	@Override
	public void checkCondition(long elapsedTime) {
		
	}

	@Override
	public void takeAction() {
		float accX = 0;
		int event = 0;
		if (Gdx.app.getType() == Application.ApplicationType.Android) {			
			accX = Gdx.input.getAccelerometerX();
			
			if(accX < 0)
				event = RIGHT;
			else if (accX == 0)
				event = NONE;
			else
				event = LEFT;
			
		}
		else if(Gdx.app.getType() == Application.ApplicationType.Desktop)
		{
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				event = LEFT;
			}
			else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				event = RIGHT;
			}
			else
				event = NONE;
		}
		
		for (InteractionCallbacks ic : this.elements) {
			((InteractionLeftRightCallbacks)ic).onEvent(event);
		}
		
	}

}
