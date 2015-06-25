package com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button;

import com.rhymes.game.entity.elements.arnold.stage.StageArnold;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class NextArnold extends Button{
	
	String imagePath_1, imagePath_2;
	int next_level;

	public NextArnold(float x, float y, float width, float height, int zIndex,
			String imagePath_1, String imagePath_2, int next_level) {
		super(x, y, width, height, zIndex, imagePath_1);

		this.imagePath_1 = imagePath_1;
		this.imagePath_2 = imagePath_2;
		
		this.next_level = next_level;
	}

	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			this.image = Helper.getImageFromAssets(imagePath_2);
			GlobalVars.ge.loadStage(new StageArnold(this.next_level));		

		}
	}

}
