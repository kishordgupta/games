package com.rhymes.game.entity.elements.ui;

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
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonRestart extends Button {

	private Result result;
	private boolean reloadCurrentStage = false;
	public ButtonRestart(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	
	}
	public ButtonRestart(float x, float y, float width, float height,
			int zIndex, String imagePath,Result result) {
		super(x, y, width, height, zIndex, imagePath);
		this.result = result;
		
	}
	
	public ButtonRestart(float x, float y, float width, float height,
			int zIndex, String imagePath, boolean reloadCurrentStage) {
		super(x, y, width, height, zIndex, imagePath);
		this.reloadCurrentStage = reloadCurrentStage;
	}
	
	int levelNumber ;
	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

//	Result res = new ResultBounce();

	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
//			Helper.println("Button Restart Hit : " + p.toString());
//			GlobalVars.ge.getCurrentStage().reload();
			if(result!= null)
			((ResultBounce) result).setLevelScore(0);
			
			if(reloadCurrentStage)
				GlobalVars.ge.getCurrentStage().reload();
			else{
				if(Constants.levelSelected==1)
				GlobalVars.ge.loadStage(new BounceLevel1());
				else if(Constants.levelSelected==2)
					GlobalVars.ge.loadStage(new BounceLevel2());
				else if(Constants.levelSelected==3)
					GlobalVars.ge.loadStage(new BounceLevel3());
				else if(Constants.levelSelected==4)
					GlobalVars.ge.loadStage(new BounceLevel4());
				else if(Constants.levelSelected==5)
					GlobalVars.ge.loadStage(new BounceLevel5());
				else if(Constants.levelSelected==6)
					GlobalVars.ge.loadStage(new BounceLevel6());
				else if(Constants.levelSelected==7)
					GlobalVars.ge.loadStage(new BounceLevel7());
				else if(Constants.levelSelected==8)
					GlobalVars.ge.loadStage(new BounceLevel8());
				else if(Constants.levelSelected==9)
					GlobalVars.ge.loadStage(new BounceLevel9());
				else if(Constants.levelSelected==10)
					GlobalVars.ge.loadStage(new BounceLevel10());
				else if(Constants.levelSelected==11)
					GlobalVars.ge.loadStage(new BounceLevel11());
				else if(Constants.levelSelected==12)
					GlobalVars.ge.loadStage(new BounceLevel12());
			}
//			if(result !=null)
//			{
//				
//			}
			
		}
	}

	public ButtonRestart(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	
	@Override
	protected boolean checkHit(Point p) {
		// TODO Auto-generated method stub
//		return super.checkHit(p);
		if(this.getLeft()-10 <= p.x && this.getRight()-10>= p.x && this.getTop()-10 >= p.y && this.getBottom()-10 <= p.y)
			return true;
		return false;
	}

}
