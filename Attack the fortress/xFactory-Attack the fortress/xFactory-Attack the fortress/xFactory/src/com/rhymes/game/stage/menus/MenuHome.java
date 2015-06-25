package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonStartGame;
import com.rhymes.game.entity.elements.piku.PlanePathSet;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class MenuHome extends StageBase {
	ISingleCollision collider;
	PlanePathSet pathSet;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BACK_2);
		assetPack.addTexture(AssetConstants.IMG_PLAY);
		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_2));

//		Text textTitle = new Text(20, Gdx.graphics.getHeight() - 100, 0, 0, 1, AssetConstants.FONT_1);
//		textTitle.setText("Sail The Ship!");
//		this.elements.add(textTitle);

		ButtonStartGame buttonStartGame;
		int x = 0;
		int y = 0;
		for(int i = 1; i <= 20; i++){
			if(i%5 == 0){
				y = y + 50;
			}
			buttonStartGame = new ButtonStartGame(40 * (i%5-1)+100, i*15, 40, 40, 1, AssetConstants.IMG_PLAY);
			this.elements.add(buttonStartGame);
			subscribeToControllingInteraction(buttonStartGame,
					InteractionTouch.class);
			buttonStartGame.setLevelNumber(i);	
		}
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}