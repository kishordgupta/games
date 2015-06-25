package com.rhymes.attackTheFortress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.attackTheFortress.button.ButtonSellTower;
import com.rhymes.attackTheFortress.button.ButtonUpgradeDamage;
import com.rhymes.attackTheFortress.button.ButtonUpgradeLife;
import com.rhymes.attackTheFortress.button.ButtonUpgradeRange;
import com.rhymes.attackTheFortress.button.ButtonUpgradeSpeed;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

		public class TowerStateView extends ElementBase implements InteractionTouchCallbacks {
		private	ButtonUpgradeLife btnUpgradelife=null;
		private	ButtonUpgradeDamage btnUpgradedamage=null;
		private	ButtonUpgradeSpeed btnUpgradespeed=null;
		private	ButtonUpgradeRange btnUpgraderange=null;
		private	ButtonSellTower btnsellTower=null;
		private	BitmapFont font;

	Texture text;
	public TowerStateView() {
		// TODO Auto-generated constructor stub
	}

	public TowerStateView(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	@Override
	public void render() {
		
	GlobalVars.ge.getScreen().getBatch().draw(text,
			x,y , width, height, 0, 0,
				text.getWidth(), text.getHeight(), false, false);	
	}

public void showButtons(){
	btnUpgradelife=new ButtonUpgradeLife(this.x+63f*LevelInfo.ratioX,this.y+133f*LevelInfo.ratioY,
			70f*LevelInfo.ratioX, 20f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_UPGRADE,font);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(btnUpgradelife);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(btnUpgradelife, InteractionTouch.class);
	
	
	btnUpgradedamage=new ButtonUpgradeDamage(this.x+63f*LevelInfo.ratioX,this.y+106f*LevelInfo.ratioY,
			70f*LevelInfo.ratioX, 20f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_UPGRADE,font);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(btnUpgradedamage);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(btnUpgradedamage, InteractionTouch.class);
	
	
	btnUpgradespeed=new ButtonUpgradeSpeed(this.x+63f*LevelInfo.ratioX,this.y+79f*LevelInfo.ratioY,
			70f*LevelInfo.ratioX, 20f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_UPGRADE,font);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(btnUpgradespeed);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(btnUpgradespeed, InteractionTouch.class);
	
	btnUpgraderange=new ButtonUpgradeRange(this.x+63f*LevelInfo.ratioX,this.y+52f*LevelInfo.ratioY,
			70f*LevelInfo.ratioX, 20f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_UPGRADE,font);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(btnUpgraderange);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(btnUpgraderange, InteractionTouch.class);
	
	
	btnsellTower=new ButtonSellTower(this.x+20f*LevelInfo.ratioX,this.y+19f*LevelInfo.ratioY,
			113f*LevelInfo.ratioX, 23f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_SELL,font);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(btnsellTower);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(btnsellTower, InteractionTouch.class);

}

 public BitmapFont createFont(String fontPath) {
 return TrueTypeFontFactory.createBitmapFont(Gdx.files
   .internal(fontPath), Text.getFrontChars(), 12.5f, 7.5f, 1.0f,
   Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
}


public void Destroy(){
	setTowerActive(true);
	((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
   	btnUpgradelife.Destroy();
   	btnUpgradedamage.Destroy();
   	btnUpgradespeed.Destroy();
   	btnUpgraderange.Destroy();
   	btnsellTower.Destroy();
}
public ButtonSellTower getSellButton(){
	return this.btnsellTower;
}
	@Override
	public void getAssets(AssetPack assetPack) {
	}

	@Override
	public void init() {
		text=Helper.getImageFromAssets(AssetConstants.IMG_TOWER_STATVIEW).getTexture();
		Helper.println("Here.....");
		font = createFont(AssetConstants.FONT);
		showButtons();
		setTowerActive(false);
	}
	public void setTowerActive(boolean bool){
		for(int i=0; i<((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).towerList.size();i++){
			Tower T=((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).towerList.get(i);
				if(T.getX()+T.getWidth()>this.x && T.getX()<this.x+this.width &&
					T.getY()+T.getHeight()>this.y && T.getY()<this.y+this.height){
				T.setActive(bool);
			}
		}			
	}
	@Override
	public void onEvent(Point hitPoint) {
		
//			if(!Helper.checkHit(hitPoint, this))
//			{
//				Destroy();
//				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().setShowState(true);
//				
//				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setCurrentTower(null);
//				
//			}
	}

}
