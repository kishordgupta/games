package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.attackTheFortress.TSVInfo;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.gameOverScreen;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonTower extends Button {
	public boolean isShow=false;
	ButtonTowerSelect gm;

	public ButtonTower(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonTower(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
//			Helper.println("Come Here....");
			if(!isShow){
			float	x = Gdx.graphics.getWidth()/10f*9f-9f*LevelInfo.ratioX;
			float	y = Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-251f*LevelInfo.ratioY;
			gm=new ButtonTowerSelect(x,y,Gdx.graphics.getWidth()/10f,240f*LevelInfo.ratioY,1,AssetConstants.IMG_TSV_BACK);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(gm);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(gm, InteractionTouch.class);
			isShow=true;
			}
			else{
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(gm);
				if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getcurrentTSVInfo()!=null){
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getcurrentTSVInfo());
				}
				isShow=false;
			}
		}
		
	}


}
