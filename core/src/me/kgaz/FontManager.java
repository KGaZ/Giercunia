package me.kgaz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import me.kgaz.garbage.Dispose;

public class FontManager implements Dispose {

    public final BitmapFont ESTONIA;

    public FontManager(final Main game){

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Estonia-Regular.ttf"));

        { // ESTONIA

            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 102;
            ESTONIA = generator.generateFont(parameter);

        }

        generator.dispose();

        game.getDisposeManager().registerDisposable(this);

    }


    @Override
    public void dispose() {

        ESTONIA.dispose();

    }
}
