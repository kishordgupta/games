package com.rhymes.game.entity.elements.solitaire.menu;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class RestartGameOver extends Button{
	String imagePath;

	public RestartGameOver(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}

	
	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){
			
			((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_interaction();
			((Deck)GlobalVars.ge.getCurrentStage()).subscribe_cards_interaction();
			
			((Deck)GlobalVars.ge.getCurrentStage()).reset_game(false);
			
			y = - Gdx.graphics.getHeight();
			
			((Deck)GlobalVars.ge.getCurrentStage()).standard_points.setY(y);
			((Deck)GlobalVars.ge.getCurrentStage()).vegas_points.setY(y);
			((Deck)GlobalVars.ge.getCurrentStage()).time_points.setY(y);
			
			((Deck)GlobalVars.ge.getCurrentStage()).game_over_screen.setY(y);
			
			((Deck)GlobalVars.ge.getCurrentStage()).restart_game_over.setY(y);
			((Deck)GlobalVars.ge.getCurrentStage()).new_game_over.setY(y);
			((Deck)GlobalVars.ge.getCurrentStage()).quit_game_over.setY(y);
		}
	}
}
