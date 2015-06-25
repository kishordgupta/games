package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;



public class StartChallengeMenu extends StageBase {

	float x;
	float y;
	

	@Override
	public void init() 
	{
		// TODO Auto-generated method stub
		Helper.println("Arif");
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) 
	{
		assetPack.addTexture(AssetConstants.IMG_BTN_BKGMAPVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_1BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_2BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_3BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_4BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_5BTNLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
		assetPack.addTexture(AssetConstants.IMG_BTN_1BTNLEVEL_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_2BTNLEVEL_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_3BTNLEVEL_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_4BTNLEVEL_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_5BTNLEVEL_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK_DOWN);
		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BTN_BACK));
//		GlobalVars.ge.getScreen().cam.position.set( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0f);
	}

	@Override
	public void tick(long stepTime) 
	{
//		Helper.println("ar");
	}
}
