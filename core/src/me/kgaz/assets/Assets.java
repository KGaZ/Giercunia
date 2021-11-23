package me.kgaz.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import me.kgaz.Main;
import me.kgaz.garbage.Dispose;
import me.kgaz.world.Tiles;

public class Assets implements Dispose {

    public final TextureAtlas TILESET_PLAINS;
    public final TextureAtlas LEVEL_BACKGROUNDS;

    public final Tiles TILESET;


    public Assets(Main main){

        LEVEL_BACKGROUNDS = new TextureAtlas("beta/atlases/backgroundAtlas.atlas");

        TILESET_PLAINS = new TextureAtlas("beta/textures/tileset_plains.atlas");

        TILESET = new Tiles(this);

    }

    @Override
    public void dispose(){

        TILESET_PLAINS.dispose();
        LEVEL_BACKGROUNDS.dispose();

    }

}
