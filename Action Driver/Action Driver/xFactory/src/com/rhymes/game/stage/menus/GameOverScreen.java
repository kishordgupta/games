package com.rhymes.game.stage.menus;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.Score;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.ui.Background;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonLoadStage;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonQuit;
import com.rhymes.game.entity.elements.ui.actiondriver.ButtonSound;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.levels.LevelActionDriver;
import com.rhymes.ge.core.controller.FontController;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverScreen extends StageBase {
	Score score;
	public GameOverScreen(Score score)
	{
		this.score = score;
		Helper.println("GOV 1 Score: " + this.score.totalScore);
	}
	
	FontController fontController;
	@Override
	public void loadElements() {
//		Helper.println("Loading game over screen");

		fontController = new FontController();
		loadFonts();
		
		this.interactions.add(new InteractionTouch());
		addElementZSorted(new Background(0, 0, Helper.getScreenWidth(), Helper.getScreenHeight(), 0, AssetConstants.IMG_MENU_BACK));
		
//		float x = Helper.getScreenWidth()/2f - (2 * 64 + 10)/2f * Constants.rx;
		float x = 50*Constants.rx;
		addElementZSorted(new ButtonLoadStage(x, 20 * Constants.ry, 
				100 * Constants.rx, 100* Constants.ry, 1, AssetConstants.IMG_RELOAD, new LevelActionDriver()));
		addElementZSorted(new ButtonQuit(x + (100 + 20) * Constants.rx, 20 * Constants.ry, 
				100* Constants.rx, 100* Constants.ry, 1, AssetConstants.IMG_QUIT_ICON));
		
		addScores();
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_MENU_BACK);
		assetPack.addTexture(AssetConstants.IMG_RELOAD);
		assetPack.addTexture(AssetConstants.IMG_QUIT_ICON);
		return assetPack;
	}

	@Override
	public void tick(long stepTime) {
	}

	private void loadFonts() {
		fontController.addFont(Constants.fontName1, AssetConstants.FONT_1);
	}

	
	Text textScore, textCarCrashed, textCash, textTotal;
	private void addScores() {
		float y = 50 * Constants.ry;
		float dy = 100 * Constants.ry;
		float x = 300 ;
		Helper.println("GOV 2 Score: " + this.score.score);
		
		textTotal = new Text(x * Constants.rx, y, Constants.fontScoreScaleX, Constants.fontScoreScaleY, 
				fontController, Constants.fontName1, "Total Score: " + (int)(this.score.totalScore));
		textTotal.setzIndex(2);
		addElementZSorted(textTotal);
		
		y+=dy;
		textScore = new Text(x * Constants.rx, y, Constants.fontScoreScaleX, Constants.fontScoreScaleY, 
				fontController, Constants.fontName1, "Score: " + this.score.score);
		textScore.setzIndex(2);
		addElementZSorted(textScore);
		
		y += dy;
		textCash = new Text(x * Constants.rx, y, Constants.fontScoreScaleX, Constants.fontScoreScaleY, fontController, Constants.fontName1, "Cash: $" 
		+ this.score.cashCollected);
		textCash.setzIndex(2);
		addElementZSorted(textCash);
		
		y += dy;
		textCarCrashed = new Text(x * Constants.rx, y, Constants.fontScoreScaleX, Constants.fontScoreScaleY, fontController, Constants.fontName1, 
				"Car Crashed: " + this.score.carCrashed);
		textCarCrashed.setzIndex(2);
		addElementZSorted(textCarCrashed);
	}
	
	@Override
	public void resume() {
		super.resume();
		fontController.reloadFonts();
		fontController.getFont(Constants.fontName1).setScale(Constants.fontScoreScaleX, Constants.fontScoreScaleY);
	}

}
