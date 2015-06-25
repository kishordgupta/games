package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonLevelPack;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.entity.elements.unsorted.NumericText;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameFailedScreen extends StageBase {

	private int level_id;
	float x, y;
	private BounceTest bounceTest;
	private Text totalPointText;
	
	public GameFailedScreen(int level_id){
		this.level_id = level_id;
	}
	public GameFailedScreen(BounceTest bounceTest) {
		// TODO Auto-generated constructor stub
		this.bounceTest = bounceTest;
	}
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_GAMEOVER_SCREEN);
		assetPack.addTexture(AssetConstants.IMG_EMPTY_STAR);
		assetPack.addTexture(AssetConstants.IMG_BACK_TO);
		assetPack.addTexture(AssetConstants.IMG_RELOAD_LEVEL);
		assetPack.addTexture(AssetConstants.IMG_EXIT_GAME);
		assetPack.addTexture(AssetConstants.IMG_TRYAGAIN);
		assetPack.addTexture(AssetConstants.FONT_NEON);
		return assetPack;
	}
//	private BitmapFont fontImagica,fontSuperstar,fontPlok,fontNeon;
//	
//	private BitmapFont createFont(String fontPath)
//	{
//		return TrueTypeFontFactory.createBitmapFont		
//		(Gdx.files.internal(fontPath), Text.getFrontChars(),
//				12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//	}
//	private void loadFonts() {
//		fontImagica = createFont(AssetConstants.FONT_IMAGICA);
//		fontSuperstar = createFont(AssetConstants.FONT_SUPER_STAR);
//		fontPlok = createFont(AssetConstants.FONT_PLOK);
//		fontNeon = createFont(AssetConstants.FONT_NEON);
//	}

	@Override
	public void loadElements() {
		bounceTest.loadFonts();
		Helper.println("Now i'm in GameFailedScreen");
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());
		InteractionTouch bLevelFinish = new InteractionTouch();
		ButtonRestart tryAgain = new ButtonRestart(0, 20*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_TRYAGAIN,bounceTest.result);
		addElement(tryAgain);
		subscribeToControllingInteraction(tryAgain, InteractionTouch.class);
		bLevelFinish.subscribe(tryAgain);
		this.showLevelScore(250*GameMenuInfo.ratio_w, 195*GameMenuInfo.ratio_h,bounceTest.fontHitScore);
		this.showTargetScore(265*GameMenuInfo.ratio_w, 105*GameMenuInfo.ratio_h,bounceTest.fontHitScore);
	}
	
	public void showLevelScore(float x, float y,BitmapFont font) {
		// TODO Auto-generated method stub
		 totalPointText = new Text(x, y,.4f*GameMenuInfo.ratio_w, .4f*GameMenuInfo.ratio_h, font,"0",false);
		this.topElements.add(totalPointText);
		totalPointText.setText(""+((ResultBounce) bounceTest.result)
				.getLevelScore());
		totalPointText.getFont().setColor(bounceTest.scoreColorR,bounceTest.scoreColorG,bounceTest.scoreColorB,.8f);
		
		

	}
	public void showHighScore(float x, float y,BitmapFont font) {
		// TODO Auto-generated method stub
		 totalPointText = new Text(x, y, .4f*GameMenuInfo.ratio_w, .4f*GameMenuInfo.ratio_h,font,"0",false);
		this.topElements.add(totalPointText);
		totalPointText.setText(""+ (((ResultBounce) bounceTest.result)
				.getTargetScore() + 5000));
		totalPointText.getFont().setColor(bounceTest.scoreColorR,bounceTest.scoreColorG,bounceTest.scoreColorB,.8f);
		
	}

	public void showTargetScore(float x, float y,BitmapFont font) {
		// TODO Auto-generated method stub
		 totalPointText = new Text(x, y,.4f*GameMenuInfo.ratio_w,.4f*GameMenuInfo.ratio_h,font,"0",false);
		this.topElements.add(totalPointText);
		totalPointText.setText(""+ ((ResultBounce) bounceTest.result)
				.getTargetScore());
		totalPointText.getFont().setColor(bounceTest.scoreColorR,bounceTest.scoreColorG,bounceTest.scoreColorB,0.8f);
		
		
	}

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
	
	
	@Override
	public void resume() {
		super.resume();
		bounceTest.fontLoaded = false;
		bounceTest.loadFonts();
	}
}