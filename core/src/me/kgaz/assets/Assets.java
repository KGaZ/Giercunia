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

    //temp
    public final TextureAtlas PLAYER_TEXTURES;


    public Assets(Main main){

        PLAYER_TEXTURES = new TextureAtlas("beta/textures/player/playerPack.atlas");

        LEVEL_BACKGROUNDS = new TextureAtlas("beta/atlases/backgroundAtlas.atlas");

        TILESET_PLAINS = new TextureAtlas("beta/textures/tileset_plains.atlas");

        TILESET = new Tiles(this);

    }

    @Override
    public void dispose(){

        PLAYER_TEXTURES.dispose();
        TILESET_PLAINS.dispose();
        LEVEL_BACKGROUNDS.dispose();

    }

}
