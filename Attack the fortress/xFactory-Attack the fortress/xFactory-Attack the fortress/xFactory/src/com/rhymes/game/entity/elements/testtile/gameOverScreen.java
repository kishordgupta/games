package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.menus.ScoreScreen;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class gameOverScreen extends Button {
	
	public float rowPos = 0;
	public float colPos = 0;
	public Texture tex;
	

	

		public gameOverScreen(float x, float y, float width, float height,	int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
		public gameOverScreen(float x, float y, float width, float height, int zIndex) {
			super(x, y, width, height, zIndex);
		}

	@Override
	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
//		Helper.println("On touch: " + p);
//		Helper.println("Gameover screen: " + this.getLocation());
//		Helper.println("Camera: x: " + GlobalVars.ge.getScreen().cam.position.x);
//		Helper.println("Camera: y: " + GlobalVars.ge.getScreen().cam.position.y);
		if(this.checkHit(p)){
			Helper.println("back to mode menu game...");
			LevelInfo.LEVEL_OVER=LevelInfo.LEVEL_DESTROYED;
			GlobalVars.ge.loadStage(new ScoreScreen());			
		
		}
	}

	

//	@Override
//	public void init() {
//		tex = Helper.getImageFromAssets(AssetConstants.IMG_GAMEOVER).getTexture();
//	}

}
