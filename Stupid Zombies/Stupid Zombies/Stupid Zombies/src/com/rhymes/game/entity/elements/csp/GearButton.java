package com.rhymes.game.entity.elements.csp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.CSPConstants;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GearButton extends ElementBase {

	boolean start = false;
	public GearButton(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void step(long stepTime) {
		super.step(stepTime);
		
		if(start)
			aniLoop.step(stepTime);
	}

	AniLoop aniLoop;

	
	@Override
	public void init() {
		TextureRegion[] images = new TextureRegion[8];
		for(int i = 1; i <= 8; i ++)
		{
			images[i-1] = Helper.getImageFromAssets(CSPConstants.GEAR_FOLDER + i + ".png");
			images[i-1].flip(true, false);
		}

		
		aniLoop = new AniLoop(this, images, false);		
		aniLoop.init();
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		Helper.addAssetsIterative(assetPack, CSPConstants.GEAR_FOLDER, 1, 8, ".png");
	}

	public void reset()
	{
		aniLoop.reset();
		this.start = false;
	}
	
	public void stop()
	{
		start = false;
	}
	
	public void start()
	{
		start = true;
	}
}
