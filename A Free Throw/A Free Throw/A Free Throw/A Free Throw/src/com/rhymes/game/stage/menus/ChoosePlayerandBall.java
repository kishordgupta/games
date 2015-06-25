package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonChooseLevel;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonInfo;
import com.rhymes.game.entity.elements.menu.ButtonInfoBack;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonSelectBall;
import com.rhymes.game.entity.elements.menu.ButtonSelectPlayer;
import com.rhymes.game.entity.elements.menu.ButtonSelectShot;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.menu.ButtonStartGame;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

public class ChoosePlayerandBall extends StageBase{
	public String[] imgPlayer = new String[6];
	public String[] imgBall = new String[3];
	
	float x;
	float y;
	float w;
	float h;
	public ButtonSelectBall selectedBall = null;
	public ButtonSelectPlayer selectedPlayer = null;
	public ButtonInfo buttoninfo;
	private ButtonStartGame buttongo;
	private SoundHandler soundHandler;
	public static ButtonInfoBack buttonInfoBack;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_CPB_BKG);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_PAUL);
		assetPack.addTexture(AssetConstants.IMG_JAMES);
		assetPack.addTexture(AssetConstants.IMG_JOHN);
		assetPack.addTexture(AssetConstants.IMG_CHARLES);
		assetPack.addTexture(AssetConstants.IMG_AMY);
		assetPack.addTexture(AssetConstants.IMG_POLAR);
		
		assetPack.addTexture(AssetConstants.IMG_BALL_BASKET);
		assetPack.addTexture(AssetConstants.IMG_BALL_RUBBER);
		assetPack.addTexture(AssetConstants.IMG_BALL_BOWL);
		assetPack.addTexture(AssetConstants.IMG_INFO);
		assetPack.addTexture(AssetConstants.IMG_INFO_PRESSED);
		
		assetPack.addTexture(AssetConstants.IMG_INFO_PAUL);
		assetPack.addTexture(AssetConstants.IMG_INFO_JAMES);
		assetPack.addTexture(AssetConstants.IMG_INFO_JOHN);
		assetPack.addTexture(AssetConstants.IMG_INFO_JOHN);
		assetPack.addTexture(AssetConstants.IMG_INFO_AMY);
		assetPack.addTexture(AssetConstants.IMG_INFO_POLAR);


		return assetPack;
	}
