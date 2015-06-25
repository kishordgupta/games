package com.rhymes.ge.pw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rhymes.ge.core.controller.GEController;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;


public class GameApplication implements ApplicationListener  {
	public AssetManager assetManager;
	private AssetPack assetPackage;

	private GEController gameEnvironment;
	
	@Override
	public void create() {
		Gdx.graphics.setTitle("Rhymes Network: Solitaire clone!");
//		Gdx.graphics.setVSync(false);
		gameEnvironment = new GEController();
		init();
	}
	
		
	private void init() {
		gameEnvironment.init();
	}


	@Override
	public void dispose() {
		gameEnvironment.finish();
		Helper.println("Game quitting successfully...Thanks for playing this game!");
		System.exit(0);
	}
	
	
	@Override
	public void pause() {
		gameEnvironment.pause();
	}
	
	long time = -1;
	float fps;
	@Override
	public void render() {
//		if(time < 0)
//			time = System.currentTimeMillis();
//		
//		if(Gdx.graphics.getFramesPerSecond() <= 10)
//			return;
//		if(StartupInfo.debugLevel > 0)
//		Helper.println("App Step Time: " + (System.currentTimeMillis() - time));	
//		Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
//		gameEnvironment.step(System.currentTimeMillis() - time);
//		fps = Gdx.graphics.getFramesPerSecond()==0? 1:Gdx.graphics.getFramesPerSecond();
//		fps = 1/fps * 1000;
//		gameEnvironment.step( (long)fps );
		gameEnvironment.step( (long) (Gdx.graphics.getDeltaTime()*1000) );
//		time = System.currentTimeMillis();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void resume() {
		gameEnvironment.resume();
	}
}
