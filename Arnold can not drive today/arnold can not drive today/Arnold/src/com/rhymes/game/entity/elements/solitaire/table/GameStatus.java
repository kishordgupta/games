package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class GameStatus {

	public Stack source_stack = null;
	public Stack destination_stack = null;
	
	public ArrayList<Card> source_card_list;
	public ArrayList<Point> source_location_list;
	public ArrayList<Boolean> source_visibility_list;
	
	public ArrayList<Card> destination_card_list;
	public ArrayList<Point> destination_location_list;
	public ArrayList<Boolean> destination_visibility_list;
	
	int i;

	
	public GameStatus(){
		source_card_list = new ArrayList<Card>();
		source_location_list = new ArrayList<Point>();
		source_visibility_list = new ArrayList<Boolean>();
		 
		destination_card_list = new ArrayList<Card>();
		destination_location_list = new ArrayList<Point>();
		destination_visibility_list = new ArrayList<Boolean>();
	}
	
	
	//reserve source
	public void source_reserved(Stack source_stack){
//		if(true)
//			return;
		this.source_stack = source_stack;
		this.source_card_list.clear();
		this.source_location_list.clear();
		this.source_visibility_list.clear();
		for(i = 0; i < source_stack.get_card_stack().size(); i++){
			this.source_card_list.add(source_stack.get_card_stack().get(i));
			
			this.source_location_list.add(new  Point( source_stack.get_card_stack().get(i).getLocation().x, 
					source_stack.get_card_stack().get(i).getLocation().y));
			
//			Helper.println("Setting Source Card Loc-orig-i: " +  i+ " -> "+ source_stack.get_card_stack().get(i).getLocation());
//			Helper.println("Setting Source Card Loc-i: " +  i+ " -> "+ getSource_location_list().get(i));
//			this.source_location_list.add(source_stack.get_card_stack().get(i).getLocation());
			this.source_visibility_list.add(source_stack.get_card_stack().get(i).is_visible());
		}	
		
		Helper.println("SOURCE : "+source_stack.getStack_tag()+" id : "+source_stack.getStack_id()
				+" 	size : "+this.source_card_list.size());
		
		for(i = 0; i < this.source_card_list.size(); i++){
			Helper.println(""+this.source_card_list.get(i).getClass()+" id : "+this.source_card_list.get(i).getCard_id()
					+" visibility : "+this.source_card_list.get(i).is_visible());
		}
	}
	
	
	//reserve destination
	public void destination_reserved(Stack destination_stack){
//		if(true)
//			return;
		this.destination_stack = destination_stack;
		this.destination_card_list.clear();
		this.destination_location_list.clear();
		this.destination_visibility_list.clear();
		
		for(i = 0; i < destination_stack.get_card_stack().size(); i++){
			this.destination_card_list.add(destination_stack.get_card_stack().get(i));
//			this.destination_location_list.add(destination_stack.get_card_stack().get(i).getLocation());

			this.destination_location_list.add(new  Point( destination_stack.get_card_stack().get(i).getLocation().x, 
					destination_stack.get_card_stack().get(i).getLocation().y));
			
			this.destination_visibility_list.add(destination_stack.get_card_stack().get(i).is_visible());
		}	
		
		Helper.println("DESTINATION : "+destination_stack.getStack_tag()+" id :  "+destination_stack.getStack_id()
				+" 	size : "+this.destination_card_list.size());
		
		for(i = 0; i < this.destination_card_list.size(); i++){
			Helper.println(""+this.destination_card_list.get(i).getClass()+" id : "+this.destination_card_list.get(i).getCard_id()
					+" visibility : "+this.destination_card_list.get(i).is_visible());
		}
	}
	
	
	
	public Stack getDestination_stack() {
		return destination_stack;
	}



	public void setDestination_stack(Stack destinationStack) {
		destination_stack = destinationStack;
	}



	public Stack getSource_stack() {
		return source_stack;
	}



	public void setSource_stack(Stack sourceStack) {
		source_stack = sourceStack;
	}
	

	public ArrayList<Boolean> getDestination_visibility_list() {
		return destination_visibility_list;
	}



	public void setDestination_visibility_list(
			ArrayList<Boolean> destinationVisibilityList) {
		destination_visibility_list = destinationVisibilityList;
	}



	public ArrayList<Point> getDestination_location_list() {
		return destination_location_list;
	}



	public void setDestination_location_list(
			ArrayList<Point> destinationLocationList) {
		destination_location_list = destinationLocationList;
	}



	public ArrayList<Card> getDestination_card_list() {
		return destination_card_list;
	}



	public void setDestination_card_list(ArrayList<Card> destinationCardList) {
		destination_card_list = destinationCardList;
	}



	public ArrayList<Boolean> getSource_visibility_list() {
		return source_visibility_list;
	}



	public void setSource_visibility_list(ArrayList<Boolean> sourceVisibilityList) {
		source_visibility_list = sourceVisibilityList;
	}



	public ArrayList<Point> getSource_location_list() {
		return source_location_list;
	}



	public void setSource_location_list(ArrayList<Point> sourceLocationList) {
		source_location_list = sourceLocationList;
	}



	public ArrayList<Card> getSource_card_list() {
		return source_card_list;
	}



	public void setSource_card_list(ArrayList<Card> sourceCardList) {
		source_card_list = sourceCardList;
	}
}
