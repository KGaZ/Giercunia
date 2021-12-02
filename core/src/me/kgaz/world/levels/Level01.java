package me.kgaz.world.levels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;
import me.kgaz.utils.LevelBuilder;
import me.kgaz.world.Level;

public class Level01 extends Level {

    public Level01(Assets assets) {

        super(assets, assets.LEVEL_BACKGROUNDS.findRegion("plainsBackground"), null);

        super.leftLevel = MenuLevel.class;

    }

    @Override
    public int[][] buildLevel() {

        return new LevelBuilder().setGroundLevel(100).
                build();

    }

}
