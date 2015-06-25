package com.rhymes.game.entity.elements.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ChooseLocation;
import com.rhymes.game.stage.menus.ChoosePlayerandBall;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonSelectShot extends Button{
	@Override
	public void render() {
		super.render();
	}

	public ButtonSelectShot(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		this.image = Helper.getImageFromAssets(imagePath);
	}

	public ButtonSelectShot(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
}
