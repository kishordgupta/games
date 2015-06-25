package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class ModeSelectView extends ElementBase {
Texture tex;
	public ModeSelectView() {
		// TODO Auto-generated constructor stub
	}

	public ModeSelectView(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		GlobalVars.ge.getScreen().getBatch().draw(tex,
				this.x,this.y , this.width, this.height, 0, 0,
				tex.getWidth(), tex.getHeight(), false, false);
		
		super.render();
	}

	@Override
	public void init() {
		this.x=80f*LevelInfo.ratioX;
		this.y=70f*LevelInfo.ratioY;
		this.width=320f*LevelInfo.ratioX;
		this.height=200f*LevelInfo.ratioY;
		tex=Helper.getImageFromAssets(AssetConstants.IMG_MODE_VIEW).getTexture();
		this.image=Helper.getImageFromAssets(AssetConstants.IMG_MODE_VIEW);
		addButtons();
		
	}
	public ButtonModeEasy easy;
	public ButtonModeHard hard;
	public ButtonModeNormal normal;
	public ButtonCloseModeSelecView close;
	public void addButtons(){
		Helper.println("Coming........"+this.x+"  "+this.y,true);
		easy=new ButtonModeEasy(this.x+25f*LevelInfo.ratioX, this.y+85f*LevelInfo.ratioY,
				80f*LevelInfo.ratioX, 25f*LevelInfo.ratioX, 1, AssetConstants.IMG_BTN_EASY);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).addElement(easy);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(easy, InteractionTouch.class);
		
		normal=new ButtonModeNormal(this.x+120f*LevelInfo.ratioX, this.y+85f*LevelInfo.ratioY,
				80f*LevelInfo.ratioX, 25f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_NORMAL);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).addElement(normal);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(normal, InteractionTouch.class);
		
		
		hard=new ButtonModeHard((this.x+215f*LevelInfo.ratioX), this.y+85f*LevelInfo.ratioY,
				80f*LevelInfo.ratioX, 25f*LevelInfo.ratioX, 1, AssetConstants.IMG_BTN_HARD);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).addElement(hard);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(hard, InteractionTouch.class);
		
		close=new ButtonCloseModeSelecView((this.x+120f*LevelInfo.ratioX), this.y+40f*LevelInfo.ratioY,
				80f*LevelInfo.ratioX, 25f*LevelInfo.ratioX, 1, AssetConstants.IMG_BTN_CLOSE);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).addElement(close);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(close, InteractionTouch.class);
		
	}
	public void Destroy(){
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).postDestroyed(easy);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).postDestroyed(normal);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).postDestroyed(hard);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		((RoundSelectView)GlobalVars.ge.getCurrentStage()).postDestroyed(close);
		
	}
}
