package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenu extends StageBase {

	public int levelNumber = 0;
	public int pageIndex = 0;
	public int LN = 0;
	public int currentpageLevelnumber = LN = 6;
	public int tpageNumber = 0;
	public int valI = 0;
	public int currentLevel = 0;

	public LevelMenu(int pageIndex) {
		levelNumber = LevelInfo.levelNumber;
		this.pageIndex = pageIndex;
		valI = levelNumber % currentpageLevelnumber;
		Helper.println("valI = " + valI);

		tpageNumber = (int) (levelNumber / currentpageLevelnumber);
		if (valI > 0)
			tpageNumber++;
		Helper.println("pagenumber = " + tpageNumber);

	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACKWARD);
		assetPack.addTexture(AssetConstants.IMG_BTN_FORWARD);
		assetPack.addTexture(AssetConstants.IMG_LS_BACK);
		return assetPack;

	}

	@Override
	public void loadElements() {
		loadFonts();
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG));

		float x = 0;
		float y = 0;

		x = 50f * LevelInfo.ratioX;
		y = 350f * LevelInfo.ratioY;
		ButtonpageIndexBackward buttonbackward = new ButtonpageIndexBackward(x,
				y, 50f * LevelInfo.ratioX, 50f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_BACKWARD);
		this.elements.add(buttonbackward);
		subscribeToControllingInteraction(buttonbackward,
				InteractionTouch.class);

		x = 924f * LevelInfo.ratioX;
		y = 350f * LevelInfo.ratioY;
		ButtonpageIndexForward buttonforward = new ButtonpageIndexForward(x, y,
				50f * LevelInfo.ratioX, 50f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_FORWARD);
		this.elements.add(buttonforward);
		subscribeToControllingInteraction(buttonforward, InteractionTouch.class);

		x = 400f * LevelInfo.ratioX;
		y = 70f * LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y,
				258f * LevelInfo.ratioX, 77f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);

		x = 170f * LevelInfo.ratioX;
		y = 400f * LevelInfo.ratioY;
		if (pageIndex == tpageNumber - 1 && valI > 0)
			currentpageLevelnumber = valI;
		for (int i = 0; i < currentpageLevelnumber; i++) {
			currentLevel = ((pageIndex * LN) + i + 1);
			
			ButtonLevel level = new ButtonLevel(x, y, 200f * LevelInfo.ratioX,
					150f * LevelInfo.ratioY, 1, AssetConstants.IMG_LS_BACK,
					currentLevel, fontController);
			
//			ButtonLevel level = new ButtonLevel(x, y, 150f * LevelInfo.ratioX,
//					100f * LevelInfo.ratioY, 1, AssetConstants.IMG_BIKE,
//					currentLevel, fontController);
			
			this.elements.add(level);
			subscribeToControllingInteraction(level, InteractionTouch.class);
			if (i < 2) {
				x = x + 250 * LevelInfo.ratioX;
			} else if (i == 2) {
				x = 170 * LevelInfo.ratioX;
				y = 200f * LevelInfo.ratioY;
			} else {
				x = x + 250 * LevelInfo.ratioX;
			}
		}

	}

	public void loadFonts() {
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_1,
				10, 7);
		fontController.addFont(AssetConstants.FONT_NEON2,
				AssetConstants.FONT_NEON2, 10, 7);
	}

	@Override
	public void tick(long stepTime) {

	}

}
