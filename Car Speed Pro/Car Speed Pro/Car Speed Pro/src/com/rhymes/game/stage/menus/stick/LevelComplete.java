package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.carspeedpro.menu.gameover.GameOverCarSpeedPro;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class LevelComplete extends Button {

	public LevelComplete(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	

	public LevelComplete(float x, float y, float width, float height,	int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void onEvent(Point p) {
//		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
//		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-512f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-320f*LevelInfo.ratioY;
	
		if(this.checkHit(p)){
			Helper.println("back to mode menu game...");
			
				GlobalVars.ge.loadStage(new GameOverCarSpeedPro());		
			
		}
	}


}
