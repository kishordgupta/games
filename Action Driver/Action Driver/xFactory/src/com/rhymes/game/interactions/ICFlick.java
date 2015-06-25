package com.rhymes.game.interactions;

import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;

public interface ICFlick extends InteractionCallbacks {
	public void onFlick(float time, float distance, float angle, Point startPoint, Point endPoint);
}
