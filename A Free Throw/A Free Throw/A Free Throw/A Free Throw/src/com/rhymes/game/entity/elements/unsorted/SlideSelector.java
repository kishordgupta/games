package com.rhymes.game.entity.elements.unsorted;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.interactions.InteractionTestCallbacks;
import com.rhymes.game.interactions.inputs.InteractionLeftRight;
import com.rhymes.game.interactions.inputs.InteractionLeftRightCallbacks;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.entity.states.StateDefault;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class SlideSelector extends ElementBase implements
		InteractionTouchCallbacks {

	public ArrayList<ElementBase> elements = new ArrayList<ElementBase>();
	public ElementBase le, ce, re;
	int focusIndex;

	private float dx = 5;
	private float dy = 5;

	private float CW = 80;
	private float CH = 80;

	private float LW = 40;
	private float LH = 40;
	private ISliderEventHandler eventHandler;

	public SlideSelector(float x, float y, float width, float height,
			ArrayList<ElementBase> elements, int focusIndex, ISliderEventHandler eventHandler) {
		super(x, y, width, height, 1);
		this.elements = elements;
		this.focusIndex = focusIndex;
		
		this.LW = width/3f;
		this.CW = width/2f;
		
		this.LH = height/2f;
		this.CH = height;
		
		this.eventHandler = eventHandler; 
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
	}

	@Override
	public void init() {
		for(ElementBase e:elements)
			e.init();
		updateElements();
	}

	private void updateElements() {

		if (focusIndex > 0) {
			elements.get(focusIndex - 1).setWidth(LW);
			elements.get(focusIndex - 1).setHeight(LH);

			elements.get(focusIndex - 1).setX(x);
			elements.get(focusIndex - 1).setY(y + LH / 2f);

			if (le != null)
				GlobalVars.ge.getCurrentStage().removeElement(le);
			le = elements.get(focusIndex - 1);
			GlobalVars.ge.getCurrentStage().addElement(le, true);
		}
		
		if (focusIndex < elements.size() - 1) {
			elements.get(focusIndex + 1).setWidth(LW);
			elements.get(focusIndex + 1).setHeight(LH);

			elements.get(focusIndex + 1).setX(x + LW + CW/3f);
			elements.get(focusIndex + 1).setY(y + LH / 2f);

			if (re != null)
				GlobalVars.ge.getCurrentStage().removeElement(re);
			re = elements.get(focusIndex + 1);
			GlobalVars.ge.getCurrentStage().addElement(re, true);
		}

		elements.get(focusIndex).setWidth(CW);
		elements.get(focusIndex).setHeight(CH);

		elements.get(focusIndex).setX(x + LW / 2f);
		elements.get(focusIndex).setY(y);

		if (ce != null)
			GlobalVars.ge.getCurrentStage().removeElement(ce);
		ce = elements.get(focusIndex);
		GlobalVars.ge.getCurrentStage().addElement(ce, true);
	}


	@Override
	public void step(long stepTime) {
	}

	@Override
	public void onTouch(Point p) {
		if (this.getLeft() <= p.x && this.getX() + LW / 2f >= p.x
				&& this.getTop() >= p.y && this.getBottom() <= p.y) {
			switchToPrev();
			this.eventHandler.onSwitchPrev();
		}
		else if (this.getX() + LW / 2f <= p.x
				&& this.getX() + LW / 2f + CW >= p.x && this.getTop() >= p.y
				&& this.getBottom() <= p.y) {
//			Helper.println("Slide hit at index: " + focusIndex);
			this.eventHandler.onSlideHit();
		}
		else if (this.getLeft() + LW * 2f <= p.x && this.getX() + LW*3f >= p.x
				&& this.getTop() >= p.y && this.getBottom() <= p.y) {
			switchToNext();
			this.eventHandler.onSwitchNext();
//			Helper.println("Switching to next");
		} 

	}


	@Override
	public void render() {
		super.render();
	}

	public void switchToNext() {
		if (focusIndex == elements.size() - 1)
			return;
		focusIndex++;

		updateElements();
	}

	public void switchToPrev() {
		if (focusIndex == 0)
			return;
		focusIndex--;

		updateElements();
	}
	
	public ElementBase getSelected()
	{
		return elements.get(focusIndex);
	}
	
	public int getFocusIndex()
	{
		return focusIndex;
	}
}
