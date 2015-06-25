package com.rhymes.game.entity.elements.ui;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.Constants;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.stage.levels.BounceLevel1;
import com.rhymes.game.stage.levels.BounceLevel10;
import com.rhymes.game.stage.levels.BounceLevel11;
import com.rhymes.game.stage.levels.BounceLevel12;
import com.rhymes.game.stage.levels.BounceLevel2;
import com.rhymes.game.stage.levels.BounceLevel3;
import com.rhymes.game.stage.levels.BounceLevel4;
import com.rhymes.game.stage.levels.BounceLevel5;
import com.rhymes.game.stage.levels.BounceLevel6;
import com.rhymes.game.stage.levels.BounceLevel7;
import com.rhymes.game.stage.levels.BounceLevel8;
import com.rhymes.game.stage.levels.BounceLevel9;
import com.rhymes.game.stage.menus.ChooseShot;
import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.game.stage.menus.MenuHome;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonQuit extends Button {

	private Result result;
	public ButtonQuit(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	public ButtonQuit(float x, float y, float width, float height,
			int zIndex, String imagePath,Result result) {
		super(x, y, width, height, zIndex, imagePath);
		this.result = result;
	}

//	boolean testing = false;
	@Override
	public void onTouch(Point p) {
		if(this.checkHit(p)){
			Helper.println("Quit game...");
			if(result == null)
				GlobalVars.ge.loadStage(new ChooseShot());
				else if(result != null){
					if(Constants.levelSelected ==1)
						GlobalVars.ge.loadStage(new BounceLevel2());
						else if(Constants.levelSelected ==2)
							GlobalVars.ge.loadStage(new BounceLevel3());
						else if(Constants.levelSelected ==3)
							GlobalVars.ge.loadStage(new BounceLevel4());
						else if(Constants.levelSelected ==4)
							GlobalVars.ge.loadStage(new BounceLevel5());
						else if(Constants.levelSelected ==5)
							GlobalVars.ge.loadStage(new BounceLevel6());
						else if(Constants.levelSelected ==6)
							GlobalVars.ge.loadStage(new BounceLevel7());
						else if(Constants.levelSelected ==7)
							GlobalVars.ge.loadStage(new BounceLevel8());
						else if(Constants.levelSelected ==8)
							GlobalVars.ge.loadStage(new BounceLevel9());
						else if(Constants.levelSelected ==9)
							GlobalVars.ge.loadStage(new BounceLevel10());
						else if(Constants.levelSelected ==10)
							GlobalVars.ge.loadStage(new BounceLevel11());
						else if(Constants.levelSelected ==11)
							GlobalVars.ge.loadStage(new BounceLevel12());
						else if(Constants.levelSelected ==12)
							GlobalVars.ge.loadStage(new BounceLevel1());
					((ResultBounce) result).setLevelScore(0);
					if(Constants.levelSelected!=12)
					Constants.levelSelected = Constants.levelSelected + 1;
					else
						Constants.levelSelected =1;
				}
			else
				Gdx.app.exit();
		}
	}

	public ButtonQuit(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
