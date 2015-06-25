package com.rhymes.game.interactions.inputs;


import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;

public interface ICClick extends InteractionCallbacks{
	public void onEvent(Point hitPoint);
}
