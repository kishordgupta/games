package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
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
	
	
	public Result getRes() {
		return res;
	}

	public void setRes(Result res) {
		this.res = res;
	}
	

	
//	int levelNumber = 1;
	Result res = new ResultBounce();
	

	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			Helper.println("level pressed..."+level_id);
			
			if(is_level_visible())
				GlobalVars.ge.loadStage(new BounceTest(level_id, res));		
//				GlobalVars.ge.loadStage(new XLevel(level_id, res));			
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
