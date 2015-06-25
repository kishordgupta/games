package com.rhymes.game.interactions;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;

public class InteractionTest extends InteractionBase {

	@Override
	public void checkCondition(long elapsedTime) {

	}

	@Override
	public void takeAction() {
		for(InteractionCallbacks element:this.elements)
		{
			((InteractionTestCallbacks)element).onTest();
		}
	}

}
