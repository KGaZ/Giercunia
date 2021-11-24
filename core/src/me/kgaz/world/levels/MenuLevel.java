package me.kgaz.world.levels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.utils.LevelBuilder;
import me.kgaz.world.Level;

public class MenuLevel extends Level {

    public MenuLevel(Assets assets) {
        super(assets, assets.LEVEL_BACKGROUNDS.findRegion("plainsBackground"), null);

    }

    @Override
    public int[][] buildLevel() {

        return new LevelBuilder().setGroundLevel(20).
                fillBlock(0, 0, 20, 100).
                fillBlock(70, 0, 100, 70).
                fillBlock(40, 50, 20, 5).build();
    }

}
