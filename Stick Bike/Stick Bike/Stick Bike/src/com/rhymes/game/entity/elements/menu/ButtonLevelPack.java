package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.LevelMenu;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevelPack extends Button{
	
	private int pack_id;
	private  boolean pack_active;

	public ButtonLevelPack(float x, float y, float width, float height,
			int zIndex, String imagePath, int levelPackIndex) {
		super(x, y, width, height, zIndex, imagePath);
		
		set_pack_id(levelPackIndex);
//		setActivePack(false);
	}

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Levelpack pressed..."+pack_id);
			
			setActivePack(true);
			
			if(isActivePack())
				GlobalVars.ge.loadStage(new LevelMenu(pack_id));
		}
	}

	public ButtonLevelPack(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	public void set_pack_id(int levelPackIndex) {
		this.pack_id = levelPackIndex;
	}

	public int get_pack_id() {
		return pack_id;
	}

	public void setActivePack(boolean isActivePack) {
		this.pack_active = isActivePack;
	}

	public boolean isActivePack() {
		return pack_active;
	}

}
