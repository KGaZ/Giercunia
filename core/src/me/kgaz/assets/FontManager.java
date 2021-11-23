package me.kgaz.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import me.kgaz.Main;
import me.kgaz.garbage.Dispose;

public class FontManager implements Dispose {

    public final BitmapFont ESTONIA_192;
    public final BitmapFont ARIAL;

    public FontManager(final Main game){

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Estonia-Regular.ttf"));

        { // ESTONIA

            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 124;
            ESTONIA_192 = generator.generateFont(parameter);

        }

        { // ARIAL

            ARIAL = new BitmapFont();

        }

        generator.dispose();

        game.getDisposeManager().registerDisposable(this);

    }


    @Override
    public void dispose() {

        ESTONIA_192.dispose();
        ARIAL.dispose();

    }
}
