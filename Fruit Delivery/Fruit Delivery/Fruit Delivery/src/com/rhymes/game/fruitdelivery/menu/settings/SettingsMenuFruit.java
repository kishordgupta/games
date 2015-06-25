package com.rhymes.game.fruitdelivery.menu.settings;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.carspeedpro.BackGroundUniversal;
import com.rhymes.game.carspeedpro.menu.ButtonStageLoad;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.main.MainMenuFruit;
import com.rhymes.game.fruitdelivery.menu.settings.res.RadioSpeedFruit;
import com.rhymes.game.fruitdelivery.menu.settings.res.SoundFruit;
import com.rhymes.game.fruitdelivery.menu.settings.res.SpeedImageButton;
import com.rhymes.game.fruitdelivery.menu.settings.res.SpeedImageButton.speedTypeFruit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SettingsMenuFruit extends StageBase {
	BackGroundUniversal bg, sound_img, speed_img;
	SoundFruit soundbutt;
	public SpeedImageButton speed_slow, speed_normal, speed_fast;
	public RadioSpeedFruit[] radio;
	ButtonStageLoad back;

	float x, y;
	public static int num_of_speed = 3;

	InteractionTouch interaction_touch;

	@Override
	public void loadElements() {
		interaction_touch = new InteractionTouch();
		this.interactions.add(interaction_touch);

		radio = new RadioSpeedFruit[num_of_speed];
		set_settings_menu();

	}

	public void set_settings_menu() {

		if(BikeLevel.gameMode == BikeLevel.GW_FAST)
		{
			is_game_mode_1 = false;
			is_game_mode_2 = false;
			is_game_mode_3 = true;
		}
		else if(BikeLevel.gameMode == BikeLevel.GW_NORMAL)
		{
			is_game_mode_1 = false;
			is_game_mode_2 = true;
			is_game_mode_3 = false;
		}
		else if(BikeLevel.gameMode == BikeLevel.GW_SLOW)
		{
			is_game_mode_1 = true;
			is_game_mode_2 = false;
			is_game_mode_3 = false;
		}

		x = 0f;
		y = 0f;
		bg = new BackGroundUniversal(x, y, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstantsFruit.ground_bg);
		this.elements.add(bg);
		
		

		x = Gdx.graphics.getWidth()
				/ 2f
				- Helper.getImageFromAssets(AssetConstantsFruit.sound_img)
						.getRegionWidth() * AssetConstantsFruit.ratio_w;
		y = Gdx.graphics.getHeight()
				/ 2f
				+ 140f
				* AssetConstantsFruit.ratio_h
				- Helper.getImageFromAssets(AssetConstantsFruit.sound_img)
						.getRegionHeight() / 2f * AssetConstantsFruit.ratio_h;
		sound_img = new BackGroundUniversal(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.sound_img).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.sound_img).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.sound_img);
		this.elements.add(sound_img);

		x = Gdx.graphics.getWidth()
				/ 2f
				+ 2.5f
				* Helper.getImageFromAssets(AssetConstantsFruit.sound_on)
						.getRegionWidth() / 2f * AssetConstantsFruit.ratio_w;
		soundbutt = new SoundFruit(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.sound_on).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.sound_on).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, get_game_sound_image());
		this.elements.add(soundbutt);
		this.interaction_touch.subscribe(soundbutt);

		x = Gdx.graphics.getWidth()
				/ 2f
				- Helper.getImageFromAssets(AssetConstantsFruit.speed_img)
						.getRegionWidth() * AssetConstantsFruit.ratio_w;
		y = y
				- 2.5f
				* Helper.getImageFromAssets(AssetConstantsFruit.speed_img)
						.getRegionHeight() * AssetConstantsFruit.ratio_h + 60
				* AssetConstantsFruit.ratio_h;
		speed_img = new BackGroundUniversal(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_img).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_img).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, AssetConstantsFruit.speed_img);
		this.elements.add(speed_img);

		x = Gdx.graphics.getWidth()
				/ 2f
				+ 2.5f
				* Helper.getImageFromAssets(AssetConstantsFruit.sel_image)
						.getRegionWidth() / 2f * AssetConstantsFruit.ratio_w;
		radio[0] = new RadioSpeedFruit(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, get_mode_image_1(), BikeLevel.GW_SLOW);
		this.elements.add(radio[0]);
		this.interaction_touch.subscribe(radio[0]);

		x = x + 70 * AssetConstantsFruit.ratio_w;
		speed_slow = new SpeedImageButton(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_slow).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_slow).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1,
				AssetConstantsFruit.speed_slow, speedTypeFruit.speed_slow);
		this.elements.add(speed_slow);
		this.interaction_touch.subscribe(speed_slow);

		x = Gdx.graphics.getWidth()
				/ 2f
				+ 2.5f
				* Helper.getImageFromAssets(AssetConstantsFruit.sel_image)
						.getRegionWidth() / 2f * AssetConstantsFruit.ratio_w;
		y = y - 100 * AssetConstantsFruit.ratio_h;
		radio[1] = new RadioSpeedFruit(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, get_mode_image_2(), BikeLevel.GW_NORMAL);
		this.elements.add(radio[1]);
		this.interaction_touch.subscribe(radio[1]);

		x = x + 70 * AssetConstantsFruit.ratio_w;
		speed_normal = new SpeedImageButton(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_normal).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_normal).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1,
				AssetConstantsFruit.speed_normal, speedTypeFruit.speed_normal);
		this.elements.add(speed_normal);
		this.interaction_touch.subscribe(speed_normal);

		x = Gdx.graphics.getWidth()
				/ 2f
				+ 2.5f
				* Helper.getImageFromAssets(AssetConstantsFruit.sel_image)
						.getRegionWidth() / 2f * AssetConstantsFruit.ratio_w;
		y = y - 100 * AssetConstantsFruit.ratio_h;
		radio[2] = new RadioSpeedFruit(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.sel_image).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1, get_mode_image_3(), BikeLevel.GW_FAST);
		this.elements.add(radio[2]);
		this.interaction_touch.subscribe(radio[2]);

		x = x + 70 * AssetConstantsFruit.ratio_w;
		speed_fast = new SpeedImageButton(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_fast).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.speed_fast).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1,
				AssetConstantsFruit.speed_fast, speedTypeFruit.speed_fast);
		this.elements.add(speed_fast);
		this.interaction_touch.subscribe(speed_fast);

		x = 40 * LevelInfo.ratioX;
		y = 35f * LevelInfo.ratioY;

		back = new ButtonStageLoad(x, y, Helper.getImageFromAssets(
				AssetConstantsFruit.back_settings_nor).getRegionWidth()
				* AssetConstantsFruit.ratio_w, Helper.getImageFromAssets(
				AssetConstantsFruit.back_settings_nor).getRegionHeight()
				* AssetConstantsFruit.ratio_h, 1,
				AssetConstantsFruit.back_settings_nor, new MainMenuFruit());
		this.elements.add(back);
		this.interaction_touch.subscribe(back);
		
		
		
	}

	public static boolean is_game_mode_1 = true;
	String mode_image_1 = null;

	public String get_mode_image_1() {

		if (is_game_mode_1)
			mode_image_1 = AssetConstantsFruit.sel_image;
		else
			mode_image_1 = AssetConstantsFruit.sel_plexi_image;

		return mode_image_1;
	}

	public static boolean is_game_mode_2 = false;
	String mode_image_2 = null;

	public String get_mode_image_2() {

		if (is_game_mode_2)
			mode_image_2 = AssetConstantsFruit.sel_image;
		else
			mode_image_2 = AssetConstantsFruit.sel_plexi_image;

		return mode_image_2;
	}

	public static boolean is_game_mode_3 = false;
	String mode_image_3 = null;

	public String get_mode_image_3() {

		if (is_game_mode_3)
			mode_image_3 = AssetConstantsFruit.sel_image;
		else
			mode_image_3 = AssetConstantsFruit.sel_plexi_image;

		return mode_image_3;
	}

	public void set_image_speed() {
		if (speed_slow.isActive()) {
			is_game_mode_1 = true;
			is_game_mode_2 = false;
			is_game_mode_3 = false;
		} else if (speed_normal.isActive()) {
			is_game_mode_2 = true;
			is_game_mode_1 = false;
			is_game_mode_3 = false;
		} else {
			is_game_mode_3 = true;
			is_game_mode_2 = false;
			is_game_mode_1 = false;
		}
	}

	public static boolean is_game_sound_active = true;
	String game_sound_image = null;

	public String get_game_sound_image() {

		if (is_game_sound_active)
			game_sound_image = AssetConstantsFruit.sound_on;
		else
			game_sound_image = AssetConstantsFruit.sound_off;

		return game_sound_image;
	}

	public void consumeTouchSpeed() {
		if (speed_fast.isActive()) {
			speed_fast.setActive(false);
		}

		else if (speed_slow.isActive()) {
			speed_slow.setActive(false);
		}

		else if (speed_normal.isActive()) {
			speed_normal.setActive(false);
		}
	}

	int i;

	public void consumeTouchRadio() {
		for (i = 0; i < num_of_speed; i++) {
			if (radio[i].isActive())
				radio[i].setActive(false);
		}
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstantsFruit.main_bg);
		assetPack.addTexture(AssetConstantsFruit.speed_img);
		assetPack.addTexture(AssetConstantsFruit.speed_fast);
		assetPack.addTexture(AssetConstantsFruit.speed_normal);
		assetPack.addTexture(AssetConstantsFruit.speed_slow);
		assetPack.addTexture(AssetConstantsFruit.sound_img);
		assetPack.addTexture(AssetConstantsFruit.sound_on);
		assetPack.addTexture(AssetConstantsFruit.sound_off);
		assetPack.addTexture(AssetConstantsFruit.sel_image);
		assetPack.addTexture(AssetConstantsFruit.sel_plexi_image);
		assetPack.addTexture(AssetConstantsFruit.back_settings_d);
		assetPack.addTexture(AssetConstantsFruit.back_settings_nor);

		return assetPack;
	}

	@Override
	public void tick(long stepTime) {

	}

}
