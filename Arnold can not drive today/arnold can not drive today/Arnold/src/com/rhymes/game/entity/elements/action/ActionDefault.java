package com.rhymes.game.entity.elements.action;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class ActionDefault implements SolitareAction {

	@Override
	public String toString() {
		return "Action 2";
	}

	@Override
	public void act() {
		Helper.println("Action2 is acting");
		this.discard();
		Helper.println("Action2 discarded");
	}

	
	@Override
	public void discard() {
		((Deck)GlobalVars.ge.getCurrentStage()).currentAction = null;
	}

	@Override
	public void start() {
		if(((Deck)GlobalVars.ge.getCurrentStage()).currentAction == null)
		{
			((Deck)GlobalVars.ge.getCurrentStage()).currentAction = this;
		}
		else{
			((Deck)GlobalVars.ge.getCurrentStage()).currentAction.act();
		}
		
	}

	long time;
	@Override
	public void initialize() {
		time = System.currentTimeMillis();
		Helper.println("Time started: " + time);
	}

}
