package com.rhymes.game.entity.elements.ui.actiondriver;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.helpers.Helper;

public class ButtonSound extends Button {

	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		if(StartupInfo.soundActive){
			this.image = imageActive;
		}
		else {
			this.image = imageInactive;
		}
	}

	TextureRegion imageInactive;
	TextureRegion imageActive;
	String imageInactivePath;
	
	@Override
	public void init() {
		super.init();
		if(imageInactivePath != null)
			this.imageInactive = Helper.getImageFromAssets(imageInactivePath);
		if(imagePath != null)
			this.imageActive = Helper.getImageFromAssets(imagePath);

		Helper.println("Image: " + this.imageActive);
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, InteractionTouch.class);
	}

	public ButtonSound(float x, float y, float width, float height,
			int zIndex, String imageSoundActive, String imageSoundinactive) {
		super(x, y, width, height, zIndex, imageSoundActive);
		this.imageInactivePath = imageSoundinactive;
	}

	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			if(StartupInfo.soundActive){
				StartupInfo.sound_handler.pause();
				StartupInfo.soundActive = false;
			}
			else{
				StartupInfo.sound_handler.resume();
				StartupInfo.soundActive = true;
			}
		}
	}

	public ButtonSound(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}