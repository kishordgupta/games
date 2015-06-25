package com.rhymes.game.bulletwar.menu.main;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.PreferenceBullet;
import com.rhymes.game.bulletwar.menu.levelpack.PackMenuBllet;
import com.rhymes.game.bulletwar.menu.main.res.ExitBullet;
import com.rhymes.game.bulletwar.menu.main.res.SoundBullet;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenuBullet extends StageBase{
	
	BackGroundUniversal bg;
	ButtonStageLoad play;
	ExitBullet exit;
	SoundBullet sound_butt;
	
	PreferenceBullet preference_bullet;
	
	InteractionTouch interaction_touch;

	float x, y;

	@Override
	public void loadElements() {
		
		preference_bullet = new PreferenceBullet();
		
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);

		set_menu();
	}
	
	public void set_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsBulletWar.main_bg);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f + 35f*AssetConstantsBulletWar.ratio_w - Helper.getImageFromAssets(AssetConstantsBulletWar.play).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 130f*AssetConstantsBulletWar.ratio_h - Helper.getImageFromAssets(AssetConstantsBulletWar.play).getRegionHeight()/2f*AssetConstantsBulletWar.ratio_h;
		play = new ButtonStageLoad(x, y,
				Helper.getImageFromAssets(AssetConstantsBulletWar.play).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.play).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, 
				AssetConstantsBulletWar.play, 
				new PackMenuBllet());
		this.elements.add(play);
		this.interaction_touch.subscribe(play);
		
//		x = x + 160*AssetConstantsBulletWar.ratio_w - Helper.getImageFromAssets(AssetConstantsBulletWar.exit).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
//		y = y + 7*AssetConstantsBulletWar.ratio_h - 1.5f * Helper.getImageFromAssets(AssetConstantsBulletWar.exit).getRegionHeight()*AssetConstantsBulletWar.ratio_h;
		
		x = 40f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight() - 100*AssetConstantsBulletWar.ratio_h;
		exit = new ExitBullet(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.exit).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.exit).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, 
				AssetConstantsBulletWar.exit);
		this.elements.add(exit);
		this.interaction_touch.subscribe(exit);
		
		
		x = 70f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f;
		sound_butt = new SoundBullet(x, y,
				Helper.getImageFromAssets(AssetConstantsBulletWar.music_on).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.music_on).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, preference_bullet.isSoundEffectsEnabled());
		this.elements.add(sound_butt);
		this.interaction_touch.subscribe(sound_butt);
		
	}
	
	public static boolean sound_active = true;
	String sound_image = "";
	public String get_sound_image(boolean sound_active){
		if(sound_active)
			sound_image = AssetConstantsBulletWar.music_on;
		else
			sound_image = AssetConstantsBulletWar.music_off;
			
			return sound_image;
		
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsBulletWar.main_bg);
		assetPack.addTexture(AssetConstantsBulletWar.play);
		assetPack.addTexture(AssetConstantsBulletWar.exit);
		assetPack.addTexture(AssetConstantsBulletWar.music_on);
		assetPack.addTexture(AssetConstantsBulletWar.music_off);
		
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
