package com.rhymes.game.stage.menus.stick;

import java.util.ArrayList;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class InstructionList extends ElementBase {
	
	public ArrayList<String> imagePaths=new ArrayList<String>();
	int imageNumber=7;
	int currentImageNumber=0;
	public InstructionList() {
		// TODO Auto-generated constructor stub
	}

	public InstructionList(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	public void setImage(int num){
	//	Helper.println("I am Here.........");
		if(num==1){
			if(currentImageNumber<imagePaths.size()-1){
//				Helper.println("I am Here.........");
				this.image=Helper.getImageFromAssets(imagePaths.get(currentImageNumber+1));
				currentImageNumber++;
			}
		}
		else if(num==2){
			if(currentImageNumber>0){
				this.image=Helper.getImageFromAssets(imagePaths.get(currentImageNumber-1));
				currentImageNumber--;
			}
		}
	}

	@Override
	public void init() {
		this.image=Helper.getImageFromAssets(AssetConstants.IMG_INSTRUCTION1);
		
		for(int i=0; i<imageNumber; i++){
			imagePaths.add(AssetConstants.IMG_INSTRUCTION+(i+1)+".png");
		}

	}

	@Override
	public void step(long stepTime) {
		// TODO Auto-generated method stub
		super.step(stepTime);
	}

}
