package com.rhymes.game.entity.elements.ui.actiondriver;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.helpers.Helper;

public class ButtonLoadStage extends Button {
	StageBase stage;
	@Override
	public void init() {
		super.init();
	}

	public ButtonLoadStage(float x, float y, float width, float height,
			int zIndex, String imagePath, StageBase stage) {
		super(x, y, width, height, zIndex, imagePath);
		this.stage = stage;
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, InteractionTouch.class);
	}

	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			GlobalVars.ge.loadStage(stage);
		}
	}

	public ButtonLoadStage(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}