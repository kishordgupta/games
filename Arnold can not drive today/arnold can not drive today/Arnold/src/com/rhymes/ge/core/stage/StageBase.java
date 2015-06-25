package com.rhymes.ge.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.buttonlabel.Text;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.interactions.InteractionCallbacks;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public abstract class StageBase {
	protected BitmapFont font;
	protected Array<ElementBase> elements;
	protected Array<ElementBase> topElements;
	protected Array<InteractionBase> interactions;
	protected Array<InteractionBase> gameControlInteractions;
	protected Array<Object> toBeRemoved;
	protected int gameState = 0;
	
	

	public BitmapFont getFont()
	{
		return font;
	}
	
	
	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public StageBase()
	{
		elements = new Array<ElementBase>();
		topElements = new Array<ElementBase>();
		interactions = new Array<InteractionBase>();
		gameControlInteractions = new Array<InteractionBase>();
		toBeRemoved = new Array<Object>();
		this.gameState = 0;
	}
	
	public abstract void loadElements();
	
	/*
	 * Returns all the assets of this Stage in AssetPack object
	 */
	public abstract AssetPack getAssets(AssetPack assetPack);
	
	
	/*
	 * Returns all the assets of this Stage in AssetPack object
	 */
	public AssetPack getElementsAssets(AssetPack assetPack){		
//		AssetPack assetPack = new AssetPack();
		
		for(ElementBase element: elements)
		{
			element.getAssets(assetPack);
		}
		
		return assetPack;
	}
	
	

	InteractionBase interaction;
	ElementBase element;
	long time, t ;
	public void step(long stepTime) {
//		time = System.currentTimeMillis();
		GlobalVars.ge.getRenderer().startRendering();
//		t = System.currentTimeMillis();
		for(int i = 0; i < elements.size; i++)
		{
//			time = System.currentTimeMillis();
			element = elements.get(i);
			element.render();
			
			if(this.gameState==GameState.PLAYING)
				element.step(stepTime);
			
//			Helper.println("Element Step Time: " + (System.currentTimeMillis() - time) + " For Element: " + element.getClass().getName());
		}
		
//		Helper.println("\n\n\nTotal Elements Render-Step Time: " + (System.currentTimeMillis() - t));
//		t = System.currentTimeMillis();
		for(int i = 0; i < topElements.size; i++)
		{
			element = topElements.get(i);
			element.render();
			if(this.gameState==GameState.PLAYING)
				element.step(stepTime);
//			Helper.println("Top Element Step Time: " + (System.currentTimeMillis() - time) 
//					+ " For Element: " + element.getClass().getName());
		}
		

//		Helper.println("TopElements Render-Step Time: " + (System.currentTimeMillis() - t));
		
//		t = System.currentTimeMillis();
		GlobalVars.ge.getRenderer().stopRendering();
		
//		Helper.println("Stop rendering" + (System.currentTimeMillis() - t));
//		t = System.currentTimeMillis();
		
		if(this.gameState==GameState.PLAYING){
			for(int i = 0; i < interactions.size; i++)
			{
				try {
					interaction = interactions.get(i);
					interaction.checkCondition(stepTime);
					interaction.takeAction();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
//		Helper.println("Interactions Time: " + (System.currentTimeMillis() - t));
//		t = System.currentTimeMillis();
		
		for(int i = 0; i < gameControlInteractions.size; i++)
		{
			try {
				interaction = gameControlInteractions.get(i);
				interaction.checkCondition(stepTime);
				interaction.takeAction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.tick(stepTime);
		updateGame();
//		Helper.println("Rest: " + (System.currentTimeMillis() - t));
//		Helper.println("Stage Step Time: " + (System.currentTimeMillis() - time));
	}

	protected void updateStage(){
		if(this.gameState == GameState.RELOADING)
		{
			Helper.println("\n\nReloading...");
			this.elements.clear();
			this.interactions.clear();
			this.topElements.clear();
			this.gameControlInteractions.clear();
			this.loadElements();
			System.gc();
			this.init();			
			return;
		}		
	}
	private void updateGame() {
		cleanRemoveList();
		updateStage();
	}

	public void init() {
		for(ElementBase element: elements)
		{
			element.init();
		}
		for(ElementBase element: topElements)
		{
			element.init();
		}
		this.setGameState(GameState.PLAYING);
		
		Helper.println("INit: gameState: " + this.getGameState());
	}	

	/**
	 * @param element
	 * This would add the element to the element list directly 
	 */
	public void addElement(ElementBase element)
	{
		if(element != null){
			element.init();
		}
		this.elements.add(element);
	}
	
	public void addElement(ElementBase element, boolean shallInit)
	{
		if(element != null && shallInit){
			element.init();
		}
		this.elements.add(element);
	}
	
	public void addTopElement(ElementBase element, boolean shallInit)
	{
		if(element != null && shallInit){
			element.init();
		}
		this.topElements.add(element);
	}
	
	/**
	 * @param element
	 * This would remove it from the element list directly 
	 */
	public void removeElement(ElementBase element)
	{
		this.elements.removeValue(element, true);
	}

	
	public void removeTopElement(ElementBase element)
	{
		this.topElements.removeValue(element, true);
	}

	
	Object obj;
	/**
	 * This removes all the elements and interactions that are posted to the remove list
	 */
	public void cleanRemoveList()
	{		
		for (int i = 0; i < toBeRemoved.size; i++) {
			obj = toBeRemoved.get(i);
			if(obj instanceof ElementBase){
				this.elements.removeValue((ElementBase)obj, true);
				this.topElements.removeValue((ElementBase)obj, true);
			}
			else if(obj instanceof InteractionBase){
				this.interactions.removeValue((InteractionBase)obj, true);
				this.gameControlInteractions.removeValue((InteractionBase)obj, true);
			}
		}
		this.toBeRemoved.clear();
	}
	
	/**
	 * This function puts the passed element to the to be remove list.
	 * And they are removed next time the clean() method is called.
	 * Normally that is called after the end of each step
	 */
	public void postToRemoveList(ElementBase element)
	{
		this.toBeRemoved.add(element);
	}
	
	/**
	 * This function puts the passed element to the to be remove list.
	 * And they are removed next time the clean() method is called.
	 * Normally that is called after the end of each step
	 */
	public void postToRemoveList(InteractionBase interaction)
	{
		this.toBeRemoved.add(interaction);
	}
	

	/**
	 * Unsubscribes the passed element from all the interactions it was subscribed
	 * And also passes the element to the "Remove List"
	 * Objects in the remove list are removed removed next time the cleanRemoveList() method is called.
	 * Normally that is called after the end of each step
	 *   
	 */
	public void postDestroyed(ElementBase element)
	{
		this.toBeRemoved.add(element);
		if(element instanceof InteractionCallbacks)
		{
			for(InteractionBase interaction: interactions)
				interaction.unSubscribe((InteractionCallbacks) element);
		}
	}
	
	
	/**
	 * @param subscriber the object that wants to subscribe to the passed interaction
	 * @param interaction return the class of the interaction
	 * For example:
	 * for a interaction named InteractionTest....get the class name using:
	 * InteractionTest.class
	 */
	public void subscribeToInteraction(InteractionCallbacks subscriber, Class<? extends InteractionBase> interaction)
	{
		for(InteractionBase i: interactions)
		{
			if(i.getClass() == interaction)
			{
				i.subscribe(subscriber);
//				Helper.println("Subscribed to: " + i.getClass().getName());
				return;
			}
		}
	}
	
	
	public void subscribeToControllingInteraction(InteractionCallbacks subscriber, Class<? extends InteractionBase> interaction)
	{
		for(InteractionBase i: gameControlInteractions)
		{
			if(i.getClass() == interaction)
			{
				i.subscribe(subscriber);
//				Helper.println("Subscribed to: " + i.getClass().getName());
				return;
			}
		}		
	}
	
	/**
	 * @param subscriber the object that wants to unsubscribe from the passed interaction
	 * @param interaction return the class of the interaction
	 * For example:
	 * for a interaction named InteractionTest....get the class name using:
	 * InteractionTest.class
	 */
	public void unSubscribeToInteraction(InteractionCallbacks subscriber, Class<? extends InteractionBase> interaction)
	{
		for(InteractionBase i: interactions)
		{
			if(i.getClass() == interaction)
			{
				i.unSubscribe(subscriber);
				return;
			}
		}		
	}

	
	public void unSubscribeToControllingInteraction(InteractionCallbacks subscriber, Class<? extends InteractionBase> interaction)
	{
		for(InteractionBase i: gameControlInteractions)
		{
			if(i.getClass() == interaction)
			{
				i.unSubscribe(subscriber);
				return;
			}
		}		
	}
	
	public Array<ElementBase> getElemensByType(Class <? extends ElementBase> elementType)
	{
		Array<ElementBase> ret = new Array<ElementBase>();
		for(ElementBase e:elements)
		{
			if(e.getClass() == elementType)
				ret.add(e);
		}
		return ret;
	}
	
	/**
	 * This is called at each step just before rendering
	 * @param stepTime Time taken by the previous step
	 */
	public abstract void tick(long stepTime);

	public void pause() {
		this.gameState = GameState.PAUSED;
	}
	

	public void resume()
	{
		this.gameState = GameState.PLAYING;
		font = TrueTypeFontFactory.createBitmapFont  
				(Gdx.files.internal(AssetConstants.font_arnold), Text.getFrontChars(),
						12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
				
				font.setColor(1f, 1f, 1f, 1f);
				font.setScale(1, 1);
	}
	
	public void finish()
	{
	}
	
	public void reload()
	{
		this.gameState = GameState.RELOADING;
	}

}
