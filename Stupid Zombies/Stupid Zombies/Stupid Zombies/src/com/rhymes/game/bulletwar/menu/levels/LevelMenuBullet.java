package com.rhymes.game.bulletwar.menu.levels;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.PreferenceBullet;
import com.rhymes.game.bulletwar.menu.levelpack.PackMenuBllet;
import com.rhymes.game.bulletwar.menu.levels.res.ButtonBackwardIndexBullet;
import com.rhymes.game.bulletwar.menu.levels.res.ButtonForwardIndexBullet;
import com.rhymes.game.bulletwar.menu.levels.res.ButtonLevelBullet;
import com.rhymes.game.bulletwar.menu.levels.res.LevelInfoBullet;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class LevelMenuBullet extends StageBase{
	public int levelNumber = 0;
	public int pageIndex = 0;
	public static int packIndex = 0;
	
	public int LN = 0;
	public int currentpageLevelnumber = LN = 6;
	public int tpageNumber = 0;
	public int valI = 0;
	public int currentLevel = 0;

	public PreferenceBullet preference_bullet;
	
	public LevelMenuBullet(int pageIndex, int packIndex) {
		
		levelNumber = LevelInfoBullet.levelNumber;
		
		this.pageIndex = pageIndex;
		LevelMenuBullet.packIndex = packIndex;
		

		valI = levelNumber % currentpageLevelnumber;
		Helper.println("valI = " + valI);

		tpageNumber = (int) (levelNumber / currentpageLevelnumber);
		if (valI > 0)
			tpageNumber++;
		Helper.println("pagenumber = " + tpageNumber);

		
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsBulletWar.level_bg);
		assetPack.addTexture(AssetConstantsBulletWar.back);
		assetPack.addTexture(AssetConstantsBulletWar.level);
		assetPack.addTexture(AssetConstantsBulletWar.level_lock);
		assetPack.addTexture(AssetConstantsBulletWar.nexxt);
		return assetPack;

	}

	@Override
	public void loadElements() {
		
		preference_bullet = new PreferenceBullet();
		preference_bullet.setLevelEnabled(PreferenceBullet.pref_level, LevelMenuBullet.packIndex, 0, true);
		
		for(int i = 0; i < 10; i++)
		Helper.printTest("level isActive :"+preference_bullet.isLevelEnabled(PreferenceBullet.pref_level, LevelMenuBullet.packIndex, i));
		
		loadFonts();
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstantsBulletWar.level_bg));

		float x = 0;
		float y = 0;

		x = 50f * LevelInfoBullet.ratioX;
		y = 350f * LevelInfoBullet.ratioY;
		ButtonBackwardIndexBullet buttonbackward = new ButtonBackwardIndexBullet(x,
				y, 50f * LevelInfoBullet.ratioX, 50f * LevelInfoBullet.ratioY, 1,
				AssetConstantsBulletWar.back);
		this.elements.add(buttonbackward);
		subscribeToControllingInteraction(buttonbackward,
				InteractionTouch.class);

		x = 924f * LevelInfoBullet.ratioX;
		y = 350f * LevelInfoBullet.ratioY;
		ButtonForwardIndexBullet buttonforward = new ButtonForwardIndexBullet(x, y,
				50f * LevelInfoBullet.ratioX, 50f * LevelInfoBullet.ratioY, 1,
				AssetConstantsBulletWar.nexxt);
		this.elements.add(buttonforward);
		subscribeToControllingInteraction(buttonforward, InteractionTouch.class);

		x =	Gdx.graphics.getWidth()/2f -  (Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth())*AssetConstantsBulletWar.ratio_w;
		y = (80f - Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight())*AssetConstantsBulletWar.ratio_h;
		
		ButtonStageLoad buttonBack = new ButtonStageLoad(x, y,
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionWidth() * LevelInfoBullet.ratioX, 
				Helper.getImageFromAssets(AssetConstantsBulletWar.back).getRegionHeight()* LevelInfoBullet.ratioY,
				1,
				AssetConstantsBulletWar.back, new PackMenuBllet());
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);

		x = 170f * LevelInfoBullet.ratioX;
		y = 400f * LevelInfoBullet.ratioY;
		if (pageIndex == tpageNumber - 1 && valI > 0)
			currentpageLevelnumber = valI;
		for (int i = 0; i < currentpageLevelnumber; i++) {
			currentLevel = ((pageIndex * LN) + i + 1);
			
			ButtonLevelBullet level = new ButtonLevelBullet(x, y, 200f * LevelInfoBullet.ratioX,
					150f * LevelInfoBullet.ratioY, 1,
					currentLevel, fontController,
					preference_bullet.isLevelEnabled(PreferenceBullet.pref_level, LevelMenuBullet.packIndex, currentLevel));
			
//			ButtonLevel level = new ButtonLevel(x, y, 150f * LevelInfo.ratioX,
//					100f * LevelInfo.ratioY, 1, AssetConstants.IMG_BIKE,
//					currentLevel, fontController);
			
			this.elements.add(level);
			subscribeToControllingInteraction(level, InteractionTouch.class);
			if (i < 2) {
				x = x + 250 * LevelInfoBullet.ratioX;
			} else if (i == 2) {
				x = 170 * LevelInfoBullet.ratioX;
				y = 200f * LevelInfoBullet.ratioY;
			} else {
				x = x + 250 * LevelInfoBullet.ratioX;
			}
		}

	}

	public void loadFonts() {
		fontController.addFont(AssetConstantsBulletWar.FONT_1, AssetConstantsBulletWar.FONT_1,
				10, 7);
		fontController.addFont(AssetConstantsBulletWar.FONT_NEON2,
				AssetConstantsBulletWar.FONT_NEON2, 10, 7);
	}

	@Override
	public void tick(long stepTime) {

	}


}
