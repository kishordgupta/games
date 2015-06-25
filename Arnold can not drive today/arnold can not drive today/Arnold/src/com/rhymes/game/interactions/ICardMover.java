package com.rhymes.game.interactions;

import java.util.ArrayList;

import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.game.entity.elements.solitaire.table.Deck;
import com.rhymes.game.entity.elements.solitaire.table.TableStack;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ICardMover extends InteractionBase {
	
	public ArrayList<Card> cards ;
	public Point startPoint = new Point();
	public Point endPoint = new Point();
	private Point curPoint = new Point();
	
	int i;
	
	public boolean active = false;
	public float distance = 0;
	public float stepDistance = 20f;
	
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setStartPoint(float x, float y) {
		this.startPoint.x = x;
		this.startPoint.y = y;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public void setEndPoint(float x, float y) {
		this.endPoint.x = x;
		this.endPoint.y = y;
	}


	public float getStepDistance() {
		return stepDistance;
	}

	public void setStepDistance(float stepDistance) {
		this.stepDistance = stepDistance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		
		if(active){
			for(i = 0; i < cards.size(); i++){
				GlobalVars.ge.getCurrentStage().removeElement(cards.get(i));
				GlobalVars.ge.getCurrentStage().addTopElement(cards.get(i), false);
			}
		}
		if(!active)
		{
//			for(int i=cards.size()-1; i >= 0; i--)
//			{
//				cards.get(i).setLocation(((Deck)GlobalVars.ge.getCurrentStage()).lastCard.getX() , 
//						((Deck)GlobalVars.ge.getCurrentStage()).lastCard.getY() - (i+1) * Deck.card_height_gap);
				for(i = 0; i < cards.size(); i++)
				{
					GlobalVars.ge.getCurrentStage().removeTopElement(cards.get(i));
					GlobalVars.ge.getCurrentStage().addElement(cards.get(i), false);
				}
				if(((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.holder instanceof TableStack)
					((Deck)GlobalVars.ge.getCurrentStage()).lastTouched.resetCardPos();
//			}
		}
	}

	public void start(Point startPoint, Point endPoint, ArrayList<Card> cards)
	{
		this.startPoint.setLocation(startPoint);
		Helper.println("Startpoint: " + startPoint);
		this.endPoint = endPoint;
		this.cards = cards;
		
		this.distance = 0;
		this.setActive(true);
		
	}
	
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public void checkCondition(long elapsedTime) {

	}

	private void updateCards()
	{
		if(cards == null)
			return;
		
		for(i = 0; i < cards.size(); i++)
		{
//			GlobalVars.ge.getCurrentStage().removeElement(cards.get(i));
//			GlobalVars.ge.getCurrentStage().addElement(cards.get(i), false);
			cards.get(i).setLocation(curPoint);
		}
	}
	
	@Override
	public void takeAction() {
		if(active)
		{
			startPoint.getPointAtDistance(startPoint, endPoint, curPoint, distance);
//			printInfo();
			updateCards();
			distance += stepDistance ;
			if(distance > startPoint.distancePoint2Point(endPoint))
			{
				curPoint.setLocation(endPoint);
				updateCards();
				this.setActive(false);
			}
		}
	}

//	private void printInfo() {
//		Helper.println("\n\nStart Point: " + startPoint + "\n" + 
//				"End Point: " + endPoint + "\n" +
//				"Cur Point: " + curPoint + "\n" +
//				"Distance: " + distance + "\n" );
//	}

}
