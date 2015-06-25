package com.rhymes.game.stage.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.piku.Birds;
import com.rhymes.game.entity.elements.piku.Boat;
import com.rhymes.game.entity.elements.piku.Bonus;
import com.rhymes.game.entity.elements.piku.Clouds;
import com.rhymes.game.entity.elements.piku.Destroyer;
import com.rhymes.game.entity.elements.piku.Joint;
import com.rhymes.game.entity.elements.piku.JointsInfo;
import com.rhymes.game.entity.elements.piku.Pearl;
import com.rhymes.game.entity.elements.piku.PlanePathSet;
import com.rhymes.game.entity.elements.piku.ScoreStar;
import com.rhymes.game.entity.elements.piku.Star;
import com.rhymes.game.entity.elements.piku.TextTime;
import com.rhymes.game.entity.elements.piku.Transformer;
import com.rhymes.game.entity.elements.piku.Transporter;
import com.rhymes.game.entity.elements.ui.ButtonPause;
import com.rhymes.game.entity.elements.ui.ButtonQuit;
import com.rhymes.game.entity.elements.ui.ButtonRestart;
import com.rhymes.game.entity.elements.unsorted.BPath;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.entity.elements.unsorted.NumericText;
import com.rhymes.game.interactions.IPathTraversal;
import com.rhymes.game.interactions.ISingleCollision;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.interactions.rangeTraversal.IRangePathTraversal;
import com.rhymes.game.interactions.rangeTraversal.InfoRangeTraversal;
import com.rhymes.game.stage.menus.GameOverScreen;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;
import com.rhymes.helpers.XMLReader;

public class XLevel extends StageBase{
	ISingleCollision collider;
	PlanePathSet pathSet;
	
	public Result result = null;
	private int levelNumber;

	private TextTime time;
	private NumericText score;
	private NumericText scoreTotalPearl;
	
	private ScoreStar scoreStar;
	
	public int numOfPlanesOnBoard = 0;
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
//		assetPack.addTexture(AssetConstants.IMG_BKG_TEAL);
		assetPack.addTexture(AssetConstants.IMG_SAIL);
		assetPack.addTexture(AssetConstants.IMG_ROAD_WHITE);
		assetPack.addTexture(AssetConstants.IMG_PEARL);
		assetPack.addTexture(AssetConstants.IMG_BOAT);
		assetPack.addTexture(AssetConstants.IMG_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_PLAY);
		assetPack.addTexture(AssetConstants.IMG_CLOSE);
//		assetPack.addTexture(AssetConstants.STITCH);
		assetPack.addTexture(AssetConstants.IMG_BRUSH_BLUE_3);
//		assetPack.addTexture(AssetConstants.IMG_BKG_BLUE);
//		assetPack.addTexture(AssetConstants.IMG_BKG_RED);
//		assetPack.addTexture(AssetConstants.IMG_BKG_GREEN);
//		assetPack.addTexture(AssetConstants.IMG_BKG_PURPLE);
//		assetPack.addTexture(AssetConstants.IMG_BKG_BROWN);

//		assetPack.addTexture(AssetConstants.IMG_PLANE_GREEN_1);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_NORMAL_1);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_RED_1);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_RED_2);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_RED_3);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_RED_4);
//		assetPack.addTexture(AssetConstants.IMG_PLANE_RED_5);
		
		assetPack.addTexture(AssetConstants.IMG_ROAD_GREEN);
		assetPack.addTexture(AssetConstants.IMG_ROAD_NORMAL);
		assetPack.addTexture(AssetConstants.IMG_ROAD_RED);
//		assetPack.addTexture(AssetConstants.IMG_BKG_GREEN_TRANSITION);
		assetPack.addTexture(AssetConstants.IMG_RELOAD);
		assetPack.addTexture(AssetConstants.IMG_QUIT);
		
		assetPack.addTexture(AssetConstants.IMG_PLAY);
		assetPack.addTexture(AssetConstants.IMG_BACK_2);
		assetPack.addTexture(AssetConstants.IMG_JOINT);
		assetPack.addTexture(AssetConstants.IMG_BONUS_5);
//		assetPack.addTexture(AssetConstants.IMG_BUTTON_HOLDER);
		
