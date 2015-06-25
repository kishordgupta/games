package com.rhymes.game.bulletwar.menu.levelpack;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.PreferenceBullet;
import com.rhymes.game.bulletwar.menu.levelpack.res.PackButtonBullet;
import com.rhymes.game.bulletwar.menu.main.MainMenuBullet;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PackMenuBllet extends StageBase{
	
	public int num_of_pack = PreferenceBullet.pack_num;
	
	BackGroundUniversal bg;
	ButtonStageLoad back;
	PackButtonBullet[] pack;
	
	InteractionTouch interaction_touch;

	float x, y;
	
	public PreferenceBullet preference;
	

	@Override
	public void loadElements() {
		
		preference = new PreferenceBullet();
		
		preference.setPackEnabled(PreferenceBullet.pref_pack, 0, true);
		
		pack = new PackButtonBullet[num_of_pack];
		
		interaction_touch = new InteractionTouch();
		this.interactions.add(this.interaction_touch);
		
		set_menu();
	}
	
	
	float gap_w = 135f*AssetConstantsBulletWar.ratio_w;
	float gap_h = 150f*AssetConstantsBulletWar.ratio_h;
	
	public void set_menu(){
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 
				AssetConstantsBulletWar.level_bg);
		this.elements.add(bg);
		
		x = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth())*AssetConstantsBulletWar.ratio_w;
		y = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight())*AssetConstantsBulletWar.ratio_h;
		back = new ButtonStageLoad(x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, 
				AssetConstantsBulletWar.back,
				new MainMenuBullet());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
		
		
		y = Gdx.graphics.getHeight() - 210f*AssetConstantsBulletWar.ratio_h;
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w
				- gap_w - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w;
		
		pack[0] = new PackButtonBullet( 0, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 0));
//		is_pack_0_active = pack[0].isActive();
		this.elements.add(pack[0]);
		this.interaction_touch.subscribe(pack[0]);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		
		pack[1] = new PackButtonBullet( 1, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 1));
//		is_pack_1_active = pack[1].isActive();
		this.elements.add(pack[1]);
		this.interaction_touch.subscribe(pack[1]);
		
		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w
				+gap_w;
		
		pack[2] = new PackButtonBullet( 2, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 2));
//		is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[2]);
		this.interaction_touch.subscribe(pack[2]);
		
	
		
		y = y - gap_h;
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w
				- gap_w - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w;
		
		pack[3] = new PackButtonBullet( 3, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 3));
//		is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[3]);
		this.interaction_touch.subscribe(pack[3]);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		
		pack[4] = new PackButtonBullet( 4, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 4));
//				is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[4]);
		this.interaction_touch.subscribe(pack[4]);
		
		x = Gdx.graphics.getWidth()/2f + Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w
				+gap_w;
		
		pack[5] = new PackButtonBullet( 5, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 5));
//				is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[5]);
		this.interaction_touch.subscribe(pack[5]);
		
		
		
		y = y - gap_h;
		
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w
				- gap_w - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w;
		
		pack[6] = new PackButtonBullet( 6, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 6));
//				is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[6]);
		this.interaction_touch.subscribe(pack[6]);
		
		x = Gdx.graphics.getWidth()/2f - Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()/2f*AssetConstantsBulletWar.ratio_w;
		
		pack[7] = new PackButtonBullet( 7, x, y, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionWidth()*AssetConstantsBulletWar.ratio_w,
				Helper.getImageFromAssets(AssetConstantsBulletWar.pack).getRegionHeight()*AssetConstantsBulletWar.ratio_h,
				1, preference.isPackEnabled(PreferenceBullet.pref_pack, 7));
//				is_pack_2_active = pack[2].isActive();
		this.elements.add(pack[7]);
		this.interaction_touch.subscribe(pack[7]);
		
	}

//	public static boolean is_pack_0_active = true;
//	public boolean check_locked_pack_0(){
//		if(is_pack_0_active)
//			return true;
//		else
//			return false;
//	}
//	
//	public static boolean is_pack_1_active = false;
//	public boolean check_locked_pack_1(){
//		if(is_pack_1_active)
//			return true;
//		else
//			return false;
//	}
//	
//	public static boolean is_pack_2_active = false;
//	public boolean check_locked_pack_2(){
//		if(is_pack_2_active)
//			return true;
//		else
//			return false;
//	}
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsBulletWar.level_bg);
		assetPack.addTexture(AssetConstantsBulletWar.back);
		assetPack.addTexture(AssetConstantsBulletWar.pack);
		assetPack.addTexture(AssetConstantsBulletWar.pack_lock);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
