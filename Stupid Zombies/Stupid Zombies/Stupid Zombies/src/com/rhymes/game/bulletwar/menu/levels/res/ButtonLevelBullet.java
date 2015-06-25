package com.rhymes.game.bulletwar.menu.levels.res;

import com.rhymes.game.bulletwar.AssetConstantsBulletWar;
import com.rhymes.game.bulletwar.menu.levels.LevelMenuBullet;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevelBullet extends Button {

	public int levelNumber=0;
	public Text damageText;
	public FontController font;
//	private	BitmapFont font;
	
	public boolean active;
	
	public ButtonLevelBullet(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);

	}

	@Override
	public void init() {
		float w = font.getFont(AssetConstantsBulletWar.FONT_NEON2).getBounds("L"+levelNumber).width;
		Text t = new Text(this.x+/*55f*LevelInfo.ratioX*/(this.width-w)/2f, 
				this.y+45f*LevelInfoBullet.ratioY, font, AssetConstantsBulletWar.FONT_NEON2,
				"L"+levelNumber);
//		t.setColor(0.5f, 0.3f, 0.5f, 1);
//		t.setColor(0.55f, 0.65f, 0.76f, 1);
//		t.setColor(0.5f, 0.8f, 0.8f, 1);
		
		t.setColor(1f, 1f, 1f, 1);
		((LevelMenuBullet)GlobalVars.ge.getCurrentStage()).addElement(t);
		super.init();
	}

	public ButtonLevelBullet(float x, float y, float width, float height, int zIndex,
			int levelNumber, FontController font, boolean active) {
		super(x, y, width, height, zIndex, changeImagePath(active));
		
		this.levelNumber=levelNumber;
		this.font=font;
		
		this.active = active;
	}
	
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){

			Helper.println("Button Leve Hit..."+this.levelNumber);
			LevelInfoBullet.currentLevelNumber=levelNumber;
			
			if(active)
				GlobalVars.ge.loadStage(new BikeLevel(LevelInfoBullet.currentLevelNumber));
			
		}
	}

	public static String changeImagePath(boolean active){
		String path = "";
		
		if(active)
			path = AssetConstantsBulletWar.level; 
		else
			path = AssetConstantsBulletWar.level_lock;
		
		return path;
	}

}