		for(int i =1 ; i < 7; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_STAR_FOLDER_PATH + i + ".png");
		}
		
		for(int i =2 ; i <= 7; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_BIRDS_FOLDER_PATH + i + ".png");
		}
		
		for(int i =2 ; i <= 3; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_CLOUDS_FOLDER_PATH + i + ".png");
		}
		
		for(int i = 1 ; i <= 7; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_TRANSFORMER_GREEN_PATH + i + ".png");
		}
		
		for(int i = 1 ; i <= 7; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_TRANSFORMER_RED_PATH + i + ".png");
		}
		
		for(int i = 1 ; i <= 4; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_MAGICAL_OBJECT_PATH + i + ".png");
		}
/*		
		for(int i = 1 ; i <= 7; i++)
		{
			assetPack.addTexture(AssetConstants.IMG_TRANSFORMER_BLACK_PATH + i + ".png");
		}
*/		
		return assetPack;
	}
	
	
	public XLevel()
	{
		super();
		this.levelNumber = 1;
		this.result = new ResultBTRClassic();
	}
	
	public XLevel(int levelNumber, Result result)
	{
		super();
		this.levelNumber = levelNumber;
		this.result = result;
		
		if(result instanceof ResultBTRTime)
		{
			((ResultBTRTime)result).setStarCollected(0);
			((ResultBTRTime)result).setPearlCollected(10);
			((ResultBTRTime)result).setElapsedTime(0);
			((ResultBTRTime)result).setExpireTime(10 * 1000);
		}
		else if(result instanceof ResultBTRMAP)
		{
//			((ResultBTRMAP)result).setStarCollected(0);
		}
		else if(result instanceof ResultBTRClassic)
		{
			((ResultBTRClassic)result).setStarCollected(0);
		}
		
	}
	
	private void initVars()
	{
		this.numOfPlanesOnBoard = 0;
	}
	
	@Override
	public void loadElements() {
		Helper.println("Starting XLevel...");
		result.reset();
		initVars();
		
//		this.interactions.add(new InteractionLeftRight());
		this.interactions.add(new InteractionTouch());
		this.interactions.add(new IPathTraversal());
		this.interactions.add(new IRangePathTraversal());
		
		this.gameControlInteractions.add(new InteractionTouch());
		
		collider = new ISingleCollision();
		this.interactions.add(collider);
		
		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_2));
		
//		this.elements.add(new Background(Gdx.graphics.getWidth() - 135, Gdx.graphics.getHeight() - 50, 135, 
//				50, 1, AssetConstants.IMG_BUTTON_HOLDER));
		
		readXML();
		
		this.topElements.add(new Birds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1));
		this.topElements.add(new Birds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1));
		this.topElements.add(new Birds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1));
//		this.topElements.add(new Birds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1));
//		this.topElements.add(new Birds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1));
		
		this.topElements.add(new Clouds(50, 60, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 1 , 2));
		this.topElements.add(new Clouds(50, 60, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 1 , 3));
