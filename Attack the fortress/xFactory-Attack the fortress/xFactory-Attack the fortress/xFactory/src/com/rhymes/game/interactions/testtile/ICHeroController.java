package com.rhymes.game.interactions.testtile;

import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICHeroController extends InteractionCallbacks {
	public abstract void onMove(int type);
}
