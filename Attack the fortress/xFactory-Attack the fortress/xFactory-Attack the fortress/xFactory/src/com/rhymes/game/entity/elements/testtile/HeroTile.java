package com.rhymes.game.entity.elements.testtile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.interactions.ICFlick;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.testtile.ICHeroController;
import com.rhymes.game.interactions.testtile.IHeroController;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.ge.pw.sound.SoundHandler.soundType;
import com.rhymes.helpers.Helper;
import com.rhymes.sundayLawn.ResultTileScore;

public class HeroTile extends ElementBase implements ICHeroController, ICFlick {
	public static final float posDif = 3f;
	public static final float heroTileStepTimeArcade = 180f;
	public static final float heroTileStepTimeChallenge = 180f;

	@Override
	public Point getLocation() {
		point.x = heroPosX;
		point.y = heroPosY;
		return point;
	}

	@Override
	public float getX() {
		return heroPosX;
	}

	@Override
	public float getY() {
		return heroPosY;
	}

	@Override
	public void setX(float x) {
		super.setX(x);
		heroPosX = x;
	}

	@Override
	public void setY(float y) {
		super.setY(y);
		heroPosY = y;
	}

	public int row = 0;
	public int col = 0;
	public float herosizeX=TileSet.TILE_SIZE_X;
	public float herosizeY=TileSet.TILE_SIZE_Y;
	public String imgname="";
	public Texture tex;
	public float heroPosX=0f;
	public float heroPosY=0f;
	public int move=0;
	public static int tileMowedNumber=0;
	
	public int getRow()
	{
		return this.row;
	}
	public int getCol()
	{
		return this.col;
	}

	@Override
	public void render()
	{
		float x=heroPosX;
		float y=heroPosY;
		if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero1"+".png").getTexture() || tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero2"+".png").getTexture())
		{
			x-=TileSet.TILE_SIZE_X;
		}
		else if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero1"+".png").getTexture() || tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero2"+".png").getTexture()) 
		{
			y-=TileSet.TILE_SIZE_Y;
		} 		
		GlobalVars.ge.getScreen().getBatch().draw(tex,
				x,y , herosizeX, herosizeY, 0, 0,
				tex.getWidth(), tex.getHeight(), false, false);
	}

	public HeroTile(int row, int col,int HeroType,String imgName) {
		this.row = row;
		this.col = col;
		imgname= imgName;	
	}

	/**
	 * Add all the texture needed by this element in this function
	 */
	@Override
	public void getAssets(AssetPack assetPack) {
//		assetPack.addTexture(AssetConstants.SOUND_BOX);
//		assetPack.addTexture(AssetConstants.SOUND_WIN);
//		assetPack.addTexture(AssetConstants.SOUND_LOSE);
//		
		//return assetPack;
	}

	@Override
	public void init() {
		tileMowedNumber=0;
		LevelInfo.LEVEL_OVER=LevelInfo.LEVEL_PLAY;
		tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+imgname+".png").getTexture();
		heroPosX=col * TileSet.TILE_SIZE_X;
		heroPosY=row * TileSet.TILE_SIZE_Y;
		if(imgname.compareTo("0_downHero1")==0  || imgname.compareTo("0_upHero1")==0)
		{						
			herosizeX=TileSet.TILE_SIZE_X;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
		}
		else if(imgname.compareTo("0_leftHero1")==0  || imgname.compareTo("0_rightHero1")==0)
		{
			herosizeX=TileSet.TILE_SIZE_X*2f;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
		}
	}

	float skipTime = 200;
	float elapsedTime = 0;
	boolean tileChange=false;
	public static float StepTime=16f;
	boolean movehero=false;
	public static boolean isTimecount=false;
	public boolean isAnimate=false;
	public void toggleAnimate(){
		if(isAnimate)
			isAnimate=false;
		else
			isAnimate=true;
	}
	public boolean getAmimation()
	{
		return isAnimate;
	}
	float skiptimeAnimation = 150f;
	float skiptimeAnimationChallenge = 150f;
	float elaspedtimeAnimation=0f;	
	private boolean active = true;
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	float getAnimationStepTime()
	{
		if(LevelInfo.GAME_MODE == 1)
			return skiptimeAnimationChallenge;
		else
			return skiptimeAnimation;
	}
	@Override
	public void step(long stepTime) 
	{
		
		if(!isTimecount)
			return;
//		Helper.println("Hero step: 1" );
		StepTime=stepTime;
		if(!active)			
			return;
//		Helper.println("Hero step: 2" );
		elaspedtimeAnimation+=stepTime;
		
//		Helper.println("Hero step: " + elaspedtimeAnimation);
		
		if(elaspedtimeAnimation > getAnimationStepTime())
		{
			elaspedtimeAnimation=0;
			toggleAnimate();
		}
		if(!isCollied)
		{
			onMove(tempMove);
			continueMove(move);
		}
//		Helper.println("Hero step: 3" );
	}
	
	public static float getAdvStep()
	{
		if(LevelInfo.GAME_MODE == 1)
			return Gdx.graphics.getDeltaTime() * 1000 /heroTileStepTimeChallenge * TileSet.TILE_SIZE_X;
		else
			return Gdx.graphics.getDeltaTime()* 1000 /heroTileStepTimeArcade * TileSet.TILE_SIZE_X;
	}
	public static float heroLastMoved = -1;
	Point tileLocation = new Point();
