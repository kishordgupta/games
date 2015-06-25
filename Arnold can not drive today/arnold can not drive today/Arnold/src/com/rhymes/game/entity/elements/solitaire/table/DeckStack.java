package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;


public class DeckStack {

	public final static int DECK_CARDS_AT_A_TIME = 3;
	
	private Stack visible_cards;
	private Stack non_visible_cards;
	
	public DeckStack(){
		visible_cards = new Stack(this);
		visible_cards.setStack_id(0);
		visible_cards.setStack_tag(getClass().toString());
		
		non_visible_cards = new Stack(this);
		non_visible_cards.setStack_id(1);
		non_visible_cards.setStack_tag(getClass().toString());
	}
	
	float gap = 0f;
	
	int i, limit;
	ArrayList<Card> card = new ArrayList<Card>();
	
	/**
	 * action for visible deck touched
	 * @return
	 */
	public ArrayList<Card> visible_deck_touched(){
//		Helper.println("Visible deck touched");
		if(!visible_cards.get_card_stack().isEmpty()){
			card.add(visible_cards.get_top_card_of_stack());
				
			//reserve source
//			((Deck) GlobalVars.ge.getCurrentStage()).game_status.set_source_status(visible_cards);
		}
		
		else{

			//reserve source
			((Deck) GlobalVars.ge.getCurrentStage()).game_status.source_reserved(non_visible_cards);
			
			//reserve destination
			((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(visible_cards);
			
			
			for(limit = 0; limit < DECK_CARDS_AT_A_TIME; limit++){

				if(!non_visible_cards.get_card_stack().isEmpty()){
					visible_cards.get_card_stack().add(non_visible_cards.get_top_card_of_stack());
					
					visible_cards.get_top_card_of_stack().setX(visible_cards.getX());
					visible_cards.get_top_card_of_stack().setY(visible_cards.getY());
					
					visible_cards.get_top_card_of_stack().set_visible(true);
			
					non_visible_cards.get_card_stack().
					remove(non_visible_cards.get_card_stack().indexOf(non_visible_cards.get_top_card_of_stack()));
				}
			}
			
			((Deck) GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
		
			((Deck) GlobalVars.ge.getCurrentStage()).check_hint();
		}
		return card;	
	}
	
	public void remove_top_card_from_visible_stack(){
		if(!visible_cards.get_card_stack().isEmpty()){
			visible_cards.get_card_stack().remove(visible_cards.get_top_card_of_stack());		
		}
	}
	
	/**
	 * action for non visible deck touched
	 * @return
	 */
	public void non_visible_deck_touched(){
		Helper.println("NON Visible deck touched");
			if(non_visible_cards.get_card_stack().isEmpty()){
				
				//reserve source
				((Deck) GlobalVars.ge.getCurrentStage()).game_status.source_reserved(visible_cards);

				//reserve destination
				((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(non_visible_cards);

				
				
				for(i = visible_cards.get_card_stack().size() - 1; i >= 0 ; i--){
					
					non_visible_cards.get_card_stack().add(visible_cards.get_card_stack().get(i));
					non_visible_cards.get_top_card_of_stack().set_visible(false);
					
					non_visible_cards.get_top_card_of_stack().setX(non_visible_cards.getX());
					non_visible_cards.get_top_card_of_stack().setY(non_visible_cards.getY());
					
					visible_cards.get_card_stack().remove(i);
				}
				
			}

			///non visible not empty!!!
			else{
				choose3_style();
				((Deck) GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
			}
			
			((Deck) GlobalVars.ge.getCurrentStage()).check_hint();
			
	}
	
	
	public void choose3_style(){
//		if(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card){
//			
//			if(((Deck)GlobalVars.ge.getCurrentStage()).left_handed_button.isActive()){
//				gap = 20f * ConfigConstants.ratio_w;
//			}
//			
//			else{
//				gap = - 20 * ConfigConstants.ratio_w;
//				
//				if(non_visible_cards.get_card_stack().size() >= DECK_CARDS_AT_A_TIME){
//					k = 3;
//				}
//				
//				else if(non_visible_cards.get_card_stack().size() == DECK_CARDS_AT_A_TIME - 1){
//					k = 2;
//				}
//				
//				else if(non_visible_cards.get_card_stack().size() == DECK_CARDS_AT_A_TIME - 2){
//					k = 1;
//				}
//				
//				g = (k-1) * gap;
//				
//				gap = 20 * ConfigConstants.ratio_w;
//			}
//		}
//		
//		else{
//			gap = 0f;
//		}
		
		
		
		//reserve source
		((Deck) GlobalVars.ge.getCurrentStage()).game_status.source_reserved(non_visible_cards);

		//reserve destination
		((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(visible_cards);

		
		for(limit = 0; limit < DECK_CARDS_AT_A_TIME; limit++){

			if(!non_visible_cards.get_card_stack().isEmpty()){
				visible_cards.get_card_stack().add(non_visible_cards.get_top_card_of_stack());
				
				visible_cards.get_top_card_of_stack().setX(visible_cards.getX());
				visible_cards.get_top_card_of_stack().setY(visible_cards.getY());
				
//				Helper.println("deck stack x : "+(visible_cards.getX() +g));
				
//				g = g + gap;
				
				visible_cards.get_top_card_of_stack().set_visible(true);
		
				non_visible_cards.get_card_stack().
				remove(non_visible_cards.get_card_stack().indexOf(non_visible_cards.get_top_card_of_stack()));
			}
		}
		
//		g = 0f;
	}
	
	
//	float g = 0f;
//	float k;
	
	public Stack get_visible_cards(){
		return visible_cards;
	}
	
	public Stack get_non_visible_cards(){
		return non_visible_cards;
	}
}
