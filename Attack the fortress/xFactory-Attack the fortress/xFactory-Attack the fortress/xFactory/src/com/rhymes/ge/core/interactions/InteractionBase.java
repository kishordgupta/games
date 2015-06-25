package com.rhymes.ge.core.interactions;

import com.badlogic.gdx.utils.Array;

public abstract class InteractionBase {
	protected Array<InteractionCallbacks> elements;
	protected boolean condition = false;
	
	public InteractionBase()
	{
		this.elements = new Array<InteractionCallbacks>(); 
	}
	public void subscribe(InteractionCallbacks element)
	{ 
		if(elements == null)
			elements = new Array<InteractionCallbacks>();

		if(!elements.contains(element, false))
			elements.add(element);
		
		
//		Log.d("Interaction", "Subsribed new element: " + element + ": " + element.hashCode() );
	}
	public void unSubscribe(InteractionCallbacks element)
	{
		if(elements != null)
			elements.removeValue(element, true);
//		Log.d("Interaction", "Unsubsribed element: " + element + ": " + element.hashCode() );
	}
	public abstract void checkCondition(long elapsedTime);
	public abstract void takeAction();	
	public Array<InteractionCallbacks> getElements() {
		return elements;
	}
	public void setElements(Array<InteractionCallbacks> elements) {
		this.elements = elements;
	}

}
