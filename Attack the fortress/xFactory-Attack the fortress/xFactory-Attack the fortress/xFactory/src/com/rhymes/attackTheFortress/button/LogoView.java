package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.attackTheFortress.xmlEnemyReader;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class LogoView extends StageBase {

	float x,y;
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_LOGO_VIEW);



		return assetPack;

	}

	@Override
	public void loadElements() {
//		xmlEnemyReader xy=new xmlEnemyReader();
//		xy.readXml();
		
		
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());
//		GlobalVars.ge.getScreen().cam.position.set( Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f,0f);
		x=0f;
		y=0f;
		ButtonLogoView buttonstartgame = new ButtonLogoView(x, y,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), 1, AssetConstants.IMG_LOGO_VIEW);
		this.elements.add(buttonstartgame);
		subscribeToControllingInteraction(buttonstartgame, InteractionTouch.class);
		
	}

	private long skipTime=2000;
	private long elaspedTime=0;
	@Override
	public void tick(long stepTime) {
		elaspedTime+=stepTime;
		if(elaspedTime>skipTime){
			elaspedTime=0;
			GlobalVars.ge.loadStage(new MainView());
		}
		

	}

}
