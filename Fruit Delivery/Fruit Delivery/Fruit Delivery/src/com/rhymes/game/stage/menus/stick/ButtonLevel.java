package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonLevel extends Button {

	public int levelNumber=0;
	public Text damageText;
	public FontController font;
//	private	BitmapFont font;
	public ButtonLevel(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		float w = font.getFont(AssetConstants.FONT_NEON2).getBounds("L"+levelNumber).width;
		Text t = new Text(this.x+/*55f*LevelInfo.ratioX*/(this.width-w)/2f, 
				this.y+45f*LevelInfo.ratioY, font, AssetConstants.FONT_NEON2,
				"L"+levelNumber);
//		t.setColor(0.5f, 0.3f, 0.5f, 1);
//		t.setColor(0.55f, 0.65f, 0.76f, 1);
//		t.setColor(0.5f, 0.8f, 0.8f, 1);
		t.setColor(1f, 1f, 1f, 1);
		((LevelMenu)GlobalVars.ge.getCurrentStage()).addElement(t);
		super.init();
	}

	public ButtonLevel(float x, float y, float width, float height, int zIndex,
			String imagePath, int levelNumber, FontController font) {
		super(x, y, width, height, zIndex, imagePath);
		this.levelNumber=levelNumber;
		this.font=font;
		
	}
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){

			Helper.println("Button Leve Hit..."+this.levelNumber);
			LevelInfo.currentLevelNumber=levelNumber;
			GlobalVars.ge.loadStage(new BikeLevel(levelNumber));
//			GlobalVars.ge.loadStage(new TestLevel(levelNumber));
//			((InstructionsMenu)GlobalVars.ge.getCurrentStage()).instruction.setImage(1);
			
		}
	}


}
