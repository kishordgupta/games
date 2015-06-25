package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonPause extends Button {

	public InterfaceiPause iPause = null;
	public ButtonResume resume;
	public ButtonRestart reload;
	public ButtonExitLevel buttonExitgame;
	public scorelbl gm;

	public ButtonPause(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	

	public ButtonPause(float x, float y, float width, float height, int zIndex,
			String imagePath,InterfaceiPause test) {
		super(x, y, width, height, zIndex, imagePath);

		this.iPause = test;
	}
	
	@Override
 	public void onEvent(Point p) {
		Helper.println("touch pause game...");
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-512f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-320f*LevelInfo.ratioY;
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){

			Helper.println("pause game...");
			
//			float	x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+90f*LevelInfo.ratioX;
//			float	y = GlobalVars.ge.getScreen().cam.position.y-Gdx.graphics.getHeight()/2f+50f*LevelInfo.ratioY;
			
			float x=0;
			float y=0;
			x=GlobalVars.ge.getScreen().cam.position.x-168f*LevelInfo.ratioX;
			y=GlobalVars.ge.getScreen().cam.position.y-136.5f*LevelInfo.ratioY;
		
			gm=new scorelbl(x, y,336f*LevelInfo.ratioX, 273f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_SELECT).getTexture());
			iPause.getStage().addElement(gm);

			x=x+70f*LevelInfo.ratioX;
			y= y+180f*LevelInfo.ratioY;
			resume=new ButtonResume(x,y, 199f*LevelInfo.ratioX, 52f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_RESUME,iPause, this);
			iPause.getStage().addElement(resume);
			iPause.getStage().subscribeToControllingInteraction(resume, InteractionTouch.class);
			
//			x = x+60f*LevelInfo.ratioX ;
			y =  y-65f*LevelInfo.ratioY;
			reload = new ButtonRestart(x,y, 199f*LevelInfo.ratioX, 52f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_RESTART);
			iPause.getStage().addElement(reload);
			iPause.getStage().subscribeToControllingInteraction(reload, InteractionTouch.class);
			
//			x=x+60f*LevelInfo.ratioX;
			y=y-65f*LevelInfo.ratioY;
			buttonExitgame = new ButtonExitLevel(x,y ,199f*LevelInfo.ratioX, 52f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_QUITLEVEL);
			iPause.getStage().addElement(buttonExitgame);
			iPause.getStage().subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
			
			GlobalVars.ge.getCurrentStage().pause();
			
		}
	}


}
