package com.rhymes.ge.core.controller;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.stage.loadingscreen.LoadingScreen;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Renderer;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.core.view.Screen;
import com.rhymes.ge.pw.assets.AssetHandler;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

/**
 * Use this class to get reference to current stage
 * and all the features of the game environment.
 */
public class GEController {	
	private AssetHandler assetHandler;
	private Renderer renderer;
	private Screen screen;
	
	private StageBase currentStage;
	private StageBase newStage;
	private boolean loading = false;
	public long stepTime = 0;
	
	public GEController()
	{
		this.assetHandler = new AssetHandler();
		this.screen = new Screen();
		this.renderer = new Renderer(this.screen);
		init();

	}
	
	/**
	 * Initialize all the components of the game environment.
	 * Loads the default stage.
	 */
	public void init()
	{
		GlobalVars.ge = this;
		if(StartupInfo.getStartupStage() == null)
			Helper.println("Startup stage is null. Please set it at the getStartupStage() method in StartupInfo.java");
		loadStage(StartupInfo.getStartupStage());		
	}
	
	public void loadStage(StageBase stage)
	{		
		if(currentStage != null)
		{
			if(currentStage.equals(stage))
				return;
			currentStage.finish();
//			this.assetHandler.clearAssets();
			Helper.disposeTextures();			
		}
		
		GlobalVars.ge.getScreen().reset();
		loading = true;
//		currentStage = stage;		
		currentStage = new LoadingScreen(stage);
		try {
			assetHandler.loadAssetsBlocking(currentStage.getAssets(new AssetPack()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		currentStage.setGameState(GameState.LOADING);
		currentStage.loadElements();
		assetHandler.loadAssetsBlocking(currentStage.getElementsAssets(new AssetPack()));
		currentStage.init();
	}
	

	public void loadStageWithoutLoadingScreen(StageBase stage)
	{		
		if(currentStage != null)
		{
			if(currentStage.equals(stage))
				return;
//			this.assetHandler.clearAssets();
			Helper.disposeTextures();
		}
		
		GlobalVars.ge.getScreen().reset();
		loading = true;
		currentStage = stage;		
		try {
			assetHandler.loadAssetsBlocking(currentStage.getAssets(new AssetPack()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		currentStage.setGameState(GameState.LOADING);
		currentStage.loadElements();
		assetHandler.loadAssetsBlocking(currentStage.getElementsAssets(new AssetPack()));
		currentStage.init();
	}

	
	
	public AssetHandler getAssetHandler() {
		return assetHandler; 
	}

	public void setAssetHandler(AssetHandler assetHandler) {
		this.assetHandler = assetHandler;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public StageBase getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(StageBase currentStage) {
		this.currentStage = currentStage;
	}


	public void pause()
	{
		if(currentStage != null)
			this.currentStage.pause();
	}
	
	public void resume()
	{
		if(currentStage != null)
			this.currentStage.resume();
	}
	
	public void finish()
	{
		if(currentStage != null)
			this.currentStage.finish();
		if(StartupInfo.sound_handler != null)
			StartupInfo.sound_handler.disposeSound();
		Helper.disposeTextures();
	}
	
//	long time = 0;	
	/**
	 * Steps to the next state(Frame) of the game environment
	 * @param time 
	 */
	public void step(long stepTime)
	{	
//		currentStage = newStage;
		Helper.printTest("\n\nGE Step Time: " + stepTime);
//		time = System.currentTimeMillis();
//		Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
		GlobalVars.ge.getRenderer().startRendering();
		this.stepTime = stepTime;
		this.screen.update();
		if(currentStage != null)
			currentStage.step(stepTime);
		GlobalVars.ge.getRenderer().stopRendering();
	}
	
	
}
