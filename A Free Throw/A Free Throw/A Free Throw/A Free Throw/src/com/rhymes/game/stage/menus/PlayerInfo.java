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
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class PlayerInfo extends StageBase{
//	public String[] imgPlayer = new String[6];
	public String[] imgShot = new String[13];
	public ArrayList<ElementBase> views = new ArrayList<ElementBase>(); 
	
	float x;
	float y;
	float w;
	float h;
	float nx =0, ny=0;
	public SlideSelector[] s= new SlideSelector[1];
	private boolean isPlrInfo=false;
//	public SlideSelector s2;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_MAIN);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		//players
		assetPack.addTexture(AssetConstants.IMG_PAUL);
		assetPack.addTexture(AssetConstants.IMG_JAMES);
		assetPack.addTexture(AssetConstants.IMG_JOHN);
		assetPack.addTexture(AssetConstants.IMG_CHARLES);
		assetPack.addTexture(AssetConstants.IMG_AMY);
		assetPack.addTexture(AssetConstants.IMG_POLAR);
		//playerinfos
		assetPack.addTexture(AssetConstants.IMG_INFO_PAUL);
		assetPack.addTexture(AssetConstants.IMG_INFO_JAMES);
		assetPack.addTexture(AssetConstants.IMG_INFO_JOHN);
		assetPack.addTexture(AssetConstants.IMG_INFO_AMY);
		assetPack.addTexture(AssetConstants.IMG_INFO_POLAR);

		return assetPack;
	}


	@Override
	public void loadElements() {		
		Helper.println("Now i'm in the player info class");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		
		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_MAIN));
		addSelector(0.6f,0.1f,0);
//	//back
		y = 280;
		x =20;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 60, 60,2, AssetConstants.IMG_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		
	}
	
	



	public void addSelector(float offsetX,float OffsetY,int sNo)
	{
		String[] imgPlr = new String[6];
		imgPlr[0] = AssetConstants.IMG_PAUL;
		imgPlr[1] = AssetConstants.IMG_JAMES;
		imgPlr[2] = AssetConstants.IMG_JOHN;
		imgPlr[3] = AssetConstants.IMG_CHARLES;
		imgPlr[4] = AssetConstants.IMG_AMY;
		imgPlr[5] = AssetConstants.IMG_POLAR;

		final int slideNo = sNo;
		
		ArrayList<ElementBase> elements = new ArrayList<ElementBase>();

		for(int i = 0; i < 6; i++){
			elements.add(new ButtonSelectShot(140,260, 40, 40, 3, imgPlr[i]));
		}
		
		s[slideNo] = new SlideSelector(100 * offsetX , 100*OffsetY, 480, 320, elements, 1, new ISliderEventHandler() {
			
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
//				Constants.levelSelected = (s[slideNo].getFocusIndex()+1);
//				addClick(s[slideNo].getX(),0,0);
				Helper.println("SelectedPlayer " + (s[slideNo].getFocusIndex()+1));
				if(!isPlrInfo)
				showPlayerInfo((s[slideNo].getFocusIndex()));
				else
					{
					s[slideNo].init();
					isPlrInfo = false;
					}
				
				
				
			}

		});
		addElement(s[slideNo]);
		subscribeToControllingInteraction(s[slideNo], InteractionTouch.class);


	}

	private void showPlayerInfo(int i) {
		// TODO Auto-generated method stub
		String[] imgPlrInfo = new String[6];
		imgPlrInfo[0] = AssetConstants.IMG_INFO_PAUL;
		imgPlrInfo[1] = AssetConstants.IMG_INFO_JAMES;
		imgPlrInfo[2] = AssetConstants.IMG_INFO_JOHN;
		imgPlrInfo[3] = AssetConstants.IMG_INFO_JOHN;
		imgPlrInfo[4] = AssetConstants.IMG_INFO_AMY;
		imgPlrInfo[5] = AssetConstants.IMG_INFO_POLAR;
			Helper.println("element added");
			((ButtonSelectShot)s[0].getSelected()).setImage(Helper.getImageFromAssets(imgPlrInfo[i]));
			((ButtonSelectShot)s[0].getSelected()).setX(((ButtonSelectShot)s[0].getSelected()).getX()-130);
			((ButtonSelectShot)s[0].getSelected()).setY(((ButtonSelectShot)s[0].getSelected()).getY()-10);
			((ButtonSelectShot)s[0].getSelected()).setHeight(340);
			((ButtonSelectShot)s[0].getSelected()).setWidth(500);
			
			isPlrInfo=true;
//			y = 280;
//			x = 20;
//			ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 400, 300,1, AssetConstants.IMG_BACK);
//			this.elements.add(buttonBack);
	}

	@Override
	public void tick(long stepTime) {
	}
}
