package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pointGame.config.Constante;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constante.SCREEN_WIDTH;
		config.height = Constante.SCREEN_HEIGHT;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
