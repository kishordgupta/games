package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Background;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class Splash extends StageBase implements InputProcessor{
	@Override
	public void loadElements() {
		addElement(new Background(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1,AssetConstants.IMG_SPLASH));
		Gdx.input.setInputProcessor(this);
		GlobalVars.ge.getScreen().setCamController(true);
		
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_SPLASH);
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		GlobalVars.ge.loadStage(new MenuActionDriver());
		Gdx.input.setInputProcessor(null);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
