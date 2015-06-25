package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.graphics.Texture;
import com.rhymes.game.carspeedpro.menu.gameover.GameOverCarSpeedPro;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class gameOverScreen extends Button {

	public float rowPos = 0;
	public float colPos = 0;
	public Texture tex;

//	@Override
//	public void render() {
//		super.render();
//		Helper.println("\nGameover x: " + this.x);
//		Helper.println("Gameover y: " + this.y);
//		Helper.println("Cam x: " + GlobalVars.ge.getScreen().cam.position.x);
//		Helper.println("Cam y: " + GlobalVars.ge.getScreen().cam.position.y);
//		// this.x = GlobalVars.ge.getScreen().cam.position.x - 212f
//		// * LevelInfo.ratioX;
//		// this.y = GlobalVars.ge.getScreen().cam.position.y - 70f
//		// * LevelInfo.ratioY;
//	}

	public gameOverScreen(float x, float y, float width, float height,
			int zIndex, String imagePath) {
		super(x, y, width, height, zIndex, imagePath);
	}

	public gameOverScreen(float x, float y, float width, float height,
			int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void onEvent(Point p) {
		// p.x=GlobalVars.ge.getScreen().cam.position.x+p.x-240f*LevelInfo.ratioX;
		// p.y=GlobalVars.ge.getScreen().cam.position.y+p.y-160f*LevelInfo.ratioY;
		// Helper.println("On touch: " + p);
		p.x = GlobalVars.ge.getScreen().cam.position.x + p.x - 512f
				* LevelInfo.ratioX;
		p.y = GlobalVars.ge.getScreen().cam.position.y + p.y - 320f
				* LevelInfo.ratioY;

		Helper.println("Gameover Button: .....");
		// Helper.println("Camera: x: " +
		// GlobalVars.ge.getScreen().cam.position.x);
		// Helper.println("Camera: y: " +
		// GlobalVars.ge.getScreen().cam.position.y);
		if (this.checkHit(p)) {
			Helper.println("back to mode menu game...");
			GlobalVars.ge.loadStage(new GameOverCarSpeedPro());

		}
	}

	// @Override
	// public void init() {
	// tex =
	// Helper.getImageFromAssets(AssetConstants.IMG_GAMEOVER).getTexture();
	// }

}
