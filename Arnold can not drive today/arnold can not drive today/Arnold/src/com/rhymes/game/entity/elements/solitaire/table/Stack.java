package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.action.ActionTable;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Stack extends ElementBase implements InteractionTouchCallbacks{

	private ArrayList<Card> card_stack;
	private int stack_id;
	private String stack_tag;
	public boolean touched;
	
	public float height_gap = Deck.card_height_gap;
	
	public ActionTable action_table;
	
	public	ArrayList<Card> touching_card_container = new ArrayList<Card>();
	
	int i, j;
	public Object holder = null;
	
	public Stack(Object holder){
		card_stack = new ArrayList<Card>();
		setStack_tag(null);
		this.holder = holder;
		setWidth(ConfigConstants.card_width * ConfigConstants.ratio_w);
		setHeight(ConfigConstants.card_height * ConfigConstants.ratio_h);
	}

	
	@Override
	public void getAssets(AssetPack assetPack) {

	}

	
	@Override
	public void init() {
		setImage(Helper.getImageFromAssets(AssetConstants.CARD_LAYER));
		setzIndex(1);
	}
	
	
	@Override
	public void onEvent(Point hitPoint) {
		if(((Deck)GlobalVars.ge.getCurrentStage()).cardMover.isActive())
			return;
//		Helper.println("Stack y: " + this.y);
//		Helper.println("Touched at: " + hitPoint);
		if(checkHit(hitPoint)){
			set_stack_Touched(true);
			((Deck)GlobalVars.ge.getCurrentStage()).lastTouched = this;
//			Helper.println("Last touched set to : " + this.getStack_tag());
			if(!card_stack.isEmpty())
				ActionTable.endPoint.setLocation(get_top_card_of_stack().getLocation());
			else{
				if(holder instanceof TableStack)
					ActionTable.endPoint.setLocation(this.x, ((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY);
				else if(holder instanceof FinalStack){
					ActionTable.endPoint.setLocation(
					this.getLocation());
					Helper.printKeyVal("EndPoint: ", ActionTable.endPoint.toString());
				}
			}
			handle_game(hitPoint);
			
		}
	}
	

//	boolean done = false;
	@Override
	public void render() {
//		Helper.printKeyVal("\nStack id", this.getId());
//		Helper.printKeyVal("\nStack size", card_stack.size());

//		Helper.printKeyVal("y", this.getBottom());
//		if(touched){
//			GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 0.4f);
//		}
		if(card_stack.isEmpty()){
			if(holder instanceof TableStack){
//				GlobalVars.ge.getRenderer().render(image, 
//						x-ConfigConstants.card_width/2f , 
//						((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY - 
//						(ConfigConstants.card_height * ConfigConstants.ratio_h)/2f, 
//						ConfigConstants.card_width * ConfigConstants.ratio_w, 
//						ConfigConstants.card_height * ConfigConstants.ratio_h);
			}
			
			else
				GlobalVars.ge.getRenderer().render(image, x-width/2f , y-height/2f, width, height);
		}
		
		else{
			if(holder instanceof FinalStack){
//				GlobalVars.ge.getRenderer().render(this.get_top_card_of_stack().getImage(), 
//						x-width/2f , 
//						y-height/2f, 
//						ConfigConstants.card_width * ConfigConstants.ratio_w, 
//						ConfigConstants.card_height * ConfigConstants.ratio_h);
//				Helper.println("Final Stack Card size: " + card_stack.size());
			}
			
			if(holder instanceof DeckStack /*|| holder instanceof FinalStack*/){
			
				for(int i = 0; i < card_stack.size(); i++)
				{
		//			Helper.printKeyVal("\nCard: ", card_stack.get(i).getClass().getName());
	//				if(holder instanceof FinalStack){
	//					card_stack.get(i).setLocation(20 + (ConfigConstants.card_width/4 + 10) * i , 100);
	//					Helper.println("Card id: " + card_stack.get(i).getCard_id() 
	//							+ " :  Visibility: " + card_stack.get(i).is_visible());
	//				}
					card_stack.get(i).renderFromStack();
				}
			}
		}
		
	
//		GlobalVars.ge.getScreen().getBatch().setColor(1, 1, 1, 1f);
	}
	
	
	
	protected boolean checkHit(Point p){
		if(holder instanceof TableStack){
//			Helper.println("Table stack");
			if(this.getLeft()-this.width/2f <= p.x && this.getRight()-width/2f >= p.x
					&& this.getTop() >= p.y && this.getBottom()  <= p.y){
				return true;
			}
		}
		else{
		
			if(this.getLeft()-this.width/2f <= p.x && this.getRight()-width/2f >= p.x
					&& this.getTop() - height/2f >= p.y && this.getBottom()  - height/2f<= p.y){
	//			Helper.println("stack touched : "+stack_tag);
				return true;
			}
		}
		return false;
	}
	
	
	public void handle_game(Point hitPoint){
		touching_card_container.clear();
		
		///deck
		if(holder instanceof DeckStack){
//			Helper.println("Deck stack clicked! "+" stack id : "+stack_id);
			
			((Deck)GlobalVars.ge.getCurrentStage()).
			discardAction(((Deck)GlobalVars.ge.getCurrentStage()).getCurrentAction());
			
			
			//non visible
			if(((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.getStack_id() == 1){
				((DeckStack)holder).non_visible_deck_touched();
			}
			
			//visible
			else if(((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.getStack_id() == 0){
				
				touching_card_container = ((DeckStack)holder).visible_deck_touched();
				
				for(j = 0; j < touching_card_container.size(); j++){
					
					if(!touching_card_container.get(j).selected )
						touching_card_container.get(j).selected = true;
					
					else
						touching_card_container.get(j).selected = false;
				}
				
				((Deck)GlobalVars.ge.getCurrentStage()).startAction(new ActionTable(touching_card_container, this));
			}
		}
	
		///table
		else if(holder instanceof TableStack){
//			Helper.println("table stack clicked! "+" stack id : "+stack_id);
					
			touching_card_container = ((TableStack) holder).get_selected_cards_from_table
				(touching_card_container, this, hitPoint);
			
			///reserve source
//			((Deck) GlobalVars.ge.getCurrentStage()).game_status.set_source_status(this);
			
			
			for(j = 0; j < touching_card_container.size(); j++){
//				Helper.printKeyVal("card touched : ", touching_card_container.get(j).getCard_id()
//						+" "+touching_card_container.get(j).getClass() + " location: " + 
//						touching_card_container.get(j).getLocation());
				
				if(!(((Deck)GlobalVars.ge.getCurrentStage()).currentAction instanceof ActionTable))
					touching_card_container.get(j).selected = true;
			}

			((Deck)GlobalVars.ge.getCurrentStage()).startAction(new ActionTable(touching_card_container, this));
		}
		
		///final
		else if(holder instanceof FinalStack){
//			Helper.println("final stack clicked! "+" stack id : "+stack_id);
			
			touching_card_container = ((FinalStack) holder).get_card(touching_card_container, this);
			
			///reserve source
//			((Deck) GlobalVars.ge.getCurrentStage()).game_status.set_source_status(this);

			
			for(j = 0; j < touching_card_container.size(); j++){
				
				if(!(((Deck)GlobalVars.ge.getCurrentStage()).currentAction instanceof ActionTable))
					touching_card_container.get(j).selected = true;
			}
			
			((Deck)GlobalVars.ge.getCurrentStage()).startAction(new ActionTable(touching_card_container, this));
		}
	}
	
	
	///get card stack
	
	public ArrayList<Card> get_card_stack(){
		return card_stack;
	}
	
	
	/// get the last card of the stack
	
	public Card get_top_card_of_stack(){
		return card_stack.get(card_stack.size() - 1);
	}
	
	
	///for final stack & deck stack && for set table stack initialize

	public void insert_to_Card_stack(Card card) {
		this.card_stack.add(card);
	}

	public void resetCardPos()
	{
		Helper.println("Card size: " + card_stack.size());
		for(int i = 0; i < card_stack.size(); i++){
			card_stack.get(i).setX(this.x);
			if(i>0)
//				card_stack.get(i).setY(card_stack.get(i-1).getY() - Deck.card_height_gap);
				card_stack.get(i).setY(card_stack.get(i-1).getY() - height_gap);

			else
				card_stack.get(i).setY(((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY);
			
//			Helper.println("Card: i: " + i + " card class: " + card_stack.get(i).getClass() + 
//					" y: " + 	card_stack.get(i).getY());
		}
	}
	
	public void appendCard(Card card)
	{
		if(holder instanceof TableStack){
			card.setX(this.x);
			if(card_stack.size() > 0)
//				card.setY(get_top_card_of_stack().getY() - Deck.card_height_gap);
				card.setY(get_top_card_of_stack().getY() - height_gap);

			else
				card.setY(((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY);
		}
		else{
			card.setX(this.x);
			card.setY(this.y);
		}
		this.card_stack.add(card);
	}
	
	public void appendCard(Card card, boolean changePosition)
	{
		if(changePosition){
			if(holder instanceof TableStack){
				card.setX(this.x);
				if(card_stack.size() > 0)
//					card.setY(get_top_card_of_stack().getY() - Deck.card_height_gap);
					card.setY(get_top_card_of_stack().getY() - height_gap);

				else
					card.setY(((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY);
			}
			else{
				card.setX(this.x);
				card.setY(this.y);
			}
		}
		
		if(holder instanceof TableStack){
			card.pX = this.x;
			if(card_stack.size() > 0)
				card.pY = (get_top_card_of_stack().getY() - Deck.card_height_gap);
			else
				card.pY = (((Deck)GlobalVars.ge.getCurrentStage()).tableStackTopY);
		}
		else{
			card.pX = (this.x);
			card.pY = (this.y);
		}
		
		this.card_stack.add(card);
	}
	
	
	Card temp;
	public Card pick_from_Card_stack() {
		temp = null;
		
		temp = card_stack.get(card_stack.size() - 1);
		card_stack.remove(card_stack.size() - 1);
		
		return temp;

	}
	
	
	
	///for table stack
	public void  appendCard(ArrayList<Card> cards) {
		for(int i = 0; i < cards.size(); i++)
			appendCard(cards.get(i));
	}
	
	public void  appendCard(ArrayList<Card> cards, boolean changePosition) {
		for(int i = 0; i < cards.size(); i++){
			appendCard(cards.get(i), changePosition);
		}
	}
	
	public void insert_to_Card_stack(ArrayList<Card> cards) {
		card_stack.addAll(cards);
	}
	
	
	ArrayList<Card> temp_list;
	public ArrayList<Card> pick_from_Card_stack(Card card) {
		temp_list = null;
		
		for(i = card_stack.indexOf(card); i < card_stack.size(); i++){
			temp_list.add(card_stack.get(i));
			card_stack.remove(i);
		}
		return temp_list;
	}

	public void setStack_id(int stack_id) {
		this.stack_id = stack_id;
	}

	public int getStack_id() {
		return stack_id;
	}

	public void setStack_tag(String stack_tag) {
		this.stack_tag = stack_tag;
	}

	public String getStack_tag() {
		return stack_tag;
	}

	public void set_stack_Touched(boolean touched) {
		this.touched = touched;
	}

	public boolean is_stack_Touched() {
		return touched;
	}
}
