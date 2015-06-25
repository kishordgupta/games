package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.game.entity.elements.solitaire.cards.Clubs;
import com.rhymes.game.entity.elements.solitaire.cards.Dice;
import com.rhymes.game.entity.elements.solitaire.cards.Hearts;
import com.rhymes.game.entity.elements.solitaire.cards.Spade;
import com.rhymes.helpers.Helper;

public class CardSet implements Cloneable{
	
	protected CardSet clone() {
		try {
			CardSet cardSet = (CardSet)super.clone();
			cardSet.cards = new ArrayList<Card>(TOTAL_CARDS);
			
			for(int i = 0; i < cards.size(); i++)
				cardSet.cards.add(cards.get(i));
			
			return cardSet;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public final static int MIN_ID = 1;
	public final static int MAX_ID = 13;
	public final static int TOTAL_CARDS = 52;
	private ArrayList<Card> cards;
	
	int i, card_id, global_id;
	
	private Clubs[]  clubs;
	private Dice[]   dice;
	private Hearts[] hearts;
	private Spade[]  spade;
	
	public CardSet(){
		
		cards = new ArrayList<Card>();
		
		clubs = new Clubs[MAX_ID];
		dice  = new Dice[MAX_ID];
		hearts = new Hearts[MAX_ID];
		spade = new Spade[MAX_ID];
		
	}
	
	public String get_specified_image(Card card, int i){
		String front_image = null;
		
		if(card instanceof Clubs){
			if(i == 0)
				front_image = AssetConstants.CLUBS_1;
			else if(i == 1)
				front_image = AssetConstants.CLUBS_2;
			else if(i == 2)
				front_image = AssetConstants.CLUBS_3;
			else if(i == 3)
				front_image = AssetConstants.CLUBS_4;
			else if(i == 4)
				front_image = AssetConstants.CLUBS_5;
			else if(i == 5)
				front_image = AssetConstants.CLUBS_6;
			else if(i == 6)
				front_image = AssetConstants.CLUBS_7;
			else if(i == 7)
				front_image = AssetConstants.CLUBS_8;
			else if(i == 8)
				front_image = AssetConstants.CLUBS_9;
			else if(i == 9)
				front_image = AssetConstants.CLUBS_10;
			else if(i == 10)
				front_image = AssetConstants.CLUBS_11;
			else if(i == 11)
				front_image = AssetConstants.CLUBS_12;
			else if(i == 12)
				front_image = AssetConstants.CLUBS_13;
		}
	
		else if(card instanceof Dice){
			if(i == 0)
				front_image = AssetConstants.DICE_1;
			else if(i == 1)
				front_image = AssetConstants.DICE_2;
			else if(i == 2)
				front_image = AssetConstants.DICE_3;
			else if(i == 3)
				front_image = AssetConstants.DICE_4;
			else if(i == 4)
				front_image = AssetConstants.DICE_5;
			else if(i == 5)
				front_image = AssetConstants.DICE_6;
			else if(i == 6)
				front_image = AssetConstants.DICE_7;
			else if(i == 7)
				front_image = AssetConstants.DICE_8;
			else if(i == 8)
				front_image = AssetConstants.DICE_9;
			else if(i == 9)
				front_image = AssetConstants.DICE_10;
			else if(i == 10)
				front_image = AssetConstants.DICE_11;
			else if(i == 11)
				front_image = AssetConstants.DICE_12;
			else if(i == 12)
				front_image = AssetConstants.DICE_13;
		}
		
		else if(card instanceof Hearts){
			if(i == 0)
				front_image = AssetConstants.HEARTS_1;
			else if(i == 1)
				front_image = AssetConstants.HEARTS_2;
			else if(i == 2)
				front_image = AssetConstants.HEARTS_3;
			else if(i == 3)
				front_image = AssetConstants.HEARTS_4;
			else if(i == 4)
				front_image = AssetConstants.HEARTS_5;
			else if(i == 5)
				front_image = AssetConstants.HEARTS_6;
			else if(i == 6)
				front_image = AssetConstants.HEARTS_7;
			else if(i == 7)
				front_image = AssetConstants.HEARTS_8;
			else if(i == 8)
				front_image = AssetConstants.HEARTS_9;
			else if(i == 9)
				front_image = AssetConstants.HEARTS_10;
			else if(i == 10)
				front_image = AssetConstants.HEARTS_11;
			else if(i == 11)
				front_image = AssetConstants.HEARTS_12;
			else if(i == 12)
				front_image = AssetConstants.HEARTS_13;
		}
		
		else if(card instanceof Spade){
			if(i == 0)
				front_image = AssetConstants.SPADE_1;
			else if(i == 1)
				front_image = AssetConstants.SPADE_2;
			else if(i == 2)
				front_image = AssetConstants.SPADE_3;
			else if(i == 3)
				front_image = AssetConstants.SPADE_4;
			else if(i == 4)
				front_image = AssetConstants.SPADE_5;
			else if(i == 5)
				front_image = AssetConstants.SPADE_6;
			else if(i == 6)
				front_image = AssetConstants.SPADE_7;
			else if(i == 7)
				front_image = AssetConstants.SPADE_8;
			else if(i == 8)
				front_image = AssetConstants.SPADE_9;
			else if(i == 9)
				front_image = AssetConstants.SPADE_10;
			else if(i == 10)
				front_image = AssetConstants.SPADE_11;
			else if(i == 11)
				front_image = AssetConstants.SPADE_12;
			else if(i == 12)
				front_image = AssetConstants.SPADE_13;
		}
		return front_image;
	}

	public void setCards(){
		global_id  = MIN_ID;

		card_id = MIN_ID;
		for(i = 0; i < MAX_ID; i++){
			clubs[i] = new Clubs(card_id, global_id, false);
			clubs[i].setCard_front_image(Helper.getImageFromAssets(get_specified_image(clubs[i], i)));
			
			card_id ++;
			global_id ++;
			
			cards.add(clubs[i]);
		}
		
		card_id = MIN_ID;
		for(i = 0; i < MAX_ID; i++){
			dice[i] = new Dice(card_id, global_id, false);
			dice[i].setCard_front_image(Helper.getImageFromAssets(get_specified_image(dice[i], i)));

			card_id ++;
			global_id ++;

			cards.add(dice[i]);
		}
		
		card_id = MIN_ID;
		for(i = 0; i < MAX_ID; i++){
			hearts[i] = new Hearts(card_id, global_id, false);
			hearts[i].setCard_front_image(Helper.getImageFromAssets(get_specified_image(hearts[i], i)));

			card_id ++;
			global_id ++;

			cards.add(hearts[i]);
		}
		
		card_id = MIN_ID;
		for(i = 0; i < MAX_ID; i++){
			spade[i] = new Spade(card_id, global_id, false);
			spade[i].setCard_front_image(Helper.getImageFromAssets(get_specified_image(spade[i], i)));

			card_id ++;
			global_id ++;
			
			cards.add(spade[i]);
		}
	}
	

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

}
