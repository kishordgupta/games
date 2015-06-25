package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MainMenu extends StageBase {

	public MainMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG);
		assetPack.addTexture(AssetConstants.IMG_BTN_INSTRUCTION);
		assetPack.addTexture(AssetConstants.IMG_BTN_NEWGAME);
		assetPack.addTexture(AssetConstants.IMG_BTN_SETTINGS);
		assetPack.addTexture(AssetConstants.IMG_BTN_EXIT);
		return assetPack;
	}

	@Override
	public void loadElements() {

		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_MAIN_MENU));

		float x = 0;
		float y = 0;

		x = 100f * LevelInfo.ratioX;
		y = 340f * LevelInfo.ratioY;
		// ButtonNewGame buttonstartgame = new ButtonNewGame(x,
		// y,324f*LevelInfo.ratioX,70f*LevelInfo.ratioY, 1,
		// AssetConstants.IMG_BTN_NEWGAME);
		ButtonNewGame buttonstartgame = new ButtonNewGame(x, y, 262 * LevelInfo.ratioX,
				 125 * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_NEWGAME);
		this.elements.add(buttonstartgame);
		subscribeToControllingInteraction(buttonstartgame,
				InteractionTouch.class);

		x = 350f * LevelInfo.ratioX;
		y = 200f * LevelInfo.ratioY;
		ButtonInstruction buttonInstruction = new ButtonInstruction(x, y,
				 263 * LevelInfo.ratioX, 139 * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_INSTRUCTION);
		this.elements.add(buttonInstruction);
		subscribeToControllingInteraction(buttonInstruction,
				InteractionTouch.class);


		x = 682f * LevelInfo.ratioX;
		y = 308f * LevelInfo.ratioY;
		ButtonSettngs buttonSettings = new ButtonSettngs(x, y,
				258 * LevelInfo.ratioX, 82 * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_SETTINGS);
		this.elements.add(buttonSettings);
		subscribeToControllingInteraction(buttonSettings,
				InteractionTouch.class);

		x = 470f * LevelInfo.ratioX;
		y = 60f * LevelInfo.ratioY;
		ButtonCloseGame buttonExit = new ButtonCloseGame(x, y,
				259 * LevelInfo.ratioX, 84 * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_EXIT);
		this.elements.add(buttonExit);
		subscribeToControllingInteraction(buttonExit, InteractionTouch.class);

	}

	@Override
	public void tick(long stepTime) {
		// TODO Auto-generated method stub

	}

}
