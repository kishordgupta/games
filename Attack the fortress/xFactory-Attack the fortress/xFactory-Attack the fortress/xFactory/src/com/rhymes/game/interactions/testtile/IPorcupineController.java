package com.rhymes.game.interactions.testtile;

import com.rhymes.game.entity.elements.testtile.HeroTile;
import com.rhymes.game.entity.elements.testtile.Porcupine;
import com.rhymes.game.entity.elements.testtile.TileSet;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.helpers.Helper;

public class IPorcupineController extends InteractionBase {

	private HeroTile hero;
	public IPorcupineController() {
	}

	@Override
	public void checkCondition(long elapsedTime) {

	}

	@Override
	public void takeAction() {
		for(int i = 0; i < elements.size; i++)
		{
			Porcupine p = (Porcupine) elements.get(i);
			
			if(p.isCollided())
				continue;
			
//			Helper.println("\n\n-------");
//			Helper.printKeyVal("P: id", p.getId());
//			Helper.printKeyVal("P: X", p.PorcupinePosX);
//			Helper.printKeyVal("P: Y", p.PorcupinePosY);
//			
//			Helper.printKeyVal("hero: X", hero.getX());
//			Helper.printKeyVal("hero: Y", hero.getY());
//			Helper.println("-------\n\n");
			
//			if(hero.row == p.row && hero.col == p.col ){
//				p.onCollision(hero);
//				hero.onCollisionPorcupine(p);
//			}
			if(hero.getX()==p.PorcupinePosX && hero.getY()<=(p.PorcupinePosY+TileSet.TILE_SIZE_Y*5f/6f) && hero.getY()>=p.PorcupinePosY){
					p.onCollision(hero);
					hero.onCollisionPorcupine(p);
			}
			else if(hero.getX()==p.PorcupinePosX && hero.getY()>=(p.PorcupinePosY-TileSet.TILE_SIZE_Y*5f/6f) && hero.getY()<=p.PorcupinePosY){
				p.onCollision(hero);
				hero.onCollisionPorcupine(p);
			}
			else if(hero.getY()==p.PorcupinePosY && hero.getX()<=(p.PorcupinePosX+TileSet.TILE_SIZE_X*5f/6f) && hero.getX()>=p.PorcupinePosX){
				p.onCollision(hero);
				hero.onCollisionPorcupine(p);
			}
			else if(hero.getY()==p.PorcupinePosY && hero.getX()>=(p.PorcupinePosX-TileSet.TILE_SIZE_X*5f/6f) && hero.getX()<=p.PorcupinePosX){
				p.onCollision(hero);
				hero.onCollisionPorcupine(p);
			}
			
			
//			break;
		}

	}

	public void setHero(HeroTile hero) {
		this.hero = hero;
	}

	public HeroTile getHero() {
		return hero;
	}

}
