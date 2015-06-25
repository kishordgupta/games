package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenu extends StageBase {

	ISingleCollision collider;
//	PlanePathSet pathSet;
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BTN_BTNSTART);
		//assetPack.addTexture(AssetConstants.IMG_BTN_BTNSTART_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_BKGMAPVIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_OPTIONS);
		//assetPack.addTexture(AssetConstants.IMG_BTN_OPTIONS_DOWN);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUIT);		
		//assetPack.addTexture(AssetConstants.IMG_BTN_QUIT_DOWN);



		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

//		Helper.println("ratioX"+LevelInfo.ratioX);
//		Helper.println("ratioY"+LevelInfo.ratioY);
		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BTN_BKGMAPVIEW));
		
		GlobalVars.ge.getScreen().cam.position.set( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0f);
		//start game
		
			x=150f*LevelInfo.ratioX;
			y=155f*LevelInfo.ratioY;
			ButtonStartGame buttonstartgame = new ButtonStartGame(x, y,180f*LevelInfo.ratioX,50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_BTNSTART);
			this.elements.add(buttonstartgame);
			subscribeToControllingInteraction(buttonstartgame, InteractionTouch.class);

			x=150f*LevelInfo.ratioX;
			y=100f*LevelInfo.ratioY;
			ButtonOptionGameMenu buttonOption = new ButtonOptionGameMenu(x, y, 180f*LevelInfo.ratioX,50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_OPTIONS);
			this.elements.add(buttonOption);
			subscribeToControllingInteraction(buttonOption, InteractionTouch.class);
		
			x=150f*LevelInfo.ratioX;
			y=45f*LevelInfo.ratioY;
			ButtonExitGame buttonExitgame = new ButtonExitGame(x, y,180f*LevelInfo.ratioX,50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_QUIT);
			this.elements.add(buttonExitgame);
			subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
	}

}
