package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.fruitdelivery.menu.main.MainMenuFruit;
import com.rhymes.game.fruitdelivery.menu.selectlevel.LevelMenuFruit;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonExitLevel extends Button {

	public ButtonExitLevel(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonExitLevel(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		this.image=Helper.getImageFromAssets(imagePath);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEvent(Point p) {
//		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
//		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-512f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-320f*LevelInfo.ratioY;
	
		if(this.checkHit(p)){
			Helper.println("Exit Level...");
//			if(LevelInfo.GAME_MODE==1){
				GlobalVars.ge.loadStage(new LevelMenuFruit(0));	
//			}
//			else if(LevelInfo.GAME_MODE==2){
//				GlobalVars.ge.loadStage(new GameModeMenu());
//			}
////			TestTileLevel.gameMode=1;
		}
	}


}
