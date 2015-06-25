package com.rhymes.game.interactions;

import com.rhymes.ge.core.interactions.InteractionCallbacks;

public interface ICSingleCollisionCallbacks extends InteractionCallbacks{
	public void onCollision(ICSingleCollisionCallbacks collider);
	public boolean isCollided();
}
