package com.rhymes.game.entity.elements.csp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.CSPConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class RearForward extends Button {
	public RearForward(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		stateForward = true;
	}

	TextureRegion imageForward, imageRear;
	
	public static boolean stateForward = true;

	public void setDirection(boolean stateForward)
	{
		this.stateForward = stateForward;
		if(stateForward)
			this.image = this.imageForward;
		else
				this.image = this.imageRear;
	}

	@Override
	public void init() {
		this.imageForward = Helper.getImageFromAssets(CSPConstants.REAR_FORWARD_F);
		this.imageRear = Helper.getImageFromAssets(CSPConstants.REAR_FORWARD_R);
		if(stateForward)
			this.image = imageForward;
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(CSPConstants.REAR_FORWARD_F);
		assetPack.addTexture(CSPConstants.REAR_FORWARD_R);
	}

	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-512f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-320f*LevelInfo.ratioY;
		if(checkHit(p))
		{
			stateForward = !stateForward;
			setDirection(stateForward);
			((BikeLevel)GlobalVars.ge.getCurrentStage()).setCarDirection(stateForward);
		}
	}

}
