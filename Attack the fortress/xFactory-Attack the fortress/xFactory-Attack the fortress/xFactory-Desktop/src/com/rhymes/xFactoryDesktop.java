/**
 * 
 */

package com.rhymes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rhymes.ge.pw.GameApplication;

public class xFactoryDesktop {

	private static final int SCREEN_WIDTH = 1024;
	private static final int SCREEN_HEIGHT = 600;

	public static void main (String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		config.vSyncEnabled = true;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;

		new LwjglApplication(new GameApplication(), config);
//		new JoglApplication(new GameApplication(), "Animator", 480, 320, false);
	}
}
