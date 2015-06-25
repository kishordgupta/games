package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.game.stage.menus.LevelMenu;
import com.rhymes.game.stage.menus.ModeMenu;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevel extends Button{
	
	private int level_id;
	private boolean level_visible;

	public ButtonLevel(float x, float y, float width, float height, int zIndex,
			String imagePath, int level_id) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.level_id = level_id;
		set_level_visibility(false);
	}
	
	public ButtonLevel(float x, float y, float width, float height, int zIndex,
			String imagePath, int level_id, Result res) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.level_id = level_id;
		set_level_visibility(false);
		this.res = res;
		fromGameOver = true;
	}
	

	
//	int levelNumber = 1;
	Result res ;
private boolean fromGameOver = false;
	

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("level pressed..."+level_id);

			if(fromGameOver){
				if(res instanceof ResultBTRMAP)
					GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRMAP()));
				else if(res instanceof ResultBTRClassic)
					GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRClassic()));
				else if(res instanceof ResultBTRTime)
					GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRTime()));
			}else{
//				if(is_level_visible())
					GlobalVars.ge.loadStage(new XLevel(level_id, new ResultBTRMAP()));
			}
		}
	}

	public ButtonLevel(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}


	public void set_level_id(int level_index) {
		this.level_id = level_index;
	}


	public int get_level_id() {
		return level_id;
	}


	public void set_level_visibility(boolean active_level) {
		this.level_visible = active_level;
	}


	public boolean is_level_visible() {
		return level_visible;
	}
}
