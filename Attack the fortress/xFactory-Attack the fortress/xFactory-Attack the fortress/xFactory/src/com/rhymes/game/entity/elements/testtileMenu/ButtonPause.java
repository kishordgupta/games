package com.rhymes.game.entity.elements.testtileMenu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.TileSet;
import com.rhymes.game.entity.elements.testtile.gameOverScreen;
import com.rhymes.game.entity.elements.testtile.scorelbl;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonPause extends Button {

	public ButtonPause(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonPause(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		// TODO Auto-generated constructor stub
	}
	private ButtonResume resume;
	public ButtonResume getButtonResume(){
		return resume;
	}
	@Override
 	public void onEvent(Point p) {
		p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_BTN_BTNSTART_DOWN));
			Helper.println("pause game...");
			
			float	x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+90f*LevelInfo.ratioX;
			float	y = GlobalVars.ge.getScreen().cam.position.y-Gdx.graphics.getHeight()/2f+50f*LevelInfo.ratioY;
			scorelbl gm=new scorelbl(x, y,300f*LevelInfo.ratioX, 250f*LevelInfo.ratioY,Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT).getTexture());
			//scorelbl gm=new scorelbl(x,y,Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT).getRegionWidth(),Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT).getRegionHeight(),Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT));
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(gm);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).setpouseScreen(gm);
		//	((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(gm, InteractionTouch.class);
		
			resume=new ButtonResume(x+60f*LevelInfo.ratioX, y+150f*LevelInfo.ratioY, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, zIndex, AssetConstants.IMG_BTN_RESUME);
//			(x,y,Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT).getRegionWidth(),Helper.getImageFromAssets(AssetConstants.IMG_BTN_SELECT).getRegionHeight(),AssetConstants.IMG_BTN_SELECT);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(resume);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(resume, InteractionTouch.class);
		
			//x = GlobalVars.ge.getScreen().cam.position.x;
			//y = GlobalVars.ge.getScreen().cam.position.y;
			ButtonRestart reload = new ButtonRestart(x+60f*LevelInfo.ratioX , y+100f*LevelInfo.ratioY, 180f*LevelInfo.ratioX, 50f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_RESTART);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(reload);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(reload, InteractionTouch.class);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).setButtonRestart(reload);
			
			//x=150f;
			//y=55f;
			ButtonExitLevel buttonExitgame = new ButtonExitLevel(x+60f*LevelInfo.ratioX, y+50f*LevelInfo.ratioY,180f*LevelInfo.ratioX,50f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_QUIT);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(buttonExitgame);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(buttonExitgame, InteractionTouch.class);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).setButtonExitLevel(buttonExitgame);			
			GlobalVars.ge.getCurrentStage().pause();
			
		}
	}


}
