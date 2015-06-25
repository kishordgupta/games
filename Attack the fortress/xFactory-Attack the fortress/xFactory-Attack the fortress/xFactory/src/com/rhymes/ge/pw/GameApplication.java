package com.rhymes.ge.pw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.ge.core.controller.GEController;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;


public class GameApplication implements ApplicationListener  {
	public AssetManager assetManager;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private AssetPack assetPackage;

	private GEController gameEnvironment;
	
	@Override
	public void create() {
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
		if(StartupInfo.sound_handler != null)
			StartupInfo.sound_handler.pause();
	}
	
	
	long time = -1;
	float fps;
	long step = 0;
	@Override
	public void render() {
		try {
			gameEnvironment.step((long) (Gdx.graphics.getDeltaTime()*1000));
		} catch (Exception e) {
		}
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void resume() {
		gameEnvironment.resume();
		if(StartupInfo.sound_handler != null)
			StartupInfo.sound_handler.resume(); 
	}
}
