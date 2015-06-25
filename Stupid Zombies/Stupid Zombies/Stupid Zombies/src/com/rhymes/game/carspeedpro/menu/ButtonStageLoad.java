package com.rhymes.game.carspeedpro.menu;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;

public class ButtonStageLoad extends Button{
	
	String imagePath;
	StageBase stageLoad;

	public ButtonStageLoad(float x, float y, float width, float height,
			int zIndex, String imagePath, StageBase stage) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
		this.stageLoad = stage;
	
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(checkHit(htiPoint)){
			GlobalVars.ge.loadStage(this.stageLoad);
		}
	
	}

	
}
