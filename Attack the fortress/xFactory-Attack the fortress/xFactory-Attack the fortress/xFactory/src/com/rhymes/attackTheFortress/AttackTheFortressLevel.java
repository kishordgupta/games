package com.rhymes.attackTheFortress;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.attackTheFortress.button.ButtonMainMenu;
import com.rhymes.attackTheFortress.button.ButtonPause;
import com.rhymes.attackTheFortress.button.ButtonPlayLevel;
import com.rhymes.attackTheFortress.button.ButtonTower;
import com.rhymes.attackTheFortress.button.MainView;
import com.rhymes.attackTheFortress.button.ScoreView;
import com.rhymes.attackTheFortress.input.InteractionInputListener;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.menu.ButtonEnterGameMenu;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.rangeTraversal.IRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.levels.LevelTest;
import com.rhymes.game.stage.menus.GameMenuInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class AttackTheFortressLevel extends StageBase {
	@Override
	public void resume() {
		super.resume();
		font1 = createFont(AssetConstants.FONT_SUPERSTAR);
		font2 = createFont(AssetConstants.FONT_SUPERSTAR);
		font3 = createFont(AssetConstants.FONT_SUPERSTAR);
		font4 = createFont(AssetConstants.FONT_SUPERSTAR);
		font5 = createFont(AssetConstants.FONT_SUPERSTAR);
	}

	public String bkg="";
	private TSVInfo currenttsvInfo;
	public int Gold=0;
	private TowerStateView  towerStat; 
	private Tower tower;
	private BitmapFont font;
	private Text goldText;
	private Text levelText;
	private Text lifeText;
	private Text killText;
	private Text leftText;
	private Enemy enemy;
	private int life=20;
	private int levelNumber=0;
	private boolean createEnemy=false;
	private int enemyLifeScale=0;
	public	NoticeView notice;
	InteractionInputListener inputListener;
	private int enemyKilled=0;
	private int levelEnemyLeft;
	private ButtonPause pause;
	
	public ButtonPause getButtonPause(){
		return this.pause;
	}
	public void setButtonPause(ButtonPause P){
		this.pause =P;
	}
	public 	boolean getCreateEnemy(){
		return createEnemy;
	}
	public void setCreateeEnemy(boolean create){
		this.createEnemy=create;
	}
	public int getLeveNumber(){
		return this.levelNumber;
	}
	public void setLevelNumber(int level){
		this.levelNumber=level;
	}
	public int getEnemyKilled(){
		return this.enemyKilled;
	}
	public void setEnemyKilled(int kill){
		this.enemyKilled=kill;
	}
	public int getEnemyLeft(){
		return this.levelEnemyLeft;
	}
	public void setEnemyLeft(int left){
		this.levelEnemyLeft=left;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public void setLife(int L){
		this.life=L;
	}
	public Enemy getEnemy()
	{
		return this.enemy;
	}
	public void setEnemy(Enemy enem)
	{
		this.enemy=enem;
	}
	public Tower getCurrentTower(){
	return this.tower;
}
	public void setCurrentTower(Tower tow){
		this.tower=tow;
	//	Helper.println("current Tower: "+ this.tower.getTowerType());
	}
	public TowerStateView gettowerStatView(){
	return towerStat;
}
	public void settowerStatView(TowerStateView tsvt ){
	this.towerStat=tsvt;
}
	public TSVInfo getcurrentTSVInfo(){
	return this.currenttsvInfo;
}
	public void setcurrentTSVInfo(TSVInfo tsv){
	this.currenttsvInfo=tsv;
}
	public int getGold(){
		return this.Gold;
	}
		public void setGold(int gold){
		this.Gold=gold;
	}
	
	public AttackTheFortressLevel() {
		xmlEnemyReader xmlEnemy=new xmlEnemyReader();
		xmlEnemy.readXml(LevelInfo.ROUND_NUMBER);
		
		
		xmlTowerReader xmlTower=new xmlTowerReader();
		xmlTower.readXml();
		
		bkg=xmlEnemyReader.bkgFile+".png";
		Gold=(int) xmlEnemyReader.moneyGold;
//		Gold=10000;
	}

	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG_ATTACK+bkg);
		assetPack.addTexture(AssetConstants.IMG_BTN_MAINMENU_1);
		assetPack.addTexture(AssetConstants.IMG_BTN_PLAYLEVEL_1);
		assetPack.addTexture(AssetConstants.IMG_BTN_Tower_1);
		assetPack.addTexture(AssetConstants.IMG_BTN_GAME_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_GAME_PLAY);
		assetPack.addTexture(AssetConstants.IMG_LABELBG);
		assetPack.addTexture(AssetConstants.IMG_BOUNDARY);
		assetPack.addTexture(AssetConstants.IMG_TSV_BACK);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER1);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER2);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER3);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER4);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER5);
		assetPack.addTexture(AssetConstants.IMG_TSV_TOWER6);
		assetPack.addTexture(AssetConstants.IMG_TOWER1);
		assetPack.addTexture(AssetConstants.IMG_TOWER2);
		assetPack.addTexture(AssetConstants.IMG_TOWER3);
		assetPack.addTexture(AssetConstants.IMG_TOWER4);
		assetPack.addTexture(AssetConstants.IMG_TOWER5);
		assetPack.addTexture(AssetConstants.IMG_TOWER6);
		assetPack.addTexture(AssetConstants.IMG_TOWER_STATVIEW);
		assetPack.addTexture(AssetConstants.IMG_NOTICE);
		assetPack.addTexture(AssetConstants.IMG_LIFE_BAR_GREEN);
		assetPack.addTexture(AssetConstants.IMG_LIFE_BAR_YELLOW);
		assetPack.addTexture(AssetConstants.IMG_REGION_CORRECT);
		assetPack.addTexture(AssetConstants.IMG_REGION_FALSE);
		
		assetPack.addTexture(AssetConstants.IMG_BTN_SELL);
		assetPack.addTexture(AssetConstants.IMG_BTN_UPGRADE);
		assetPack.addTexture(AssetConstants.IMG_BULLET_1);
		assetPack.addTexture(AssetConstants.IMG_BULLET_2);
		assetPack.addTexture(AssetConstants.IMG_BULLET_3);
		assetPack.addTexture(AssetConstants.IMG_BULLET_4);
		assetPack.addTexture(AssetConstants.IMG_BULLET_5);
		assetPack.addTexture(AssetConstants.IMG_BULLET_6);
		
		assetPack.addTexture(AssetConstants.IMG_BLOW_1);
		assetPack.addTexture(AssetConstants.IMG_BLOW_2);
		assetPack.addTexture(AssetConstants.IMG_BLOW_3);
		assetPack.addTexture(AssetConstants.IMG_BLOW_4);
		assetPack.addTexture(AssetConstants.IMG_BLOW_5);
		assetPack.addTexture(AssetConstants.IMG_BLOW_6);
		assetPack.addTexture(AssetConstants.IMG_BLOW_7);
		assetPack.addTexture(AssetConstants.IMG_BLOW_8);
		assetPack.addTexture(AssetConstants.IMG_BLOW_9);
		assetPack.addTexture(AssetConstants.IMG_BLOW_10);
		assetPack.addTexture(AssetConstants.IMG_BLOW_11);
		assetPack.addTexture(AssetConstants.IMG_BLOW_12);
		assetPack.addTexture(AssetConstants.IMG_BLOW_13);
		assetPack.addTexture(AssetConstants.IMG_BLOW_14);
		assetPack.addTexture(AssetConstants.IMG_BLOW_15);
		assetPack.addTexture(AssetConstants.IMG_BLOW_16);
		assetPack.addTexture(AssetConstants.IMG_BLOW_17);
		assetPack.addTexture(AssetConstants.IMG_BLOW_18);
		assetPack.addTexture(AssetConstants.IMG_BLOW_19);
		assetPack.addTexture(AssetConstants.IMG_BLOW_20);
		assetPack.addTexture(AssetConstants.IMG_BLOW_21);
		assetPack.addTexture(AssetConstants.IMG_BLOW_22);
		
		assetPack.addTexture(AssetConstants.ENEMY_1_1);
		assetPack.addTexture(AssetConstants.ENEMY_1_2);
		assetPack.addTexture(AssetConstants.ENEMY_1_3);
		assetPack.addTexture(AssetConstants.ENEMY_1_4);
		assetPack.addTexture(AssetConstants.ENEMY_1_5);
		assetPack.addTexture(AssetConstants.ENEMY_1_6);
		assetPack.addTexture(AssetConstants.ENEMY_1_7);
		assetPack.addTexture(AssetConstants.ENEMY_1_8);
		
		assetPack.addTexture(AssetConstants.ENEMY_2_1);
		assetPack.addTexture(AssetConstants.ENEMY_2_2);
		assetPack.addTexture(AssetConstants.ENEMY_2_3);
		assetPack.addTexture(AssetConstants.ENEMY_2_4);
		assetPack.addTexture(AssetConstants.ENEMY_2_5);
		assetPack.addTexture(AssetConstants.ENEMY_2_6);
		assetPack.addTexture(AssetConstants.ENEMY_2_7);
		assetPack.addTexture(AssetConstants.ENEMY_2_8);
		assetPack.addTexture(AssetConstants.ENEMY_2_9);
		assetPack.addTexture(AssetConstants.ENEMY_2_10);
		assetPack.addTexture(AssetConstants.ENEMY_2_11);
		assetPack.addTexture(AssetConstants.ENEMY_2_12);
		assetPack.addTexture(AssetConstants.ENEMY_2_13);
		assetPack.addTexture(AssetConstants.ENEMY_2_14);
		assetPack.addTexture(AssetConstants.ENEMY_2_15);
		assetPack.addTexture(AssetConstants.ENEMY_2_16);
		assetPack.addTexture(AssetConstants.ENEMY_2_17);
		assetPack.addTexture(AssetConstants.ENEMY_2_18);
		assetPack.addTexture(AssetConstants.ENEMY_2_19);
		assetPack.addTexture(AssetConstants.ENEMY_2_20);
		
		assetPack.addTexture(AssetConstants.ENEMY_3_1);
		assetPack.addTexture(AssetConstants.ENEMY_3_2);
		assetPack.addTexture(AssetConstants.ENEMY_3_3);
		assetPack.addTexture(AssetConstants.ENEMY_3_4);
		assetPack.addTexture(AssetConstants.ENEMY_3_5);
		assetPack.addTexture(AssetConstants.ENEMY_3_6);
		assetPack.addTexture(AssetConstants.ENEMY_3_7);
		assetPack.addTexture(AssetConstants.ENEMY_3_8);
		assetPack.addTexture(AssetConstants.ENEMY_3_9);
		assetPack.addTexture(AssetConstants.ENEMY_3_10);

		assetPack.addTexture(AssetConstants.ENEMY_4_1);
		assetPack.addTexture(AssetConstants.ENEMY_4_2);
		assetPack.addTexture(AssetConstants.ENEMY_4_3);
		assetPack.addTexture(AssetConstants.ENEMY_4_4);
		assetPack.addTexture(AssetConstants.ENEMY_4_5);
		assetPack.addTexture(AssetConstants.ENEMY_4_6);
		assetPack.addTexture(AssetConstants.ENEMY_4_7);
		assetPack.addTexture(AssetConstants.ENEMY_4_8);
		assetPack.addTexture(AssetConstants.ENEMY_4_9);
		assetPack.addTexture(AssetConstants.ENEMY_4_10);
		
		assetPack.addTexture(AssetConstants.ENEMY_5_1);
		assetPack.addTexture(AssetConstants.ENEMY_5_2);
		assetPack.addTexture(AssetConstants.ENEMY_5_3);
		assetPack.addTexture(AssetConstants.ENEMY_5_4);
		assetPack.addTexture(AssetConstants.ENEMY_5_5);
		assetPack.addTexture(AssetConstants.ENEMY_5_6);
		assetPack.addTexture(AssetConstants.ENEMY_5_7);
		assetPack.addTexture(AssetConstants.ENEMY_5_8);
		assetPack.addTexture(AssetConstants.ENEMY_5_9);
		assetPack.addTexture(AssetConstants.ENEMY_5_10);
		assetPack.addTexture(AssetConstants.ENEMY_5_11);
		assetPack.addTexture(AssetConstants.ENEMY_5_12);
		assetPack.addTexture(AssetConstants.ENEMY_5_13);
		
		assetPack.addTexture(AssetConstants.ENEMY_6_1);
		assetPack.addTexture(AssetConstants.ENEMY_6_2);
		assetPack.addTexture(AssetConstants.ENEMY_6_3);
		assetPack.addTexture(AssetConstants.ENEMY_6_4);
		assetPack.addTexture(AssetConstants.ENEMY_6_5);
		assetPack.addTexture(AssetConstants.ENEMY_6_6);
		assetPack.addTexture(AssetConstants.ENEMY_6_7);
		assetPack.addTexture(AssetConstants.ENEMY_6_8);
		assetPack.addTexture(AssetConstants.ENEMY_6_9);
		assetPack.addTexture(AssetConstants.ENEMY_6_10);
		assetPack.addTexture(AssetConstants.ENEMY_6_11);
		assetPack.addTexture(AssetConstants.ENEMY_6_12);
		assetPack.addTexture(AssetConstants.ENEMY_6_13);
		assetPack.addTexture(AssetConstants.ENEMY_6_14);
		assetPack.addTexture(AssetConstants.ENEMY_6_15);
		assetPack.addTexture(AssetConstants.ENEMY_6_16);
		assetPack.addTexture(AssetConstants.ENEMY_6_17);
		assetPack.addTexture(AssetConstants.ENEMY_6_18);
		assetPack.addTexture(AssetConstants.ENEMY_6_19);
		assetPack.addTexture(AssetConstants.ENEMY_6_20);
		
		assetPack.addTexture(AssetConstants.ENEMY_7_1);
		assetPack.addTexture(AssetConstants.ENEMY_7_2);
		assetPack.addTexture(AssetConstants.ENEMY_7_3);
		assetPack.addTexture(AssetConstants.ENEMY_7_4);
		assetPack.addTexture(AssetConstants.ENEMY_7_5);
		assetPack.addTexture(AssetConstants.ENEMY_7_6);
		assetPack.addTexture(AssetConstants.ENEMY_7_7);
		assetPack.addTexture(AssetConstants.ENEMY_7_8);
		assetPack.addTexture(AssetConstants.ENEMY_7_9);
		assetPack.addTexture(AssetConstants.ENEMY_7_10);
		
		assetPack.addTexture(AssetConstants.ENEMY_8_1);
		assetPack.addTexture(AssetConstants.ENEMY_8_2);
		assetPack.addTexture(AssetConstants.ENEMY_8_3);
		assetPack.addTexture(AssetConstants.ENEMY_8_4);
		assetPack.addTexture(AssetConstants.ENEMY_8_5);
		assetPack.addTexture(AssetConstants.ENEMY_8_6);
		assetPack.addTexture(AssetConstants.ENEMY_8_7);
		assetPack.addTexture(AssetConstants.ENEMY_8_8);
		assetPack.addTexture(AssetConstants.ENEMY_8_9);
		assetPack.addTexture(AssetConstants.ENEMY_8_10);
		assetPack.addTexture(AssetConstants.ENEMY_8_11);
		assetPack.addTexture(AssetConstants.ENEMY_8_12);
		assetPack.addTexture(AssetConstants.ENEMY_8_13);
		assetPack.addTexture(AssetConstants.ENEMY_8_14);
		assetPack.addTexture(AssetConstants.ENEMY_8_15);
		
		assetPack.addTexture(AssetConstants.ENEMY_9_1);
		assetPack.addTexture(AssetConstants.ENEMY_9_2);
		assetPack.addTexture(AssetConstants.ENEMY_9_3);
		assetPack.addTexture(AssetConstants.ENEMY_9_4);
		assetPack.addTexture(AssetConstants.ENEMY_9_5);
		assetPack.addTexture(AssetConstants.ENEMY_9_6);
		assetPack.addTexture(AssetConstants.ENEMY_9_7);
		assetPack.addTexture(AssetConstants.ENEMY_9_8);
		assetPack.addTexture(AssetConstants.ENEMY_9_9);
		assetPack.addTexture(AssetConstants.ENEMY_9_10);
		assetPack.addTexture(AssetConstants.ENEMY_9_11);
		assetPack.addTexture(AssetConstants.ENEMY_9_12);
		assetPack.addTexture(AssetConstants.ENEMY_9_13);
		assetPack.addTexture(AssetConstants.ENEMY_9_14);
				
		assetPack.addTexture(AssetConstants.ENEMY_10_1);
		assetPack.addTexture(AssetConstants.ENEMY_10_2);
		assetPack.addTexture(AssetConstants.ENEMY_10_3);
		assetPack.addTexture(AssetConstants.ENEMY_10_4);
		assetPack.addTexture(AssetConstants.ENEMY_10_5);
		assetPack.addTexture(AssetConstants.ENEMY_10_6);
		assetPack.addTexture(AssetConstants.ENEMY_10_7);
		assetPack.addTexture(AssetConstants.ENEMY_10_8);
		assetPack.addTexture(AssetConstants.ENEMY_10_9);
		assetPack.addTexture(AssetConstants.ENEMY_10_10);
		assetPack.addTexture(AssetConstants.ENEMY_10_11);
		assetPack.addTexture(AssetConstants.ENEMY_10_12);
		assetPack.addTexture(AssetConstants.ENEMY_10_13);
		assetPack.addTexture(AssetConstants.ENEMY_10_14);
		assetPack.addTexture(AssetConstants.ENEMY_10_15);
		assetPack.addTexture(AssetConstants.ENEMY_10_16);
		assetPack.addTexture(AssetConstants.ENEMY_10_17);
		assetPack.addTexture(AssetConstants.ENEMY_10_18);
		
		return assetPack;
	}

	IInteractionRangeDetect ird;
	private BitmapFont font1;
	private BitmapFont font2;
	private BitmapFont font3;
	private BitmapFont font4;
	private BitmapFont font5; 
	
	@Override
	public void loadElements() {
		ird = new IInteractionRangeDetect();
		this.interactions.add(ird);
		
		IRangePathTraversal iRangePathTraversal = new IRangePathTraversal();
		this.interactions.add(iRangePathTraversal);
		
		
//		LevelInfo.LEVEL_NUMBER=0;
		this.levelNumber=0;
		// Interaction
		if(LevelInfo.GAME_MODE==1){
			enemyLifeScale=1;
			Gold=Gold*3;
		}
		else if(LevelInfo.GAME_MODE==2){
			enemyLifeScale=2;
			Gold=Gold*4;
		}
		else if(LevelInfo.GAME_MODE==3){
			enemyLifeScale=3;
			Gold=Gold*5;
		}
		this.levelEnemyLeft=xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.size();
		
		this.interactions.add(new InteractionTouch());
		this.inputListener = new InteractionInputListener();
		this.interactions.add(inputListener);
		this.gameControlInteractions.add(new InteractionTouch());
		
		Gdx.input.setInputProcessor(inputListener);
		font1 = createFont(AssetConstants.FONT_SUPERSTAR);
		font2 = createFont(AssetConstants.FONT_SUPERSTAR);
		font3 = createFont(AssetConstants.FONT_SUPERSTAR);
		font4 = createFont(AssetConstants.FONT_SUPERSTAR);
		font5 = createFont(AssetConstants.FONT_SUPERSTAR);
		
		float x=0f;
		float y=0f;
		this.elements.add(new Background(x, y, Gdx.graphics.getWidth(),
				(Gdx.graphics.getHeight()), 1, (AssetConstants.IMG_BKG_ATTACK+bkg)));
		this.elements.add(new Background(x, y, Gdx.graphics.getWidth(),
				(Gdx.graphics.getHeight()), 1, (AssetConstants.IMG_BOUNDARY)));
		x=9f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-12f*LevelInfo.ratioY;
		ButtonMainMenu buttonmainMenu = new ButtonMainMenu(x, y,Gdx.graphics.getWidth()/6f-15f*LevelInfo.ratioX,
				Gdx.graphics.getHeight()/15f+4f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_MAINMENU_1);
		this.elements.add(buttonmainMenu);
		subscribeToControllingInteraction(buttonmainMenu, InteractionTouch.class);
	
		x=Gdx.graphics.getWidth()/6f-6f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-12f*LevelInfo.ratioY;
		LabelBKG label = new LabelBKG(x, y,Gdx.graphics.getWidth()/2f+21f*LevelInfo.ratioX,
				Gdx.graphics.getHeight()/15f+4f*LevelInfo.ratioY,1, AssetConstants.IMG_LABELBG);
		this.elements.add(label);
//		subscribeToControllingInteraction(label, InteractionTouch.class);
		
		x=Gdx.graphics.getWidth()/6f*4f+15f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-12f*LevelInfo.ratioY;
		ButtonPlayLevel playlabel = new ButtonPlayLevel(x, y,Gdx.graphics.getWidth()/6f-15f*LevelInfo.ratioX,
				Gdx.graphics.getHeight()/15f+4f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_PLAYLEVEL_1);
		this.elements.add(playlabel);
		subscribeToControllingInteraction(playlabel, InteractionTouch.class);
		
		x=Gdx.graphics.getWidth()/6f*5f;
		y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-12f*LevelInfo.ratioY;
		ButtonTower tower = new ButtonTower(x, y,Gdx.graphics.getWidth()/6f-9f*LevelInfo.ratioX,
				Gdx.graphics.getHeight()/15f+4f*LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_Tower_1);
		this.elements.add(tower);
		subscribeToControllingInteraction(tower, InteractionTouch.class);
		
		x=Gdx.graphics.getWidth()/6f+2f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15f-4f*LevelInfo.ratioY;		
		levelText=new Text(x,y,	0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font1, ("LEVEL: "
				+(this.levelNumber+1)));
		levelText.getFont().setColor(Color.WHITE);
		this.addElement(levelText);		
//		levelText.getFont().setColor(1, 1, 1, 1);
		x=x+45f*LevelInfo.ratioX;
		lifeText=new Text(x,y,	0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font2, (" LIFE: "
				+this.life));
		lifeText.getFont().setColor(Color.GREEN);
		this.addElement(lifeText);
//		lifeText.getFont().setColor(1, 1, 1, 1);
		x=x+45f*LevelInfo.ratioX;
		goldText=new Text(x,y,	0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font3, (" GOLD: "
				+this.Gold));
		goldText.getFont().setColor(Color.ORANGE);
		this.addElement(goldText);		
//		goldText.getFont().setColor(1, 1, 1, 1);
		x=x+65f*LevelInfo.ratioX;
		killText=new Text(x,y,	0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font4, (" KILL: "
				+this.enemyKilled));
//		killText.getFont().setColor(1, 1, 1, 1);
		killText.getFont().setColor(Color.RED);
		this.addElement(killText);
		
		x=x+45f*LevelInfo.ratioX;
		leftText=new Text(x,y,	0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font5, (" LEFT: "
				+this.levelEnemyLeft));
//		leftText.getFont().setColor(1, 1, 1, 1);
		leftText.getFont().setColor(Color.BLUE);
		this.addElement(leftText);
		
		
		pause=new ButtonPause(10f*LevelInfo.ratioX, 12f*LevelInfo.ratioY,
				35f*LevelInfo.ratioX, 30f*LevelInfo.ratioX, 1, AssetConstants.IMG_BTN_GAME_PAUSE);
		this.addElement(pause);
		this.subscribeToControllingInteraction(pause, InteractionTouch.class);
		
		addNotice(false);
	
	}
	public void addNotice(boolean shallInit){
		float x=40f*LevelInfo.ratioX;
		float y=10f*LevelInfo.ratioY;
		notice = new NoticeView(x,y,400f*LevelInfo.ratioX,70f*LevelInfo.ratioY,1,AssetConstants.IMG_NOTICE,
				createFont(AssetConstants.FONT_SUPERSTAR),this.levelNumber);
		if(shallInit)
			addElement(notice);
		else
			elements.add(notice);
	}
	private int enemyNumber=0;
	private double skipEnemyTime=2000;
	private double elapsedEnemyTime=0;
	private double skipNoticeTime=5000;
	private double elapsedNoticeTime=0;
	
	public void destroyNotice(long stepTime){
		elapsedNoticeTime+=stepTime;
		if(elapsedNoticeTime>skipNoticeTime){
			if(this.notice!=null){
			notice.Destroy();
			this.notice=null;
		}}
	}

	@Override
	public void tick(long stepTime) {
		levelText.setText("LEVEL: "+(this.levelNumber+1));
		lifeText.setText(" LIFE: "+this.life);
		goldText.setText(" GOLD: "+this.Gold);
		killText.setText(" KILL: "+this.enemyKilled);
		leftText.setText(" LEFT: "+this.levelEnemyLeft);
		createEnemy(stepTime);
		destroyNotice(stepTime);
		if(this.isLevelFinished==true && this.worldEnemyCounter==0){
			this.levelNumber++;
			
			this.enemyNumber=0;
			
			this.isLevelFinished=false;
			if(levelNumber>=xmlEnemyReader.levelEnemy.size()){
				LevelInfo.GAME_VICTORY=true;
				gameOverScreen();
			}
			else{
				setEnemyLeft(xmlEnemyReader.levelEnemy.get(getLeveNumber()).enemy.size());	
				this.addNotice(true);
				elapsedNoticeTime=0;
			}
		}
		checkGameOver();
		if(isGameOver)
			gameOverScreen();

	}
	private	int worldEnemyCounter=0;
	public int getWorldEnemyCounter(){
	return this.worldEnemyCounter;
}
	public void setWorldEnemyCounter(int enemcount){
	this.worldEnemyCounter=enemcount;
}
	private boolean isLevelFinished =false;
	public boolean getIsLevelFinished(){
	return this.isLevelFinished;
}
	public void setIsLevelFinished(boolean isFinish){
	this.isLevelFinished=isFinish;
}
	private boolean isPaused=false;
	public boolean getPaused(){
		return this.isPaused;
	}
	public void setPaused(boolean pause){
		this.isPaused=pause;
	}

	public void createEnemy(long stepTime){
		elapsedEnemyTime+=stepTime;
		if(elapsedEnemyTime>skipEnemyTime){
			elapsedEnemyTime=0;
		if(!isPaused){
		if(createEnemy){
			if(enemyNumber>=xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.size()){
				createEnemy=false;
//				this.levelNumber++;
				this.isLevelFinished=true;
				
				return;
			}
			else{
			enemy=new Enemy(xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemyType,
					xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemySpeed,
					(xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemyLife)*enemyLifeScale,
					xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemyDamage,
					xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemyRange,
					xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).EnemyValue,
					xmlEnemyReader.levelEnemy.get(this.levelNumber).enemy.get(enemyNumber).Enemyshooting);
			addEnemey(enemy, xmlEnemyReader.path.startTraverse());
			this.worldEnemyCounter++;
			enemyNumber++;
		}
			}}
		}
	}
	public static Path path;
	Tower tempTower;
	public ArrayList<Tower> towerList=new ArrayList<Tower>();
	public ArrayList getTowerList(){
		return this.towerList;
	}
	
	public void bringTowerAtPoint(Point p)
	{
		if(this.Gold<xmlTowerReader.towers.get(this.currenttsvInfo.towerNumber-1).cost)
			return;
		tempTower = new Tower(this.currenttsvInfo.towerNumber, p.x, p.y);
		addElement(tempTower);
		subscribeToInteraction(tempTower, InteractionTouch.class);
		this.ird.addSourceElements(tempTower);
		this.towerList.add(tempTower);
		this.towercounter++;
	}
	 public BitmapFont createFont(String fontPath) {
		 return TrueTypeFontFactory.createBitmapFont(Gdx.files
		   .internal(fontPath), Text.getFrontChars(), 12.5f, 7.5f, 1.0f,
		   Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	
	
	public void destroyEnemey(Enemy enemy) {
		this.worldEnemyCounter--;
		postDestroyed(enemy);
		ird.removeTargetElements(enemy);
	}
	
	public void destroyTower(Tower tower) {
		postDestroyed(tower);
		ird.removeSourceElements(tower);
		this.towerList.remove(tower);
		this.towercounter--;
	}
	private int towercounter=0;
	private boolean isGameOver=false;
	public void checkGameOver(){
		if(this.life<=0){
			this.isGameOver=true;
		}
		else if(this.towercounter==0 && this.Gold<25){
			this.isGameOver=true;
		}
		LevelInfo.GAME_VICTORY=false;
	}
	public void gameOverScreen(){
		if(LevelInfo.ROUND_NUMBER==1)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_1.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_1.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_1.setLife(this.life);
			LevelInfo.ROUND_RESULT_1.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_1.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		else if(LevelInfo.ROUND_NUMBER==2)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_2.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_2.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_2.setLife(this.life);
			LevelInfo.ROUND_RESULT_2.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_2.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		else if(LevelInfo.ROUND_NUMBER==3)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_3.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_3.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_3.setLife(this.life);
			LevelInfo.ROUND_RESULT_3.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_3.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		else if(LevelInfo.ROUND_NUMBER==4)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_4.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_4.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_4.setLife(this.life);
			LevelInfo.ROUND_RESULT_4.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_4.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		else if(LevelInfo.ROUND_NUMBER==5)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_5.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_5.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_5.setLife(this.life);
			LevelInfo.ROUND_RESULT_5.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_5.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		else if(LevelInfo.ROUND_NUMBER==6)
		{
			if(LevelInfo.ROUND_NUMBER<LevelInfo.ROUND_RESULT_1.getMode())
				return;
			LevelInfo.ROUND_RESULT_6.setGold(this.Gold);
			LevelInfo.ROUND_RESULT_6.setKill(this.enemyKilled);
			LevelInfo.ROUND_RESULT_6.setLife(this.life);
			LevelInfo.ROUND_RESULT_6.setMode(LevelInfo.ROUND_NUMBER);
			LevelInfo.ROUND_RESULT_6.setScore(this.Gold*100+this.life*500+this.enemyKilled*300);		
		}
		StartupInfo.sound_handler.pauseMusic();
		StartupInfo.sound_handler.playMusic(musicType.MUSIC_BGM);
	
		GlobalVars.ge.loadStage(new ScoreView());
	}
	public void addEnemey(Enemy enemy, TraversePointInfo info) {
		this.addElement(enemy);
		ird.addTargetElements(enemy);
		
		
		Helper.println("Adding enemey at: " + info.toString());
		
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = 0;
		infoRangeTraversal.rightRange = info.getTotalDistanceInPath();
		infoRangeTraversal.method = Path.METHOD_RIGHT;
		infoRangeTraversal.traverseInfo = info;
		
		enemy.setRangeTraverseInfo(infoRangeTraversal);

		subscribeToInteraction(enemy, IRangePathTraversal.class);
	}	
	
	public void addEnemey(TraversePointInfo info)
	{

	}

}
