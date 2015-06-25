package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.scorelbl;
import com.rhymes.game.entity.elements.testtileMenu.ButtonExitLevel;
import com.rhymes.game.entity.elements.testtileMenu.NextLevel;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.NumericText;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ScoreScreen extends StageBase {

	public float x = 0f;
	public float y = 0f;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_SCORE);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_NEXTLEVEL);
		assetPack.addTexture(AssetConstants.IMG_SCORE);
		assetPack.addTexture(AssetConstants.IMG_TIME_BONUS);
		assetPack.addTexture(AssetConstants.IMG_TOTAL_SCORE);
		assetPack.addTexture(AssetConstants.IMG_CONGRATULATIONS);
		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(GlobalVars.ge.getScreen().cam.position.x-240f*LevelInfo.ratioX,GlobalVars.ge.getScreen().cam.position.y-160f*LevelInfo.ratioY, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BKG_SCORE));
		loadFonts();
		if(LevelInfo.LEVEL_OVER==LevelInfo.LEVEL_DESTROYED)
			LevelInfo.timeBonus=0;
		LevelInfo.totalScore=LevelInfo.score+LevelInfo.timeBonus;
		long total=LevelInfo.totalScore;
		if(LevelInfo.GAME_MODE==1 || LevelInfo.GAME_COMPLETE==true || LevelInfo.LEVEL_OVER==LevelInfo.LEVEL_DESTROYED){
			LevelInfo.totalScore=0;
		}
		Text img;
		if(LevelInfo.GAME_COMPLETE)
		{
			x = GlobalVars.ge.getScreen().cam.position.x-215f*LevelInfo.ratioX;
			y = GlobalVars.ge.getScreen().cam.position.y+120f*LevelInfo.ratioY;
			img=new Text(x, y, 0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioX, font,"congrtulations! you have mowed all the lawns.");
			this.elements.add(img);
			img=new Text(x+75f*LevelInfo.ratioX, y-30f*LevelInfo.ratioY, 0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, font,"you can make a better score !!");
			this.elements.add(img);
			img=new Text(x+235f*LevelInfo.ratioX, y-60f*LevelInfo.ratioY, 0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, font,"just play again.....");
			this.elements.add(img);
		}
		x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+20f*LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y+50f*LevelInfo.ratioY;
		img=new Text(x, y,0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioX, font, "SCORE        "+LevelInfo.score);
		this.elements.add(img);
		
		x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+20f*LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y;
	    img=new Text(x, y,0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioX, font,"time bonus   "+LevelInfo.timeBonus);
		this.elements.add(img);
		
		x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+20f*LevelInfo.ratioX;
		y = GlobalVars.ge.getScreen().cam.position.y-50f*LevelInfo.ratioY;
		img=new Text(x, y,0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioX, font,"total score  "+total);
		this.elements.add(img);
		
		x = GlobalVars.ge.getScreen().cam.position.x;
		y = GlobalVars.ge.getScreen().cam.position.y-20f*LevelInfo.ratioY;
		ButtonRestart reload = new ButtonRestart(x , y, 200f*LevelInfo.ratioX, 60f*LevelInfo.ratioY,
				1,AssetConstants.IMG_BTN_RESTART);
		this.elements.add(reload);
		subscribeToControllingInteraction(reload, InteractionTouch.class);
		if(!LevelInfo.GAME_COMPLETE)
		{
			//x = GlobalVars.ge.getScreen().cam.position.x;
			if(LevelInfo.LEVEL_OVER==LevelInfo.LEVEL_COMPLETE){
			y = GlobalVars.ge.getScreen().cam.position.y+30f*LevelInfo.ratioY;
			NextLevel nextLevel = new NextLevel(x , y, 200f*LevelInfo.ratioX, 60f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_NEXTLEVEL);
			this.elements.add(nextLevel);
			subscribeToControllingInteraction(nextLevel, InteractionTouch.class);
						}
		}
		y = GlobalVars.ge.getScreen().cam.position.y-70f*LevelInfo.ratioY;
		ButtonExitLevel exit = new ButtonExitLevel(x , y, 200f*LevelInfo.ratioX, 60f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_QUIT);
		this.elements.add(exit);
		subscribeToControllingInteraction(exit, InteractionTouch.class);
		LevelInfo.GAME_COMPLETE=false;

	}
	 BitmapFont font;
 	 private void loadFonts() {
 	  font = TrueTypeFontFactory.createBitmapFont  
 	  (Gdx.files.internal(AssetConstants.FONT), Text.getFrontChars(),
 	    12.0f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
 	  font.setColor(1f, 1f, 1f, 1f);
 	 }	

	@Override
	public void tick(long stepTime) {
		// TODO Auto-generated method stub

	}

}

