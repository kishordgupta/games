package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.ge.core.renderer.Point;


public class TableStack {

	public static final int NUMBER_OF_TABLE_STACK = 7;
	
	private Stack[] table_stack;
	
	int i, j, k;
	
	public TableStack(){

		table_stack = new Stack[NUMBER_OF_TABLE_STACK];
		
		for(i = 0; i < NUMBER_OF_TABLE_STACK; i++){
			table_stack[i] = new Stack(this);
			table_stack[i].setStack_id(i);
			table_stack[i].setStack_tag(getClass().toString());
		}
	}
	
	
	/**
	 * 
	 * select cards by touching
	 * 
	 */
	public ArrayList<Card> get_selected_cards_from_table(ArrayList<Card> touching_card_list, 
				Stack table_stack, Point touched_point){
		if(!table_stack.get_card_stack().isEmpty()){

			// if last card
			if((table_stack.get_top_card_of_stack().getTop() - 
					table_stack.get_top_card_of_stack().getHeight()/2f > touched_point.y)){
				if (table_stack.get_top_card_of_stack().is_visible()) {
					touching_card_list.add(table_stack.get_top_card_of_stack());
				}
				else{
					table_stack.get_top_card_of_stack().set_visible(true);
				}
			}
			else{
				for(j = table_stack.get_card_stack().size() - 1; j >= 0; j--){
					if (table_stack.get_card_stack().get(j).is_visible()) {
						if(table_stack.get_card_stack().get(j).getTop() -  
								table_stack.get_card_stack().get(j).getHeight()/2f > touched_point.y)
								break;
					}
					else return touching_card_list;
				}
				
				if(j<0)
					return touching_card_list;
					for(; j < table_stack.get_card_stack().size(); j++)
							touching_card_list.add(table_stack.get_card_stack().get(j));
					return touching_card_list;
			}
		}

		return touching_card_list;
	}
	

	public Stack get_table_stack(int stack_id){
		return table_stack[stack_id];
	}
}
