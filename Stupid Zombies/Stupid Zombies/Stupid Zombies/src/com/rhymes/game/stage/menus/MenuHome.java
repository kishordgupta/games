package com.rhymes.game.stage.menus;

import java.lang.reflect.Method;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonBackToMainmenu;
import com.rhymes.game.entity.elements.menu.ButtonStartGame;
import com.rhymes.game.entity.elements.ui.ButtonPause;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.entity.elements.unsorted.NumericText;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.IClick;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class MenuHome extends StageBase {
	ISingleCollision collider;
//	PlanePathSet pathSet;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_MAIN);
		assetPack.addTexture(AssetConstants.IMG_PLAY);
		assetPack.addTexture(AssetConstants.IMG_BACK);
		return assetPack;
	}

	@Override
	public void loadElements() {
		Helper.println("Now i'm in menuHome");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_MAIN));

//		Text textTitle = new Text(20, Gdx.graphics.getHeight() - 100, 0, 0, 1);
//		textTitle.setText("Sail The Ship!");
//		this.elements.add(textTitle);

		ButtonStartGame buttonStartGame;
	float	x = 200;
	float	y = 80;
		
			buttonStartGame = new ButtonStartGame(x,y, 147, 67, 1, AssetConstants.IMG_PLAY);
			this.elements.add(buttonStartGame);
			subscribeToControllingInteraction(buttonStartGame,
					InteractionTouch.class);
			//back
			x = 200;
			y = 160;
			
			ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y,150,70,1, AssetConstants.IMG_BACK);
			this.elements.add(buttonBack);
			subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
		
		
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}