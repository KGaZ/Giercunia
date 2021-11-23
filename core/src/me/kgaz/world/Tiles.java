package me.kgaz.world;

import me.kgaz.Main;
import me.kgaz.assets.Assets;

public class Tiles {

    public final Tile AIR_TILE;
    public final Tile FULL_TILE;

    public final Tile[] tileSet;

    public Tiles(Assets assets){

        tileSet = new Tile[10];

        AIR_TILE = new Tile(false, assets, this, 0);
        FULL_TILE = new Tile(true, assets, this, 1);

    }

}
