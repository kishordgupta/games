package com.rhymes.game.stage.menus.stick;

import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class GameOver {

	public int gameState = 0;
	public InterfaceiPause iPause = null;

	public GameOver(int GameState, InterfaceiPause pause) {
		this.gameState = GameState;
		this.iPause = pause;
	}

	float sw;

	/**
	 * @param args
	 */
	public void main() {

		float x = GlobalVars.ge.getScreen().cam.position.x - 212f
				* LevelInfo.ratioX;
		float y = GlobalVars.ge.getScreen().cam.position.y - 70f
				* LevelInfo.ratioY;
		if (this.gameState == LevelInfo.GAME_WIN) {
			sw = Helper.getImageFromAssets(AssetConstants.IMG_BTN_LEVELCOMPLETE)
					.getRegionWidth() * LevelInfo.ratioX;
			x = GlobalVars.ge.getScreen().cam.position.x - sw / 2f;
			y = GlobalVars.ge.getScreen().cam.position.y
					- Helper.getImageFromAssets(AssetConstants.IMG_BTN_LEVELCOMPLETE)
							.getRegionHeight() / 2f * LevelInfo.ratioY;

			LevelComplete gm = new LevelComplete(x, y, Helper
					.getImageFromAssets(AssetConstants.IMG_BTN_LEVELCOMPLETE)
					.getRegionWidth()
					* LevelInfo.ratioX, Helper.getImageFromAssets(
					AssetConstants.IMG_BTN_LEVELCOMPLETE).getRegionHeight()
					* LevelInfo.ratioY, 10,
					AssetConstants.IMG_BTN_LEVELCOMPLETE);
			iPause.getStage().addElementZSorted(gm);
			iPause.getStage().subscribeToControllingInteraction(gm,
					InteractionTouch.class);
			gm.init();
			// ((TestTileLevel)GlobalVars.ge.getCurrentStage()).gameOver();

		} else if (this.gameState == LevelInfo.GAME_FAILED) {
			sw = Helper.getImageFromAssets(AssetConstants.IMG_BTN_GAMEOVER)
					.getRegionWidth()* LevelInfo.ratioX;
			x = GlobalVars.ge.getScreen().cam.position.x - sw / 2f ;
			y = GlobalVars.ge.getScreen().cam.position.y
					- Helper.getImageFromAssets(AssetConstants.IMG_BTN_GAMEOVER)
							.getRegionHeight() / 2f* LevelInfo.ratioY;
			gameOverScreen gm = new gameOverScreen(x, y, Helper
					.getImageFromAssets(AssetConstants.IMG_BTN_GAMEOVER)
					.getRegionWidth()
					* LevelInfo.ratioX, Helper.getImageFromAssets(
					AssetConstants.IMG_BTN_GAMEOVER).getRegionHeight()
					* LevelInfo.ratioY, 1, AssetConstants.IMG_BTN_GAMEOVER);
			iPause.getStage().addElementZAtTop(gm);
			iPause.getStage().subscribeToControllingInteraction(gm,
					InteractionTouch.class);
			gm.init();

			// ((TestTileLevel)GlobalVars.ge.getCurrentStage()).gameOver();
		}

	}

}
