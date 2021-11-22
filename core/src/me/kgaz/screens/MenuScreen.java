package me.kgaz.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.Main;

public class MenuScreen implements Screen {

    private final Main handler;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public MenuScreen(Main game){

        this.handler = game;

        this.batch = game.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 960);

    }

    private int i = 0;

    @Override
    public void render(float delta) {

        System.out.println(i);

        i++;

        if(i==255) i = -255;

        ScreenUtils.clear(i > 0 ? i/255f : -i/255f, 0, i > 0 ? i/255f : -i/255f, 0);

        camera.update();

        this.batch.setProjectionMatrix(camera.combined);

        this.batch.begin();

        handler.manager.ESTONIA.draw(batch, "Jebac Disa Kurwe Zwisa", 100, 700);

        this.batch.end();

        if(Gdx.input.isTouched()){

            handler.setScreen(new GameScreen(handler));
            dispose();

        }

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