/*	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		imgPlayer[0] = "AssetConstants.IMG_PAUL";
		imgPlayer[1] = "AssetConstants.IMG_JAMES";
		imgPlayer[2] = "AssetConstants.IMG_JOHN";
		imgPlayer[3] = "AssetConstants.IMG_CHARLES";
		imgPlayer[41] = "AssetConstants.IMG_AMY";
		imgPlayer[5] = "AssetConstants.IMG_POLAR";
		imgBall[0] = "AssetConstants.IMG_BALL_BASKET";
		imgBall[1] = "AssetConstants.IMG_BALL_RUBBER";
		imgBall[2] = "AssetConstants.IMG_BALL_BOWL";
//		imgClick = Helper.getImageFromAssets(AssetConstants.IMG_CLICK);
//		this.setImages(imgNormal);
	}*/

	@Override
	public void loadElements() {
		imgPlayer[0] = AssetConstants.IMG_PAUL;
		imgPlayer[1] = AssetConstants.IMG_JAMES;
		imgPlayer[2] = AssetConstants.IMG_JOHN;
		imgPlayer[3] = AssetConstants.IMG_CHARLES;
		imgPlayer[4] = AssetConstants.IMG_AMY;
		imgPlayer[5] = AssetConstants.IMG_POLAR;
		imgBall[0] = AssetConstants.IMG_BALL_BASKET;
		imgBall[1] = AssetConstants.IMG_BALL_RUBBER;
		imgBall[2] = AssetConstants.IMG_BALL_BOWL;
		Helper.println("Now i'm in Choose playerandball class");
//			StartupInfo.sound_handler.playMusic(SoundHandler.musicType.MUSIC_MENU);
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_CPB_BKG));
		//back
		x =0*GameMenuInfo.ratio_w;
		y =280*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, w, h,1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		x =440*GameMenuInfo.ratio_w;
		y =280*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		buttoninfo = new ButtonInfo(x, y, w, h,1, AssetConstants.IMG_INFO,false);
		this.elements.add(buttoninfo);
		subscribeToControllingInteraction(buttoninfo, InteractionTouch.class);
		x = 440*GameMenuInfo.ratio_w;
		y = 20*GameMenuInfo.ratio_h;
		w=42*GameMenuInfo.ratio_w;
		h=36*GameMenuInfo.ratio_h;
		ButtonChooseLevel buttonChooseLevel = new ButtonChooseLevel(x, y, w, h,1, AssetConstants.IMG_GO);
		this.elements.add(buttonChooseLevel);
		subscribeToControllingInteraction(buttonChooseLevel, InteractionTouch.class);
		
		
		ButtonSelectPlayer buttonSelectPlayer[] = new ButtonSelectPlayer[6];
		ButtonSelectBall buttonSelectBall[] = new ButtonSelectBall[3];
		x = 60*GameMenuInfo.ratio_w;
		y = 160*GameMenuInfo.ratio_h;
		for (int i = 0; i < 6; i++) {
//			buttonSelectPlayer[i] = new ButtonSelectPlayer(x, y, 50, 50,
//					1, imgPlayer[i]);
			buttonSelectPlayer[i] = new ButtonSelectPlayer(x, y, 75*GameMenuInfo.ratio_w, 75*GameMenuInfo.ratio_h,
					1, imgPlayer[i],i+1);
			
			this.elements.add(buttonSelectPlayer[i]);
			subscribeToControllingInteraction(buttonSelectPlayer[i],
					InteractionTouch.class);
			x = x + 60*GameMenuInfo.ratio_w;
			if (x > 420*GameMenuInfo.ratio_w) {
				x = 20*GameMenuInfo.ratio_w;
				y = y- 60*GameMenuInfo.ratio_h;
			}
		}
		x = 60*GameMenuInfo.ratio_w;
		y = 85*GameMenuInfo.ratio_h;
		for (int i = 0; i < 3; i++) {//for2 start
			buttonSelectBall[i] = new ButtonSelectBall(x, y, 75*GameMenuInfo.ratio_w, 75*GameMenuInfo.ratio_h,
					1, imgBall[i],i+1);
			this.elements.add(buttonSelectBall[i]);
			subscribeToControllingInteraction(buttonSelectBall[i],
					InteractionTouch.class);
			x = x + 60*GameMenuInfo.ratio_w;
			if (x > 420*GameMenuInfo.ratio_w) {
				x = 20*GameMenuInfo.ratio_w;
				y = y - 60*GameMenuInfo.ratio_h;
			}
		}///for2 end
		
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());

	}
	public void selectBall(ButtonSelectBall buttonSelectBall) {
		
		if(this.selectedBall != null && !(selectedBall == buttonSelectBall))
			{selectedBall.clearSelection();}
		this.selectedBall = buttonSelectBall;
//		((BounceTest)GlobalVars.ge.getCurrentStage()).setPlayerNo(select.get)
		Constants.ballSelected = selectedBall.getBcb();
		Helper.println("selectedBall"+Constants.ballSelected);
	}
	public void selectPlayer(ButtonSelectPlayer buttonSelectPlayer) {
		
		if(this.selectedPlayer != null && !(selectedPlayer == buttonSelectPlayer))
			{selectedPlayer.clearSelection();}
		this.selectedPlayer = buttonSelectPlayer;
		
		Constants.playerSelected = selectedPlayer.getBcp();
		Helper.println("selectedPlayer"+Constants.playerSelected);
		
	}

	public void showPlayerInfo() {
		// TODO Auto-generated method stub
//		this.postToRemoveList(element)
			// TODO Auto-generated method stub
			String[] imgPlrInfo = new String[6];
			imgPlrInfo[0] = AssetConstants.IMG_INFO_PAUL;
			imgPlrInfo[1] = AssetConstants.IMG_INFO_JAMES;
			imgPlrInfo[2] = AssetConstants.IMG_INFO_JOHN;
			imgPlrInfo[3] = AssetConstants.IMG_INFO_JOHN;
			imgPlrInfo[4] = AssetConstants.IMG_INFO_AMY;
			imgPlrInfo[5] = AssetConstants.IMG_INFO_POLAR;
				Helper.println("element added");
//				Background playerInfo = new Background(0, 0, 480, 320, 1, imgPlrInfo[Constants.playerSelected-1]);
//				this.elements.add(playerInfo);
//				((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).addElement(playerInfo);
				buttonInfoBack = new ButtonInfoBack(40*GameMenuInfo.ratio_w, 40*GameMenuInfo.ratio_h, 400*GameMenuInfo.ratio_w, 260*GameMenuInfo.ratio_h,1, imgPlrInfo[Constants.playerSelected-1],true);
				((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).addElement(buttonInfoBack);
				subscribeToControllingInteraction(buttonInfoBack, InteractionTouch.class);
//				((ChoosePlayerandBall)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(buttonInfoBack, InteractionTouch.class);
//				buttonInfoBack.setInfoBack(false);
				
				

	}
}
