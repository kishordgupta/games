package com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class ThemeButton extends Button{
	
	private boolean active;
	String imagePath;

	public ThemeButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		
		this.imagePath = imagePath;

	}

	
	@Override
	public void onEvent(Point p) {
		if(this.checkHit_panel(p)){
			if(!isActive()){
				setActive(true);
//				((Deck)GlobalVars.ge.getCurrentStage()).last_touched_button = this;
				
				((Deck)GlobalVars.ge.getCurrentStage()).unsubscribe_menu_button_interaction();
				((Deck)GlobalVars.ge.getCurrentStage()).themePanelActive();
			}
			
			else{
				setActive(false);
				
				((Deck)GlobalVars.ge.getCurrentStage()).subscribe_menu_button_interaction();
				((Deck)GlobalVars.ge.getCurrentStage()).themePanelInactive();
			}
		}
	}


	protected boolean checkHit_panel(Point p)
	{
//		Helper.println("\n\nChecking hitpoint..." + p + " for: " + this.getClass());
//		Helper.printKeyVal("x", this.x);
//		Helper.printKeyVal("r", this.getRight());
//		
//		Helper.printKeyVal("y", this.y);
//		Helper.printKeyVal("t", this.getTop());
		
//		if((this.getLeft() - this.getWidth()) <= p.x && (this.getRight() + this.getWidth())>= p.x 
//				&& (this.getTop() + this.getWidth()) >= p.y && (this.getBottom() - this.getWidth()) <= p.y)
		if(this.getLeft() - this.width <= p.x && this.getRight() + this.width >= p.x && this.getTop() + this.height >= p.y && this.getBottom() - this.height <= p.y)
		{
			set_pressed(true);
			return true;
		}
		return false;
	}

	
	
	public void setActive(boolean active) {
		this.active = active;
	}


	public boolean isActive() {
		return active;
	}
}
