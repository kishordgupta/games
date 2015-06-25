package com.rhymes.game.entity.elements.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ButtonPause extends Button {


	@Override
	public void init() {
		super.init();
		imagePlay = Helper.getImageFromAssets(AssetConstants.IMG_PLAY);
		imagePause = this.image;
	}

	TextureRegion imagePause;
	TextureRegion imagePlay;
	public ButtonPause(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	@Override
	public void onEvent(Point p) {
		if(this.checkHitPrev(p)){
			Helper.println("Button Pause Hit : " + p.toString());
			Helper.println("Button Pause Hit : Current Game state : " + GlobalVars.ge.getCurrentStage().getGameState());
			if(GlobalVars.ge.getCurrentStage().getGameState() == GameState.PLAYING){
				GlobalVars.ge.getCurrentStage().pause();		
				this.image = imagePlay;
			}
			else if(GlobalVars.ge.getCurrentStage().getGameState() == GameState.PAUSED){
				GlobalVars.ge.getCurrentStage().resume();
				this.image = imagePause;
			}
			Helper.println("Button Pause Hit : Game state set: " + GlobalVars.ge.getCurrentStage().getGameState());
		}
	}

	public ButtonPause(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}