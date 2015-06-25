package com.rhymes.game.fruitdelivery.menu.selectlevel;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.ButtonLevelFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.ButtonpageIndexBackwardFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.ButtonpageIndexForwardFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.LevelInfoFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.SelectVehicleMenuFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenuFruit extends StageBase {

	public int levelNumber = 0;
	public int pageIndex = 0;
	public int LN = 0;
	public int currentpageLevelnumber = LN = 6;
	public int tpageNumber = 0;
	public int valI = 0;
	public int currentLevel = 0;

	public LevelMenuFruit(int pageIndex) {
		levelNumber = LevelInfoFruit.levelNumber;
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
		assetPack.addTexture(AssetConstantsFruit.main_bg);
		assetPack.addTexture(AssetConstantsFruit.back_settings_nor);
		assetPack.addTexture(AssetConstantsFruit.btn_backward);
		assetPack.addTexture(AssetConstantsFruit.btn_forward);
		assetPack.addTexture(AssetConstantsFruit.ls_back);
		return assetPack;

	}

	@Override
	public void loadElements() {
		loadFonts();
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstantsFruit.main_bg));

		float x = 0;
		float y = 0;

		x = 50f * LevelInfoFruit.ratioX;
		y = 350f * LevelInfoFruit.ratioY;
		ButtonpageIndexBackwardFruit buttonbackward = new ButtonpageIndexBackwardFruit(x,
				y, 50f * LevelInfoFruit.ratioX, 50f * LevelInfoFruit.ratioY, 1,
				AssetConstantsFruit.btn_backward);
		this.elements.add(buttonbackward);
		subscribeToControllingInteraction(buttonbackward,
				InteractionTouch.class);

		x = 924f * LevelInfoFruit.ratioX;
		y = 350f * LevelInfoFruit.ratioY;
		ButtonpageIndexForwardFruit buttonforward = new ButtonpageIndexForwardFruit(x, y,
				50f * LevelInfoFruit.ratioX, 50f * LevelInfoFruit.ratioY, 1,
				AssetConstantsFruit.btn_forward);
		this.elements.add(buttonforward);
		subscribeToControllingInteraction(buttonforward, InteractionTouch.class);

		x = 400f * LevelInfoFruit.ratioX;
		y = 70f * LevelInfoFruit.ratioY;
		ButtonStageLoad buttonBack = new ButtonStageLoad(x, y,
				258f * LevelInfoFruit.ratioX, 77f * LevelInfoFruit.ratioY, 1,
				AssetConstantsFruit.back_settings_nor, new SelectVehicleMenuFruit());
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);

		x = 170f * LevelInfoFruit.ratioX;
		y = 400f * LevelInfoFruit.ratioY;
		if (pageIndex == tpageNumber - 1 && valI > 0)
			currentpageLevelnumber = valI;
		for (int i = 0; i < currentpageLevelnumber; i++) {
			currentLevel = ((pageIndex * LN) + i + 1);
			
			ButtonLevelFruit level = new ButtonLevelFruit(x, y, 200f * LevelInfoFruit.ratioX,
					150f * LevelInfoFruit.ratioY, 1, AssetConstantsFruit.ls_back,
					currentLevel, fontController);
			
//			ButtonLevel level = new ButtonLevel(x, y, 150f * LevelInfo.ratioX,
//					100f * LevelInfo.ratioY, 1, AssetConstants.IMG_BIKE,
//					currentLevel, fontController);
			
			this.elements.add(level);
			subscribeToControllingInteraction(level, InteractionTouch.class);
			if (i < 2) {
				x = x + 250 * LevelInfoFruit.ratioX;
			} else if (i == 2) {
				x = 170 * LevelInfoFruit.ratioX;
				y = 200f * LevelInfoFruit.ratioY;
			} else {
				x = x + 250 * LevelInfoFruit.ratioX;
			}
		}

	}

	public void loadFonts() {
		fontController.addFont(AssetConstantsFruit.FONT_1, AssetConstantsFruit.FONT_1,
				10, 7);
		fontController.addFont(AssetConstantsFruit.FONT_NEON2,
				AssetConstantsFruit.FONT_NEON2, 10, 7);
	}

	@Override
	public void tick(long stepTime) {

	}

}
