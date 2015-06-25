package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonResume extends Button {

	InterfaceiPause iPause=null;
	ButtonPause pause;
	public ButtonResume(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonResume(float x, float y, float width, float height,
			int zIndex, String imagePath,InterfaceiPause test,ButtonPause pause) {
		super(x, y, width, height, zIndex, imagePath);
		this.image=Helper.getImageFromAssets(imagePath);
		iPause=test;
		this.pause=pause;
	}

	@Override
	public void onEvent(Point p) {
//		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
//		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-512f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-320f*LevelInfo.ratioY;
	
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("resume game...");			
			
			
			iPause.getStage().postDestroyed(this);
			iPause.getStage().postDestroyed(pause.gm);
//			iPause.getStage().postDestroyed(pause.resume);
			iPause.getStage().postDestroyed(pause.reload);
			iPause.getStage().postDestroyed(pause.buttonExitgame);
			GlobalVars.ge.getCurrentStage().resume();
		}

}
}