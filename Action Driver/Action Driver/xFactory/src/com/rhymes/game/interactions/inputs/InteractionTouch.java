package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class InteractionTouch extends InteractionBase{
	long debounceTime = 1000;
	long elapsedTime = 0;
	boolean firstTime = true;
	
	@Override
	public void checkCondition(long elapsedTime) {
		this.elapsedTime+=elapsedTime;
	}

	InteractionCallbacks ic;
	int i;
	
	@Override
	public void takeAction() {
		if(firstTime){
			if(elapsedTime < debounceTime){
//				Helper.println("takeAction() return!");
				return;
			}
			firstTime = false;
			elapsedTime = 0;
		}
		
		
		
		i = 0;
		if(Gdx.input.justTouched()){
//			StartupInfo.sound_handler.playSound(soundType.SOUND_TOUCH);
//			Helper.println("\n\ntaking action: " + new Point(Gdx.input.getX() / GlobalVars.ge.getRenderer().getScaleX(),
//					(Gdx.graphics.getHeight() - Gdx.input.getY() ) / GlobalVars.ge.getRenderer().getScaleY() ).toString());
			for (i = 0; i < elements.size; i++) {
				ic = elements.get(i);
//				((InteractionTouchCallbacks)ic).onEvent(new Point(Gdx.input.getX() / GlobalVars.ge.getRenderer().getScaleX(), (Gdx.graphics.getHeight() - Gdx.input.getY() ) / GlobalVars.ge.getRenderer().getScaleY() ));
				((InteractionTouchCallbacks)ic).onEvent
				(new Point(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY() )));
			}
		}
		
	}

}
