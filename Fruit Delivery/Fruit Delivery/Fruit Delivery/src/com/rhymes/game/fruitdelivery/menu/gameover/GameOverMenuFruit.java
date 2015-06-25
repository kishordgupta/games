package com.rhymes.game.fruitdelivery.menu.gameover;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.main.MainMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.res.LevelInfoFruit;
import com.rhymes.game.fruitdelivery.menu.selectvehicle.SelectVehicleMenuFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.stick.ScoreManage;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverMenuFruit extends StageBase {
	BackGroundUniversal bg, score;
	ButtonStageLoad retry, yes, no, play_again;
	public Text textTotalConsumedFruit;
	ScoreManage scoremanage = new ScoreManage();

	float x, y;
	boolean failed;

	InteractionTouch interaction_touch;
	private ButtonStageLoad back;
	private Text textTotalIssuedFruit;

	public GameOverMenuFruit(boolean failed) {
		this.failed = failed;

	}

	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(interaction_touch);
		loadFonts();
		set_game_over();

	}

	public void set_game_over() {
		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstantsFruit.main_bg);
		this.elements.add(bg);

		if (!failed) {
			x = Gdx.graphics.getWidth() / 2f - 250
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight() / 2f + 200
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}
		score = new BackGroundUniversal(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.score_img).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.score_img).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.score_img);
		this.elements.add(score);

		if (!failed) {
			x = Gdx.graphics.getWidth() / 2f - 40f
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight() / 2f + 215
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}
		float r, g, b, a;
		r = 0.99f;
		g = 0.99f;
		b = 0.2f;
		a = 0.9f;
		textTotalIssuedFruit = new Text(x, y, fontController,
				AssetConstants.FONT_1, "Issued: "
						+ scoremanage.getTotalIssuedFruit() + " 	Pc");
		textTotalIssuedFruit.setColor(r, g, b, a);
		this.elements.add(textTotalIssuedFruit);

		if (!failed) {
			x = Gdx.graphics.getWidth() / 2f - 40f
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight() / 2f + 170
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}

		textTotalConsumedFruit = new Text(x, y, fontController,
				AssetConstants.FONT_1, "Delivered: "
						+ scoremanage.getTotalTruckedFruit() + " 	Pc");
		textTotalConsumedFruit.setColor(r, g, b, a);
		this.elements.add(textTotalConsumedFruit);
		if (failed) {
			x = Gdx.graphics.getWidth()
					/ 2f
					- Helper.getImageFromAssets(AssetConstantsFruit.retry_nor)
							.getRegionWidth() / 2f
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight()
					/ 2f
					- 200
					* AssetConstantsFruit.ratio_h
					+ Helper.getImageFromAssets(AssetConstantsFruit.retry_nor)
							.getRegionHeight() / 2f
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}
		retry = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.retry_nor).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.retry_nor).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1,
				AssetConstantsFruit.retry_nor, new BikeLevel(
						LevelInfoFruit.currentLevelNumber));
		this.elements.add(retry);
		this.interaction_touch.subscribe(retry);

		if (failed) {
			x = Gdx.graphics.getWidth()
					/ 2f
					- Helper.getImageFromAssets(AssetConstantsFruit.back_nor)
							.getRegionWidth() / 2f
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight()
					/ 2f
					- 300
					* AssetConstantsFruit.ratio_h
					+ Helper.getImageFromAssets(AssetConstantsFruit.back_nor)
							.getRegionHeight() / 2f
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}

		back = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.back_nor).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.back_nor).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.back_nor,
				new SelectVehicleMenuFruit());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);

		if (!failed) {
			x = Gdx.graphics.getWidth()
					/ 2f
					- Helper.getImageFromAssets(AssetConstantsFruit.retry_nor)
							.getRegionWidth() / 2f
					* AssetConstantsFruit.ratio_w;
			y = Gdx.graphics.getHeight()
					/ 2f
					- 200
					* AssetConstantsFruit.ratio_h
					+ Helper.getImageFromAssets(AssetConstantsFruit.retry_nor)
							.getRegionHeight() / 2f
					* AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}
		play_again = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.again).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.again).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.again,
				new BikeLevel(LevelInfoFruit.currentLevelNumber));
		this.elements.add(play_again);
		this.interaction_touch.subscribe(play_again);

		gap = 40f;

		if (!failed) {
			x = Gdx.graphics.getWidth()
					/ 2f
					- gap
					* AssetConstantsFruit.ratio_w
					- Helper.getImageFromAssets(AssetConstantsFruit.yes_nor)
							.getRegionWidth() * AssetConstantsFruit.ratio_w;
			y = 50 * AssetConstantsFruit.ratio_h;
		} else {
			x = -Gdx.graphics.getWidth();
			y = -Gdx.graphics.getHeight();
		}
		yes = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.yes_nor).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.yes_nor).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.yes_nor,
				new BikeLevel(LevelInfoFruit.currentLevelNumber));
		this.elements.add(yes);
		this.interaction_touch.subscribe(yes);

		if (!failed)
			x = Gdx.graphics.getWidth() / 2f + gap
					* AssetConstantsFruit.ratio_w;
		else {
			x = -Gdx.graphics.getHeight();
		}

		no = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.yes_nor).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.yes_nor).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.no_nor,
				new SelectVehicleMenuFruit());
		this.elements.add(no);
		this.interaction_touch.subscribe(no);

	}

	float gap;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		
		assetPack.addTexture(AssetConstantsFruit.back_nor);
		assetPack.addTexture(AssetConstantsFruit.yes_h);
		assetPack.addTexture(AssetConstantsFruit.yes_nor);
		assetPack.addTexture(AssetConstantsFruit.no_h);
		assetPack.addTexture(AssetConstantsFruit.no_nor);
		assetPack.addTexture(AssetConstantsFruit.main_bg);
		assetPack.addTexture(AssetConstantsFruit.retry_d);
		assetPack.addTexture(AssetConstantsFruit.retry_nor);
		assetPack.addTexture(AssetConstantsFruit.again);
		assetPack.addTexture(AssetConstantsFruit.score_img);

		return assetPack;
	}

	public void loadFonts() {
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_2,
				20, 15);
		// fontController.addFont(AssetConstants.FONT_1 + "2",
		// AssetConstants.FONT_2,
		// 40, 30);
	}

	@Override
	public void tick(long stepTime) {

	}

}
