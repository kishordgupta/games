package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonUpgradeLife extends Button {
	public Text lifeText;
	public String info="";
	public BitmapFont font;
	public int currentlife;
	public int buylife;
	public int costGold;
	public int towertype=-1;
	public ButtonUpgradeLife(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean get_pressed() {		
		return super.get_pressed();
	}

	@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()<costGold)
				return;
			if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getLife()<((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getMaxLife())
			{
				currentlife+=buylife;
				if(currentlife>((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getMaxLife())
					currentlife=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getMaxLife();
				
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().setLife(currentlife);
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setGold(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()-costGold);
				lifeText.setText(currentlife+"   "+buylife+"L  for  "+costGold+"G");
				
			}
		Helper.println("btnUpgrade: life");	
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
			
			if(towertype==1){
				buylife=10;
				costGold=2;}
			else if(towertype==2){
				buylife=15;
				costGold=4;
			}				
			else if(towertype==3){
				buylife=20;
				costGold=6;
				}	
			else if(towertype==4){
				buylife=25;
				costGold=8;
				}	
			else if(towertype==5){
				buylife=30;
				costGold=10;
				}
			else if(towertype==6){
				buylife=35;
				costGold=12;
				}
			info=currentlife+"   "+buylife+"L  for  "+costGold+"G";
		testString();
		super.init();
		
	}

	public void testString(){
		lifeText=new Text(this.x-15f*LevelInfo.ratioX,this.y+7f*LevelInfo.ratioY,	0.2f*LevelInfo.ratioX, 0.2f*LevelInfo.ratioY, this.font, info);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(lifeText);
	}

	public ButtonUpgradeLife(float x, float y, float width, float height,
			int zIndex, String imagePath, BitmapFont font) {
		super(x, y, width, height, zIndex, imagePath);
		
		if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower() != null)	
		{
			towertype=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTowerType();
			currentlife=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getLife();
			Helper.println("Type "+towertype);
		}
		this.font=font;
		//percentText.setText(text)
		
	}
	public void Destroy(){
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(lifeText);
	}
}