private float tilelength=0f;

	private void move(int mv) {
		float posDif = getAdvStep();
		heroLastMoved = posDif;
//		Helper.println("hero last moved:" + heroLastMoved);
		if (mv == TileSet.MOVE_LEFT) {
//			heroPosX -= (posDif)*LevelInfo.ratioX;
//			tilelength +=posDif*LevelInfo.ratioX;
			heroPosX -= (posDif);
			tilelength +=posDif;

			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero2"+".png").getTexture();
			}
			
			herosizeX=TileSet.TILE_SIZE_X*2f;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
			if(this.getLocation().x<GlobalVars.ge.getScreen().cam.position.x)
			((TestTileLevel) GlobalVars.ge.getCurrentStage()).moveCamera(mv);
		} else if (mv == TileSet.MOVE_RIGHT) {
//			heroPosX += (posDif)*LevelInfo.ratioX;
//			tilelength +=posDif*LevelInfo.ratioX;
			heroPosX += (posDif);
			tilelength +=posDif;
			if(isAnimate){			
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero1"+".png").getTexture();
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero2"+".png").getTexture();
			}
			herosizeX=TileSet.TILE_SIZE_X*2f;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
			if(this.getLocation().x>GlobalVars.ge.getScreen().cam.position.x)
				((TestTileLevel) GlobalVars.ge.getCurrentStage()).moveCamera(mv);
		} else if (mv == TileSet.MOVE_UP) {
//			heroPosY += (posDif)*LevelInfo.ratioY;
//			tilelength +=posDif*LevelInfo.ratioY;
			heroPosY += (posDif);
			tilelength +=posDif;
			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero2"+".png").getTexture();
			}
			herosizeX=TileSet.TILE_SIZE_X;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
			if(this.getLocation().y>GlobalVars.ge.getScreen().cam.position.y)
				((TestTileLevel) GlobalVars.ge.getCurrentStage()).moveCamera(mv);
		} else if (mv == TileSet.MOVE_DOWN) {
//			heroPosY -= (posDif)*LevelInfo.ratioY;
//			tilelength +=posDif*LevelInfo.ratioY;
			heroPosY -= (posDif);
			tilelength +=posDif;
			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero2"+".png").getTexture();
			}
			
			herosizeX=TileSet.TILE_SIZE_X;
			herosizeY=TileSet.TILE_SIZE_Y*2f;
			if(this.getLocation().y<GlobalVars.ge.getScreen().cam.position.y)
				((TestTileLevel) GlobalVars.ge.getCurrentStage()).moveCamera(mv);
		}
	}
	int heroDirec=0;
	public int heroMove(){
		return move;
	}
	
	public void continueMove(int mv) {
		int collidedTileType = ((TestTileLevel) GlobalVars.ge.getCurrentStage())
				.getTileset().getTileTypeOnMove(this.row, this.col, mv);

		switch (collidedTileType) {
		case TileSet.TILE_TYPE_1:
			break;
		case TileSet.TILE_TYPE_2:
//			move=mv;
	
				move(mv);
			
			if(tilelength>=TileSet.TILE_SIZE_X || tilelength==0f)
				{
					Tilemove(mv, tilelength);
				
//					Helper.println("length: "+tilelength);
					tilelength=Math.min(10f,tilelength-TileSet.TILE_SIZE_X);
				}		
			break;
			
		case TileSet.TILE_BLANK:
//			move=mv;
			
				move(mv);
			
			if(tilelength>=TileSet.TILE_SIZE_X || tilelength==0f)
				{
					Tilemove(mv, tilelength);	
					tilelength=Math.min(10f,tilelength-TileSet.TILE_SIZE_X);
//					Helper.println("length: "+tilelength);
				}		
			break;

		default:
			break;
		}
	}
	
	int tempMove=0;
	@Override
	public void onMove(int mv) {
//		Helper.println("Hero onMove(): " + mv);
		try{
		tempMove=mv;
		int collidedTileType = ((TestTileLevel) GlobalVars.ge.getCurrentStage())
				.getTileset().getTileTypeOnMove(this.row, this.col, mv);

		switch (collidedTileType) {
		case TileSet.TILE_TYPE_1:
			break;
		case TileSet.TILE_TYPE_2:
			if(tilelength>=TileSet.TILE_SIZE_X || tilelength<=10f*LevelInfo.ratioX){
			move=mv;
			isTimecount=true;
			}
			break;
		case TileSet.TILE_BLANK:
			if(tilelength>=TileSet.TILE_SIZE_X || tilelength<=10f*LevelInfo.ratioX){
			move=mv;
			isTimecount=true;
			}
			break;

		default:
			break;
			
			
		}
		}catch(Exception e){}
	}
	public boolean isCollied=false;
	public void onCollisionPorcupine(Porcupine p)

	{
		StartupInfo.sound_handler.playSound(soundType.SOUND_LOSE);
		isTimecount=false;
		isCollied=true;
		((ResultTileScore)((TestTileLevel)GlobalVars.ge.getCurrentStage()).result).consumeTile(false);
		//ResultTileScore.count=0;
		((TestTileLevel)GlobalVars.ge.getCurrentStage()).unSubscribeToInteraction(this, IHeroController.class);
		if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero1"+".png").getTexture() ||tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero2"+".png").getTexture())
		{
			tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"upDestroy"+".png").getTexture();
			herosizeX=68f*LevelInfo.ratioX;
			herosizeY=73f*LevelInfo.ratioY;
			heroPosY=(row * TileSet.TILE_SIZE_Y)-32f*LevelInfo.ratioY;
			heroPosX=(col * TileSet.TILE_SIZE_X)-16f*LevelInfo.ratioX;
		}
		else if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero1"+".png").getTexture() || tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero2"+".png").getTexture())
		{
			tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"downDestroy"+".png").getTexture();
		herosizeX=68f*LevelInfo.ratioX;
		herosizeY=73f*LevelInfo.ratioY;
		heroPosY=row * TileSet.TILE_SIZE_Y;
		heroPosX=(col * TileSet.TILE_SIZE_X)-16f*LevelInfo.ratioX;
		}
		else if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero1"+".png").getTexture() || tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero2"+".png").getTexture())
		{
			tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"leftDestroy"+".png").getTexture();
		herosizeX=89f*LevelInfo.ratioX;
		herosizeY=52f*LevelInfo.ratioY;
		heroPosY=row * TileSet.TILE_SIZE_Y;
		heroPosX=(col * TileSet.TILE_SIZE_X);
		}
		else if(tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero1"+".png").getTexture() || tex == Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero2"+".png").getTexture())
		{
			tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"rightDestroy"+".png").getTexture();
		herosizeX=89f*LevelInfo.ratioX;
		herosizeY=52f*LevelInfo.ratioY;
		heroPosY=row * TileSet.TILE_SIZE_Y;
		heroPosX=(col * TileSet.TILE_SIZE_X)-32f*LevelInfo.ratioX;
		}
	
		
		float	x = GlobalVars.ge.getScreen().cam.position.x-128f*LevelInfo.ratioX;
		float	y = GlobalVars.ge.getScreen().cam.position.y-16f*LevelInfo.ratioY;
		gameOverScreen gm=new gameOverScreen(x,y,256f*LevelInfo.ratioX,50f*LevelInfo.ratioY,1,AssetConstants.IMG_GAMEOVER);
		((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(gm);
		((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(gm, InteractionTouch.class);
		
		((TestTileLevel)GlobalVars.ge.getCurrentStage()).gameOver();
		
	}
	
	private void Tilemove(int mv, float tilelength2) { 
		if (mv == TileSet.MOVE_LEFT) {
			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_leftHero2"+".png").getTexture();
			}
			
			herosizeX=TileSet.TILE_SIZE_X*2f;
			herosizeY=TileSet.TILE_SIZE_Y*2f;

			col--;
			if (col < 0)
				col = 0;
			
			heroPosX=(col) * TileSet.TILE_SIZE_X - (tilelength2-TileSet.TILE_SIZE_X);
			heroPosY=row * TileSet.TILE_SIZE_Y;
			if(TileSet.tiles[row][col].image==Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0"+".png")){
			TileSet.tiles[row][col].image=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"1"+".png");
			tileMowedNumber++;
			StartupInfo.sound_handler.playSound(soundType.SOUND_BOX);
//			heroDirec=mv;
			}
		} else if (mv == TileSet.MOVE_RIGHT) {
			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_rightHero2"+".png").getTexture();
			}
			
			herosizeX=TileSet.TILE_SIZE_X*2f;
			herosizeY=TileSet.TILE_SIZE_Y*2f;

			col++;
				if (col > TileSet.MAX_COL)
					col = TileSet.MAX_COL - 1;

				heroPosX=(col) * TileSet.TILE_SIZE_X  + (tilelength2-TileSet.TILE_SIZE_X);
				heroPosY=row * TileSet.TILE_SIZE_Y;
				
				
				if(TileSet.tiles[row][col].image==Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0"+".png")){
				TileSet.tiles[row][col].image=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"1"+".png");
				tileMowedNumber++;
				StartupInfo.sound_handler.playSound(soundType.SOUND_BOX);
//				heroDirec=mv;
				}	
			} else if (mv == TileSet.MOVE_UP) {
				if(isAnimate){
					tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero1"+".png").getTexture();	
				}
				else{
					tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_upHero2"+".png").getTexture();
				}
				
				herosizeX=TileSet.TILE_SIZE_X;
				herosizeY=TileSet.TILE_SIZE_Y*2f;

				row++;
				if (row > TileSet.MAX_ROW)
					row = TileSet.MAX_ROW - 1;

				heroPosX=col * TileSet.TILE_SIZE_X;
				heroPosY=(row) * TileSet.TILE_SIZE_Y + (tilelength2-TileSet.TILE_SIZE_X);
				
				if(TileSet.tiles[row][col].image==Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0"+".png")){
				TileSet.tiles[row][col].image=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"1"+".png");
				tileMowedNumber++;
				StartupInfo.sound_handler.playSound(soundType.SOUND_BOX);
//				heroDirec=mv;
				}
		} else if (mv == TileSet.MOVE_DOWN) {
			if(isAnimate){
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero1"+".png").getTexture();	
			}
			else{
				tex = Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0_downHero2"+".png").getTexture();
			}
			
			herosizeX=TileSet.TILE_SIZE_X;
			herosizeY=TileSet.TILE_SIZE_Y*2f;

			row--;
			if (row < 0)
				row = 0;	

			heroPosY=(row) * TileSet.TILE_SIZE_Y - (tilelength2-TileSet.TILE_SIZE_X);
			heroPosX=col * TileSet.TILE_SIZE_X;
			
			if(TileSet.tiles[row][col].image==Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"0"+".png")){
			TileSet.tiles[row][col].image=Helper.getImageFromAssets(AssetConstants.IMAGE_PATH+"1"+".png");
			tileMowedNumber++;
			StartupInfo.sound_handler.playSound(soundType.SOUND_BOX);
//			heroDirec=mv;
			
			}
		
		}
	 boolean isShit=false;
		if(TileSet.tiles[row][col].image==Helper.getImageFromAssets(AssetConstants.IMG_DOGSHIT_BIG)){
			TileSet.tiles[row][col].image=Helper.getImageFromAssets(AssetConstants.IMG_TBLOCK_1);
			isShit=true;
			MinusScore ms=new MinusScore(this.heroPosX,this.heroPosY);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(ms);
		}
		
		for(int i=0;i<((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().size();i++)
		{		
			float x1=((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().get(i).getX();
			float y1=((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().get(i).getY();
			float x2=this.heroPosX;
			float y2=this.heroPosY;
			
			if(x2>=x1 && x2<(x1+TileSet.TILE_SIZE_X))
			{
				if(y2>=y1 && y2<(y1+TileSet.TILE_SIZE_Y))
				{
					Helper.println("hero"+x2+"and"+y2);
					Helper.println("sX"+x1+"and"+(x1+TileSet.TILE_SIZE_X));
					Helper.println("sY"+y1+"and"+(y1+TileSet.TILE_SIZE_Y));
						
				
					if(((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().get(i).isActiveSwitch())
					((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().get(i).setActiveSwitch(false);
					else
					((TestTileLevel)GlobalVars.ge.getCurrentStage()).getWSW().get(i).setActiveSwitch(true);
				}
			}
		}
		percentage=(100*tileMowedNumber)/TileSet.MOW_NUMBER;
		if(TileSet.MOW_NUMBER==tileMowedNumber)
		{
			
			isTimecount=false;
			StartupInfo.sound_handler.playSound(soundType.SOUND_WIN);
			//ResultTileScore.count=0;
			LevelInfo.LEVEL_OVER=LevelInfo.LEVEL_COMPLETE;
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).unSubscribeToInteraction(this, IHeroController.class);
			float	x = GlobalVars.ge.getScreen().cam.position.x-128f*LevelInfo.ratioX;
			float	y = GlobalVars.ge.getScreen().cam.position.y-16f*LevelInfo.ratioY;
			LevelComplete gm=new LevelComplete(x,y,256f*LevelInfo.ratioX,50f*LevelInfo.ratioY,1,AssetConstants.IMG_BTN_LEVELCOMPLETE);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).addElement(gm);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).subscribeToControllingInteraction(gm, InteractionTouch.class);
			((TestTileLevel)GlobalVars.ge.getCurrentStage()).gameOver();
			active = false;
		}
		((ResultTileScore)((TestTileLevel)GlobalVars.ge.getCurrentStage()).result).consumeTile(isShit);
	}

	public int percentage=0;
	public int getPercentage(){
		
		return	percentage;
	}

	@Override
	public void onFlick(float time, float distance, float angle,
			Point startPoint, Point endPoint) {
//		Helper.printKeyVal("HeroTile: Angle: ", angle);
		
		if( angle > -45f && angle < 45f){
			onMove(TileSet.MOVE_RIGHT);
//			Helper.printKeyVal("HeroTile: move: ", TileSet.MOVE_RIGHT);
		}
		else if( angle > 45f && angle < 135f){
			onMove(TileSet.MOVE_UP);
//			Helper.printKeyVal("HeroTile: move: ", TileSet.MOVE_UP);
		}
		else if( angle > 135f && angle < 220f){
			onMove(TileSet.MOVE_LEFT);
//			Helper.printKeyVal("HeroTile: move: ", TileSet.MOVE_LEFT);
		}
		else if( angle > 220f && angle < 360f){
			onMove(TileSet.MOVE_DOWN);
//			Helper.printKeyVal("HeroTile: move: ", TileSet.MOVE_DOWN);
		}
		else if( angle < -45f && angle > -135f){
			onMove(TileSet.MOVE_DOWN);
//			Helper.printKeyVal("HeroTile: move: ", TileSet.MOVE_DOWN);
		}
	}

}
