package com.rhymes.game.entity.animations.actiondriver;

import com.rhymes.game.data.Constants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class AniFlashTransparency extends AnimationBase {
	float counter = 0, flashTimeCounter = 0, timeBetweenFlash = 0, duration;
	boolean flash = true;
	public AniFlashTransparency(ElementBase element, float duration, float timeBetweenFlash) {
		super(element);
		this.timeBetweenFlash = timeBetweenFlash;
		this.duration = duration;
	}

	@Override
	public void init() {

	}
	
	

	@Override
	public void step(long stepTime) {
		if(this.finished)
			return;
		
		flashTimeCounter+=stepTime;
		
		if(flashTimeCounter > timeBetweenFlash)
		{
			flashTimeCounter = 0;
			if(flash)
			{
				flash = false;
				Helper.println("Switching: " + flash);
			}
			else
			{
				flash = true;
				Helper.println("Switching: " + flash);
			}
		}

		if(flash)
			GlobalVars.ge.getScreen().setColor(1,1,1, 0.3f);
		else
			GlobalVars.ge.getScreen().setColor(1,1,1, 1f);
		
		this.counter+=stepTime;
		if(counter > duration)
			setFinished(true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void reset() {
		counter = 0;
	}
}
