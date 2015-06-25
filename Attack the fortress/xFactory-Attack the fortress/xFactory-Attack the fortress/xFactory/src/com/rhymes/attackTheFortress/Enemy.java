package com.rhymes.attackTheFortress;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants; //import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.animations.common.AniLoop;
import com.rhymes.game.interactions.rangeTraversal.ICRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class Enemy extends ElementBase implements IRangeDetection, IBulletHit, ICRangePathTraversal {
	private int enemyType = -1;
	private int enemySpeed = 0;
	private int enemyLife = 0;
	private int enemyDamage = 0;
	private int enemyRange = 0;
	private int enemyvalue = 0;
	private int enemyshooting = 0;
	private int maxLife=0;
	private Texture tex;
	private InfoRangeTraversal infoRangeTraversal;
	public Enemy(int type, int speed, int life, int damage, int range,
			int value, int shooting) {
		this.enemyType = type;
//		this.enemySpeed = speed;
		this.enemyDamage = damage;
		this.enemyRange = (int) (range*LevelInfo.ratioX/2f);
		this.enemySpeed = (int)(speed*LevelInfo.ratioX);
		this.enemyLife = life;
//		this.enemyRange = range;
		this.enemyshooting = shooting;
		this.enemyvalue = value;
		this.maxLife=life;
	}

	public Enemy(int type, int speed, int life, int damage, int range,
			int value, int shooting, InfoRangeTraversal infoRangeTraversal) {
		this.setInfoRangeTraversal(infoRangeTraversal);
		this.enemyType = type;		
		this.enemyDamage = damage;
		this.enemyLife = life;
		this.enemyRange = (int) (range*LevelInfo.ratioX/2f);
		this.enemySpeed = (int)(speed*LevelInfo.ratioX);
		this.enemyRange = (int) (range);
		this.enemySpeed = (int)(speed);
		this.enemyshooting = shooting;
		this.enemyvalue = value;
		this.maxLife=life;
	}


private boolean isShow=true;
	@Override
	public void render() {

//		GlobalVars.ge.getScreen().getBatch().draw(tex, this.x, this.y,
//				this.width, this.height, 0, 0, tex.getWidth(), tex.getHeight(),
//				false, false);
		if(!isShow)
			return;
		
		GlobalVars.ge.getRenderer().render(image, x-width/2f, y - height/2f, 
				width, height, width/2f, height/2f, this.rotateAngle+90);

//		super.render();
	}

	private double skiptime = 500;
	private double elapsedtime = 0;
	private int i = 0;

	@Override
	public void step(long stepTime) {
		if(this.x>9f*LevelInfo.ratioX && this.x<(Gdx.graphics.getWidth()-9f*LevelInfo.ratioX) && this.y>8f*LevelInfo.ratioY && y<Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/15f+10f*LevelInfo.ratioY))
			isShow=true;
		else 
			isShow=false;
		
		aniLoop.step(stepTime);
		EnemyKilled();
		super.step(stepTime);
	}
	public void EnemyPassOver(long stepTime){
		elapsedtime += stepTime;
		if (elapsedtime > skiptime) {
			if (i >= xmlEnemyReader.road.nodes.size()) {
				((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
						.setLife(((AttackTheFortressLevel) GlobalVars.ge
								.getCurrentStage()).getLife() - 1);
				if(((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage()).getLife()<0)
					((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage()).setLife(0);
				destroy();
				setEnemyNumber();
				return;
			}
			elapsedtime = 0;
			
			
			this.x = xmlEnemyReader.road.nodes.get(i).getX()-this.width/2f;
			// Helper.println("X: "+this.x);
			this.y = xmlEnemyReader.road.nodes.get(i).getY()-this.height/2f;
			
			
			// Helper.println("Y: "+this.y);
			i++;
			// Helper.println("i: "+i);
			

		}

	}
	public void EnemyKilled(){
		if (this.enemyLife <= 0) {
			blow = new Blow(this.x, this.y, 2);
			((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
					.addElement(blow);
//			Helper.println("Blowing enemy");
			PlaySound();
			destroy();
			
			((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
					.setGold(((AttackTheFortressLevel) GlobalVars.ge
							.getCurrentStage()).getGold()
							+ this.enemyvalue);
			((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
			.setEnemyKilled(((AttackTheFortressLevel) GlobalVars.ge
					.getCurrentStage()).getEnemyKilled()
					+ 1);
			setEnemyNumber();
	
		}
	
	}
	public void setEnemyNumber(){
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage())
		.setEnemyLeft(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage())
						.getEnemyLeft()-1);

	}
	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	LifeBar lifeBarYellow;
	LifeBar lifeBarGreen;
	public void addLifeBar(){
		lifeBarYellow=new LifeBar(this.x+5f*LevelInfo.ratioX,this.y+40f*LevelInfo.ratioY,15f*LevelInfo.ratioY,
				2f*LevelInfo.ratioY,2,this,2);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(lifeBarYellow);
		lifeBarGreen=new LifeBar(this.x+5f*LevelInfo.ratioX,this.y+40f*LevelInfo.ratioY,15f*LevelInfo.ratioY,
				2f*LevelInfo.ratioY,1,this,2);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(lifeBarGreen);
	}


	@Override
	public void init() {
		super.width = this.width = 30f * LevelInfo.ratioX;
		super.height = this.height = 30f * LevelInfo.ratioY;
		
		super.x = this.x = xmlEnemyReader.road.nodes.get(0).getX()-this.width/2f;
		super.y = this.y = xmlEnemyReader.road.nodes.get(0).getY()-this.height/2f;
		
//		this.image = Helper.getImageFromAssets(AssetConstants.ENEMY_1_1);
//		this.tex = this.image.getTexture();
//		enemyType=9;
		setImage();
		setSize();
		addLifeBar();
		SetAnimation();

	}

	public void	setImage(){	 
		String imagePath=AssetConstants.ENEMY+(this.enemyType+1)+"_1_iPad.png";
		this.image = Helper.getImageFromAssets(imagePath);
//		this.tex = this.image.getTexture();	
	}
	
	public void destroy() {
		
		((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage()).destroyEnemey(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(lifeBarYellow);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(lifeBarGreen);

	}
	public void PlaySound(){
		if(this.enemyType==0)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_0);
		else if(this.enemyType==1)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_1);
		else if(this.enemyType==2)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_2);
		else if(this.enemyType==3)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_3);
		else if(this.enemyType==4)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_4);
		else if(this.enemyType==5)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_5);
		else if(this.enemyType==6)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_6);
		else if(this.enemyType==7)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_7);
		else if(this.enemyType==8)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_8);
		else if(this.enemyType==9)
			StartupInfo.sound_handler.playSound(soundType.SOUND_CRY_9);
	}
	private AniLoop aniLoop;
	public void SetAnimation(){
		int listLength=0;
		if(this.enemyType==0)
			listLength=8;
		else if(this.enemyType==1)
			listLength=20;
		else if(this.enemyType==2)
			listLength=10;
		else if(this.enemyType==3)
			listLength=10;
		else if(this.enemyType==4)
			listLength=13;
		else if(this.enemyType==5)
			listLength=20;
		else if(this.enemyType==6)
			listLength=10;
		else if(this.enemyType==7)
			listLength=15;
		else if(this.enemyType==8)
			listLength=14;
		else if(this.enemyType==9)
			listLength=18;
		TextureRegion[] image0 = new TextureRegion[listLength];
		for(int i =1 ; i <= listLength; i++) 
		{
			image0[i-1] = Helper.getImageFromAssets(AssetConstants.ENEMY+(this.enemyType+1)+"_"+i+"_iPad.png");
		}
		
		aniLoop = new AniLoop(this, image0, true);
		aniLoop.init();
	}
	
	public void setSize(){
		if(this.enemyType==0){
			this.width=35f*LevelInfo.ratioX;
			this.height=11f*LevelInfo.ratioX;
		}
		else if(this.enemyType==1){
			this.width=30f*LevelInfo.ratioX;
			this.height=25f*LevelInfo.ratioX;
		}
			
		else if(this.enemyType==2){
			this.width=30f*LevelInfo.ratioX;
			this.height=30f*LevelInfo.ratioX;
		}
			
		else if(this.enemyType==3)
		{
			this.width=30f*LevelInfo.ratioX;
			this.height=30f*LevelInfo.ratioX;
		}
		else if(this.enemyType==4)
		{
			this.width=35f*LevelInfo.ratioX;
			this.height=35f*LevelInfo.ratioX;
		}
		else if(this.enemyType==5)
		{
			this.width=38f*LevelInfo.ratioX;
			this.height=35f*LevelInfo.ratioX;
		}
		else if(this.enemyType==6)
		{
			this.width=40f*LevelInfo.ratioX;
			this.height=30f*LevelInfo.ratioX;
		}
		else if(this.enemyType==7)
		{
			this.width=35f*LevelInfo.ratioX;
			this.height=30f*LevelInfo.ratioX;
		}

		else if(this.enemyType==8)
		{
			this.width=30f*LevelInfo.ratioX;
			this.height=25f*LevelInfo.ratioX;
		}
		else if(this.enemyType==9)
		{
			this.width=40f*LevelInfo.ratioX;
			this.height=30f*LevelInfo.ratioX;
		}
	}
	
	private Blow blow;

	public void attackByBullet(Bullet bullet) {
		blow = new Blow(bullet.getX(), bullet.getY(), 1);
		((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
				.addElement(blow);
		this.enemyLife -= bullet.getDamage();

	}
	Random rnd=new Random();
	public void attackOnTower(Tower tower) {
//		Helper.println("Attaching on tower");
		
		if (this.enemyshooting == 1 || true) {
			StartupInfo.sound_handler.playSound(soundType.SOUND_TOWER_LAUNCH);
			float targetX=rnd.nextInt((int) (tower.getWidth()));
			float targetY=rnd.nextInt((int) (tower.getHeight()));
			Bullet bul = new Bullet(this.x, this.y, tower.getX()+targetX-(10f*LevelInfo.ratioX),
					tower.getY()+targetY-(10f*LevelInfo.ratioY),
					5, this.enemyDamage, this.enemySpeed, tower);
			((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
					.addElement(bul);
//			Helper.println("bullet added from enemey", true);
		}
	}
	float bulletSkipTime = 1000;
	float currentBulletSkipTime = bulletSkipTime;

	@Override
	public void onRange(ElementBase target) {
		//----skipping---
		currentBulletSkipTime+=GlobalVars.ge.stepTime;
		if(bulletSkipTime>currentBulletSkipTime)
			return;
		currentBulletSkipTime = 0;
		if(target!= null)
			attackOnTower((Tower) target);
//		Helper.println("Enemy: On Range()");
	}

	@Override
	public float getRange() {

		return this.enemyRange;
	}

	@Override
	public void onBulletHit(Bullet bullet) {
		attackByBullet(bullet);
	}

	public float getLife() {
		
		return this.enemyLife;
	}
	private Point p=new Point();
	public Point getlocation(){
		p.setX(this.x);
		p.setY(this.y);
		return p;
	}

	public float getMaxLife() {
		
		return this.maxLife;
	}

	public void setInfoRangeTraversal(InfoRangeTraversal infoRangeTraversal) {
		this.infoRangeTraversal = infoRangeTraversal;
	}

	public InfoRangeTraversal getInfoRangeTraversal() {
		return infoRangeTraversal;
	}

	@Override
	public void onPathTraverseFinished() {
		Helper.println("Enemey Passed!");
		((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage())
		.setLife(((AttackTheFortressLevel) GlobalVars.ge
				.getCurrentStage()).getLife() - 1);
		if(((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage()).getLife()<0)
			((AttackTheFortressLevel) GlobalVars.ge.getCurrentStage()).setLife(0);
		destroy();
		setEnemyNumber();
		return;
	}

	@Override
	public InfoRangeTraversal getRangeTraverseInfo() {
		return this.infoRangeTraversal;
	}
	
	boolean rangeTraverseActive = true;

	@Override
	public boolean isActive() {
		return rangeTraverseActive;
	}

	@Override
	public void setActive(boolean active) {
		this.rangeTraverseActive = active;
	}

	@Override
	public void setRangeTraverseInfo(InfoRangeTraversal traverseInfo) {
		this.infoRangeTraversal = traverseInfo;
	}

	public float getSpeed() {
		return enemySpeed/3f;
	}
}