//		this.topElements.add(new Clouds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1 , 3));
//		this.topElements.add(new Clouds(50, 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1 , 3));
		
		addControls();
		
		
		
		if(this.result instanceof ResultBTRMAP){
			score = new NumericText(10, Gdx.graphics.getHeight()- 20, 15, 30, 1);
			this.elements.add(score);
			
			scoreTotalPearl = new NumericText(10, Gdx.graphics.getHeight()- 60, 15, 30, 1);
			this.elements.add(scoreTotalPearl);			
		}

		else if(this.result instanceof ResultBTRTime){
			time = new TextTime(Gdx.graphics.getWidth() - 80, 40, 15, 30, 1);
			this.elements.add(time);
			
			score = new NumericText(10, Gdx.graphics.getHeight()- 20, 15, 30, 1);
			this.elements.add(score);

			scoreStar = new ScoreStar(10, Gdx.graphics.getHeight()- 80, 15, 30, 1);
			this.elements.add(scoreStar);
		}
		else if(this.result instanceof ResultBTRClassic)
		{
			scoreStar = new ScoreStar(10, Gdx.graphics.getHeight()- 40, 15, 30, 1);
			this.elements.add(scoreStar);
		}
	
	}

	
	
	private void addControls() {

		ButtonRestart bRestart = new ButtonRestart(Gdx.graphics.getWidth() - 129, Gdx.graphics.getHeight() - 47, 34, 34, 1, AssetConstants.IMG_RELOAD);
		this.topElements.add(bRestart);
		subscribeToControllingInteraction(bRestart, InteractionTouch.class);
		
		ButtonPause bPause = new ButtonPause(Gdx.graphics.getWidth() - 89, Gdx.graphics.getHeight() - 47, 34, 34, 1, AssetConstants.IMG_PAUSE);
		this.topElements.add(bPause);
		subscribeToControllingInteraction(bPause, InteractionTouch.class);
		
		ButtonQuit bQuit = new ButtonQuit(Gdx.graphics.getWidth() - 48, Gdx.graphics.getHeight() - 47, 34, 34, 1, AssetConstants.IMG_QUIT, levelNumber);
		this.topElements.add(bQuit);
		subscribeToControllingInteraction(bQuit, InteractionTouch.class);
		
	}


	private void readXML()
	{
		if(this.gameState == GameState.RELOADING){
			loadPath(AssetConstants.FILE_LEVEL_INFO + levelNumber + ".xml", false);
		}
		else
			loadPath(AssetConstants.FILE_LEVEL_INFO + levelNumber + ".xml", true);
		
		XMLReader xml=new XMLReader();
		xml.AddElementS();
	}
	
	Array<Pearl> pearls = new Array<Pearl>();
	public int totalPearls = 0;
	public void addPearl(Pearl pearl)
	{
		pearls.add(pearl);
		addElement(pearl);
		subscribeToInteraction(pearl, ISingleCollision.class);
		totalPearls++;
	}
	public void resetPearls()
	{
		for(Pearl pearl:pearls)
		{
			pearl.setCollided(false);
//			Helper.printKeyVal("Pearl id: ", pearl.getId());
			addElement(pearl);
			subscribeToInteraction(pearl, ISingleCollision.class);
		}
	}
	
	Birds birds;
	private void loadPath(String fileName, boolean reload)
	{
		if(!reload){
			if(this.pathSet != null)
			{
//				Helper.println("Reseting path...");
				this.pathSet.reset();
				this.elements.add(this.pathSet);				
				if(result instanceof ResultBTRMAP || result instanceof ResultBTRTime)
					resetPearls();
				subscribeToInteraction(pathSet, InteractionTouch.class);
				return;
			}
		}
		else{
//			Helper.println("Game state: " + this.gameState );
//			Helper.println("Elements size: " + elements.size );
			pathSet = new PlanePathSet(10, 12, 23, 121, 1);
			this.elements.add(pathSet);
			subscribeToInteraction(pathSet, InteractionTouch.class);
			XMLReader.reset();
			XMLReader.main(fileName);
			addPearls(this.getPlanePathSet());
		}
	}

	private float pearlDistance = 5; 
	private Pearl pearl;
	private int nodesPassed = 0;
	public void addPearls(PlanePathSet pathSet) {
		for(Path p: pathSet.getPaths()){
			for(PathNode pn:p.getNodes())
			if(nodesPassed++ > pearlDistance)
			{
				nodesPassed = 0;
				pearl = new Pearl( pn.getLocation());
				((XLevel)GlobalVars.ge.getCurrentStage()).addPearl(pearl);
			}
		}
	}

	private Array<TraversePointInfo> constructJointInfo()
	{
		Array<TraversePointInfo> tinfosArray = new Array<TraversePointInfo>();
//		for(int i = 0 ; i < pathSet.getPaths().size; i++)
//		{
//			if(i == 0)
//				continue;
//			tinfosArray.add(pathSet.getPaths().get(i).startTraverse(i * 50, Path.METHOD_RIGHT));
//		}
		tinfosArray.add(pathSet.getPaths().get(2).startTraverse(0, Path.METHOD_RIGHT));
		return tinfosArray;
	}
	
	public void bringPlane(TraversePointInfo info)
	{
		Boat p = new Boat(info);
		p.setPlaneType(((BPath)info.getPath()).getColor());
		p.setPathTraversalDirection(Path.METHOD_RIGHT);
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		
		TraversePointInfo info2 = new TraversePointInfo();
		info2.initialize(info);

		p = new Boat(info2);
		p.setPlaneType(((BPath)info2.getPath()).getColor());
		p.setPathTraversalDirection(Path.METHOD_LEFT);
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		
		this.numOfPlanesOnBoard += 2;
	}

	
	public void addPlane(TraversePointInfo info, int method)
	{
		Boat p = new Boat(info);
		p.setPlaneType(((BPath)info.getPath()).getColor());
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		this.numOfPlanesOnBoard++;
		p.setPathTraversalDirection(method);
	}
	
	public void addJointPlanes(TraversePointInfo info, Boat collidedBoat, Joint joint)
	{
		Boat p = new Boat(info);
		p.setPlaneType(collidedBoat.getPlaneType());
		p.lastCollidedJoint = collidedBoat.lastCollidedJoint;
		p.addCollider(joint);
		p.setPathTraversalDirection(Path.METHOD_RIGHT);
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		
		
		
		TraversePointInfo info2 = new TraversePointInfo();
		info2.initialize(info);
		
		info2.getPath().traverse(info2, 10, Path.METHOD_LEFT);

		p = new Boat(info2);
		p.setPathTraversalDirection(Path.METHOD_LEFT);
//		if(p.check()){
			p.setPlaneType(collidedBoat.getPlaneType());
			p.lastCollidedJoint = collidedBoat.lastCollidedJoint;
			p.addCollider(joint);
			this.addElement(p);	
			subscribeToInteraction(p, IPathTraversal.class);
			collider.addHero(p);
			
			this.numOfPlanesOnBoard += 2;
//		}
//		else
//			this.numOfPlanesOnBoard++;
		
	}
	
	
	public Boat addTransportedPlane(TraversePointInfo info, int method, int type)
	{
		Boat p = new Boat(info);
		Helper.println("\n\n\n\n\n\nCreating Transported boat: " + p.getId());
		p.setPlaneType(type);
		p.setPathTraversalDirection(method);
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		this.numOfPlanesOnBoard++;
		return p;
	}
	
	public Boat addTransportedPlane(Boat p, int method, int type)
	{
		Helper.println("\n\n\n\n\n\nCreating Transported boat: " + p.getId());
		p.setPlaneType(type);
		p.setPathTraversalDirection(method);
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		this.numOfPlanesOnBoard++;
		return p;
	}
	
	public void addPlane(TraversePointInfo info)
	{
		Boat p = new Boat(info);
		p.setPlaneType(((BPath)info.getPath()).getColor());
		this.addElement(p);	
		subscribeToInteraction(p, IPathTraversal.class);
		collider.addHero(p);
		this.numOfPlanesOnBoard++;
	}
	
	public void removePlane(Boat p)
	{
		postToRemoveList(p);
		unSubscribeToInteraction(p, IPathTraversal.class);
		unSubscribeToInteraction(p, ISingleCollision.class);
		collider.removeHero(p);
		this.numOfPlanesOnBoard--;
		if(numOfPlanesOnBoard == 0){
			Helper.println("Num of boats: " + numOfPlanesOnBoard);
			this.result.setState(GameState.OVER);
		}
	}
	
	public void addTransporter(TraversePointInfo info, TraversePointInfo destinationInfo)
	{
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = 0;
		infoRangeTraversal.rightRange = 0;
		infoRangeTraversal.method = Path.METHOD_RIGHT;
		infoRangeTraversal.traverseInfo = info;
		
		Helper.printKeyVal("Transporter starting point: ", info.getTraverseLocation().toString());
		
		Transporter t = new Transporter(infoRangeTraversal, destinationInfo);
//		addElement(t);
		this.topElements.add(t);
//		addElement(new Background(info.getPoint().x, info.getPoint().y, 20, 20, 1, AssetConstants.IMG_BKG_GREEN));
		
		subscribeToInteraction(t, IRangePathTraversal.class);
		subscribeToInteraction(t, ISingleCollision.class);
	}
	
	

	public void addTransformer(TraversePointInfo info, int colorType)
	{
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = -20;
		infoRangeTraversal.rightRange = 20;
		infoRangeTraversal.method = Path.METHOD_RIGHT;
		infoRangeTraversal.traverseInfo = info;
		
		Transformer t = new Transformer(infoRangeTraversal, colorType);
		addElement(t);
//		addElement(new Background(info.getPoint().x, info.getPoint().y, 20, 20, 1, AssetConstants.IMG_BKG_GREEN));
		
		subscribeToInteraction(t, IRangePathTraversal.class);
		subscribeToInteraction(t, ISingleCollision.class);
	}
	
	public void addStar(TraversePointInfo info)
	{
		
		if(!(this.result instanceof ResultBTRTime || this.result instanceof ResultBTRClassic ))
			return;
		Helper.println("Adding start at: " + info.toString());
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = 0;
		infoRangeTraversal.rightRange = 0;
		infoRangeTraversal.method = Path.METHOD_RIGHT;
		infoRangeTraversal.traverseInfo = info;
		
		Star t = new Star(infoRangeTraversal);
		addElement(t);

		subscribeToInteraction(t, IRangePathTraversal.class);
		subscribeToInteraction(t, ISingleCollision.class);
	}
	
	public void addDestroyer(TraversePointInfo info)
	{
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = 0;
		infoRangeTraversal.rightRange = 0;
		infoRangeTraversal.method = Path.METHOD_RIGHT;
		infoRangeTraversal.traverseInfo = info;
		
		Destroyer t = new Destroyer(infoRangeTraversal);
		addElement(t);
//		addElement(new Background(info.getPoint().x, info.getPoint().y, 20, 20, 1, AssetConstants.IMG_BKG_GREEN));
		
		subscribeToInteraction(t, IRangePathTraversal.class);
		subscribeToInteraction(t, ISingleCollision.class);
	}

	public void addJoint(TraversePointInfo info, Array<JointsInfo> jointsInfo)
	{
		InfoRangeTraversal infoRangeTraversal = new InfoRangeTraversal();
		infoRangeTraversal.currentDistance = 0;
		infoRangeTraversal.leftRange = 0;
		infoRangeTraversal.rightRange = 0;
		infoRangeTraversal.method = Path.METHOD_LEFT;
		infoRangeTraversal.traverseInfo = info;
		
		Joint t = new Joint(infoRangeTraversal, jointsInfo);
		addElement(t);
//		addElement(new Background(info.getPoint().x, info.getPoint().y, 20, 20, 1, AssetConstants.IMG_BKG_GREEN));
		
//		subscribeToInteraction(t, IRangePathTraversal.class);
		subscribeToInteraction(t, ISingleCollision.class);
	}
	
	public PlanePathSet getPlanePathSet(){
		return pathSet;
	}
	
	
	private void gameOver() {
		Helper.println("\n\n\nGame is over!");
		Helper.println("Result: " + this.result.toString());
//		this.pause();
		GlobalVars.ge.loadStage(new GameOverScreen(levelNumber, this.result));			

	}

	@Override
	public void tick(long stepTime) {
		if(this.gameState == GameState.PLAYING){
//			Helper.println("Result: " + result.toString()) ;
			if(this.result instanceof ResultBTRTime)
			{
				((ResultBTRTime)this.result).step(stepTime);
				time.setTime(((ResultBTRTime)this.result).getElapsedTime()/100);
				score.setNumber((long) ((ResultBTRTime)this.result).getPearlCollected());
				((ResultBTRTime)result).setLevelPearlNumber(this.totalPearls);
			}
			else if(this.result instanceof ResultBTRClassic)
			{
//				score.setNumber((long) ((ResultBTRClassic)this.result).getTotalTraversedDistance());
//				Helper.printKeyVal("Score: ", score.getNumber());
//				Helper.printKeyVal("Total traversed: ", (long) ((ResultBTRClassic)this.result).getTargetDistance());
			}
			else if(this.result instanceof ResultBTRMAP)
			{
				score.setNumber((long) ((ResultBTRMAP)this.result).getPearlCollected());
				scoreTotalPearl.setNumber(this.totalPearls);
				((ResultBTRMAP)result).setLevelPearlNumber(this.totalPearls);
			}
		}
		if(this.result.getState() == GameState.OVER && this.gameState == GameState.PLAYING)
		{
			gameOver();
		}
	}
	
	public static final int BONUS_5 = 0;
	public static final int BONUS_50 = 1;

	public void brintBonus(float x, float y, int type)
	{
		if(type == BONUS_5)
		{
			this.elements.add(new Bonus(x, y, AssetConstants.IMG_BONUS_5));
		}
	}
	public void printBoatsInfo()
	{
		for(ElementBase boat: getElemensByType(Boat.class)){
			((Boat)boat).printInfo();
		}
	}
}
