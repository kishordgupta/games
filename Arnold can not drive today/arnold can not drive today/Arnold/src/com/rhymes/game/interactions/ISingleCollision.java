package com.rhymes.game.interactions;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.entity.elements.burntherope.Boat;
import com.rhymes.game.entity.elements.burntherope.Joint;
import com.rhymes.game.entity.elements.burntherope.Pearl;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.helpers.Helper;

public class ISingleCollision extends InteractionBase {

	Array<ElementBase> heroes;
	public ISingleCollision()
	{
		super();
		this.heroes = new  Array<ElementBase>();
	}
	
	public void addHero(ElementBase hero)
	{
		this.heroes.add(hero);
	}
	
	public void removeHero(ElementBase hero)
	{
		this.heroes.removeValue(hero, true);
	}
	
	@Override
	public void checkCondition(long elapsedTime) {

	}
	
	private ElementBase leftElement, rightElement;
	private ElementBase topElement, bottomElement;

	/**
	 * Search through the elements subscribed and notifies
	 * all the elements that collided with the hero
	 */
	@Override
	public void takeAction() {
		ElementBase e;
		if(elements.size == 0)
			return;
		for(ElementBase hero:heroes){		
//			for(InteractionCallbacks element:this.elements)
			for(int i = 0; i < elements.size; i++)
			{
				InteractionCallbacks element = elements.get(i);
				e = (ElementBase) element;
				if(((ICSingleCollisionCallbacks) e).isCollided())
					continue;
				
				if(hero.getLeft() < e.getLeft()){
					leftElement = hero;
					rightElement = e;
				}
				else{
					rightElement = hero;
					leftElement = e;
				}
				
				if(hero.getTop() > e.getTop()){
					topElement = hero;
					bottomElement = e;
				}
				else{
					topElement = e;
					bottomElement = hero;
				}
				
//				if(leftElement.getLeft()- leftElement.getWidth()/2f >= rightElement.getLeft() - rightElement.getWidth()/2f
//						&& topElement.getBottom()- topElement.getHeight()/2f <= bottomElement.getBottom()- bottomElement.getHeight()/2f)
				if(leftElement.getLeft()+12 >= rightElement.getLeft() 
						&& topElement.getBottom() <= bottomElement.getBottom()+12)
				{
					if(!((Boat)hero).checkCollision((ICSingleCollisionCallbacks) element))
					{
						continue;
					}
					
					
//					Helper.println("Hero: " + hero.getLocation().toString());
//					Helper.println("Other element: " + e.getLocation().toString());
//						Helper.println("\nCollision occured : " + hero.getClass() + " <=> " + e.getClass());
						((ICSingleCollisionCallbacks)hero).onCollision((ICSingleCollisionCallbacks) e);
						((ICSingleCollisionCallbacks)e).onCollision((ICSingleCollisionCallbacks) hero);
						
						if(e instanceof Joint)
							return;
				}
			}
		}
	}
}
