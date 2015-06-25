package com.rhymes.game.fruitdelivery.menu.selectlevel.res;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.fruitdelivery.AssetConstantsFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.LevelMenuFruit;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevelFruit extends Button {

	public int levelNumber=0;
	public Text damageText;
	public FontController font;
//	private	BitmapFont font;
	
	public ButtonLevelFruit(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);

	}

	@Override
	public void init() {
		float w = font.getFont(AssetConstantsFruit.FONT_NEON2).getBounds("L"+levelNumber).width;
		Text t = new Text(this.x+/*55f*LevelInfo.ratioX*/(this.width-w)/2f, 
				this.y+45f*LevelInfoFruit.ratioY, font, AssetConstantsFruit.FONT_NEON2,
				"L"+levelNumber);
//		t.setColor(0.5f, 0.3f, 0.5f, 1);
//		t.setColor(0.55f, 0.65f, 0.76f, 1);
//		t.setColor(0.5f, 0.8f, 0.8f, 1);
		
		t.setColor(1f, 1f, 1f, 1);
		((LevelMenuFruit)GlobalVars.ge.getCurrentStage()).addElement(t);
		super.init();
	}

	public ButtonLevelFruit(float x, float y, float width, float height, int zIndex,
			String imagePath, int levelNumber, FontController font) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.levelNumber=levelNumber;
		this.font=font;
		
	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){

			Helper.println("Button Leve Hit..."+this.levelNumber);
			LevelInfoFruit.currentLevelNumber=levelNumber;
			
			GlobalVars.ge.loadStage(new BikeLevel(LevelInfoFruit.currentLevelNumber));
			
		}
	}


}
