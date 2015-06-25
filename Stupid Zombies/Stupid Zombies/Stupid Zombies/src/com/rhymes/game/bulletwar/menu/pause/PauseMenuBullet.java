package com.rhymes.game.bulletwar.menu.pause;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.menu.main.MainMenuBullet;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PauseMenuBullet extends StageBase{
	BackGroundUniversal bg, paused;
	ButtonStageLoad retry, resume;
	
	InteractionTouch interaction_touch;
	
	float x, y;

	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
	
		set_menu();
	}
	
	
	public void set_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsBulletWar.level_bg);
		this.elements.add(bg);	
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.paused).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f + 120f*AssetConstantsBulletWar.ratio_h;
		paused = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.paused).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.paused).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, 
				AssetConstantsBulletWar.paused);
		this.elements.add(paused);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.resume).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.resume).getRegionHeight()/2f*AssetConstantsBulletWar.ratio_h;
		resume = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.resume).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.resume).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, 
				AssetConstantsBulletWar.resume, 
				new MainMenuBullet());
		this.elements.add(resume);
		this.interaction_touch.subscribe(resume);
		
		y = y - (30f + Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionWidth()/2f)*AssetConstantsBulletWar.ratio_h;
		retry = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, 
				AssetConstantsBulletWar.retry, 
				new MainMenuBullet());
		this.elements.add(retry);
		this.interaction_touch.subscribe(retry);
		
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsBulletWar.level_bg);
		assetPack.addTexture(AssetConstantsBulletWar.paused);
		assetPack.addTexture(AssetConstantsBulletWar.resume);
		assetPack.addTexture(AssetConstantsBulletWar.retry);
		
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
