package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class InteractionTouchCompas extends InteractionBase{
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
		/*if(Gdx.input.justTouched())			
			for (i = 0; i < elements.size; i++) {
				ic = elements.get(i);
//				Helper.println("\n\ntaking action just touched: " + ic);
				((InteractionTouchCallbacks)ic).onTouch(new Point(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
			}
		else*/ 
		if(Gdx.input.isTouched())			
			for (i = 0; i < elements.size; i++) {
				ic = elements.get(i);
//				Helper.println("\n\ntaking action touched: " + ic);
				((InteractionTouchCompasCallbacks)ic).onTouch(new Point(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
			}
		
//		Gdx.input.getInputProcessor().
	}

}
