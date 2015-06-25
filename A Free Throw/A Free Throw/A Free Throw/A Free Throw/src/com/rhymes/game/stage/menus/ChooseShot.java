package com.rhymes.game.stage.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonSelectBall;
import com.rhymes.game.entity.elements.menu.ButtonSelectPlayer;
import com.rhymes.game.entity.elements.menu.ButtonSelectShot;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.entity.elements.unsorted.ISliderEventHandler;
import com.rhymes.game.entity.elements.unsorted.SlideSelector;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.game.stage.levels.BounceLevel1;
import com.rhymes.game.stage.levels.BounceLevel10;
import com.rhymes.game.stage.levels.BounceLevel11;
import com.rhymes.game.stage.levels.BounceLevel12;
import com.rhymes.game.stage.levels.BounceLevel2;
import com.rhymes.game.stage.levels.BounceLevel3;
import com.rhymes.game.stage.levels.BounceLevel4;
import com.rhymes.game.stage.levels.BounceLevel5;
import com.rhymes.game.stage.levels.BounceLevel6;
import com.rhymes.game.stage.levels.BounceLevel7;
import com.rhymes.game.stage.levels.BounceLevel8;
import com.rhymes.game.stage.levels.BounceLevel9;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ChooseShot extends StageBase{
//	public String[] imgPlayer = new String[6];
	public String[] imgShot = new String[13];
	public ArrayList<ElementBase> views = new ArrayList<ElementBase>(); 
	
	float x;
	float y;
	float w;
	float h;
	float nx =0, ny=0;
	public SlideSelector[] s= new SlideSelector[3];
//	public SlideSelector s2;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_CPB_BKG);
		assetPack.addTexture(AssetConstants.IMG_BKG_CS);
		assetPack.addTexture(AssetConstants.IMG_BACK_PRESSED);
		assetPack.addTexture(AssetConstants.IMG_SHOT1);
		assetPack.addTexture(AssetConstants.IMG_SHOT2);
		assetPack.addTexture(AssetConstants.IMG_SHOT3);
		assetPack.addTexture(AssetConstants.IMG_SHOT4);
		assetPack.addTexture(AssetConstants.IMG_SHOT5);
		assetPack.addTexture(AssetConstants.IMG_SHOT6);
		assetPack.addTexture(AssetConstants.IMG_SHOT7);
		assetPack.addTexture(AssetConstants.IMG_SHOT8);
		assetPack.addTexture(AssetConstants.IMG_SHOT9);
		assetPack.addTexture(AssetConstants.IMG_SHOT10);
		assetPack.addTexture(AssetConstants.IMG_SHOT11);
		assetPack.addTexture(AssetConstants.IMG_SHOT12);
		assetPack.addTexture(AssetConstants.IMG_SHOT_LOCK);
		assetPack.addTexture(AssetConstants.IMG_CHECK);


		return assetPack;
	}


	@Override
	public void loadElements() {		
		Helper.println("Now i'm in Choose shot class");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		//back
		y = 280*GameMenuInfo.ratio_h;
		x = 00*GameMenuInfo.ratio_w;
		
//		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 30, 30,1, AssetConstants.IMG_BACK);
//		this.elements.add(buttonBack);
//		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		addElement(new Background(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_CS));
		
		addLocation(0,0,0);
		addLocation(2.5f,0,1);
		
		addLocation(0,1.5f,2);
		addLocation(2.5f,1.5f,3);
		
		ButtonChoosePlayerandBall buttonChoosePlayerandBall = new ButtonChoosePlayerandBall(x, y, 42*GameMenuInfo.ratio_w, 36*GameMenuInfo.ratio_h,1, AssetConstants.IMG_BACK);
		this.elements.add(buttonChoosePlayerandBall);
		subscribeToControllingInteraction(buttonChoosePlayerandBall, InteractionTouch.class);
		
		addSelector(0.4f, 0.3f,2);
//		addSelector(2.9f, 0.3f,3);
		
		addSelector(0.4f,1.8f,0);
		addSelector(2.9f,1.8f,1);
		

	}
	
	
	private void addLocation(float offsetX, float OffsetY, int i) {
		// TODO Auto-generated method stub
		String[] imgLoc = new String[4];
		imgLoc[0] = AssetConstants.IMG_LOC_RANCH;
		imgLoc[1] = AssetConstants.IMG_LOC_RANCH_LOCKED;
		imgLoc[2] = AssetConstants.IMG_LOC_BACKYARD;
		imgLoc[3] = AssetConstants.IMG_LOC_PLAYGROUND;
			
			this.elements.add(new Background(100*offsetX*GameMenuInfo.ratio_w, 100*OffsetY*GameMenuInfo.ratio_h, Gdx.graphics.getWidth()/2,
					Gdx.graphics.getHeight()/2, 1, imgLoc[i]));
	}


	public void addSelector(float offsetX,float OffsetY,final int sNo)
	{
		String[] imgShot = new String[13];
		imgShot[0] = AssetConstants.IMG_CHECK;
		imgShot[1] = AssetConstants.IMG_SHOT1;
		imgShot[2] = AssetConstants.IMG_SHOT2;
		imgShot[3] = AssetConstants.IMG_SHOT3;
		imgShot[4] = AssetConstants.IMG_SHOT4;
		imgShot[5] = AssetConstants.IMG_SHOT5;
		imgShot[6] = AssetConstants.IMG_SHOT6;
		imgShot[7] = AssetConstants.IMG_SHOT7;
		imgShot[8] = AssetConstants.IMG_SHOT8;
		imgShot[9] = AssetConstants.IMG_SHOT9;
		imgShot[10] = AssetConstants.IMG_SHOT10;
		imgShot[11] = AssetConstants.IMG_SHOT11;
		imgShot[12] = AssetConstants.IMG_SHOT12;
		final int slideNo = sNo;
		
		ArrayList<ElementBase> elements = new ArrayList<ElementBase>();

		if (slideNo==0) {
			for (int i = 1; i < 5; i++) {
				elements.add(new ButtonSelectShot(140*GameMenuInfo.ratio_w, 260*GameMenuInfo.ratio_h, 40*GameMenuInfo.ratio_w, 40*GameMenuInfo.ratio_h, 3,
						imgShot[i]));
			}
		}
		
		else if (slideNo==1) {
			for (int i = 5; i < 9; i++) {
				elements.add(new ButtonSelectShot(140*GameMenuInfo.ratio_w, 260*GameMenuInfo.ratio_h, 40*GameMenuInfo.ratio_w, 40*GameMenuInfo.ratio_h, 3,
						imgShot[i]));
			}
		}
		else if (slideNo==2) {
			for (int i = 9; i < 13; i++) {
				elements.add(new ButtonSelectShot(140*GameMenuInfo.ratio_w, 260*GameMenuInfo.ratio_h, 40*GameMenuInfo.ratio_w, 40*GameMenuInfo.ratio_h, 3,
						imgShot[i]));
			}
		}
//		else if (slideNo==3) {
//			for (int i = 10; i < 13; i++) {
//				elements.add(new ButtonSelectShot(140, 260, 40, 40, 3,
//						imgShot[i]));
//			}
//		}
		s[slideNo] = new SlideSelector(100 * offsetX*GameMenuInfo.ratio_w , 100*OffsetY*GameMenuInfo.ratio_h, 200*GameMenuInfo.ratio_w, 100*GameMenuInfo.ratio_h, elements, 1, new ISliderEventHandler() {
			
			@Override
			public void onSwitchPrev() {
				Helper.println("Switching to Prev : " + (s[slideNo].getFocusIndex()+1));
				
				
			}
			
			@Override
			public void onSwitchNext() {
				Helper.println("Switching to Next" + (s[slideNo].getFocusIndex()+1));	
			}
			
			@Override
			public void onSlideHit() {
				Helper.println("Slide hit at index: " + (s[slideNo].getFocusIndex()+1));
//				((ButtonSelectShot)s.getSelected()).setImage(Helper.getImageFromAssets(AssetConstants.IMG_CHECK));
				Constants.levelSelected = (s[slideNo].getFocusIndex()+1+sNo*4);
//				addClick(s[slideNo].getX(),0,0);
				Helper.println("SelectedLevel " + Constants.levelSelected);
				if(Constants.levelSelected ==1)
				GlobalVars.ge.loadStage(new BounceLevel1());
				else if(Constants.levelSelected ==2)
					GlobalVars.ge.loadStage(new BounceLevel2());
				else if(Constants.levelSelected ==3)
					GlobalVars.ge.loadStage(new BounceLevel3());
				else if(Constants.levelSelected ==4)
					GlobalVars.ge.loadStage(new BounceLevel4());
				else if(Constants.levelSelected ==5)
					GlobalVars.ge.loadStage(new BounceLevel5());
				else if(Constants.levelSelected ==6)
					GlobalVars.ge.loadStage(new BounceLevel6());
				else if(Constants.levelSelected ==7)
					GlobalVars.ge.loadStage(new BounceLevel7());
				else if(Constants.levelSelected ==8)
					GlobalVars.ge.loadStage(new BounceLevel8());
				else if(Constants.levelSelected ==9)
					GlobalVars.ge.loadStage(new BounceLevel9());
				else if(Constants.levelSelected ==10)
					GlobalVars.ge.loadStage(new BounceLevel10());
				else if(Constants.levelSelected ==11)
					GlobalVars.ge.loadStage(new BounceLevel11());
				else if(Constants.levelSelected ==12)
					GlobalVars.ge.loadStage(new BounceLevel12());
				
			}
		});
		addElement(s[slideNo]);
		subscribeToControllingInteraction(s[slideNo], InteractionTouch.class);


	}

	@Override
	public void tick(long stepTime) {
	}
}
