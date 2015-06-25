package com.rhymes.game.pinball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.game.interactions.inputs.InteractionLeftRightCallbacks;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public class ILeftRight extends InteractionBase {

	public static final int CTL_LEFT = 0;
	public static final int CTL_RIGHT = 1;
	public static final int CTL_NONE = 2;
	
	private int ctl = CTL_NONE;
	
	@Override
	public void checkCondition(long elapsedTime) {
		if(Gdx.app.getType() == ApplicationType.Desktop){
			
			if (Gdx.input.isKeyPressed(Keys.LEFT))
					ctl = CTL_LEFT;
			else if (Gdx.input.isKeyPressed(Keys.RIGHT))
				ctl = CTL_RIGHT;
			else
				ctl = CTL_NONE;
			
		}
		else if(Gdx.app.getType() == ApplicationType.Android){
			
			if(Gdx.input.getAccelerometerX() < 0)
				ctl = CTL_LEFT;
			else if(Gdx.input.getAccelerometerX() > 0)
				ctl = CTL_RIGHT;
			
		}
	}

	@Override
	public void takeAction() {
			for (InteractionCallbacks ic : this.elements) {
				((ICLeftRight)ic).onEvent(ctl);
			}
	}

}
