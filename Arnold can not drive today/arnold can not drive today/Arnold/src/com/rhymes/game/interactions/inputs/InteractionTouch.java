package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;

public class InteractionTouch extends InteractionBase{
//	long debounceTime = 200;
//	long elapsedTime = 0;
	
	@Override
	public void checkCondition(long elapsedTime) {
//		this.elapsedTime+=elapsedTime;
	}

	InteractionCallbacks ic;
	int i;
	
	@Override
	public void takeAction() {
		/*
		if(elapsedTime < debounceTime){
			return;
		}
		
		elapsedTime = 0;*/
		
//		Helper.println("takeAction() in Action!");
		
		i = 0;
		if(Gdx.input.justTouched()){
//			Helper.println("\n\ntaking action: " + new Point(Gdx.input.getX() / GlobalVars.ge.getRenderer().getScaleX(),
//					(Gdx.graphics.getHeight() - Gdx.input.getY() ) / GlobalVars.ge.getRenderer().getScaleY() ).toString());
			for (i = 0; i < elements.size; i++) {
				ic = elements.get(i);
//				((InteractionTouchCallbacks)ic).onEvent(new Point(Gdx.input.getX() / GlobalVars.ge.getRenderer().getScaleX(), (Gdx.graphics.getHeight() - Gdx.input.getY() ) / GlobalVars.ge.getRenderer().getScaleY() ));
				((InteractionTouchCallbacks)ic).onEvent(new Point(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY() )));
				
			}
		}
		
	}

}
