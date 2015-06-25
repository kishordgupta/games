package com.rhymes.game.entity.elements.solitaire.menu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverScreenSolitaire extends ElementBase{
	String imagePath;

	public GameOverScreenSolitaire(float x, float y, float width, float height,
			int zIndex, String imagePath){
		super(x, y, width, height, zIndex);

		this.imagePath = imagePath; 

	}
	
	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(imagePath);

	}

	@Override
	public void init() {
		this.image = Helper.getImageFromAssets(imagePath);
	}

//	@Override
//	public void onEvent(Point hitPoint) {
//		if(checkHit(hitPoint)){
//			((Deck)GlobalVars.ge.getCurrentStage()).reset_game(true);
//			
//			y = -Gdx.graphics.getHeight();
//			((Deck)GlobalVars.ge.getCurrentStage()).game_over_screen.setY(y);
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).standard_points.setY(y);
//			((Deck)GlobalVars.ge.getCurrentStage()).vegas_points.setY(y);
//			((Deck)GlobalVars.ge.getCurrentStage()).time_points.setY(y);
//			
//			((Deck)GlobalVars.ge.getCurrentStage()).reset_game(true);
//		}
//	}

	
//	protected boolean checkHit(Point p)
//	{
////		Helper.println("\n\nChecking hit point..." + p + " for: " + this.getClass());
////		Helper.printKeyVal("x", this.x);
////		Helper.printKeyVal("r", this.getRight());
////		
////		Helper.printKeyVal("y", this.y);
////		Helper.printKeyVal("t", this.getTop());
//		
////		if((this.getLeft() - this.getWidth()) <= p.x && (this.getRight() + this.getWidth())>= p.x 
////				&& (this.getTop() + this.getWidth()) >= p.y && (this.getBottom() - this.getWidth()) <= p.y)
//		if(this.getLeft() <= p.x && this.getRight()>= p.x && this.getTop() >= p.y && this.getBottom() <= p.y)
//		{
//			return true;
//		}
//		return false;
//	}
}
