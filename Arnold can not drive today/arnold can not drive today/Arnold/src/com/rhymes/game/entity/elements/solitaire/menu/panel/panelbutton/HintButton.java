package com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton;

import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.solitaire.table.Stack;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class HintButton extends Button{
	
	String imagePath;
	int count_hint;
	int i;

	public HintButton(float x, float y, float width, float height, int zIndex,
			String imagePath) {
		super(x, y, width, height, zIndex, imagePath);

		this.imagePath = imagePath;
		
		count_hint = 0;
	}

	@Override
	public void onEvent(Point p) {

		if(this.checkHit(p)){

			consume_hint();
			
			count_hint = show_hint(count_hint);
			count_hint++;
		}
		
	}
	
	public Stack reserve_hint_source = null;
	public Stack reserve_hint_destination = null;
	
	
	public int show_hint(int count){
		if(!((Deck)GlobalVars.ge.getCurrentStage()).hint_source.isEmpty()){
			if(count > (((Deck)GlobalVars.ge.getCurrentStage()).hint_source.size() - 1)){
				count = 0;
			}
			
			
			///source -> selected
			for(i = 0; i < ((Deck)GlobalVars.ge.getCurrentStage()).hint_source.get(count).get_card_stack().size(); i++){
				((Deck)GlobalVars.ge.getCurrentStage()).hint_source.get(count).get_card_stack().get(i).selected = true;
				this.reserve_hint_source = ((Deck)GlobalVars.ge.getCurrentStage()).hint_source.get(count);
			}
				
			///destination -> selected
			for(i = 0; i < ((Deck)GlobalVars.ge.getCurrentStage()).hint_destination.get(count).get_card_stack().size(); i++){
				((Deck)GlobalVars.ge.getCurrentStage()).hint_destination.get(count).get_card_stack().get(i).selected = true;
				this.reserve_hint_destination = ((Deck)GlobalVars.ge.getCurrentStage()).hint_destination.get(count);
			}
		}
		
		return count;
	}
	
	public void consume_hint(){
		if(this.reserve_hint_source != null){
			for(i = 0; i < this.reserve_hint_source.get_card_stack().size(); i++){
				if(this.reserve_hint_source.get_card_stack().get(i).selected)
					this.reserve_hint_source.get_card_stack().get(i).selected = false;
			}
		}
		
		if(this.reserve_hint_destination != null){
			for(i = 0; i < this.reserve_hint_destination.get_card_stack().size(); i++){
				if(this.reserve_hint_destination.get_card_stack().get(i).selected)
					this.reserve_hint_destination.get_card_stack().get(i).selected = false;
			}
		}
	}
}
