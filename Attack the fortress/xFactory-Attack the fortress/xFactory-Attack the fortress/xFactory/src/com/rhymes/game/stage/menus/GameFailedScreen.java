package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonLevelPack;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameFailedScreen extends StageBase {

	private int level_id;
	private int pack_id;
	
	
	float x, y;
	
	public GameFailedScreen(int level_id){
		this.level_id = level_id;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_GAMEOVER_SCREEN);
		assetPack.addTexture(AssetConstants.IMG_EMPTY_STAR);
		assetPack.addTexture(AssetConstants.IMG_BACK_TO);
		assetPack.addTexture(AssetConstants.IMG_RELOAD_LEVEL);
		assetPack.addTexture(AssetConstants.IMG_EXIT_GAME);

		return assetPack;
	}

	
	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_GAME_FAILED_SCREEN));

		
		////go to pack menu
		x = 23;
		y = 375;
		
		ButtonLevelPack level_pack = new ButtonLevelPack(x*GameMenuInfo.ratio_wl, y* GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_BACK_TO).getRegionWidth()/2*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_BACK_TO).getRegionHeight()/2*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_BACK_TO, select_pack(level_id));
		this.elements.add(level_pack);
		subscribeToControllingInteraction(level_pack,
					InteractionTouch.class);

		
		////reload
		x = 23;
		y = 236;
		ButtonRestart reload = new ButtonRestart(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_RELOAD_LEVEL).getRegionWidth()/2*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_RELOAD_LEVEL).getRegionHeight()/2*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_RELOAD_LEVEL);
		this.elements.add(reload);
		subscribeToControllingInteraction(reload,
					InteractionTouch.class);
	}
	
	
	public int select_pack(int level_id){
		if(level_id < GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 0;
		else if(level_id >= GameMenuInfo.num_of_level_in_a_pack && level_id < 2*GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 1;
		else if(level_id >= 2*GameMenuInfo.num_of_level_in_a_pack && level_id < 3*GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 2;
		
		return pack_id;
	}
	

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}