package com.rhymes.game.interactions.testtile;

import javax.xml.bind.JAXBElement.GlobalScope;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.interactions.InteractionBase;
import com.rhymes.helpers.Helper;

public class ICameraController extends InteractionBase {
	public float CAMERA_STEP = 5;
	@Override
	public void checkCondition(long elapsedTime) {

	}

	@Override
	public void takeAction() {
		if(Gdx.app.getType() == ApplicationType.Desktop){
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				GlobalVars.ge.getScreen().cam.translate(-CAMERA_STEP, 0, 0);
			else if (Gdx.input.isKeyPressed(Keys.RIGHT))
				GlobalVars.ge.getScreen().cam.translate(CAMERA_STEP, 0, 0);
			else if (Gdx.input.isKeyPressed(Keys.UP))
				GlobalVars.ge.getScreen().cam.translate(0, CAMERA_STEP, 0);
			else if (Gdx.input.isKeyPressed(Keys.DOWN))
				GlobalVars.ge.getScreen().cam.translate(0, -CAMERA_STEP, 0);
		}
	}

}
