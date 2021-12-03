package me.kgaz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.kgaz.Main;

public class DesktopLauncher {

	public static final int GAME_WIDTH = 1920;
	public static final int GAME_HEIGHT = 1080;

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Jump King 2";

		config.width = 1440;
		config.height = 810;

		config.foregroundFPS = 60;

		config.fullscreen = true;

		config.resizable = false;

		//config.resizable = false;

		new LwjglApplication(new Main(), config);

	}

}
