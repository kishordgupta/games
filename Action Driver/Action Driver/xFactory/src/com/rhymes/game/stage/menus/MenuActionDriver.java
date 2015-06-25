package com.rhymes.game.stage.menus;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.ui.Background;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonLoadStage;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonQuit;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonSound;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MenuActionDriver extends StageBase {

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		addElementZSorted(new Background(0, 0, Helper.getScreenWidth(), Helper.getScreenHeight(), 0, AssetConstants.IMG_MENU_BACK));
		addElementZSorted(new ButtonLoadStage(150 * Constants.rx, 230 * Constants.ry, 
				350* Constants.rx, 100* Constants.ry, 1, AssetConstants.IMG_START, new LevelActionDriver()));
		addElementZSorted(new ButtonQuit(150 * Constants.rx, 100 * Constants.ry, 
				350* Constants.rx, 100* Constants.ry, 1, AssetConstants.IMG_QUIT));
		addElementZSorted(new ButtonSound(Helper.getScreenWidth() - 20 * Constants.rx - 100 * Constants.rx, Helper.getScreenHeight() - 150 * Constants.ry, 
				80 * Constants.rx, 80 * Constants.ry, 1, AssetConstants.IMG_SPEAKER_ON, AssetConstants.IMG_SPEAKER_OFF));
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_START);
		assetPack.addTexture(AssetConstants.IMG_QUIT);
		assetPack.addTexture(AssetConstants.IMG_SPEAKER_ON);
		assetPack.addTexture(AssetConstants.IMG_SPEAKER_OFF);
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {

	}

}
