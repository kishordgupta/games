package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.LevelMenu;
import com.rhymes.game.stage.menus.ModeMenu;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevelPack extends Button{
	
	private int pack_id;
	private  boolean pack_active;

	private boolean fromGameOver = false;
	private Result res;
	public ButtonLevelPack(float x, float y, float width, float height,
			int zIndex, String imagePath, int levelPackIndex) {
		super(x, y, width, height, zIndex, imagePath);
		
		set_pack_id(levelPackIndex);
//		setActivePack(false);
	}

	public ButtonLevelPack(float x, float y, float width, float height,
			int zIndex, String imagePath, int levelPackIndex, Result res) {
		super(x, y, width, height, zIndex, imagePath);
		fromGameOver = true;
		set_pack_id(levelPackIndex);
		this.res = res;
//		setActivePack(false);
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("Levelpack pressed..."+pack_id);
			
			setActivePack(true);
			
			if(fromGameOver){
				if(res instanceof ResultBTRMAP)
					GlobalVars.ge.loadStage(new LevelMenu(pack_id));
				else if(res instanceof ResultBTRClassic)
					GlobalVars.ge.loadStage(new ModeMenu());
				else if(res instanceof ResultBTRTime)
					GlobalVars.ge.loadStage(new ModeMenu());
			}
			else {
				if(isActivePack())
					GlobalVars.ge.loadStage(new LevelMenu(pack_id));
			}
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
