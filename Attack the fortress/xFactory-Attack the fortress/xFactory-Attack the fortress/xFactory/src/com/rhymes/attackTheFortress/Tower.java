package com.rhymes.attackTheFortress;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rhymes.attackTheFortress.button.ButtonSellTower;
import com.rhymes.attackTheFortress.button.ButtonUpgradeDamage;
import com.rhymes.attackTheFortress.button.ButtonUpgradeLife;
import com.rhymes.attackTheFortress.button.ButtonUpgradeRange;
import com.rhymes.attackTheFortress.button.ButtonUpgradeSpeed;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtileMenu.LevelMenu;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.inputs.InteractionTouchCallbacks;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;

public class Tower extends ElementBase implements InteractionTouchCallbacks,IRangeDetection, IBulletHit{
	private float life=0f;
	private float maxLife=0f;
	private int damage=0;
	private int speed=0;
	private int range=0;
	private float cost=0f;
	private float positionX=0f;
	private float positionY=0f;
	private float totalvalue=0f;
	private String image="";	
	private Texture text;
	private int towerType = -1;
	public TowerStateView towerStat = null;
	private int damageUpdateCount=0;
	private int speedUpdateCount=0;
	private int rangeUpdateCount=0;
	private boolean isActive=true;

	public boolean getActive(){
		return this.isActive;
	}
	public void setActive(boolean bool){
		this.isActive=bool;
	}
	public Tower(int num, float x, float y) {
		Helper.println("Creating tower Num: " + num) ;
		towerType = num;
		num--;
		life=xmlTowerReader.towers.get(num).maxLife;
		maxLife=xmlTowerReader.towers.get(num).maxLife;
		damage=(int) xmlTowerReader.towers.get(num).damage;
//		speed=(int) (xmlTowerReader.towers.get(num).speed);
//		range= (int)( xmlTowerReader.towers.get(num).range);
		speed=(int) (xmlTowerReader.towers.get(num).speed*LevelInfo.ratioX);
		range= (int)( xmlTowerReader.towers.get(num).range*LevelInfo.ratioX/2f);
		cost=xmlTowerReader.towers.get(num).cost;
		totalvalue=xmlTowerReader.towers.get(num).cost;
		image=AssetConstants.IMG_TOWER+ (num+1) +"_iPad.png";
		this.x = positionX = x;
		this.y = positionY = y;
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setGold((int) (((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).getGold()-cost));
		
	}

	public Tower(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		// TODO Auto-generated method stub

	}
	private LifeBar lifeBarYellow;
	private LifeBar lifeBarGreen;
	public void addLifeBar(){
		lifeBarYellow=new LifeBar(this.x+5f*LevelInfo.ratioX,this.y+52f*LevelInfo.ratioY,20f*LevelInfo.ratioX,
				3f*LevelInfo.ratioY,2,this,1);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(lifeBarYellow);
		lifeBarGreen=new LifeBar(this.x+5f*LevelInfo.ratioX,this.y+52f*LevelInfo.ratioY,20f*LevelInfo.ratioX,
				3f*LevelInfo.ratioY,1,this,1);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(lifeBarGreen);
	}
	@Override
	public void init() {
		this.height=50f*LevelInfo.ratioY;
		this.width=30f*LevelInfo.ratioX;
		text = Helper.getImageFromAssets(image).getTexture();
		//Helper.println("towerPox: "+this.x+"  "+this.y, true);
		addLifeBar();
	}
	@Override
	public void render() {
		
//		GlobalVars.ge.getRenderer().render(image, col * TileSet.TILE_SIZE_X,
//		row * TileSet.TILE_SIZE_Y, herosizeX,
//		herosizeY, TileSet.TILE_SIZE_X / 2,
//		TileSet.TILE_SIZE_Y / 2, rotateAngle+90f, 1, 1);
		
		GlobalVars.ge.getScreen().getBatch().draw(text,
			(positionX),(positionY) , width, height, 0, 0,
				text.getWidth(), text.getHeight(), false, false);
	}
	public float getMaxLife(){
		return this.maxLife;
	}
	public float getLife(){
		return this.life;
	}
	public void setLife(float life){
		this.life=life;
	}
	public float getDamage(){
		return this.damage;
	}
	public void setDamage(int damage){
		this.damage=damage;
	}
	public float getSpeed(){
		return this.speed;
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public float getRange(){
		return this.range;
	}
	public void setRange(int range){
		this.range=range;
	}
	public float getCost(){
		return this.cost;
	}
	public void setCost(float cost){
		this.cost=cost;
	}
	public Point getPosition(){
		Point p=new Point();
		p.x=(int) this.positionX;
		p.y=(int) this.positionY;
		return p;
	}
	public void setPosition(Point p){
		this.positionX=p.x;
		this.positionY=p.y;
	}
	public float getTotalValue(){
		return this.totalvalue;
	}
	public void setTotalValue(float total){
		this.totalvalue=total;
	}
	public int getTowerType(){
		return this.towerType;
	}
	public void setTowerType(int type){
		this.towerType=type;
	}
	public int getDamageUpdateCount()
	{
		return this.damageUpdateCount;
	}
	public void setDamageUpdateCount(int k)
	{
		this.damageUpdateCount=k;
	}
	public int getSpeedUpdateCount()
	{
		return this.speedUpdateCount;
	}
	public void setSpeedUpdateCount(int l)
	{
		this.speedUpdateCount=l;
	}
	public int getRangeUpdateCount()
	{
		return this.rangeUpdateCount;
	}
	public void setRangeUpdateCount(int m)
	{
		this.rangeUpdateCount=m;
	}
	@Override
	public void step(long stepTime) {
		if(this.life<=0){
			blow=new Blow(this.x, this.y+this.height*2f/3f, 2);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(blow);
			destroy();
		}
		super.step(stepTime);
	}

	@Override
	public void onEvent(com.rhymes.ge.core.renderer.Point hitPoint) {
		if(!isActive)
			return;
		
		if(Helper.checkHit(hitPoint, this))
		{
			Helper.println("Tower inside Touched: " + this.towerType);
			showTowerstatView();
		}
		else{
			Helper.println("Tower outside Touched: " + this.towerType);
			closeTowerState(hitPoint);
		}				
	}
	private boolean showStat=false;
	public void setShowState(boolean bool){
		this.showStat=bool;
	}
	public void showTowerstatView()
	{
		if(showStat)
		{
			if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).gettowerStatView()!=null)
				((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).gettowerStatView().Destroy();
			float poX=this.x+this.width;
			float poY=this.y;
			
			if(poX+150f*LevelInfo.ratioX>Gdx.graphics.getWidth())
				poX=poX-182f*LevelInfo.ratioX;
			if(poY+225f*LevelInfo.ratioY>Gdx.graphics.getHeight())
				poY=Gdx.graphics.getHeight()-235f*LevelInfo.ratioY;
			
			Helper.println("Tower Touched and create state: " + this.towerType);
			towerStat=new TowerStateView(poX,poY, 150f*LevelInfo.ratioX,200f*LevelInfo.ratioY, 1);
						((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).settowerStatView(towerStat);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setCurrentTower(this);	
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(towerStat);
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(towerStat, InteractionTouch.class);

			showStat=false;
			
		}
	else
		{
			if(towerStat!=null)
			{
				Helper.println("Tower Touched and destroy state: " + this.towerType);
				towerStat.Destroy();
			}
			showStat=true;
		}
	}
	public void closeTowerState( com.rhymes.ge.core.renderer.Point hitPoint){
		Helper.println("Outside Tower Touched: " + this.towerType);
		if(towerStat!=null){
			if(hitPoint.x<towerStat.getX() || hitPoint.x>(towerStat.getX()+towerStat.getWidth()) || hitPoint.y<towerStat.getY() || hitPoint.y>(towerStat.getY()+towerStat.getHeight())){
			towerStat.Destroy();
			towerStat=null;
			}
		}	
		showStat=true;
	
	}
	public void destroy(){
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).destroyTower(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).setCurrentTower(null);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(lifeBarYellow);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(lifeBarGreen);
		if(((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).gettowerStatView() != null)
			((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).gettowerStatView().Destroy();
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).settowerStatView(null);
	}
	public void attackOnEnemy(Enemy enemy){
		StartupInfo.sound_handler.playSound(soundType.SOUND_TOWER_LAUNCH);
		Bullet bul=new Bullet(this.x+this.width/2f, this.y+this.height*2f/3f, enemy.getX(), enemy.getY(), this.towerType, this.damage,this.speed, enemy);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(bul);
	}
	private Blow blow;
	public void attackByBullet(Bullet bullet){
		blow=new Blow(bullet.getX(), bullet.getY(), 1);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(blow);
		this.life-=bullet.getDamage();
		
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
			attackOnEnemy((Enemy) target);
//		Helper.println("Tower: On Range()");
	}

	@Override
	public void onBulletHit(Bullet bullet) {
		attackByBullet(bullet);
	}
}
