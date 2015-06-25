package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.MenuHome;
import com.rhymes.game.stage.menus.ModeMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class ButtonEnterGameMenu extends Button {

	public ButtonEnterGameMenu(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
//		adjust_animation();
	}

	
	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Helper.println("Enter Game");
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_PLAY_PRESSED));
//			set_pressed(true);
//			set_animation(AssetConstants.IMG_PLAY_MENU_PRESSED);
			
//			GlobalVars.ge.loadStage(new ModeMenu());
			GlobalVars.ge.loadStage(new MenuHome());
		}
	}

	public ButtonEnterGameMenu(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
