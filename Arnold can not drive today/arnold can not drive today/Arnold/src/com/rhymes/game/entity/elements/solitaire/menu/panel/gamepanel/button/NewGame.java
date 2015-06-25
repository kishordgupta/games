package com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.button;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class NewGame extends Button{
	String imagePath;
	
	public NewGame(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
//			((Deck)GlobalVars.ge.getCurrentStage()).game_over_screen.setY(- Gdx.graphics.getHeight());
			((Deck)GlobalVars.ge.getCurrentStage()).reset_game(true);
			((Deck)GlobalVars.ge.getCurrentStage()).cross_button.setActive(true);
		}
	}
}
