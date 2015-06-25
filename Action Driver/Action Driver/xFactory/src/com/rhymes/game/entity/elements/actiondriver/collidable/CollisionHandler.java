package com.rhymes.game.entity.elements.actiondriver.collidable;

import com.rhymes.ge.core.entity.elements.ElementBase;

public interface CollisionHandler {
	public void onCollision(ElementBase collider, ADCollidable source);
}
