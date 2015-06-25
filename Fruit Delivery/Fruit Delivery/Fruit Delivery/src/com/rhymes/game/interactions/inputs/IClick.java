package com.rhymes.game.interactions.inputs;


import com.badlogic.gdx.Gdx;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class IClick extends InteractionBase{

	@Override
	public void checkCondition(long elapsedTime) {
		
	}
	ElementBase e;
	Point p = new Point();
	@Override
	public void takeAction() {

		if(Gdx.input.justTouched()){
			p.x = Gdx.input.getX();
			p.y = Gdx.input.getY();
			for (InteractionCallbacks ic : this.elements) {
				if(ic instanceof ElementBase){
					e = (ElementBase) ic;
					if(e.getLeft() <= p.x && e.getRight()>= p.x && e.getTop() >= p.y && e.getBottom() <= p.y){
						Helper.println("taking action: " + ic);
						((InteractionTouchCallbacks)ic).onEvent(new Point(Gdx.input.getX(), Gdx.input.getY()));
					}
					
				}
				
			}
		}
		
	}

}
