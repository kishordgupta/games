package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonChooseLevel;
import com.rhymes.game.entity.elements.menu.ButtonChooseLocation;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonInstruction;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonPlayerInfo;
import com.rhymes.game.entity.elements.menu.ButtonSelectLocation;
import com.rhymes.game.entity.elements.menu.ButtonSelectShot;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class OptionMenu extends StageBase{
	
	float x;
	float y;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_MAIN);
		assetPack.addTexture(AssetConstants.IMG_OPTIONS);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_CPB);
		
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF_PRESSED);
		
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON);
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF_PRESSED);


		return assetPack;
	}

	@Override
	public void loadElements() {
		Helper.println("Now i'm in Optionmenu");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_MAIN));
		//music
		x = 200;
		y = 0;
		ButtonMusicControl buttonMusic = new ButtonMusicControl(x, y,30,30,1,AssetConstants.IMG_MUSIC_ON);
		this.elements.add(buttonMusic);
		subscribeToControllingInteraction(buttonMusic, InteractionTouch.class);
		//sound
		y = 70;
		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y,30,30, 1, AssetConstants.IMG_SOUND_ON);
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);
		//buttonChoosePlayerandBall
		y = 140;
		ButtonChoosePlayerandBall buttonChoosePlayerandBall = new ButtonChoosePlayerandBall(x, y, 150, 60,1, AssetConstants.IMG_CPB);
		this.elements.add(buttonChoosePlayerandBall);
		subscribeToControllingInteraction(buttonChoosePlayerandBall, InteractionTouch.class);
		//ButtonChooseLocation
		y = 210;
		ButtonChooseLocation buttonChooseLocation = new ButtonChooseLocation(x, y, 100, 40,1, AssetConstants.IMG_CL);
		this.elements.add(buttonChooseLocation);
		subscribeToControllingInteraction(buttonChooseLocation, InteractionTouch.class);
		//ButtonInstruction
		y = 280;
		ButtonInstruction buttonInstruction = new ButtonInstruction(x, y, 100, 40,1, AssetConstants.IMG_CL);
		this.elements.add(buttonInstruction);
		subscribeToControllingInteraction(buttonInstruction, InteractionTouch.class);
		//button back to main menu
		x= 10;
		y = 290;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 30, 30,1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		//Choose Shhot/level
		y = 10;
		ButtonChooseLevel buttonChooseLevel = new ButtonChooseLevel(x, y, 150, 60,1, AssetConstants.IMG_CS);
		this.elements.add(buttonChooseLevel);
		subscribeToControllingInteraction(buttonChooseLevel, InteractionTouch.class);
		y = 70;
		ButtonPlayerInfo buttonPlayerInfo = new ButtonPlayerInfo(x, y, 150, 60,1, AssetConstants.IMG_CS);
		this.elements.add(buttonPlayerInfo);
		subscribeToControllingInteraction(buttonPlayerInfo, InteractionTouch.class);
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}
