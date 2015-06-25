package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonChoosePlayerandBall;
import com.rhymes.game.entity.elements.menu.ButtonMusicControl;
import com.rhymes.game.entity.elements.menu.ButtonOptionGameMenu;
import com.rhymes.game.entity.elements.menu.ButtonSelectBall;
import com.rhymes.game.entity.elements.menu.ButtonSelectLocation;
import com.rhymes.game.entity.elements.menu.ButtonSelectPlayer;
import com.rhymes.game.entity.elements.menu.ButtonSoundControl;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ChooseLocation extends StageBase{
	public String[] imgLocation = new String[4];
	
	float x;
	float y;
	public ButtonSelectLocation selectedLocation = null;
//	public ButtonSelectPlayer selectedPlayer = null;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_CL_BKG);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		assetPack.addTexture(AssetConstants.IMG_LOC_PLAYGROUND);
		assetPack.addTexture(AssetConstants.IMG_LOC_BACKYARD);
		assetPack.addTexture(AssetConstants.IMG_LOC_CAMP);
		assetPack.addTexture(AssetConstants.IMG_LOC_RANCH);
		assetPack.addTexture(AssetConstants.IMG_LOC_CAMP_LOCKED);
		assetPack.addTexture(AssetConstants.IMG_LOC_RANCH_LOCKED);
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
		imgLocation[0] = AssetConstants.IMG_LOC_PLAYGROUND;
		imgLocation[1] = AssetConstants.IMG_LOC_BACKYARD;
		imgLocation[2] = AssetConstants.IMG_LOC_CAMP;
		imgLocation[3] = AssetConstants.IMG_LOC_RANCH;

		Helper.println("Now i'm in Choose Location class");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_CL_BKG));
		x = 10;
		//back
		y = 290;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y, 30, 30,1, AssetConstants.IMG_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		
		ButtonSelectLocation buttonSelectLocation[] = new ButtonSelectLocation[4];
//		ButtonSelectBall buttonSelectBall[] = new ButtonSelectBall[3];
		x = 50;
		y = 20;
		for (int i = 0; i < 4; i++) {
//			buttonSelectPlayer[i] = new ButtonSelectPlayer(x, y, 50, 50,
//					1, imgPlayer[i]);
			buttonSelectLocation[i] = new ButtonSelectLocation(x, y, 120,120 ,
					1, imgLocation[i],i+1);
			
			this.elements.add(buttonSelectLocation[i]);
			subscribeToControllingInteraction(buttonSelectLocation[i],
					InteractionTouch.class);
			x = x + 110;
			if (x > 320) {
				x = 50;
				y = y + 90;
			}
			if (y > 165) {
				x = 160;
				y = 0;
			}
		}
	
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

	public void selectLocation(ButtonSelectLocation buttonSelectedLocation) {
		
		if(this.selectedLocation != null && !(selectedLocation == buttonSelectedLocation))
			{selectedLocation.clearSelection();}
		this.selectedLocation = buttonSelectedLocation;
		
		Constants.locationSelected = selectedLocation.getBcl();
		Helper.println("selectedLocation"+Constants.locationSelected);
		
	}
}
