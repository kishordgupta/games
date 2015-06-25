package com.rhymes.game.stage.levels;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.testtile.Dog;
import com.rhymes.game.entity.elements.testtile.HeroTile;
import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.game.entity.elements.testtile.Porcupine;
import com.rhymes.game.entity.elements.testtile.TileSet;
import com.rhymes.game.entity.elements.testtile.WorldPiller;
import com.rhymes.game.entity.elements.testtile.WorldSwitch;
import com.rhymes.game.entity.elements.testtile.scorelbl;
import com.rhymes.game.entity.elements.testtileMenu.ButtonChallenges;
import com.rhymes.game.entity.elements.testtileMenu.ButtonExitGame;
import com.rhymes.game.entity.elements.testtileMenu.ButtonExitLevel;
import com.rhymes.game.entity.elements.testtileMenu.ButtonPause;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.ui.NumericText;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.interactions.InteractionFlick;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.testtile.IHeroController;
import com.rhymes.game.interactions.testtile.IPorcupineController;
import com.rhymes.game.stage.result.Result;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;
import com.rhymes.sundayLawn.ResultTileScore;
import com.rhymes.sundayLawn.XmlRead;
import com.rhymes.game.interactions.testtile.TextTime;

public class TestTileLevel extends StageBase {
	public float CAMERA_STEP = /*HeroTile.getAdvStep()*/ 0;
	private TileSet tileset ;
	public int HeroRow=0;
	public int HeroCol=0;
	public IHeroController iHeroController ;
	public Result result;
	private Text 	level;
	private HeroTile 	heroTile;
	private Text 		scorelb;
	private Text 		bonus;
	private Text 		yard;
	private Text		percentText;
	private ButtonPause pause;
	private WorldSwitch worldswitch;
	private scorelbl 	pouseScreen;
	private WorldPiller 	 worldpiller;
	private ButtonRestart	 reload;
	private	ButtonExitLevel  buttonExitlevel;
	private long score;
	
	
	public ButtonExitLevel getButtonExitLevel() {
		return buttonExitlevel;
	}
	public void setButtonExitLevel(ButtonExitLevel exit) {
		this.buttonExitlevel = exit;
	}
	public ButtonRestart getButtonRestart() {
		return reload;
	}
	public void setButtonRestart(ButtonRestart reLoad) {
		this.reload = reLoad;
	}
	public scorelbl getpouseScreen() {
		return pouseScreen;
	}
	public void setpouseScreen(scorelbl pause) {
		this.pouseScreen = pause;
	}
	public TileSet getTileset() {
		return tileset;
	}
	public void setTileset(TileSet tileset) {
		this.tileset = tileset;
	}
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_downHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_leftHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_rightHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_upHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_downHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_leftHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_rightHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_0_upHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1_downHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1_leftHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1_rightHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_1_upHero1);
		
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_downHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_leftHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_rightHero1);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_upHero1);
		
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_downHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_leftHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_rightHero2);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_2_upHero2);
		
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_downDestroy);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_upDestroy);
		assetPack.addTexture(AssetConstants.IMG_GAMEOVER);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_leftDestroy);
		assetPack.addTexture(AssetConstants.IMG_TBLOCK_rightDestroy);
		assetPack.addTexture(AssetConstants.IMG_BTN_LEVELCOMPLETE);
		assetPack.addTexture(AssetConstants.IMG_LBL_BONUS);
		assetPack.addTexture(AssetConstants.IMG_LBL_SCORE);
		assetPack.addTexture(AssetConstants.IMG_LBL_YARDS);
		assetPack.addTexture(AssetConstants.IMG_GREENPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_GREENPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_GREENSWITCH);
		assetPack.addTexture(AssetConstants.IMG_GREENSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_YELLOWPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_YELLOWPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_YELLOWSWITCH);
		assetPack.addTexture(AssetConstants.IMG_YELLOWSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_REDPILLER_HIGH);
		assetPack.addTexture(AssetConstants.IMG_REDPILLER_LOW);
		assetPack.addTexture(AssetConstants.IMG_REDSWITCH);
		assetPack.addTexture(AssetConstants.IMG_REDSWITCH_BLANK);
		assetPack.addTexture(AssetConstants.IMG_DOGSHIT_BIG);
		assetPack.addTexture(AssetConstants.IMG_DOGSHIT_SMALL);
		assetPack.addTexture(AssetConstants.IMG_MINUS_SCORE);
		assetPack.addTexture(AssetConstants.IMG_YUCK);
		assetPack.addTexture(AssetConstants.IMG_BTN_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_SELECT);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESUME);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.SCORE_FONT_PERCENT);
		assetPack.addTexture(AssetConstants.SCORE_FONT_BY);
		assetPack.addTexture(AssetConstants.IMG_LEVEL);
		return assetPack;
	}
	
	ArrayList<Porcupine> porcupines = new ArrayList<Porcupine>();
	ArrayList<Dog> dogs = new ArrayList<Dog>();
	ArrayList<WorldSwitch> Wsw = new ArrayList<WorldSwitch>();
	
	public ArrayList<WorldSwitch> getWSW(){
		return this.Wsw;
	}
	IPorcupineController iPorcupineController;
	
	@Override
	public void loadElements() {
		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		loadFonts();
		XmlRead.Read(AssetConstants.FILE_LEVEL_INFO +levelNumber +".xml");
		iHeroController = new IHeroController();
		this.interactions.add(iHeroController);
		
		iPorcupineController = new IPorcupineController();
		this.interactions.add(iPorcupineController);
		
		this.gameControlInteractions.add(new InteractionTouch());
		
		
		tileset = new TileSet(XmlRead.TRow,XmlRead.TCol);
		addElement(tileset);
		for(int i=0;i<XmlRead.switches.size;i++)
		{
				worldswitch=new WorldSwitch(XmlRead.switches.get(i).switchid.row,XmlRead.switches.get(i).switchid.col,XmlRead.switches.get(i).switchid.tileTypes,XmlRead.switches.get(i).switchid.tileImageName );
				addElement(worldswitch);
				for(int j=0;j<XmlRead.switches.get(i).pillers.size();j++)
				{
//					Helper.println("Piller added j: "+j);
						worldpiller=new WorldPiller(XmlRead.switches.get(i).pillers.get(j).row,XmlRead.switches.get(i).pillers.get(j).col,XmlRead.switches.get(i).pillers.get(j).tileTypes,XmlRead.switches.get(i).pillers.get(j).tileImageName );
						addElement(worldpiller);
						worldswitch.Wpl.add(worldpiller);
				}
//				Helper.println("Wpl"+worldswitch.Wpl.size());
				Wsw.add(worldswitch);
//				Helper.println("Wsw: Elements loaded: "+Wsw.size());
		}
		
		
		for(int i=0;i<XmlRead.Elements.size;i++)
		{
			
			
			if(XmlRead.Elements.get(i).tileTypes==70)
			{
				Porcupine porc = new Porcupine(XmlRead.Elements.get(i).row,XmlRead.Elements.get(i).col,XmlRead.Elements.get(i).tileTypes,XmlRead.Elements.get(i).tileImageName );
				for(int k=0;k<XmlRead.paths.size;k++)
				{
				if(XmlRead.paths.get(k).path.get(0).row==XmlRead.Elements.get(i).row && XmlRead.paths.get(k).path.get(0).col==XmlRead.Elements.get(i).col){
					porc.pathId=k;
				}
				}
				addElement(porc);
				porcupines.add(porc);
				iPorcupineController.subscribe(porc);
				}
			
			else if(XmlRead.Elements.get(i).tileTypes==80)
			{
				Dog dog = new Dog(XmlRead.Elements.get(i).row,XmlRead.Elements.get(i).col,XmlRead.Elements.get(i).tileTypes,XmlRead.Elements.get(i).tileImageName );
				for(int k=0;k<XmlRead.paths.size;k++)
				{
				if(XmlRead.paths.get(k).path.get(0).row==XmlRead.Elements.get(i).row && XmlRead.paths.get(k).path.get(0).col==XmlRead.Elements.get(i).col){
					dog.pathId=k;
				}
				}
				addElement(dog);
				dogs.add(dog);
				iPorcupineController.subscribe(dog);
				}
			else if(XmlRead.Elements.get(i).tileTypes==60)
			{
				HeroRow=XmlRead.Elements.get(i).row;
				HeroCol=XmlRead.Elements.get(i).col;
				 heroTile = new HeroTile(HeroRow,HeroCol,XmlRead.Elements.get(i).tileTypes,XmlRead.Elements.get(i).tileImageName );
				addElement(heroTile);
				iHeroController.subscribe(heroTile);
				float m= HeroCol*TileSet.TILE_SIZE_X;
				float n= HeroRow*TileSet.TILE_SIZE_Y;
				
				if(m<Gdx.graphics.getWidth()/2f)
					m=Gdx.graphics.getWidth()/2f;
				
				if(n<Gdx.graphics.getHeight()/2f)
					n=Gdx.graphics.getHeight()/2f;
				
				if(m>XmlRead.TCol*TileSet.TILE_SIZE_X-Gdx.graphics.getWidth()/2f)
					m=XmlRead.TCol*TileSet.TILE_SIZE_X-Gdx.graphics.getWidth()/2f;
				
				if(n>XmlRead.TRow*TileSet.TILE_SIZE_Y-Gdx.graphics.getHeight()/2f)
					n=XmlRead.TRow*TileSet.TILE_SIZE_Y-Gdx.graphics.getHeight()/2f;
					
				GlobalVars.ge.getScreen().cam.position.set( m,n, 0);
				
				iPorcupineController.setHero(heroTile);
		
				Gdx.input.setInputProcessor(new InteractionFlick(heroTile));
			}
		}
		///////////////////////////
		
		
		//////////////////////////
		score=(LevelInfo.totalScore);
		
		bonus = new Text(GlobalVars.ge.getScreen().cam.position.x-237f*LevelInfo.ratioX,
				GlobalVars.ge.getScreen().cam.position.y+(Gdx.graphics.getHeight()/2f)-15f*LevelInfo.ratioY,
				0.6f*LevelInfo.ratioX, 0.6f*LevelInfo.ratioY, font, "bonus "+bonusTime);
		this.elements.add(bonus);
		
		
		if(LevelInfo.GAME_MODE==2)
		{
			int TotalLevel=8;
			level=new Text(GlobalVars.ge.getScreen().cam.position.x+(Gdx.graphics.getWidth()/2f)-60f*LevelInfo.ratioX,
			GlobalVars.ge.getScreen().cam.position.y-(Gdx.graphics.getHeight()/2f)+5f*LevelInfo.ratioY,
			0.6f*LevelInfo.ratioX, 0.6f*LevelInfo.ratioX, font, "level"+LevelInfo.LEVEL_NUMBER+"/"+TotalLevel);
			this.elements.add(level);

		}
		
		
		scorelb=new Text(GlobalVars.ge.getScreen().cam.position.x-15f*LevelInfo.ratioX, 
				GlobalVars.ge.getScreen().cam.position.y+(Gdx.graphics.getHeight()/2f)-20f*LevelInfo.ratioY, 
				0.5f*LevelInfo.ratioX, 0.5f*LevelInfo.ratioY, font, "SCORE "+score);
		this.elements.add(scorelb);
	
		yard=new Text(heroTile.getX()-20f*LevelInfo.ratioX, (heroTile.getY()+TileSet.TILE_SIZE_Y*2f),
				0.4f*LevelInfo.ratioX, 0.4f*LevelInfo.ratioY, font, "Yard "+0);
		yard.setActive(false);
		this.elements.add(yard);
		
		percentText=new Text(GlobalVars.ge.getScreen().cam.position.x-237f*LevelInfo.ratioX,
				GlobalVars.ge.getScreen().cam.position.y-(Gdx.graphics.getHeight()/2f)+5f*LevelInfo.ratioY,
				0.6f*LevelInfo.ratioX, 0.6f*LevelInfo.ratioY, font, 0+"%");
		this.elements.add(percentText);
		
		float x = GlobalVars.ge.getScreen().cam.position.x+Gdx.graphics.getWidth()/2f-37f*LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-25f*LevelInfo.ratioY;
		pause = new ButtonPause(x , y, 32f*LevelInfo.ratioX, 20f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_PAUSE);
		this.elements.add(pause);
		subscribeToControllingInteraction(pause, InteractionTouch.class);

		
		
		
	}
	float skipTimeporc = 300;
	float elapsedTimeporc = 0;
	float posDiffporc=1f;
	float skipTimedog = 300;
	float elapsedTimedog = 0;
	float posDiffdog=2f;
	public long bonusTime=2000;
	float skipTimeBonus=100;
	float elaspedTimeBonus=0;
	private int levelNumber;
	float porcupinePathlength=0f;
	
	
	public void SwitchLogic()
	{
		
//		Helper.println("Wsw" + Wsw.size());
		
		for(int i=0;i<Wsw.size();i++)
		{
//			Helper.println("Piller Size: "+ i + " --> "+Wsw.get(i).Wpl.size());
			if(Wsw.get(i).isActiveSwitch())
			{
				for(int j=0;j<Wsw.get(i).Wpl.size();j++)
				{
					int pr=Wsw.get(i).Wpl.get(j).getRow();
					int pc=Wsw.get(i).Wpl.get(j).getCol();
				
					TileSet.tiles[pr][pc].type=1;
			
					if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_GREENPILLER_HIGH))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_GREENPILLER_LOW);
					}
				
					else if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_YELLOWPILLER_HIGH))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_YELLOWPILLER_LOW);
					}
					
					else if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_REDPILLER_HIGH))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_REDPILLER_LOW);
					}
				}
			}
			else
			{
				for(int j=0;j<Wsw.get(i).Wpl.size();j++)
				{
					int pr=Wsw.get(i).Wpl.get(j).getRow();
					int pc=Wsw.get(i).Wpl.get(j).getCol();
					TileSet.tiles[pr][pc].type=2;
				
					if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_GREENPILLER_LOW))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_GREENPILLER_HIGH);
					}
							
					else if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_YELLOWPILLER_LOW))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_YELLOWPILLER_HIGH);
					}
						
					else if(TileSet.tiles[pr][pc].image==Helper.getImageFromAssets(AssetConstants.IMG_REDPILLER_LOW))
					{
						TileSet.tiles[pr][pc].image=Helper.getImageFromAssets(AssetConstants.IMG_REDPILLER_HIGH);
					}
				}
			}	
		}
		

	}
	public void DogPath(long stepTime){
		for(int i=0;i<dogs.size();i++)
		{
			if(dogs.get(i).PrevCol!=2000 && dogs.get(i).PrevRow!=2000)
				
			if(dogs.get(i).PrevCol<XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col)
			{
				dogs.get(i).PorcupinePosX+=posDiffdog;
				dogs.get(i).pathLength+=posDiffdog;
				if(heroTile.getAmimation()){
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_rightHero1"+".png").getTexture();	
				}
				else{
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_rightHero2"+".png").getTexture();
				}
				
				dogs.get(i).PorcupinesizeX=48f*LevelInfo.ratioX;
				dogs.get(i).PorcupinesizeY=36f*LevelInfo.ratioY;
				
			}
			
			else if(dogs.get(i).PrevCol>XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col)
			{
				dogs.get(i).PorcupinePosX-=posDiffdog;
				dogs.get(i).pathLength+=posDiffdog;
				if(heroTile.getAmimation()){
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_leftHero1"+".png").getTexture();	
				}
					
				else{
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_leftHero2"+".png").getTexture();
				}
				dogs.get(i).PorcupinesizeX=48f*LevelInfo.ratioX;
				dogs.get(i).PorcupinesizeY=36f*LevelInfo.ratioY;
			}
			
			if(dogs.get(i).PrevRow<XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row)
			{
				dogs.get(i).PorcupinePosY+=posDiffdog;
				dogs.get(i).pathLength+=posDiffdog;
				if(heroTile.getAmimation()){
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_upHero1"+".png").getTexture();	
				}
				else{
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_upHero2"+".png").getTexture();
				}
				dogs.get(i).PorcupinesizeX=27f*LevelInfo.ratioX;
				dogs.get(i).PorcupinesizeY=45f*LevelInfo.ratioY;
			}
			else if(dogs.get(i).PrevRow>XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row)
			{
				dogs.get(i).PorcupinePosY-=posDiffdog;
				dogs.get(i).pathLength+=posDiffdog;
				if(heroTile.getAmimation()){
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_downHero1"+".png").getTexture();	
				}
				else{
					dogs.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"2_downHero2"+".png").getTexture();
				}
				dogs.get(i).PorcupinesizeX=27f*LevelInfo.ratioX;
				dogs.get(i).PorcupinesizeY=45f*LevelInfo.ratioY;
			}
			}

			for(int i=0;i<dogs.size();i++)
			{
				if(dogs.get(i).pathLength>=TileSet.TILE_SIZE_X || dogs.get(i).pathLength==0f)
				{
					dogs.get(i).pathLength=0f;
				dogs.get(i).PorcupinePosX=XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col*TileSet.TILE_SIZE_X;
				dogs.get(i).PorcupinePosY=XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row*TileSet.TILE_SIZE_Y;
				
				dogs.get(i).row = XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row ;
				dogs.get(i).col = XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col ;
				if(TileSet.tiles[dogs.get(i).row][dogs.get(i).col].image==Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"1"+".png"))
				{
					////random function;;;;
					boolean b=isDogshit(i);
					if(b)
					{
						TileSet.tiles[dogs.get(i).row][dogs.get(i).col].image=Helper.getImageFromAssets(AssetConstants.IMG_DOGSHIT_BIG);
					}
				}
					
				dogs.get(i).PrevCol=XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col;
				dogs.get(i).PrevRow=XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row;
					
				if(dogs.get(i).direction)
				{			
					dogs.get(i).PathTile =	dogs.get(i).PathTile+1;
					if(dogs.get(i).PathTile == XmlRead.paths.get(dogs.get(i).pathId).path.size-1)
						{
							if(XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).col==XmlRead.paths.get(dogs.get(i).pathId).path.get(0).col && XmlRead.paths.get(dogs.get(i).pathId).path.get(dogs.get(i).PathTile).row==XmlRead.paths.get(dogs.get(i).pathId).path.get(0).row)
								dogs.get(i).PathTile=0;
							else
							{
								dogs.get(i).direction=false;						
							}
						}
				}
				else
				{
					dogs.get(i).PathTile=dogs.get(i).PathTile-1;
					if(dogs.get(i).PathTile==0)
					{
						dogs.get(i).direction=true;						
					}
				}
			}
		}
		
		
	
	}
	//
	public Random rnd=new Random();
	public boolean isDogshit(int i)
	{		
		boolean val=false;
		//int c;
		if(dogs.get(i).c>=15)
		{
			val=rnd.nextBoolean();
			if(val==true)
			{
				dogs.get(i).c=0;
			}
		}
		dogs.get(i).c++;	
		return val;		
	}
 	public void PorcupinePath(long stepTime){
		for(int i=0;i<porcupines.size();i++)
		{
			
			if(porcupines.get(i).PrevCol!=2000 && porcupines.get(i).PrevRow!=2000)
			{
			if(porcupines.get(i).PrevCol<XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col)
			{
				porcupines.get(i).PorcupinePosX+=posDiffporc;
				porcupines.get(i).pathLength+=posDiffporc;
				porcupines.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_1_rightHero1).getTexture();
			}
			
			else if(porcupines.get(i).PrevCol>XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col)
			{
				porcupines.get(i).PorcupinePosX-=posDiffporc;
				porcupines.get(i).pathLength+=posDiffporc;
				porcupines.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_1_leftHero1).getTexture();	
			}
			
			if(porcupines.get(i).PrevRow<XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row)
			{
				porcupines.get(i).PorcupinePosY+=posDiffporc;
				porcupines.get(i).pathLength+=posDiffporc;
				porcupines.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_1_upHero1).getTexture();
			}
			else if(porcupines.get(i).PrevRow>XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row)
			{
				porcupines.get(i).PorcupinePosY-=posDiffporc;
				porcupines.get(i).pathLength+=posDiffporc;
				porcupines.get(i).tex=Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_1_downHero1).getTexture();	
			}
			}
		}
		
		
			
		//	elapsedTimeporc = 0;
			for(int i=0;i<porcupines.size();i++)
			{
				if(porcupines.get(i).pathLength>=TileSet.TILE_SIZE_X || porcupines.get(i).pathLength==0f)
				{
					porcupines.get(i).pathLength=0f;
				porcupines.get(i).PorcupinePosX=XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col * TileSet.TILE_SIZE_X;
				porcupines.get(i).PorcupinePosY=XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row * TileSet.TILE_SIZE_Y;
				
				porcupines.get(i).row = XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row ;
				porcupines.get(i).col = XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col ;
			
				porcupines.get(i).PrevCol=XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col;
				porcupines.get(i).PrevRow=XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row;
					
				if(porcupines.get(i).direction)
				{			
					porcupines.get(i).PathTile=	porcupines.get(i).PathTile+1;
					if(porcupines.get(i).PathTile==XmlRead.paths.get(porcupines.get(i).pathId).path.size-1)
						{
							if(XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).col==XmlRead.paths.get(porcupines.get(i).pathId).path.get(0).col && XmlRead.paths.get(porcupines.get(i).pathId).path.get(porcupines.get(i).PathTile).row==XmlRead.paths.get(porcupines.get(i).pathId).path.get(0).row)
								porcupines.get(i).PathTile=0;
							else{
								porcupines.get(i).direction=false;						
						}
							}
				}
				else
				{
					porcupines.get(i).PathTile=porcupines.get(i).PathTile-1;
					if(porcupines.get(i).PathTile==0)
					{
						porcupines.get(i).direction=true;						
					}
				}
			}
		}
	}
 	
 	 BitmapFont font;
 	 private void loadFonts() {
 	  font = TrueTypeFontFactory.createBitmapFont  
 	  (Gdx.files.internal(AssetConstants.FONT), Text.getFrontChars(),
 	    12.0f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
 	  font.setColor(1f, 1f, 1f, 1f);
 	 }	
// 	 public BitmapFont createFont(String fontPath) {
// 		  return TrueTypeFontFactory.createBitmapFont(Gdx.files
// 		    .internal(fontPath), Text.getFrontChars(), 12.5f, 7.5f, 1.0f,
// 		    Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
// 		 }
// 	fontHitScore = createFont(AssetConstants.FONT_IMAGICA);
	@Override
	public void tick(long stepTime) 
	{
		if(heroTile.heroMove()==TileSet.MOVE_NONE)
			return;
//		percenTage.setNumber((long) (heroTile.getPercentage()));
		percentText.setText((long) (heroTile.getPercentage())+"%");
//		percentText.setText("*/*");
	//	score.setNumber((long) ((ResultTileScore)this.result).getStarCollected());
		scorelb.setText("SCORE "+((long) ((ResultTileScore)this.result).getStarCollected()));
		LevelInfo.score=(long) ((ResultTileScore)this.result).getStarCollected();
		
		
		
//		Helper.println("Aise tick 1");
		if(LevelInfo.LEVEL_OVER==LevelInfo.LEVEL_COMPLETE)
			return;
	
		if(HeroTile.heroLastMoved < 0)
			return;
		if(this.gameState == GameState.PLAYING)
		{
			if(HeroTile.isTimecount)
			{
				 elaspedTimeBonus+=stepTime;
				 if(elaspedTimeBonus>skipTimeBonus)
				 {
					 elaspedTimeBonus=0;
					 bonusTime--;
					 if(bonusTime<0)
					 bonusTime=0;			
					 LevelInfo.timeBonus=bonusTime;
				 }
			}

			posDiffdog = HeroTile.heroLastMoved/3 * 2;
			posDiffporc = HeroTile.heroLastMoved/3 * 1;
			DogPath(stepTime);
			PorcupinePath(stepTime);		
			SwitchLogic();
			resultCount();
			
//			Helper.println("Aise tick 2");
		}
		
		float x = GlobalVars.ge.getScreen().cam.position.x+Gdx.graphics.getWidth()/2f-37f*LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-25f*LevelInfo.ratioY;
		pause.setX(x);
		pause.setY(y);
	}
	public void resultCount(){
		if(this.result instanceof ResultTileScore)
		{
			percentText.setX(GlobalVars.ge.getScreen().cam.position.x-237f*LevelInfo.ratioX);
			percentText.setY(GlobalVars.ge.getScreen().cam.position.y-(Gdx.graphics.getHeight()/2f)+5f*LevelInfo.ratioY);
			if(ResultTileScore.count==0)
				{
				yard.setActive(false);
				}
			else
				{
				yard.setActive(true);
				}
			scorelb.setX(GlobalVars.ge.getScreen().cam.position.x-20f*LevelInfo.ratioX);
			scorelb.setY(GlobalVars.ge.getScreen().cam.position.y+(Gdx.graphics.getHeight()/2f)-15f*LevelInfo.ratioY);
			
			bonus.setX(GlobalVars.ge.getScreen().cam.position.x-237f*LevelInfo.ratioX);
			bonus.setY(GlobalVars.ge.getScreen().cam.position.y+(Gdx.graphics.getHeight()/2f)-15f*LevelInfo.ratioY);
			bonus.setText("bonus "+bonusTime);
			yard.setX(heroTile.getX()-20f*LevelInfo.ratioX);
			yard.setY(heroTile.getY()+TileSet.TILE_SIZE_Y*2);
			yard.setText("Yard "+ResultTileScore.count);
			
			if(LevelInfo.GAME_MODE==2)
			{
				level.setX(GlobalVars.ge.getScreen().cam.position.x+(Gdx.graphics.getWidth()/2f)-60f*LevelInfo.ratioX);
				level.setY(GlobalVars.ge.getScreen().cam.position.y-(Gdx.graphics.getHeight()/2f)+5f*LevelInfo.ratioY);
			}
		}
	}
	public HeroTile hero(){
		return heroTile;
	}
	public WorldSwitch wSitch(){
		return worldswitch;
	}
	public WorldPiller wPiller(){
		return worldpiller;
	}
	public void moveCamera(int direction)
	{
		/*
		 * Write camera movement logics here
		 */
			
		/******/
		CAMERA_STEP = HeroTile.heroLastMoved;
		if(HeroTile.heroLastMoved < 0)
			return;
		if (direction == TileSet.MOVE_LEFT){
			if(	GlobalVars.ge.getScreen().cam.position.x<=Gdx.graphics.getWidth()/2)
				return;	
			GlobalVars.ge.getScreen().cam.translate(-CAMERA_STEP, 0, 0);
		}
		else if (direction == TileSet.MOVE_RIGHT){
			if(	GlobalVars.ge.getScreen().cam.position.x>=XmlRead.TCol*TileSet.TILE_SIZE_X-Gdx.graphics.getWidth()/2)
				return;	
			GlobalVars.ge.getScreen().cam.translate(CAMERA_STEP, 0, 0);
		}
		else if (direction == TileSet.MOVE_UP){
			if(	GlobalVars.ge.getScreen().cam.position.y>=XmlRead.TRow*TileSet.TILE_SIZE_Y-Gdx.graphics.getHeight()/2)
				return;	
			GlobalVars.ge.getScreen().cam.translate(0, CAMERA_STEP, 0);
		}
		else if (direction == TileSet.MOVE_DOWN){
			if(	GlobalVars.ge.getScreen().cam.position.y<=Gdx.graphics.getHeight()/2)
				return;	
			GlobalVars.ge.getScreen().cam.translate(0, -CAMERA_STEP, 0);
		}

	}
	public TestTileLevel(int levelNumber, Result result)
	{
		super();
		this.levelNumber = levelNumber;
		this.result = result;
		
//		if(result instanceof ResultBTRTime)
//		{
//			((ResultBTRTime)result).setStarCollected(0);
//			((ResultBTRTime)result).setPearlCollected(10);
//			((ResultBTRTime)result).setElapsedTime(0);
//			((ResultBTRTime)result).setExpireTime(10 * 1000);
//		}
//		else if(result instanceof ResultBTRMAP)
//		{
////			((ResultBTRMAP)result).setStarCollected(0);
//		}
		 if(result instanceof ResultTileScore)
		{
			((ResultTileScore)result).setStarCollected(01);
		}
		 
		 
		 
//		 GlobalVars.ge.loadStage(new TestTileLevel(1, new ResultTileScore()));
//		 GlobalVars.ge.getCurrentStage().pause();
//		 GlobalVars.ge.getCurrentStage().resume();
//		 GlobalVars.ge.getCurrentStage().reload();
		
	}
	public TestTileLevel() {
		// TODO Auto-generated constructor stub
		super();
		this.levelNumber = 1;
		this.result = new ResultTileScore();
	}
	public TestTileLevel(int i) {
		super();
		this.levelNumber=i;
		this.result=new ResultTileScore();
	}
	public void gameOver()
	{
		iPorcupineController.getElements().clear();
	}
	
}

