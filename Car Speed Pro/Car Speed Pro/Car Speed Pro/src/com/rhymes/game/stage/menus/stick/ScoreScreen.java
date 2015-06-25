package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ScoreScreen extends StageBase {

	public float x = 0f;
	public float y = 0f;
	public int gameState = 0;

	public ScoreScreen(int gamestat) {
		this.gameState = gamestat;

	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_SCORE_WIN);
		assetPack.addTexture(AssetConstants.IMG_BKG_SCORE_FAIL);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_NEXTLEVEL);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUITLEVEL);
		return assetPack;
	}

	public void loadFonts() {
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_2,
				25, 20);
	}

	@Override
	public void loadElements() {
		loadFonts();
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		if (this.gameState == LevelInfo.GAME_WIN)
			this.elements.add(new Background(0f, 0f, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), 1,
					AssetConstants.IMG_BKG_SCORE_WIN));
		else
			// if(this.gameState == LevelInfo.GAME_FAILED)
			this.elements.add(new Background(0f, 0f, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight(), 1,
					AssetConstants.IMG_BKG_SCORE_FAIL));

		x = 150f * LevelInfo.ratioX;
		y = 295f * LevelInfo.ratioY;

		Text textDistance = new Text(x, y, fontController,
				AssetConstants.FONT_1, "Distance: " + ScoreManage.distance
						+ "m");
		textDistance.setColor(0.2f, 0.1f, 0.2f, 1.0f);
		addElement(textDistance);

		y = 245f * LevelInfo.ratioY;
		Text textTime = new Text(x, y, fontController, AssetConstants.FONT_1,
				"Time: " + (long) ScoreManage.time + " s");
		textTime.setColor(0.2f, 0.1f, 0.2f, 1.0f);
		addElement(textTime);
		// x = GlobalVars.ge.getScreen().cam.position.x;
		// y = GlobalVars.ge.getScreen().cam.position.y-20f*LevelInfo.ratioY;

		y = 150f * LevelInfo.ratioY;
		Text textPercent = new Text(x, y, fontController,
				AssetConstants.FONT_1, "Percent: " + (long) ScoreManage.percent
						+ "%");

		if (this.gameState == LevelInfo.GAME_FAILED)
			textPercent.setColor(0.8f, 0.1f, 0.2f, 0.8f);
		else
			textPercent.setColor(0.1f, 0.3f, 0.8f, 0.8f);
		addElement(textPercent);

		
		
		x = Gdx.graphics.getWidth() - 264f * LevelInfo.ratioX;
		if (gameState == LevelInfo.GAME_WIN) {
			y = 0;
			NextLevel nextLevel = new NextLevel(x, y, 
					264f * LevelInfo.ratioX, 142f * LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_NEXTLEVEL);
			Helper.println("width: " + Helper.getImageFromAssets(AssetConstants.IMG_BTN_NEXTLEVEL).getRegionWidth());
			Helper.println("height: " + Helper.getImageFromAssets(AssetConstants.IMG_BTN_NEXTLEVEL).getRegionHeight());
			this.elements.add(nextLevel);
			subscribeToControllingInteraction(nextLevel, InteractionTouch.class);
		}
		
		x = 630f * LevelInfo.ratioX;
		y = 250f * LevelInfo.ratioY;
		ButtonRestart reload = new ButtonRestart(x, y, 199 * LevelInfo.ratioX, 52f * LevelInfo.ratioX,
				1, AssetConstants.IMG_BTN_RESTART);
		this.elements.add(reload);
		subscribeToControllingInteraction(reload, InteractionTouch.class);
		// if(!LevelInfo.GAME_COMPLETE)
		// {
		// x = GlobalVars.ge.getScreen().cam.position.x;
		
		// }
//		y = 190f * LevelInfo.ratioY;
		y = 250 * LevelInfo.ratioY - 72 * LevelInfo.ratioY;
		ButtonExitLevel exit = new ButtonExitLevel(x, y, 
				199 * LevelInfo.ratioX, 52f * LevelInfo.ratioX,1, AssetConstants.IMG_BTN_QUITLEVEL);
		this.elements.add(exit);
		subscribeToControllingInteraction(exit, InteractionTouch.class);

	}

	@Override
	public void tick(long stepTime) {
		// TODO Auto-generated method stub

	}

}
