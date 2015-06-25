package com.rhymes.game.entity.elements.actiondriver.collidable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.helpers.Helper;

public class ADCollidable extends Collidable {

	private AnimationBase normalAnimation;
	public AnimationBase getNormalAnimation() {
		return normalAnimation;
	}

	public void setNormalAnimation(AnimationBase normalAnimation) {
		this.normalAnimation = normalAnimation;
	}

	public AnimationBase getFinishAnimation() {
		return finishAnimation;
	}

	public void setFinishAnimation(AnimationBase finishAnimation) {
		this.finishAnimation = finishAnimation;
	}

	public AnimationBase getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationBase animation) {
		this.animation = animation;
	}

	private AnimationBase finishAnimation;
	private AnimationBase animation;
	private int type = 0;
	
	private CollisionHandler collisionHandler;
	
	public CollisionHandler getCollisionHandler() {
		return collisionHandler;
	}

	public void setCollisionHandler(CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
	}

	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		super.onCollision(collider);
		this.animation = finishAnimation;
		if(this.collisionHandler != null)
			this.collisionHandler.onCollision((ElementBase) collider, this);
	}
	
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		if(this.animation != null){
			animation.step(stepTime);
			if(this.finishAnimation != null)
				if(this.animation == this.finishAnimation)
					if(this.animation.isFinished())
						this.die();
		}
	}
	
	private void die() {
		GlobalVars.ge.getCurrentStage().postDestroyed(this);
	}

	@Override
	public void init() {
		super.init();
		
		if(this.normalAnimation != null)
			this.normalAnimation.init();
		
		if(this.finishAnimation != null)
			this.finishAnimation.init();
	}
	
	public ADCollidable(float x, float y, float width, float height, int zIndex, int type, TextureRegion image) {
		super(x, y, width * 0.6f, height* 0.6f, zIndex);
		this.normalAnimation = normalAnimation;
		this.finishAnimation = finishAnimation;
		this.animation = normalAnimation;
		this.image = image;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
