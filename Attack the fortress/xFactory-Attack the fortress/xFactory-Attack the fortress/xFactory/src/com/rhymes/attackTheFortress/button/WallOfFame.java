package com.rhymes.attackTheFortress.button;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class WallOfFame extends ElementBase {
	ArrayList<Text> textList=new ArrayList<Text>();

	public WallOfFame() {
		this.x=0;
		this.y=0;
		this.height=Gdx.graphics.getHeight();
		this.width=Gdx.graphics.getWidth();
		this.image=Helper.getImageFromAssets(AssetConstants.IMG_WALLOF_FAME);
	}

	public WallOfFame(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

public String getMode(int m){
	String mode="";
	if(m==1)
		mode="EASY";
	else if(m==2)
		mode="NORMAL";
	else if(m==3)
		mode="HARD";
	return mode;
}
	@Override
	public void init() {
		loadFonts();
		float l=80f*LevelInfo.ratioX;
		float m=100f*LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(l, m, 100f*LevelInfo.ratioX, 25f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_CLOSE);
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(buttonBack);
		((MainView)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(buttonBack, InteractionTouch.class);
	
		Text result=new Text(260f*LevelInfo.ratioX, 177f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		LevelInfo.ROUND_RESULT_1.getScore()+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
		
		result=new Text(260f*LevelInfo.ratioX, 167f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		getMode(LevelInfo.ROUND_RESULT_1.getMode())+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
				
		result=new Text(325f*LevelInfo.ratioX, 177f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		LevelInfo.ROUND_RESULT_2.getScore()+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
				
		result=new Text(325f*LevelInfo.ratioX, 167f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		getMode(LevelInfo.ROUND_RESULT_2.getMode())+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
						
		result=new Text(390f*LevelInfo.ratioX, 177f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		LevelInfo.ROUND_RESULT_3.getScore()+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
						
		result=new Text(390f*LevelInfo.ratioX, 167f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		getMode(LevelInfo.ROUND_RESULT_3.getMode())+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
						
		result=new Text(260f*LevelInfo.ratioX, 83f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		LevelInfo.ROUND_RESULT_4.getScore()+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
 
		result=new Text(260f*LevelInfo.ratioX, 73f*LevelInfo.ratioY,
		0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
		getMode(LevelInfo.ROUND_RESULT_4.getMode())+"");
		((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
		this.textList.add(result);
		
		result=new Text(325f*LevelInfo.ratioX, 83f*LevelInfo.ratioY,
				0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
				LevelInfo.ROUND_RESULT_5.getScore()+"");
				((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
				this.textList.add(result);
						
				result=new Text(325f*LevelInfo.ratioX, 73f*LevelInfo.ratioY,
				0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
				getMode(LevelInfo.ROUND_RESULT_5.getMode())+"");
				((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
				this.textList.add(result);
				
				result=new Text(390f*LevelInfo.ratioX, 83f*LevelInfo.ratioY,
				0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
				LevelInfo.ROUND_RESULT_6.getScore()+"");
				((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
				this.textList.add(result);
										
				result=new Text(390f*LevelInfo.ratioX, 73f*LevelInfo.ratioY,
				0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font,
				getMode(LevelInfo.ROUND_RESULT_6.getMode())+"");
				((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
				this.textList.add(result);
					
				result=new Text(90f*LevelInfo.ratioX, 162f*LevelInfo.ratioY,
				0.6f*LevelInfo.ratioX, 0.6f*LevelInfo.ratioY, font,
				""+(LevelInfo.ROUND_RESULT_1.getScore()+LevelInfo.ROUND_RESULT_2.getScore()+
						LevelInfo.ROUND_RESULT_3.getScore()+LevelInfo.ROUND_RESULT_4.getScore()+
						LevelInfo.ROUND_RESULT_5.getScore()+LevelInfo.ROUND_RESULT_6.getScore()));
				((MainView)GlobalVars.ge.getCurrentStage()).addElement(result);
				this.textList.add(result);	
				
	}
	 BitmapFont font;
 	 private void loadFonts() {
 	  font = TrueTypeFontFactory.createBitmapFont  
 	  (Gdx.files.internal(AssetConstants.FONT_SUPERSTAR), Text.getFrontChars(),
 	    12.0f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
// 	  font.setColor(1f, 1f, 1f, 1f);
 	  font.setColor(Color.BLACK);
 	 }	

}
