package com.rhymes.game.bulletwar.menu.main.res;

import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.PreferenceBullet;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class SoundBullet extends Button{
	
	String imagePath;
	boolean active;
	
	PreferenceBullet pref;

	public SoundBullet(float x, float y, float width, float height, int zIndex,
			boolean active) {
		super(x, y, width, height, zIndex, changeImagePath(active));

		this.active = active;
		
		pref = new PreferenceBullet();
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			
			if(active){
				pref.setSoundEffectsEnabled(false);
				setImage(Helper.getImageFromAssets(AssetConstantsBulletWar.music_off));
				active = false;
			}
			else{
				pref.setSoundEffectsEnabled(true);
				setImage(Helper.getImageFromAssets(AssetConstantsBulletWar.music_on));
				active = true;
			}
			
		}
	}

	public static String changeImagePath(boolean active){
		String path = "";
		
		if(active)
			path = AssetConstantsBulletWar.music_on; 
		else
			path = AssetConstantsBulletWar.music_off;
		
		return path;
	}
	
}
