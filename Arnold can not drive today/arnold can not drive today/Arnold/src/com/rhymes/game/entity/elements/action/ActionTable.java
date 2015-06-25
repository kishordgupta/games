package com.rhymes.game.entity.elements.action;

import java.util.ArrayList;
import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.game.entity.elements.solitaire.cards.CardBlack;
import com.rhymes.game.entity.elements.solitaire.cards.CardRed;
import com.rhymes.game.entity.elements.solitaire.cards.Clubs;
import com.rhymes.game.entity.elements.solitaire.cards.Dice;
import com.rhymes.game.entity.elements.solitaire.cards.Hearts;
import com.rhymes.game.entity.elements.solitaire.cards.Spade;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.solitaire.table.DeckStack;
import com.rhymes.game.entity.elements.solitaire.table.FinalStack;
import com.rhymes.game.entity.elements.solitaire.table.Stack;
import com.rhymes.game.entity.elements.solitaire.table.TableStack;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ActionTable implements SolitareAction {
	public ArrayList<Card> sourceCards = null;
	boolean started = false;
	public Stack sourceStack;
	boolean active = true;
	
	int i;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ActionTable(ArrayList<Card> sourceCards, Stack sourceStack) {
		this.sourceCards = sourceCards;
		this.sourceStack = sourceStack;
		
//		if(!(sourceStack.holder instanceof DeckStack))
		
	}

	@Override
	public String toString() {
		return "Action 1";
	}
	

	@Override
	public void initialize() {
		time = System.currentTimeMillis();
//		Helper.println("Time started: " + time);		
	}
	

	@Override
	public void act() {
		if (!isActive())
			return;

		if (!started) {
//			Helper.println("Action1 Started for: " + sourceStack.getStack_tag());
			
			((Deck) GlobalVars.ge.getCurrentStage()).game_status.source_reserved(sourceStack);
//			Helper.println("Source from action table : "+sourceStack.getStack_tag()+" id : "+sourceStack.getStack_id());
			
			started = true;
			return;
		}
		
//		Helper.println("Action1 already started & continuing for: " + sourceStack.getStack_tag());

		if (sourceStack == ((Deck) GlobalVars.ge.getCurrentStage()).lastTouched) {
			this.discard();
			for(i = 0; i < sourceStack.get_card_stack().size(); i++){
				if(sourceStack.get_card_stack().get(i).selected)
					sourceStack.get_card_stack().get(i).selected = false;
			}
			return;
		}
		checkMove();
		// this.discard();
		// Helper.println("Action1 discarded");
	}

	public static Point startPoint = new Point();
	public static Point endPoint = new Point();

	private void checkMove() {
		if (((Deck) GlobalVars.ge.getCurrentStage()).lastTouched.holder instanceof TableStack) {
//			Helper.println("Action1 is acting");
//			Helper.println("Checking move for Source Stack: " + sourceStack.getStack_tag());

			// ((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.appendCard(sourceCards);

			// if(((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.get_top_card_of_stack()
			// != sourceCards.get(sourceCards.size() - 1))
			// {
			if (sourceCards != null && sourceCards.size() > 0)
				// startPoint.setLocation(sourceCards.get(0).getX(),
				// sourceCards.get(0).getY());

				if (table_card_logic(sourceCards, ((Deck) GlobalVars.ge.getCurrentStage()).lastTouched)) {
					
					///manage stack height gap
					((Deck) GlobalVars.ge.getCurrentStage()).adjust_height_gap(((Deck) GlobalVars.ge.getCurrentStage()).lastTouched);
					if(sourceStack.holder instanceof TableStack)
						((Deck) GlobalVars.ge.getCurrentStage()).adjust_height_gap(sourceStack);

					
					///reserve destination
//					((Deck) GlobalVars.ge.getCurrentStage()).game_status.set_destination_status(((Deck) GlobalVars.ge.getCurrentStage()).lastTouched);


					for (i = 0; i < sourceCards.size(); i++) {
						sourceStack.get_card_stack().remove(sourceCards.get(i));
					}
					
					
					///adjust draw3
					if(sourceStack.holder instanceof DeckStack){
						((Deck) GlobalVars.ge.getCurrentStage()).
						reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
					}
					

					///manage stack height gap
					if(sourceStack.holder instanceof TableStack)
						((Deck) GlobalVars.ge.getCurrentStage()).adjust_height_gap(sourceStack);
					
					
					
					if (!sourceStack.get_card_stack().isEmpty()){
						sourceStack.get_card_stack().get(
								sourceStack.get_card_stack().size() - 1)
								.set_visible(true);
						
						
						///result standard
							
						((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTurn_visible());
					}
				}
			this.discard();
			
		}

		// else if(((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.holder
		// instanceof DeckStack)
		// {
		// this.discard();
		// }

		else if (((Deck) GlobalVars.ge.getCurrentStage()).lastTouched.holder instanceof FinalStack) {
			
			if (final_card_logic(sourceCards, ((Deck) GlobalVars.ge.getCurrentStage()).lastTouched)) {
				
//				///reserve destination
//				((Deck) GlobalVars.ge.getCurrentStage()).game_status.set_destination_status(((Deck) GlobalVars.ge.getCurrentStage()).lastTouched);

				
				for (i = 0; i < sourceCards.size(); i++) {
					sourceStack.get_card_stack().remove(sourceCards.get(i));
				}
				
				
				
				///adjust draw3
				if(sourceStack.holder instanceof DeckStack){
					((Deck) GlobalVars.ge.getCurrentStage()).reArrange_deck_cards_for_draw_3_new(((Deck)GlobalVars.ge.getCurrentStage()).draw_3card);
				}
				

				if (!sourceStack.get_card_stack().isEmpty()){
					sourceStack.get_card_stack().get(
							sourceStack.get_card_stack().size() - 1)
							.set_visible(true);
					
					//result standard
					
					((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
							+
							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTurn_visible());
				}
			}

			this.discard();
		}

		else {
//			Helper.println("Action1 is discarded");
			this.discard();
		}
	}


	@Override
	public void discard() {
		
//		Helper.println("Time ended: " + time);
		
		///updating time
		((Deck) GlobalVars.ge.getCurrentStage()).actionEnded(System.currentTimeMillis() - time);
		
		///checking hint
		((Deck) GlobalVars.ge.getCurrentStage()).check_hint();
		
		
		((Deck) GlobalVars.ge.getCurrentStage()).currentAction = null;
		if (this.sourceCards != null) {
			for (i = 0; i < sourceCards.size(); i++)
				sourceCards.get(i).selected = false;
		}
	}
	
	long time;
	@Override
	public void start() {
		if (!isActive())
			return;
		
		if (((Deck) GlobalVars.ge.getCurrentStage()).currentAction == null) {
			((Deck) GlobalVars.ge.getCurrentStage()).currentAction = this;
		} 
		else
			((Deck) GlobalVars.ge.getCurrentStage()).currentAction.act();

	}

	boolean is_settable;
	Point p = new Point();
	public boolean table_card_logic(ArrayList<Card> source_cards, Stack last_touched_stack) {

		is_settable = false;

		if (!source_cards.isEmpty()) {
			if (last_touched_stack.get_card_stack().isEmpty()) {
				if (source_cards.get(0).getCard_id() == 13) {
					
					///reserve destination
					((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
					
//					Helper.println("Starting from: " + sourceCards.get(0).getLocation());
					((Deck) GlobalVars.ge.getCurrentStage())
							.startCardMover(sourceCards.get(0).getLocation(), endPoint,
							sourceCards);
					last_touched_stack.appendCard(source_cards, false);
					is_settable = true;
					
//					///manage stack height
//					((Deck) GlobalVars.ge.getCurrentStage()).adjust_height_gap(last_touched_stack);
					
					///result standard
						
					if(sourceStack.getStack_tag() == DeckStack.class.toString()){

						((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getDeck_to_table());
					}
						
					else if(sourceStack.getStack_tag() == FinalStack.class.toString()){
							
						((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getFinal_table());
					}
					
					return is_settable;

				}
			}

			else {
				if (last_touched_stack.get_top_card_of_stack().getCard_id() - 1 == source_cards
						.get(0).getCard_id()) {

					if (last_touched_stack.get_top_card_of_stack() instanceof CardRed
							&& source_cards.get(0) instanceof CardBlack) {
						
						
						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						

//						Helper.println("Starting from: " + sourceCards.get(0).getLocation());
						((Deck) GlobalVars.ge.getCurrentStage())
								.startCardMover(sourceCards.get(0).getLocation(), endPoint, sourceCards);
						
						last_touched_stack.appendCard(source_cards, false);
						is_settable = true;
						
						///manage stack height
//						((Deck) GlobalVars.ge.getCurrentStage()).adjust_height_gap(last_touched_stack);

						///result standard
							
						if(sourceStack.getStack_tag() == DeckStack.class.toString()){

							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
									+
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getDeck_to_table());
						}
							
						else if(sourceStack.getStack_tag() == FinalStack.class.toString()){
							
							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
									+
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getFinal_table());
						}
						
						return is_settable;
					}

					else if (last_touched_stack.get_top_card_of_stack() instanceof CardBlack
							&& source_cards.get(0) instanceof CardRed) {

						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						
//						p.setLocation(sourceCards.get(0).getLocation());
//						Helper.println("Starting from: " + sourceCards.get(0).getLocation());
						((Deck) GlobalVars.ge.getCurrentStage())
								.startCardMover(sourceCards.get(0).getLocation(),
								// ((Deck)GlobalVars.ge.getCurrentStage()).getLastTouchedCardLocation(),
										endPoint, sourceCards);

						last_touched_stack.appendCard(source_cards, false);
						is_settable = true;
						

						///result standard
							
						if(sourceStack.getStack_tag() == DeckStack.class.toString()){

							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
									+
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getDeck_to_table());
						}
							
						else if(sourceStack.getStack_tag() == FinalStack.class.toString()){
							((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
									+
									((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getFinal_table());
						}
						
						return is_settable;

					}
				}
			}
		}
		return is_settable;
	}

	public boolean final_card_logic(ArrayList<Card> source_cards, Stack last_touched_stack) {
		if (source_cards.isEmpty()) {
			is_settable = false;
			return false;
		}
		is_settable = false;

		if (!source_cards.isEmpty() && source_cards.size() == 1) {
			
//			Helper.println("ActionTable: Start point: " + sourceCards.get(0).getLocation());
			if (last_touched_stack.get_card_stack().isEmpty()) {

				if (source_cards.get(source_cards.size() - 1).getCard_id() == 1) {
					
					///reserve destination
					((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
					
					((Deck) GlobalVars.ge.getCurrentStage()).startCardMover(
							sourceCards.get(0).getLocation(), endPoint,
							sourceCards);
					last_touched_stack.appendCard(source_cards.get(source_cards
							.size() - 1), false);
					is_settable = true;
//					Helper.println("Move 1");
					
					//result vegas
					
					((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.									
					setTotal_profit(
							((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getTotal_profit()
							+
							((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getCard_to_final()
							);
					
					///result standard
						
					if(sourceStack.getStack_tag() == DeckStack.class.toString()){
						((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getDeck_to_final());
					}
						
					else if(sourceStack.getStack_tag() == TableStack.class.toString()){
						((Deck) GlobalVars.ge.getCurrentStage()).result_standard.setTotal_points(
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTotal_points()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_standard.getTable_to_final());
					}
				}
			}

			else {

				if (last_touched_stack.get_top_card_of_stack() instanceof Clubs
						&& source_cards.get(source_cards.size() - 1) instanceof Clubs) {

					if (last_touched_stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						
						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						
						((Deck) GlobalVars.ge.getCurrentStage())
								.startCardMover(sourceCards.get(0)
										.getLocation(), endPoint, sourceCards);
						last_touched_stack.appendCard(source_cards
								.get(source_cards.size() - 1), false);
						is_settable = true;
//						Helper.println("Move 2");
						
						//result vegas
						
						((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.									
						setTotal_profit(
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getTotal_profit()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getCard_to_final()
								);
					}
				}

				else if (last_touched_stack.get_top_card_of_stack() instanceof Dice
						&& source_cards.get(source_cards.size() - 1) instanceof Dice) {

					if (last_touched_stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						
						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						
						((Deck) GlobalVars.ge.getCurrentStage())
								.startCardMover(sourceCards.get(0)
										.getLocation(), endPoint, sourceCards);
						last_touched_stack.appendCard(source_cards
								.get(source_cards.size() - 1), false);
						is_settable = true;
//						Helper.println("Move 3");
						
						//result vegas
						
						((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.									
						setTotal_profit(
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getTotal_profit()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getCard_to_final()
								);
					}
				}

				if (last_touched_stack.get_top_card_of_stack() instanceof Hearts
						&& source_cards.get(source_cards.size() - 1) instanceof Hearts) {

					if (last_touched_stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						
						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						
						((Deck) GlobalVars.ge.getCurrentStage())
								.startCardMover(sourceCards.get(0)
										.getLocation(), endPoint, sourceCards);
						last_touched_stack.appendCard(source_cards
								.get(source_cards.size() - 1), false);
						is_settable = true;
//						Helper.println("Move 4");
						
						//result vegas
						
						((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.									
						setTotal_profit(
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getTotal_profit()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getCard_to_final()
								);
					}
				}

				if (last_touched_stack.get_top_card_of_stack() instanceof Spade
						&& source_cards.get(source_cards.size() - 1) instanceof Spade) {

					if (last_touched_stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						
						///reserve destination
						((Deck) GlobalVars.ge.getCurrentStage()).game_status.destination_reserved(last_touched_stack);
						
						
						((Deck) GlobalVars.ge.getCurrentStage())
						.startCardMover(sourceCards.get(0)
								.getLocation(), endPoint, sourceCards);
						last_touched_stack.appendCard(source_cards
								.get(source_cards.size() - 1), false);
						is_settable = true;
//						Helper.println("Move 5");
						
						//result vegas
						
						((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.									
						setTotal_profit(
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getTotal_profit()
								+
								((Deck) GlobalVars.ge.getCurrentStage()).result_vegas.getCard_to_final()
								);
					}
				}
			}
		}
		return is_settable;
	}

}