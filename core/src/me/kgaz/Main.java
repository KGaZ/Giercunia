package me.kgaz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.garbage.DisposeManager;
import me.kgaz.screens.MenuScreen;

import java.awt.*;

public class Main extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	private DisposeManager disposeManager;

	public FontManager manager;

	@Override
	public void create () {

		disposeManager = new DisposeManager();

		manager = new FontManager(this);

		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new MenuScreen(this));

	}

	@Override
	public void render () {

		super.render();

	}
	
	@Override
	public void dispose () {

		disposeManager.dispose();

		batch.dispose();
		font.dispose();

	}

	public DisposeManager getDisposeManager(){

		return disposeManager;

	}
}
