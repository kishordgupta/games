package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class TestLevel extends StageBase implements InterfaceiPause{

	ScoreManage score=new ScoreManage();
	public Text textDistance;
	public Text textTime;
	public Text textLevelNumber;
	public Text textPercent;
	public ButtonPause pause;
	public TestLevel(int levelNumber) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG);
		assetPack.addTexture(AssetConstants.IMG_BTN_PAUSE);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESTART);
		assetPack.addTexture(AssetConstants.IMG_BTN_RESUME);
		assetPack.addTexture(AssetConstants.IMG_BTN_QUITLEVEL);
		assetPack.addTexture(AssetConstants.IMG_SELECT);
		assetPack.addTexture(AssetConstants.IMG_BTN_GAMEOVER);
		assetPack.addTexture(AssetConstants.IMG_BTN_LEVELCOMPLETE);
		return assetPack;
	}

	public void loadFonts()
	{
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_1, 30, 20);
	}
	
	@Override
	public void loadElements() {
		loadFonts();
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1,AssetConstants.IMG_BKG));
		
		float x=0;
		float y=0;
		
//		x = GlobalVars.ge.getScreen().cam.position.x+Gdx.graphics.getWidth()/2f-37f*LevelInfo.ratioX;
//		y = GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-25f*LevelInfo.ratioY;
		x=Gdx.graphics.getWidth()-55f*LevelInfo.ratioX;
		y=Gdx.graphics.getHeight()-45f*LevelInfo.ratioY;
		pause = new ButtonPause(x , y, 50f*LevelInfo.ratioX, 40f*LevelInfo.ratioY, 1,AssetConstants.IMG_BTN_PAUSE,this);
		this.elements.add(pause);
		subscribeToControllingInteraction(pause, InteractionTouch.class);
		
		
		GameOver go=new GameOver(LevelInfo.GAME_FAILED, this);
		go.main();
		addText();
	}

	public void addText(){
		float x=20f*LevelInfo.ratioX;
		float y=Gdx.graphics.getHeight()-30f*LevelInfo.ratioY;
		textDistance=new Text(x, y , fontController, AssetConstants.FONT_1, "Distance: "+score.getDistance());
		addElement(textDistance);

		x=x+350f*LevelInfo.ratioX;
		textTime=new Text(x, y , fontController, AssetConstants.FONT_1, "Time: "+score.getTime());
		addElement(textTime);
		
		x=x+350f*LevelInfo.ratioX;
		textLevelNumber=new Text(x, y , fontController, AssetConstants.FONT_1, "Level: "+LevelInfo.currentLevelNumber);
		addElement(textLevelNumber);
		
		x=20f*LevelInfo.ratioX;
		y=10f*LevelInfo.ratioY;
		textPercent=new Text(x, y , fontController, AssetConstants.FONT_1, "Percent: "+getPercentage());
		addElement(textPercent);
	}
	public void setStep(long stepTime){
		currentDistance+=5;
		score.setTime(stepTime);
		score.setDistance(currentDistance);
		getPercentage();
		
		textDistance.setText("Distance: "+score.getDistance());
		textTime.setText("Time: "+score.getTime());
		textLevelNumber.setText("Level: "+LevelInfo.currentLevelNumber);
		textPercent.setText("Percent: "+getPercentage());
		
		float x = GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+20f*LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-30f*LevelInfo.ratioY;

		textDistance.setX(x);
		textDistance.setY(y);
		x=x+350f*LevelInfo.ratioX;
		textTime.setX(x);
		textTime.setY(y);
		x=x+350f*LevelInfo.ratioX;
		textLevelNumber.setX(x);
		textLevelNumber.setY(y);
		x=GlobalVars.ge.getScreen().cam.position.x-Gdx.graphics.getWidth()/2f+20f*LevelInfo.ratioX;
		y=GlobalVars.ge.getScreen().cam.position.y-Gdx.graphics.getHeight()/2f+10f*LevelInfo.ratioY;
		textPercent.setX(x);
		textPercent.setY(y);
		x=GlobalVars.ge.getScreen().cam.position.x+Gdx.graphics.getWidth()/2f-55f*LevelInfo.ratioX;
		y=GlobalVars.ge.getScreen().cam.position.y+Gdx.graphics.getHeight()/2f-45f*LevelInfo.ratioY;
		pause.setX(x);
		pause.setY(y);
	}
	@Override
	public void tick(long stepTime) {
		setStep(stepTime);
	}
	
	public double totalDistance=10000;
	public long currentDistance=0;
	public double getPercentage(){
		double percent=0;
		percent = currentDistance*100/totalDistance;
		return percent;
	}
	@Override
	public TestLevel getStage() {
		return this;
			
	}

	
}
