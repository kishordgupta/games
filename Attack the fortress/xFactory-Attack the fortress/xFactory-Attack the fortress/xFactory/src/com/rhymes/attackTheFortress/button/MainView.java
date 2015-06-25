package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class MainView extends StageBase {

	ISingleCollision collider;
//	PlanePathSet pathSet;
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_MENU_VIEW);
		assetPack.addTexture(AssetConstants.IMG_BTN_PLAY);
		assetPack.addTexture(AssetConstants.IMG_BTN_WALLOF_FAME);
		assetPack.addTexture(AssetConstants.IMG_BTN_CLOSE);	
		assetPack.addTexture(AssetConstants.IMG_WALLOF_FAME);
		assetPack.addTexture(AssetConstants.IMG_BTN_SOUND_ASE);
		assetPack.addTexture(AssetConstants.IMG_BTN_SOUND_NAI);



		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

//		Helper.println("ratioX"+LevelInfo.ratioX);
//		Helper.println("ratioY"+LevelInfo.ratioY);
		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_MENU_VIEW));
		
		//start game
		x=Gdx.graphics.getWidth()-40f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-40f*LevelInfo.ratioY;
		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y,30f*LevelInfo.ratioX,30f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_SOUND_ASE);
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);

		
			x=150f*LevelInfo.ratioX;
			y=140f*LevelInfo.ratioY;
			ButtonPlay buttonstartgame = new ButtonPlay(x, y,180f*LevelInfo.ratioX,35f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_PLAY);
			this.elements.add(buttonstartgame);
			subscribeToControllingInteraction(buttonstartgame, InteractionTouch.class);

			x=150f*LevelInfo.ratioX;
			y=90f*LevelInfo.ratioY;
			ButtonWallofFame buttonOption = new ButtonWallofFame(x, y, 180f*LevelInfo.ratioX,35f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_WALLOF_FAME);
			this.elements.add(buttonOption);
			subscribeToControllingInteraction(buttonOption, InteractionTouch.class);
		
			x=150f*LevelInfo.ratioX;
			y=40f*LevelInfo.ratioY;
			ButtonCloseGame buttonExitgame = new ButtonCloseGame(x, y,180f*LevelInfo.ratioX,35f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_CLOSE);
			this.elements.add(buttonExitgame);
			subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
	}

}
