package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.entity.elements.solitaire.cards.Card;


public class FinalStack{
	
	public static final int NUMBER_OF_FINAL_STACK = 4;
	private Stack[] final_stack;

	int i;
	
	public FinalStack(){
		final_stack = new Stack[NUMBER_OF_FINAL_STACK];
	
		for(i = 0; i < NUMBER_OF_FINAL_STACK; i++){
			final_stack[i] = new Stack(this);
			final_stack[i].setStack_id(i);
			final_stack[i].setStack_tag(getClass().toString());
		}
	}
	
	
	public ArrayList<Card> get_card(ArrayList<Card> top_card, Stack final_stack){
		if(!final_stack.get_card_stack().isEmpty()){
			
			top_card.add(final_stack.get_top_card_of_stack());
		}
		return top_card;
	}
	

	public Stack get_final_stack(int stack_id){
		return final_stack[stack_id];
	}

}
