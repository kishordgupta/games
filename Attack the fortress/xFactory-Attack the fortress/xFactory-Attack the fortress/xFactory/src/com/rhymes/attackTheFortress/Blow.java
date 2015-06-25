package com.rhymes.attackTheFortress;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class Blow extends ElementBase {
private int blowType=-1;
private int blowNumber=0;
private Texture tex;

	public Blow(float posX, float posY,int type) {
		super.x=this.x=posX;
		super.y=this.y=posY;
		this.blowType=type;
	}

	public Blow(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		StartupInfo.sound_handler.playSound(soundType.SOUND_EXPLODE);
		super.width=this.width=30f*LevelInfo.ratioX;
		super.height=this.height=30f*LevelInfo.ratioY;
		tex=Helper.getImageFromAssets(AssetConstants.IMG_BLOW_1).getTexture();
		this.image=Helper.getImageFromAssets(AssetConstants.IMG_BLOW_1);
		if(this.blowType==1)
			blowNumber=9;
		else if(this.blowType==2)
			blowNumber=22;
	}

	@Override
	public void render() {
		GlobalVars.ge.getScreen().getBatch().draw(tex,
				this.x,this.y , this.width, this.height, 0, 0,
				tex.getWidth(), tex.getHeight(), false, false);
		
		super.render();
	}
	
	
	public int blowcount=1;

	float bulletSkipTime = 150;
	float currentBulletSkipTime = bulletSkipTime;
@Override
	public void step(long stepTime) {
		//----skipping---
		currentBulletSkipTime+=GlobalVars.ge.stepTime;
		if(bulletSkipTime>currentBulletSkipTime)
			return;
		currentBulletSkipTime = 0;
	
		if(blowcount>blowNumber){
			Destroy();
			return;
		}
		String path=AssetConstants.IMG_BLOW+blowcount+"_iPad.png";
		this.tex=Helper.getImageFromAssets(path).getTexture();
		this.image=Helper.getImageFromAssets(path);
		blowcount++;
		super.step(stepTime);
	}
	public void Destroy(){
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
}
}
