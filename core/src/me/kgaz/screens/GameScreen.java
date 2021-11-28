package me.kgaz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.Main;
import me.kgaz.player.Player;
import me.kgaz.world.Level;
import me.kgaz.world.levels.MenuLevel;

public class GameScreen implements Screen {

    private final Main game;

    private Level currentLevel;

    private final Player player;

    private OrthographicCamera camera;

    public GameScreen(final Main main, Level start) {

        this.game = main;

        this.player = new Player(main, start.getDefaultPosition(), this);

        this.currentLevel = start;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    public Level getDisplayedLevel(){
        return currentLevel;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        SpriteBatch batch = game.batch;

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        currentLevel.render(game.batch);

        player.render(batch, currentLevel);

        if(Gdx.input.isTouched()){ // UI Manager

            Vector3 mousePos = new Vector3();

            mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(mousePos);

            player.click(mousePos.x, mousePos.y);

        }

        currentLevel.renderForeGround(game.batch);

        game.manager.ARIAL.draw(batch, "FPS: "+ Gdx.graphics.getFramesPerSecond()+". Ver. InDev 1.01\nFrame Time: "+Gdx.graphics.getDeltaTime()+"ms\nX: "+player.loc.x+" Y: "+player.loc.y+"\nGround: "+player.isOnGround(), 0, 1080);

        game.batch.end();
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
