package com.rhymes.game.entity.elements.actiondriver.collidable;

import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class Collidable extends ElementBase implements
		ICSingleCollisionCallbacks {

	public Collidable(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		collided = true;
	}

	boolean collided = false;
	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void init() {

	}

	@Override
	public void getAssets(AssetPack assetPack) {

	}

}
