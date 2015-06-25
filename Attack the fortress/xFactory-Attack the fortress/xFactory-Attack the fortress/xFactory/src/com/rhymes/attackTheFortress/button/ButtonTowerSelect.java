package com.rhymes.attackTheFortress.button;

import com.badlogic.gdx.Gdx;
import com.rhymes.attackTheFortress.AttackTheFortressLevel;
import com.rhymes.attackTheFortress.LevelInfo;
import com.rhymes.attackTheFortress.RangeSelector;
import com.rhymes.attackTheFortress.TSVInfo;
import com.rhymes.attackTheFortress.Tower;
import com.rhymes.attackTheFortress.xmlEnemyReader;
import com.rhymes.attackTheFortress.input.ICInputListener;
import com.rhymes.attackTheFortress.input.InteractionInputListener;
import com.rhymes.game.data.AssetConstants;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class ButtonTowerSelect extends Button implements ICInputListener{
private	TSVInfo tsvInfo;
private RangeSelector rangeSelect;

	public ButtonTowerSelect(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
		setTouchHandler();
	}

	public ButtonTowerSelect(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
		setTouchHandler();
	}
	
	
	private void setTouchHandler()
	{	
		GlobalVars.ge.getCurrentStage().subscribeToInteraction(this, InteractionInputListener.class);		
	}
	

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	Point p = new Point();
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		p.setLocation(x, Gdx.graphics.getHeight() - y);
		if(this.checkHit(p)){
			//		Helper.println("Coming Here....");
					float X=this.x-150f*LevelInfo.ratioX;
					float Y=0f;   
					if(p.y>=this.y && p.y<=this.y+40f*LevelInfo.ratioY){
						Helper.println("Tower 6");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y-40f*LevelInfo.ratioY;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1, 
								AssetConstants.IMG_TSV_TOWER6,6);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					else if(p.y>=this.y+40f*LevelInfo.ratioY && p.y<=this.y+80f*LevelInfo.ratioY){
						Helper.println("Tower 5");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1,
								AssetConstants.IMG_TSV_TOWER5,5);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					else if(p.y>=this.y+80f*LevelInfo.ratioY && p.y<=this.y+120f*LevelInfo.ratioY){
						Helper.println("Tower 4");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y+40f*LevelInfo.ratioY;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1, 
								AssetConstants.IMG_TSV_TOWER4,4);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					else if(p.y>=this.y+120f*LevelInfo.ratioY && p.y<=this.y+160f*LevelInfo.ratioY){
						Helper.println("Tower 3");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y+80f*LevelInfo.ratioY;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1,
								AssetConstants.IMG_TSV_TOWER3,3);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					else if(p.y>=this.y+160f*LevelInfo.ratioY && p.y<=this.y+200f*LevelInfo.ratioY){
						Helper.println("Tower 2");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y+120f*LevelInfo.ratioY;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1,
								AssetConstants.IMG_TSV_TOWER2,2);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					else if(p.y>=this.y+200f*LevelInfo.ratioY && p.y<=this.y+240f*LevelInfo.ratioY){
						Helper.println("Tower 1");
						if(tsvInfo!=null)
							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
						
						Y=this.y+160f*LevelInfo.ratioY;
						tsvInfo =new TSVInfo(X, Y, 150f*LevelInfo.ratioX, 80f*LevelInfo.ratioY, 1,
								AssetConstants.IMG_TSV_TOWER1,1);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(tsvInfo);
					}
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setcurrentTSVInfo(tsvInfo);
					
//					X=this.x+4f*LevelInfo.ratioY;
//					Y=Y+40f*LevelInfo.ratioY;
//					rangeSelect =new RangeSelector(X, Y,tsvInfo.towerNumber);
//					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(rangeSelect);
					rangeSelect =new RangeSelector(p.x, p.y,tsvInfo.towerNumber);
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(rangeSelect);
					
				}
				else
				{
					
					
					
					//outside of tower select view
					if(tsvInfo!=null){
						
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
					
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(rangeSelect);
					tsvInfo=null;
					}
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
//					else
//					{						
//						if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower()!=null)
//						{
//							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().towerStat.Destroy();
//							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getCurrentTower().showStat=true;
//							((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setCurrentTower(null);
//						}
//					}
				}
				
					
		return false;
	}

	Point rp = new Point();
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		y=Gdx.graphics.getHeight()-y;
		if(rangeSelect!=null){
			rangeSelect.setX(x);
			rangeSelect.setY(y
);
			rp.setLocation(x, y);
			
			 if(outBoundary(x, y)==true || xmlEnemyReader.path.checkRange(rp, 30 * LevelInfo.ratioX)==true
					 || onTower(x, y)==true || onPauseButton(x, y)==true)
				 rangeSelect.setCorrectPosition(false);
			 else
				 rangeSelect.setCorrectPosition(true);

						
//			 if(xmlEnemyReader.path.checkRange(rp, 30 * LevelInfo.ratioX)){
//					rangeSelect.setCorrectPosition(false);
//				}
			 
//			 rangeSelect.setCorrectPosition(!onTower(x, y));
			if((x<this.x || x>this.x+this.width
					|| y<this.y || y>this.y+this.height) ){
				if(tsvInfo!=null){
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(tsvInfo);
				tsvInfo=null;
				}
				
//				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
				setRender=false;
			}
			
		}
		return false;
	}
	public boolean onPauseButton(int x, int y){
		boolean b=false;
		
			ButtonPause B=((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getButtonPause();
			
			if(x+(12f*LevelInfo.ratioX)>B.getX() && x-(12f*LevelInfo.ratioX)<B.getX()+B.getWidth() &&
					y-(12f*LevelInfo.ratioY)<B.getY()+B.getHeight() && y+(38f*LevelInfo.ratioX)>B.getY()){
				b= true;
			}		
		
		return b;	
	}
public boolean onTower(int x, int y){
	boolean b=false;
	for(int i=0;i<((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).towerList.size();i++){
		Tower t=((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).towerList.get(i);
		
		if(x+(12f*LevelInfo.ratioX)>t.getX() && x<t.getX()+(45f*LevelInfo.ratioX) &&
				y<t.getY()+(64f*LevelInfo.ratioY) && y+(15f*LevelInfo.ratioY)>t.getY()){
			b= true;
		}		
	}
	return b;	
}

public boolean outBoundary(int x, int y){
	boolean b=false;
			if(y>Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/15f+35f*LevelInfo.ratioY) || y<20f*LevelInfo.ratioY ||
					x<24f*LevelInfo.ratioX || x> Gdx.graphics.getWidth()-24f*LevelInfo.ratioX){
			b= true;		
	}
	return b;	
}
	@Override
	public boolean touchMoved(int x, int y) {
		// NO NEED
		return false;
	}
private boolean setRender=true;
	@Override
	public void render() {
		if(setRender)
		super.render();
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		y=Gdx.graphics.getHeight()-y;
		if((x<this.x || x>(this.x+this.width) || y<this.y || y>(this.y+this.height)) && y<Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/15f+24f*LevelInfo.ratioY) && rangeSelect.getCorrectPosition()==true){
		if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getcurrentTSVInfo()!=null)
		{
			p.x=x-15f*LevelInfo.ratioX;
			p.y=y-10f*LevelInfo.ratioY;
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).bringTowerAtPoint(p);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setcurrentTSVInfo(null);	
			tsvInfo=null;
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		}
		
		}
		else{
			if(setRender==false  && y<Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/15f+24f*LevelInfo.ratioY) && rangeSelect.getCorrectPosition()==true){
				if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getcurrentTSVInfo()!=null)
				{
					p.x=x-15f*LevelInfo.ratioX;
					p.y=y-10f*LevelInfo.ratioY;
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).bringTowerAtPoint(p);
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setcurrentTSVInfo(null);
					tsvInfo=null;
					((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
					
				}
			}			
		}
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(rangeSelect);
		
		return false;
	}


}
