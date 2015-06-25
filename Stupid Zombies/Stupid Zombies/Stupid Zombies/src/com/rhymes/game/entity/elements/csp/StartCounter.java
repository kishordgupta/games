package com.rhymes.game.entity.elements.csp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.CSPConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class StartCounter extends ElementBase {
	
	public StartCounter(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		if(started)
		{
			aniLoop.step(stepTime);
//			Helper.println("Startcounter image:  " + this.image);
			if(aniLoop.isFinished()){
				this.finished = true;
				GlobalVars.ge.getCurrentStage().postDestroyed(this);
			}
		}
	}


	private AniLoop aniLoop;
	boolean started = false;
	public boolean isStarted() {
		return started;
	}


	boolean finished = false;
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void init() {
		TextureRegion[] images = new TextureRegion[4];
		images[0] = Helper.getImageFromAssets(CSPConstants.READY_1);
		images[1] = Helper.getImageFromAssets(CSPConstants.READY_2);
		images[2] = Helper.getImageFromAssets(CSPConstants.READY_3);
		images[3] = Helper.getImageFromAssets(CSPConstants.READY_3);
		
		aniLoop = new AniLoop(this, images, false);
		aniLoop.setStepTime(1000);
		aniLoop.init();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(CSPConstants.READY_1);
		assetPack.addTexture(CSPConstants.READY_2);
		assetPack.addTexture(CSPConstants.READY_3);
	}
		
	
	public void start()
	{
		started = true;
	}

}
