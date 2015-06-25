package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonChooseLevel;
import com.rhymes.game.entity.elements.menu.ButtonChooseLocation;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonEnterGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonExitGame;
import com.rhymes.game.entity.elements.menu.ButtonInstruction;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonPlayerInfo;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class MainMenu extends StageBase {

	ISingleCollision collider;
//	PlanePathSet pathSet;
	
	float x;
	float y;
	float w;
	float h;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_MAIN);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU);
		assetPack.addTexture(AssetConstants.IMG_PLAY_MENU_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_OPTIONS);
		assetPack.addTexture(AssetConstants.IMG_EXIT);
		assetPack.addTexture(AssetConstants.IMG_EXIT_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF);
		assetPack.addTexture(AssetConstants.IMG_MUSIC_OFF_PRESSED);
		
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON);
		assetPack.addTexture(AssetConstants.IMG_SOUND_ON_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF);
		assetPack.addTexture(AssetConstants.IMG_SOUND_OFF_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_PLAY_TEXT);
		assetPack.addTexture(AssetConstants.IMG_INS);

		return assetPack;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	

		
	}

	@Override
	public void loadElements() {
		Helper.println("Now i'm in mainmenu");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());
		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_MAIN));
//		Helper.println(""+Helper.getImageFromAssets(AssetConstants.IMG_EXIT).getRegionWidth());
		x=180*GameMenuInfo.ratio_w;
		y = 70*GameMenuInfo.ratio_h;
		w=147*GameMenuInfo.ratio_w;
		h=67*GameMenuInfo.ratio_h;
		ButtonChoosePlayerandBall buttonChoosePlayerandBall = new ButtonChoosePlayerandBall(x, y, w, h,1, AssetConstants.IMG_PLAY_TEXT);
		this.elements.add(buttonChoosePlayerandBall);
		subscribeToControllingInteraction(buttonChoosePlayerandBall, InteractionTouch.class);

		x=180*GameMenuInfo.ratio_w;
		y = 20*GameMenuInfo.ratio_h;
		w=147*GameMenuInfo.ratio_w;
		h=67*GameMenuInfo.ratio_h;
		ButtonInstruction buttonInstruction = new ButtonInstruction(x, y, w, h,1, AssetConstants.IMG_INS);
		this.elements.add(buttonInstruction);
		subscribeToControllingInteraction(buttonInstruction, InteractionTouch.class);
		//music
//		x = 05;
//		y = 290;
		x=10*GameMenuInfo.ratio_w;
		y = 280*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		
		ButtonMusicControl buttonMusic = new ButtonMusicControl(x, y,w,h,1,AssetConstants.IMG_MUSIC_ON);
		this.elements.add(buttonMusic);
		subscribeToControllingInteraction(buttonMusic, InteractionTouch.class);
		
		//sound
//		x= 40;
//		y = 290;
		x=50*GameMenuInfo.ratio_w;
		y = 280*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		
		ButtonSoundControl buttonSound = new ButtonSoundControl(x, y,w,h, 1, AssetConstants.IMG_SOUND_ON);
		this.elements.add(buttonSound);
		subscribeToControllingInteraction(buttonSound, InteractionTouch.class);
		

		//exit
//		x= 450;
//		y= 290;
		x=440*GameMenuInfo.ratio_w;
		y = 280*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		ButtonExitGame buttonExitgame = new ButtonExitGame(x, y,w,h, 1, AssetConstants.IMG_EXIT,true);
		this.elements.add(buttonExitgame);
		subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
		//start game
//		x=150;
//		y=250;
//		ButtonEnterGameMenu buttonEntergame = new ButtonEnterGameMenu(x,y,140,60, 1, AssetConstants.IMG_PLAY_MENU);
//		this.elements.add(buttonEntergame);
//		subscribeToControllingInteraction(buttonEntergame, InteractionTouch.class);
		//buttonChoosePlayerandBall
//		y = 70;
//		ButtonPlayerInfo buttonPlayerInfo = new ButtonPlayerInfo(x, y, 150, 60,1, AssetConstants.IMG_CS);
//		this.elements.add(buttonPlayerInfo);
//		subscribeToControllingInteraction(buttonPlayerInfo, InteractionTouch.class);
//		//option
//		ButtonOptionGameMenu buttonOption = new ButtonOptionGameMenu(150,200, 60,40,1, AssetConstants.IMG_OPTIONS);
//		this.elements.add(buttonOption);
//		subscribeToControllingInteraction(buttonOption, InteractionTouch.class);
		//ButtonChooseLocation
//		x=150;
//		y = 170;
//		ButtonChooseLocation buttonChooseLocation = new ButtonChooseLocation(x, y, 150, 60,1, AssetConstants.IMG_CL);
//		this.elements.add(buttonChooseLocation);
//		subscribeToControllingInteraction(buttonChooseLocation, InteractionTouch.class);
		//Choose Shhot/level
//		x= 150;
//		y = 130;
//		ButtonChooseLevel buttonChooseLevel = new ButtonChooseLevel(x, y, 150, 60,1, AssetConstants.IMG_PLAY_MENU);
//		this.elements.add(buttonChooseLevel);
//		subscribeToControllingInteraction(buttonChooseLevel, InteractionTouch.class);
		//ButtonInstruction
		
	}
	long prevSteptime;
	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}
