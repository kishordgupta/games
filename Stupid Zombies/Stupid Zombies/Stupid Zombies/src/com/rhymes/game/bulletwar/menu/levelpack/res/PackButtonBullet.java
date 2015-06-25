package com.rhymes.game.bulletwar.menu.levelpack.res;

import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.menu.levelpack.PackMenuBllet;
import com.rhymes.game.bulletwar.menu.levels.LevelMenuBullet;
import com.rhymes.game.bulletwar.menu.levels.res.LevelInfoBullet;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class PackButtonBullet extends Button{

	int id;
	private boolean active;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		
//		if(this.id == 0){
//			PackMenuBllet.is_pack_0_active = active;
//		}
//		
//		else if(this.id == 1){
//			PackMenuBllet.is_pack_1_active = active;
//		}
//		
//		else if(this.id == 2){
//			PackMenuBllet.is_pack_2_active = active;
//		}
	}

	public PackButtonBullet(int id,float x, float y, float width, float height,
			int zIndex, boolean active) {
		super(x, y, width, height, zIndex, changeImagePath(active));

		this.id = id;
		this.active = active;
		
	}
	
	public static String changeImagePath(boolean active){
		String path = "";
		
		if(active)
			path = AssetConstantsBulletWar.pack; 
		else
			path = AssetConstantsBulletWar.pack_lock;
		
		return path;
	}
	
	public void changeImage(boolean active){
		if(active)
			this.image = Helper.getImageFromAssets(AssetConstantsBulletWar.pack);
		else
			this.image = Helper.getImageFromAssets(AssetConstantsBulletWar.pack_lock);
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			Helper.println("pack ::: id: "+this.id+"active : "+isActive());
			
			if(active){
				GlobalVars.ge.loadStage(new LevelMenuBullet(0, id));

			}
		}
	
	}
}
