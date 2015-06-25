package com.rhymes.game.entity.elements.menu;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.menus.ChooseLocation;
import com.rhymes.game.stage.menus.ChoosePlayerandBall;
import com.rhymes.game.stage.menus.Instructions;
import com.rhymes.game.stage.menus.MainMenu;
import com.rhymes.game.stage.menus.OptionMenu;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonNextInstruction extends Button{
	boolean isTouched;
	int i=0;
	private boolean isNext;
	private boolean isBack;
	@Override
	public void render() {
		if(isTouched){
			GlobalVars.ge.getScreen().getBatch().setColor(0, 1, 0, 1f);
		}
		super.render();
		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}

	public ButtonNextInstruction(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}
	
	@Override
	public void onEvent(Point p) {
//		Helper.println("Checking hitpoint...");
		if(this.checkHit(p)){
			isTouched = !isTouched;
//			Helper.println("show next instruction image...");
//			this.setImage(Helper.getImageFromAssets(AssetConstants.IMG_OPTIONS_PRESSED));
//			GlobalVars.ge.loadStage(new Instructions());
			Helper.println("i ========"+i);
			
			if(isNext)
			{	
				if(this.i>5)
				this.i=0;
				Helper.println("i withinnext ========"+this.i);
				((Instructions)GlobalVars.ge.getCurrentStage()).showNextInstruction(this.i);
				this.i = i+1;
			
			}
			else if(!isNext){
				if(this.i<=0)
					this.i=5;
				Helper.println("i withinprev ========"+i);
				((Instructions)GlobalVars.ge.getCurrentStage()).showNextInstruction(this.i);
				this.i = i-1;
			}
			if(isBack)
				GlobalVars.ge.loadStage(new MainMenu());
		}
	}

	public ButtonNextInstruction(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}
	public ButtonNextInstruction(float x, float y, float width, float height, int zIndex,String imagePath,boolean isNext) {
		super(x, y, width, height, zIndex,imagePath);
		this.isNext = isNext;
	}
	public ButtonNextInstruction(float x, float y, float width, float height, int zIndex,String imagePath,boolean isNext,boolean isBack) {
		super(x, y, width, height, zIndex,imagePath);
		this.isNext = isNext;
		this.isBack = isBack;
	}

}
