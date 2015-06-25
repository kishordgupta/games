package com.rhymes.attackTheFortress;

import java.util.ArrayList;

import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.helpers.Helper;

public class IInteractionRangeDetect extends InteractionBase {
private ArrayList<IRangeDetection> targetElements;
private ArrayList<IRangeDetection> sourceElements;

	boolean twoWayCheck = true;

	public IInteractionRangeDetect() {
		targetElements=new ArrayList<IRangeDetection>();
		sourceElements=new ArrayList<IRangeDetection>();
	}

	@Override
	public void checkCondition(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	private boolean checkRange(ElementBase sourceElement, ElementBase targetElement, float range) {
	//	range = 2000;
		if(sourceElement.getLocation().distancePoint2Point(targetElement.getLocation()) 
				<= range)
			return true;
		else
			return false;
		
	}
	
	@Override
	public void takeAction() {
		for(int i = 0; i < sourceElements.size(); i++)
		{
			for(int j = 0; j < targetElements.size(); j++)
			{
				if(checkRange((ElementBase)sourceElements.get(i), (ElementBase)targetElements.get(j)
						,sourceElements.get(i).getRange()))
				{
					sourceElements.get(i).onRange((ElementBase) targetElements.get(j));		
				}
			}
//			Helper.println("Checking Source--->Target Range : " + sourceElements.get(i).getRange());
		}
		
		
		if(twoWayCheck)
		{
			for(int i = 0; i < targetElements.size(); i++)
			{
				for(int j = 0; j < sourceElements.size(); j++)
				{
					if(checkRange((ElementBase)targetElements.get(i), (ElementBase)sourceElements.get(j)
							,targetElements.get(i).getRange()))
					{
						targetElements.get(i).onRange((ElementBase) sourceElements.get(j));		
					}
				}
//				Helper.println("Checking Target--->Source Range : " + targetElements.get(i).getRange());
			}
		}
	}

	
	public void addTargetElements(IRangeDetection elem){
		this.targetElements.add(elem);
	}
	public void removeTargetElements(IRangeDetection elem){
		this.targetElements.remove(elem);
	}
	public void addSourceElements(IRangeDetection elem){
		this.sourceElements.add(elem);
	}
	public void removeSourceElements(IRangeDetection elem){
		this.sourceElements.remove(elem);
	}
}
