package com.rhymes.game.stage.menus.stick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;

public class InstructionsMenu extends StageBase {
	public InstructionList instruction;

	public InstructionsMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BKG);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION1);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION2);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION3);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION4);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION5);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION6);
//		assetPack.addTexture(AssetConstants.IMG_INSTRUCTION7);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACK);
		assetPack.addTexture(AssetConstants.IMG_BTN_BACKWARD);
		assetPack.addTexture(AssetConstants.IMG_BTN_FORWARD);
		return assetPack;

	}

	@Override
	public void loadElements() {
		loadFonts();

		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		addElementZSorted(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_INSTRUCTION_BKG));

		float x = 0;
		float y = 0;

		x = 50f * LevelInfo.ratioX;
		y = 300f * LevelInfo.ratioY;
		ButtonBackward buttonbackward = new ButtonBackward(x, y,
				50f * LevelInfo.ratioX, 40f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_BACKWARD);
		this.elements.add(buttonbackward);
		subscribeToControllingInteraction(buttonbackward,
				InteractionTouch.class);

		x = 924f * LevelInfo.ratioX;
		y = 300f * LevelInfo.ratioY;
		ButtonForward buttonforward = new ButtonForward(x, y,
				50f * LevelInfo.ratioX, 40f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_FORWARD);
		this.elements.add(buttonforward);
		subscribeToControllingInteraction(buttonforward, InteractionTouch.class);

		x = 400f * LevelInfo.ratioX;
		y = 70f * LevelInfo.ratioY;
		ButtonBackToMainmenu buttonBack = new ButtonBackToMainmenu(x, y,
				258f * LevelInfo.ratioX, 77f * LevelInfo.ratioY, 1,
				AssetConstants.IMG_BTN_BACK);
		this.elements.add(buttonBack);
		subscribeToControllingInteraction(buttonBack, InteractionTouch.class);

		// x = 150f * LevelInfo.ratioX;
		// y = 200f * LevelInfo.ratioY;
		// instruction = new InstructionList(x, y, 724f * LevelInfo.ratioX,
		// 400f * LevelInfo.ratioY, 1);
		// this.elements.add(instruction);

	}

	String instruction_1 = "\n\n - Touch the left side of your screen to brake and right side of your screen to accelerate. \n\n- Tilt your handset to right and left to lean the bike to front and backwards. ";

	String instruction_2 = "\n\n - Prepare yourself to unleash your stunts through 25 different levels of puzzling difficulties. After level 5 you will find elevators to get a lift up or down,"
			+ "\n\n - Drive carefully to look for them. The elevators are marked by green color."
			+ "\n\n - Control your speed through the ups and downs and jumpings, because this is going to be a HELL of a bumpy ride. Use the brake while needed and accelerate"
			+ " when you need the speed. You never know what comes at the next scene!";

	String instruction_3 = "\n\n - While going up or coming down or jumping, control ur bike by tilting your handset to lean forward or backward, so that you can continue to show your stunt"
			+ " in one piece."
			+ "\n\n - Levels are going to be tough after the first few levels,so, practise and emerge as the best biker in the stick world ever seen.";

	String instruction_string = instruction_1;

	@Override
	public void tick(long stepTime) {
		fontController.getFont(AssetConstants.FONT_1).setColor(0.1f, 0.1f,
				0.1f, 0.8f);
		fontController.getFont(AssetConstants.FONT_1).drawWrapped(
				GlobalVars.ge.getScreen().getBatch(), instruction_string,
				170 * LevelInfo.ratioX, 560 * LevelInfo.ratioY,
				724 * LevelInfo.ratioX);
	}

	public void switchInstruction(boolean next) {
		if (next) {
			if (instruction_string.compareTo(instruction_2) == 0)
				instruction_string = instruction_3;
			if (instruction_string.compareTo(instruction_1) == 0)
				instruction_string = instruction_2;
		} else {
			if (instruction_string.compareTo(instruction_2) == 0)
				instruction_string = instruction_1;
			if (instruction_string.compareTo(instruction_3) == 0)
				instruction_string = instruction_2;
		}
	}

	public void loadFonts() {
		fontController.addFont(AssetConstants.FONT_1, AssetConstants.FONT_1,
				35, 30);
	}

}
