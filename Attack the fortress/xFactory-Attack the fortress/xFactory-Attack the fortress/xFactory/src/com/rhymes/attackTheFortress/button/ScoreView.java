package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
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

public class ScoreView extends StageBase {
	
	private int life=0;
	private int gold=0;
	private int kill=0;
	private int score=0;
	private int mode=0;

	public float x = 0f;
	public float y = 0f;

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_SCORE_VIEW);
		assetPack.addTexture(AssetConstants.IMG_SCORE_MESSAGE_VIC);
		assetPack.addTexture(AssetConstants.IMG_SCORE_MESSAGE_FAIL);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUIT_TO_MAP);
		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0,0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_SCORE_VIEW));

		loadFonts();
		getResult();
		String imagePath="";
		if(LevelInfo.GAME_VICTORY==true){
		imagePath=AssetConstants.IMG_SCORE_MESSAGE_VIC;
		}
		else{
			imagePath=AssetConstants.IMG_SCORE_MESSAGE_FAIL;
		}
		ScoreMessage scoreMessage=new ScoreMessage(35f*LevelInfo.ratioX, 90f*LevelInfo.ratioY, 160f*LevelInfo.ratioX,
				125f*LevelInfo.ratioY, 1,imagePath);
		this.addElement(scoreMessage);
		
		Text message=new Text(240f*LevelInfo.ratioX,200f*LevelInfo.ratioY,
				0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, this.font,
				"LIFE   "+this.life+"*"+500+"  ="+this.life*500);
		this.addElement(message);
		
		message=new Text(240f*LevelInfo.ratioX,170f*LevelInfo.ratioY,
				0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, this.font,
				"KILL   "+this.kill+"*"+300+"  ="+this.kill*300);
		this.addElement(message);

		message=new Text(240f*LevelInfo.ratioX,140f*LevelInfo.ratioY,
				0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, this.font,
				"GOLD  "+this.gold+"*"+100+"  ="+this.gold*100);
		this.addElement(message);
		
		message=new Text(240f*LevelInfo.ratioX,90f*LevelInfo.ratioY,
				0.8f*LevelInfo.ratioX, 0.8f*LevelInfo.ratioY, this.font,
				"TOTAL SCORE  ="+this.score);
		this.addElement(message);


		ButtonQuitToMap quit=new ButtonQuitToMap(Gdx.graphics.getWidth()/2f-50f*LevelInfo.ratioX,
				30f*LevelInfo.ratioY, 100f*LevelInfo.ratioX, 30f*LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_QUIT_TO_MAP);
		this.addElement(quit);
		this.subscribeToControllingInteraction(quit, InteractionTouch.class);
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
public void getResult(){
	if(LevelInfo.ROUND_NUMBER==1)
	{
		this.gold=LevelInfo.ROUND_RESULT_1.getGold();
		this.kill=LevelInfo.ROUND_RESULT_1.getKill();
		this.life=LevelInfo.ROUND_RESULT_1.getLife();
		this.mode=LevelInfo.ROUND_RESULT_1.getMode();
		this.score=LevelInfo.ROUND_RESULT_1.getScore();		
	}
	else if(LevelInfo.ROUND_NUMBER==2)
	{
		this.gold=LevelInfo.ROUND_RESULT_2.getGold();
		this.kill=LevelInfo.ROUND_RESULT_2.getKill();
		this.life=LevelInfo.ROUND_RESULT_2.getLife();
		this.mode=LevelInfo.ROUND_RESULT_2.getMode();
		this.score=LevelInfo.ROUND_RESULT_2.getScore();		
	}
	else if(LevelInfo.ROUND_NUMBER==3)
	{
		this.gold=LevelInfo.ROUND_RESULT_3.getGold();
		this.kill=LevelInfo.ROUND_RESULT_3.getKill();
		this.life=LevelInfo.ROUND_RESULT_3.getLife();
		this.mode=LevelInfo.ROUND_RESULT_3.getMode();
		this.score=LevelInfo.ROUND_RESULT_3.getScore();		
	}
	else if(LevelInfo.ROUND_NUMBER==4)
	{
		this.gold=LevelInfo.ROUND_RESULT_4.getGold();
		this.kill=LevelInfo.ROUND_RESULT_4.getKill();
		this.life=LevelInfo.ROUND_RESULT_4.getLife();
		this.mode=LevelInfo.ROUND_RESULT_4.getMode();
		this.score=LevelInfo.ROUND_RESULT_4.getScore();		
	}
	else if(LevelInfo.ROUND_NUMBER==5)
	{
		this.gold=LevelInfo.ROUND_RESULT_5.getGold();
		this.kill=LevelInfo.ROUND_RESULT_5.getKill();
		this.life=LevelInfo.ROUND_RESULT_5.getLife();
		this.mode=LevelInfo.ROUND_RESULT_5.getMode();
		this.score=LevelInfo.ROUND_RESULT_5.getScore();		
	}
	else if(LevelInfo.ROUND_NUMBER==6)
	{
		this.gold=LevelInfo.ROUND_RESULT_6.getGold();
		this.kill=LevelInfo.ROUND_RESULT_6.getKill();
		this.life=LevelInfo.ROUND_RESULT_6.getLife();
		this.mode=LevelInfo.ROUND_RESULT_6.getMode();
		this.score=LevelInfo.ROUND_RESULT_6.getScore();
	}
}
}

