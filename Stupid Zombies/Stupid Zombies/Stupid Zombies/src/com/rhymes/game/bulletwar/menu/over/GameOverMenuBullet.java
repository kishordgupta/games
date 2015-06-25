package com.rhymes.game.bulletwar.menu.over;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.menu.levelpack.PackMenuBllet;
import com.rhymes.game.bulletwar.menu.main.MainMenuBullet;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverMenuBullet extends StageBase{
	
	BackGroundUniversal bg, complete, failed;
	ButtonStageLoad retry, next, back, back_menu;
	
	BackGroundUniversal[] star_img;
	
	InteractionTouch interaction_touch;

	ArrayList<BackGroundUniversal> total_star_list = new ArrayList<BackGroundUniversal>();
	ArrayList<BackGroundUniversal> collected_star_list = new ArrayList<BackGroundUniversal>();
	
	float x, y;
	
	boolean is_over;
	int total_star;
	int collected_star;
	
	public GameOverMenuBullet(boolean is_over, int total_star, int collected_star){
		this.is_over = is_over;
		this.total_star = total_star;
		this.collected_star= collected_star;
		
		star_img = new BackGroundUniversal[total_star];

	}
	
	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
	
		set_menu();
		set_star(total_star);
	}

	public void set_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsBulletWar.score_bg);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.complete).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 100f*AssetConstantsBulletWar.ratio_h;
		
		complete = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.complete).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.complete).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, AssetConstantsBulletWar.complete);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.failed).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;

		failed = new BackGroundUniversal(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.failed).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.failed).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, AssetConstantsBulletWar.failed);
		
	
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		y = Gdx.graphics.getHeight()/2f - 200*AssetConstantsBulletWar.ratio_h;
		
		retry = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.retry).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, AssetConstantsBulletWar.retry,
				new MainMenuBullet());
		
		next = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.next).getRegionWidth()*AssetConstantsBulletWar.ratio_w, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.next).getRegionHeight()*AssetConstantsBulletWar.ratio_h, 
				1, AssetConstantsBulletWar.next,
				new MainMenuBullet());
		
		x = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth())*AssetConstantsBulletWar.ratio_w;
		y = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight())*AssetConstantsBulletWar.ratio_h;
		back = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, 
				AssetConstantsBulletWar.back,
				new MainMenuBullet());
		
//		x = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth())*AssetConstantsBulletWar.ratio_w;
//		y = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight())*AssetConstantsBulletWar.ratio_h;
		back_menu = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, 
				AssetConstantsBulletWar.back,
				new PackMenuBllet());
		
		if(is_over){
			this.elements.add(complete);
			
			this.elements.add(next);
			this.interaction_touch.subscribe(next);
			
			this.elements.add(back);
			this.interaction_touch.subscribe(back);
		}
		
		else{
			this.elements.add(failed);
			
			this.elements.add(retry);
			this.interaction_touch.subscribe(retry);
			
			this.elements.add(back_menu);
			this.interaction_touch.subscribe(back_menu);
		}
		
	}
	
	int i;
	float star_gap = 35f*AssetConstantsBulletWar.ratio_w;//(Helper.getImageFromAssets(AssetConstantsBulletWar.star_active).getRegionWidth()+5)*AssetConstantsBulletWar.ratio_w;
	public void set_star(int star){
		y = Gdx.graphics.getHeight()/2f + 25f*AssetConstantsBulletWar.ratio_h;
		x = Gdx.graphics.getWidth()/2f - 80f*AssetConstantsBulletWar.ratio_h;
		
		for(i = 0; i < total_star; i++){
			star_img[i] = new BackGroundUniversal(x, y,
					Helper.getImageFromAssets(AssetConstantsBulletWar.star_deactive).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
					Helper.getImageFromAssets(AssetConstantsBulletWar.star_deactive).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
					1, AssetConstantsBulletWar.star_deactive);
	
			this.elements.add(star_img[i]);
			total_star_list.add(star_img[i]);
			
			x = x + star_gap;
		}
		
		for(i = 0; i < collected_star; i++){
			collected_star_list.add(total_star_list.get(i));
		}
	}
	
	float target_time = 1 * 1000f;
	float step = 0;
	public void star_animation(float stepTime){
		
		for(i = 0; i < collected_star_list.size(); i++){
			if(!collected_star_list.isEmpty()){
				step += stepTime;
				if(step >= target_time){
					collected_star_list.get(0).setImage(Helper.getImageFromAssets(AssetConstantsBulletWar.star_active));
					collected_star_list.remove(0);
					
					step = 0f;
				}
			}
		}
	}
	
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		
		assetPack.addTexture(AssetConstantsBulletWar.score_bg);
		assetPack.addTexture(AssetConstantsBulletWar.next);
		assetPack.addTexture(AssetConstantsBulletWar.retry);
		assetPack.addTexture(AssetConstantsBulletWar.star_active);
		assetPack.addTexture(AssetConstantsBulletWar.star_deactive);
		assetPack.addTexture(AssetConstantsBulletWar.complete);
		assetPack.addTexture(AssetConstantsBulletWar.failed);
		assetPack.addTexture(AssetConstantsBulletWar.back);
		
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		star_animation((float)stepTime);
	}

}
