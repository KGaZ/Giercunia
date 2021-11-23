package me.kgaz.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import me.kgaz.Main;
import me.kgaz.world.Level;

public class TestLevelScreen implements Screen {

    private Main main;
    private Level currentLevel;

    public TestLevelScreen(Main main, Level level) {

        this.currentLevel = level;
        this.main = main;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 1, 0);

        main.batch.begin();

        currentLevel.render(main.batch);

        main.batch.end();
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
