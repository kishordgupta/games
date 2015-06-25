package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.menu.ButtonNextInstruction;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonSelectBall;
import com.rhymes.game.entity.elements.menu.ButtonSelectPlayer;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Instructions extends StageBase{
	public TextureRegion[] imgInstruction = new TextureRegion[6];
	
	float x;
	float y;
	Background instructionImage;//= new Background();

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_INS1);
		assetPack.addTexture(AssetConstants.IMG_INS2);
		assetPack.addTexture(AssetConstants.IMG_INS3);
		assetPack.addTexture(AssetConstants.IMG_INS4);
		assetPack.addTexture(AssetConstants.IMG_INS5);
		assetPack.addTexture(AssetConstants.IMG_INS6);
		assetPack.addTexture(AssetConstants.IMG_INS_BCK);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		return assetPack;
	}

	@Override
	public void loadElements() {
		imgInstruction[0] = Helper.getImageFromAssets(AssetConstants.IMG_INS1);
		imgInstruction[1] = Helper.getImageFromAssets(AssetConstants.IMG_INS2);
		imgInstruction[2] = Helper.getImageFromAssets(AssetConstants.IMG_INS3);
		imgInstruction[3] = Helper.getImageFromAssets(AssetConstants.IMG_INS4);
		imgInstruction[4] = Helper.getImageFromAssets(AssetConstants.IMG_INS5);
		imgInstruction[5] = Helper.getImageFromAssets(AssetConstants.IMG_INS6);
		Helper.println("Now i'm in Instruction class ");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());
//		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
//				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_INS1));
		instructionImage = new Background(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), 1, AssetConstants.IMG_INS1);
		this.elements.add(instructionImage);
		ButtonNextInstruction buttonBackToInstruction = new ButtonNextInstruction(10*GameMenuInfo.ratio_w, 280*GameMenuInfo.ratio_h, 42*GameMenuInfo.ratio_w, 36*GameMenuInfo.ratio_h,1, AssetConstants.IMG_BACK,false,true);
		this.elements.add(buttonBackToInstruction);
		subscribeToControllingInteraction(buttonBackToInstruction, InteractionTouch.class);
		ButtonNextInstruction buttonNextIns = new ButtonNextInstruction(425*GameMenuInfo.ratio_w,160*GameMenuInfo.ratio_h, 50*GameMenuInfo.ratio_w, 50*GameMenuInfo.ratio_h,1, AssetConstants.IMG_INS_BCK_PRESSED,true);
		this.elements.add(buttonNextIns);
		subscribeToControllingInteraction(buttonNextIns, InteractionTouch.class);
		ButtonNextInstruction buttonPrevIns = new ButtonNextInstruction(5*GameMenuInfo.ratio_w, 160*GameMenuInfo.ratio_h, 50*GameMenuInfo.ratio_w, 50*GameMenuInfo.ratio_h,1, AssetConstants.IMG_BACK,false);
		this.elements.add(buttonPrevIns);
		subscribeToControllingInteraction(buttonPrevIns, InteractionTouch.class);

	}
	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
	public void showNextInstruction(int instructionNo) {
		// TODO Auto-generated method stub
		
		this.instructionImage.setImage(imgInstruction[instructionNo]);
		
			
	}
}
