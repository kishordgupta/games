package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.attackTheFortress.xmlTowerReader;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonUpgradeDamage extends Button {

	public Text damageText;
	public String info="";
	public BitmapFont font;
	public int currentdamage;
	public Integer buydamage=0;
	public Integer costGold=0;
	public int towertype=-1;
	public ButtonUpgradeDamage(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public ButtonUpgradeDamage(float x, float y, float width, float height,
			int zIndex, String imagePath, BitmapFont font) {
		super(x, y, width, height, zIndex, imagePath);

		if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower() != null)	
		{
			towertype=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTowerType();
			currentdamage=(int) ((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getDamage();
			Helper.println("Type "+towertype);
		}
		this.font=font;

	}
		@Override
	public void onEvent(Point htiPoint) {
		if(this.checkHit(htiPoint)){
			int count =((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getDamageUpdateCount();
			if(count>=xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.size())
				return;
//			buydamage=xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.get(count);
//			costGold=xmlTowerReader.towers.get(towertype-1).damageUpgradeCost.get(count);
			if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()<costGold)
				return;
				currentdamage+=buydamage;
				
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().setDamage(currentdamage);
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setGold(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()-costGold);
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().setTotalValue(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getTotalValue()+costGold);
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).gettowerStatView().getSellButton().setInfo(costGold);
				count++;
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().setDamageUpdateCount(count);
				if(count<xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.size()){
				buydamage=xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.get(count);
				costGold=xmlTowerReader.towers.get(towertype-1).damageUpgradeCost.get(count);
				damageText.setText(currentdamage+"   "+buydamage+"D  for  "+costGold+"G");
				}
				else
				{
				damageText.setText(currentdamage+"   NO MORE UPDT");
				}
			
		Helper.println("btnUpgrade: damage");	
		}
	}

	public void testString(){
		damageText=new Text(this.x-15f*LevelInfo.ratioX,this.y+7f*LevelInfo.ratioY,	0.2f*LevelInfo.ratioX, 0.2f*LevelInfo.ratioY, this.font, info);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(damageText);
	}

	@Override
	public void init() {
		int count =((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().getDamageUpdateCount();
		if(count<xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.size())
		{
		buydamage=xmlTowerReader.towers.get(towertype-1).damageUpgradeValue.get(count);
		costGold=xmlTowerReader.towers.get(towertype-1).damageUpgradeCost.get(count);
		info=currentdamage+"   "+buydamage+"D  for  "+costGold+"G";
		}
		else
		{
			info=currentdamage+"   NO MORE UPDT";	
		}
		
		testString();

		super.init();
	}

	public void Destroy(){
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(damageText);
	}


}
