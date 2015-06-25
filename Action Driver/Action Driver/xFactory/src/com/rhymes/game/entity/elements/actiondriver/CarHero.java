package com.rhymes.game.entity.elements.actiondriver;


import com.badlogic.gdx.graphics.Color;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.actiondriver.AniCarSteepJump;
import com.rhymes.game.entity.animations.actiondriver.AniShrink;
import com.rhymes.game.entity.elements.actiondriver.collidable.ADCollidable;
import com.rhymes.game.interactions.ICFlick;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.actiondriver.ICCarController;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.animations.AnimationBase;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class CarHero extends Car implements ICCarController{
	@Override
	public void render() {
//		GlobalVars.ge.getScreen().setColor(Color.luminanceAlpha(1f, 1f));
//		GlobalVars.ge.getScreen().setColor(Color.GRAY);
		super.render();
		GlobalVars.ge.getScreen().setColor(Color.WHITE);
	}

	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		if(this.animation != null)
		{
			if(this.animation == aniShrink)
				if(this.animation.isFinished()){
					die();
					return;
				}
//			if(this.animation == aniCarSteepJump)
//				if(this.animation.isFinished())
//					GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, ISingleCollision.class);
			this.animation.step(stepTime);
		}
	}

	private AniShrink aniShrink;
	private AniCarSteepJump aniCarSteepJump;
	private AnimationBase animation;
	
	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		super.onCollision(collider);
		
		if(collider instanceof ADCollidable)
		{
			switch (((ADCollidable)collider).getType()) {
			case Constants.COLLIDABLE_CRACK:
				StartupInfo.sound_handler.playSound(soundType.SOUND_DIE);
//				Helper.println("Collided with crack");
				this.animation = aniShrink;
				this.stopCar();
//				die();
				break;
			case Constants.COLLIDABLE_CASH_1:
				StartupInfo.sound_handler.playSound(soundType.SOUND_SCORE);
//				Helper.println("Collided with cash 1");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCashCollected(Constants.CASH_AMOUNT_1);
				break;
			case Constants.COLLIDABLE_CASH_2:
				StartupInfo.sound_handler.playSound(soundType.SOUND_SCORE);
//				Helper.println("Collided with cash 2");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCashCollected(Constants.CASH_AMOUNT_2);
				break;
			case Constants.COLLIDABLE_CONE:
				StartupInfo.sound_handler.playSound(soundType.SOUND_OBSTACLES);
//				Helper.println("Collided with cone");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).increaseScore(Constants.OBSTACLES_SCORE);
				break;
			case Constants.COLLIDABLE_BOX:
				StartupInfo.sound_handler.playSound(soundType.SOUND_OBSTACLES);
//				Helper.println("Collided with box");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).increaseScore(Constants.GIFT_SCORE);
				break;
			case Constants.COLLIDABLE_DRUM:
				StartupInfo.sound_handler.playSound(soundType.SOUND_OBSTACLES);
//				Helper.println("Collided with drum");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).increaseScore(Constants.OBSTACLES_SCORE);
				break;
			case Constants.COLLIDABLE_PEEBLE:
				StartupInfo.sound_handler.playSound(soundType.SOUND_OBSTACLES);
//				Helper.println("Collided with peeble");
				((LevelActionDriver)GlobalVars.ge.getCurrentStage()).increaseScore(Constants.OBSTACLES_SCORE);
				break;
			case Constants.COLLIDABLE_FALLING_ROCK:
				StartupInfo.sound_handler.playSound(soundType.SOUND_DIE);
//				Helper.println("Collided with falling rock");
				this.animation = aniShrink;
				this.stopCar();
//				die();
				break;
			case Constants.COLLIDABLE_STEEP_JUMPER:
//				Helper.println("Collided with steep jumper");
				this.animation = aniCarSteepJump;
//				GlobalVars.ge.getCurrentStage().unSubscribeToInteraction(this, ISingleCollision.class);
				break;
			default:
				break;
			}
		}
	}

	float speed;
	float lastCheckPoint = 0;
	@Override
	public void moveCar() {
		super.moveCar();
		Helper.getCamera().position.x = this.x+this.width * 1.5f ;
		
		if( (getTotalDistance() - lastCheckPoint) > 3000 ){
			changeSpeed(1 * Constants.rx, 0);
//			Constants.carMaxSpeedY+=0.3f;
			Helper.println("Switched Gear: " + getSpeedX());
			lastCheckPoint = getTotalDistance();
//			((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addGearSwitchText((int)(this.getSpeedX() - Constants.carStartingSpeedX + 1) + "X");
		}
	}

	public CarHero(float x, float y, float width, float height, int zIndex,
			float speedX) {
		super(x, y, width, height, zIndex, speedX);
		this.aniShrink = new AniShrink(this, 7, 7);
		this.aniCarSteepJump = new AniCarSteepJump(this, 10, 200);
		
		Helper.println("Car generating using speed: " + speedX);
	}
	
	public void die()
	{
		Helper.println("Car hero died!");		
//		Helper.println("Total score: " + );
//		GlobalVars.ge.getCurrentStage().pause();
		GlobalVars.ge.getCurrentStage().postDestroyed(this);
		((LevelActionDriver)GlobalVars.ge.getCurrentStage()).gameOver();
//		StartupInfo.sound_handler.stopSound(soundType.SOUND_CAR);
	}
	
	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(AssetConstants.IMG_CAR);
//		StartupInfo.sound_handler.playSoundLooping(soundType.SOUND_CAR);
	}
	
}
