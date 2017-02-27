package com.mcelrea.firstservertry.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcelrea.firstservertry.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//eric mcelrea
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
