package com.rhymes.game.stage.menus;

import java.awt.Font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonPearl;
import com.rhymes.game.entity.elements.menu.ButtonStar;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.NumericText;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverScreen extends StageBase {

	@Override
	public void resume() {
		super.resume();
		bounceTest.fontLoaded = false;
		bounceTest.loadFonts();
	}	
	
	private int level_id;
	private int pack_id;
	float x, y;
	int i, j;
	private BounceTest bounceTest;
	private Result result;
	
	public GameOverScreen(int level_id, Result res){
		this.level_id = level_id;
		this.result = res;
	}
	
	public GameOverScreen(BounceTest bounceTest) {
		this.bounceTest = bounceTest;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_GREATSHOT);
		assetPack.addTexture(AssetConstants.IMG_STARS);
		assetPack.addTexture(AssetConstants.IMG_EMPTY_STAR);
		assetPack.addTexture(AssetConstants.IMG_BACK_TO);
		assetPack.addTexture(AssetConstants.IMG_RELOAD_LEVEL);
		assetPack.addTexture(AssetConstants.IMG_NEXT);
		assetPack.addTexture(AssetConstants.IMG_EXIT_GAME);
//		assetPack.addTexture(AssetConstants.SCORE_FONT_00);
//		assetPack.addTexture(AssetConstants.SCORE_FONT_BY);
//		assetPack.addTexture(AssetConstants.SCORE_FONT_EQUAL);
//		assetPack.addTexture(AssetConstants.SCORE_FONT_PERCENT);
		return assetPack;
	}
	
	String star_imagepath;
	private NumericText scoreTotalPoint;
	private SpriteBatch batch;
	private Text totalPointText;
	
	@Override
	public void loadElements() {
		Helper.println("Now i'm in GameSuccessScreenF rom here this shouold go to the next level");
		
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());
//		InteractionTouch bLevelFinish = new InteractionTouch();
		ButtonQuit greatShot = new ButtonQuit(0, 20*GameMenuInfo.ratio_h, 480*GameMenuInfo.ratio_w, 320*GameMenuInfo.ratio_h, 1,
				AssetConstants.IMG_GREATSHOT,bounceTest.result);
		this.addElement(greatShot);
		subscribeToControllingInteraction(greatShot, InteractionTouch.class);
//		bLevelFinish.subscribe(greatShot);
//		bLevelFinish.subscribe(greatShot);
		this.showLevelScore(250*GameMenuInfo.ratio_w, 180*GameMenuInfo.ratio_h,bounceTest.fontHitScore);
		this.showTargetScore(315*GameMenuInfo.ratio_w, 90*GameMenuInfo.ratio_h,bounceTest.fontHitScore);
//		this.showHighScore(250*GameMenuInfo.ratio_w, 210*GameMenuInfo.ratio_h,bounceTest.fontHitScore);
		

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
	totalPointText.getFont().setColor(bounceTest.scoreColorR,bounceTest.scoreColorG,bounceTest.scoreColorB,.8f);
	
	
}



	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}