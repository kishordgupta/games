package com.rhymes.game.entity.elements.ui.actiondriver;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.helpers.Helper;

public class ButtonQuit extends Button {
	@Override
	public void init() {
		super.init();
	}

	public ButtonQuit(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, InteractionTouch.class);
	}

	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Gdx.app.exit();
		}
	}

	public ButtonQuit(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}