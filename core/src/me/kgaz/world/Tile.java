package me.kgaz.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.Main;
import me.kgaz.assets.Assets;

public class Tile {

    public static final int TILE_SIZE = 8;

    private TextureRegion texture;
    private boolean solid;
    private int id;

    public Tile(Boolean solid, Assets assets, Tiles tiles, int id) {

        this.id =id;
        this.texture = assets.TILESET_PLAINS.findRegion("tile051");
        this.solid = solid;
        tiles.tileSet[id] = this;

    }

    private boolean isSolid(int x, int y) {
        return solid;
    }

    public void render(SpriteBatch batch, int x, int y) {

        if(id != 0) batch.draw(texture, x, y, TILE_SIZE, TILE_SIZE);

    }

}
