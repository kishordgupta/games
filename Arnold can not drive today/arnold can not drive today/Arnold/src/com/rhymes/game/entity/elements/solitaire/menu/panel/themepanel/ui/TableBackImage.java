package com.rhymes.game.entity.elements.solitaire.menu.panel.themepanel.ui;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class TableBackImage extends Button{
	
	public boolean selected = false;
	String imagePath;
	
	public TableBackImage(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;
	}
	
	@Override
	public void onEvent(Point p) {
		
		if(this.checkHit(p)){
			selected = true;
			((Deck)GlobalVars.ge.getCurrentStage()).change_TableBack_image(this.imagePath);
			((Deck)GlobalVars.ge.getCurrentStage()).reserve_table_back_image = this.imagePath;
			((Deck)GlobalVars.ge.getCurrentStage()).last_selected_table_back_img = this;
		}
	}
	
	@Override
	public void render() {
		if(selected)
			GlobalVars.ge.getScreen().getBatch().setColor(0.8f, 0.8f, 1, 1f);
		super.render();
		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}
	
}
