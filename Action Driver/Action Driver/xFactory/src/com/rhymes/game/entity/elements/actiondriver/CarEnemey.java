package com.rhymes.game.entity.elements.actiondriver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.actiondriver.AniFlashTransparency;
import com.rhymes.game.entity.animations.actiondriver.AniShrink;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.interactions.actiondriver.ICCarController;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class CarEnemey extends Car implements ICCarController{
	@Override
	public void render() {
		if(this.aniFlashTransparency != null){
			aniFlashTransparency.step(GlobalVars.ge.stepTime);
			if(aniFlashTransparency.isFinished())
				this.aniFlashTransparency = null;
		}
		
		super.render();
		
		GlobalVars.ge.getScreen().setColor(Color.WHITE);
	}


	float prevX = 0;
	@Override
	public void step(long stepTime) {
		super.step(stepTime);
//		Helper.println("\nEnemey car id: " + this.getId() + " x: " + this.getX());
		if(prevX == this.getX())
			Gdx.app.exit();
		prevX = this.x;
		if(this.aniShrink != null){
			aniShrink.step(stepTime);
			if(aniShrink.isFinished())
				die();
		}
	}


	private AniShrink aniShrink;
	private AniFlashTransparency aniFlashTransparency;

	@Override
	public void onCollision(ICSingleCollisionCallbacks collider) {
		super.onCollision(collider);
		if(collider instanceof CarHero && aniShrink == null)
		{
			((LevelActionDriver)GlobalVars.ge.getCurrentStage()).increaseScore(10);
			StartupInfo.sound_handler.playSound(soundType.SOUND_OBSTACLES);
			doDamage(Constants.enemeyDamageAfterHit);
		
			if(this.aniFlashTransparency == null)
				this.aniFlashTransparency = new AniFlashTransparency(this, 6000, 400);
			else
				this.aniFlashTransparency.reset();
		}
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}


	private float health = 100f;
	@Override
	public void moveCar() {
		super.moveCar();
		if(this.getRight() < Helper.getCameraX()){
			GlobalVars.ge.getCurrentStage().postDestroyed(this);
		}
			
	}

	public CarEnemey(float x, float y, float width, float height, int zIndex,
			float speedX, float health) {
		super(x, y, width, height, zIndex, speedX);
		this.health = health;
	}
	

	public void die()
	{
		StartupInfo.sound_handler.playSound(soundType.SOUND_SCORE);
		Helper.println("Enemey car died");
		GlobalVars.ge.getCurrentStage().postDestroyed(this);
	}
	public void doDamage(float damage)
	{
		this.health -= damage;
		if( this.health <=0 ){
			this.aniShrink = new AniShrink(this, 10, 10);
			((LevelActionDriver)GlobalVars.ge.getCurrentStage()).addCarCrashed(1);
		}
	}
		
	
}
