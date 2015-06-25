package com.rhymes.game.interactions.testtile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.game.entity.elements.testtile.TileSet;
import com.rhymes.game.interactions.ICFlick;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;

public class IHeroController extends InteractionBase{
	int debounce = 5;
	@Override
	public void checkCondition(long elapsedTime) {

	}
	int move = TileSet.MOVE_NONE;
	ICHeroController hero;
	@Override
	public void takeAction() {
		if(debounce > 0 ){
			debounce--;
			return;
		}
		debounce = 3;
		for(int i = 0; i < elements.size; i++){
			hero = (ICHeroController) elements.get(i);
			if(Gdx.app.getType() == ApplicationType.Desktop){
				if (Gdx.input.isKeyPressed(Keys.LEFT)){
					move = TileSet.MOVE_LEFT; 
				}
				else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
					move = TileSet.MOVE_RIGHT;
				}
				else if (Gdx.input.isKeyPressed(Keys.UP)){
					move = TileSet.MOVE_UP;
				}
				else if (Gdx.input.isKeyPressed(Keys.DOWN)){
					move = TileSet.MOVE_DOWN;
				}
			}
			else if(Gdx.app.getType() == ApplicationType.Android){
				
			}
			
			if(move != TileSet.MOVE_NONE)
				hero.onMove(move);
			
			move = TileSet.MOVE_NONE;
		}
	}
}
