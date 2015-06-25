package com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.button;

import com.rhymes.game.entity.elements.arnold.stage.StageArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class LevelSelectorArnold extends Button{

	String imagePath_1, imagePath_2;
	
	private int level_id;
	
	public LevelSelectorArnold(float x, float y, float width, float height,
			int zIndex, String imagePath_1, String imagePath_2, int level_id) {
		super(x, y, width, height, zIndex, imagePath_1);

		this.imagePath_1 = imagePath_1;
		this.imagePath_2 = imagePath_2;
		
		this.level_id = level_id;

	}
	
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			this.image = Helper.getImageFromAssets(this.imagePath_2);
			GlobalVars.ge.loadStage(new StageArnold(this.level_id));		

		}
	}

	public int getLevel_id() {
		return level_id;
	}

	public void setLevel_id(int levelId) {
		level_id = levelId;
	}

}
