package com.rhymes.game.entity.elements.solitaire.table;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.menu.GameOverScreenSolitaire;
import com.rhymes.game.entity.elements.solitaire.menu.NewGameGameOver;
import com.rhymes.game.entity.elements.solitaire.menu.RestartGameOver;
import com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.button.Quit;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOver extends StageBase{
	
	GameOverScreenSolitaire game_over_screen;
	RestartGameOver restart;
	NewGameGameOver new_game;
	Quit quit;
	
	public InteractionTouch interact;
	
	float x, y;
	
	CardSet cardset;
	
	public GameOver(CardSet card_set){
		this.cardset = card_set;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		
		assetPack.addTexture(AssetConstants.GAME_OVER);
		assetPack.addTexture(AssetConstants.NEW_GAME);
		assetPack.addTexture(AssetConstants.RESTART);
		assetPack.addTexture(AssetConstants.QUIT_BUTT);
		return assetPack;
	}

	@Override
	public void loadElements() {
		
		interact = new InteractionTouch();
		
		x = 0;
		y = 0;
		
		game_over_screen = new GameOverScreenSolitaire(x, y, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 
				1, AssetConstants.GAME_OVER);
		this.topElements.add(game_over_screen);


		x = Gdx.graphics.getWidth()/2f - (Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth()/2f)
		* ConfigConstants.ratio_w;
		y = Gdx.graphics.getHeight()/2f;
		restart = new RestartGameOver(x, y, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.RESTART);
		this.topElements.add(restart);
		this.interact.subscribe(restart);
		
		y = y - (100 * ConfigConstants.ratio_h);
		new_game = new NewGameGameOver(x, y, 
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.NEW_GAME);
		this.topElements.add(new_game);
		this.interact.subscribe(new_game);
		
		y = y - (100 * ConfigConstants.ratio_h);
		quit = new Quit(x, y, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.QUIT_BUTT);
		this.topElements.add(quit);
		this.interact.subscribe(quit);
	}

	@Override
	public void tick(long stepTime) {
		
	}

}
