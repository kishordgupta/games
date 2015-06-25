package com.rhymes.game.entity.elements.ui;

import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.game.stage.menus.LevelMenu;
import com.rhymes.game.stage.menus.MenuHome;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonQuit extends Button {

	public int level_id;
	public ButtonQuit(float x, float y, float width, float height,
			int zIndex, String imagePath, int level_id) {
		super(x, y, width, height, zIndex, imagePath);
		this.level_id = level_id;
	}

	int i, pack_id;
	boolean testing = false;
	@Override
	public void onEvent(Point p) {
		if(this.checkHit(p)){
			Helper.println("Quit game...");
			if(!testing){
					if(level_id < GameMenuInfo.num_of_level_in_a_pack)
						pack_id = 0;
					
					else if(level_id >= GameMenuInfo.num_of_level_in_a_pack 
							&& level_id < 2 *GameMenuInfo.num_of_level_in_a_pack)
						pack_id = 1;
					
					else if(level_id >= 2 *GameMenuInfo.num_of_level_in_a_pack
							&& level_id < 3 *GameMenuInfo.num_of_level_in_a_pack)
						pack_id = 2;
						
					
					
					GlobalVars.ge.loadStage(new LevelMenu(pack_id));
				}
				else
				System.exit(0);
		}
	}

	public ButtonQuit(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

}
