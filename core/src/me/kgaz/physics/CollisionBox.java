package me.kgaz.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import me.kgaz.assets.Assets;
import me.kgaz.world.Level;

public class CollisionBox {

    public Array<CollisionCheck> colliionChecks;

    public CollisionBox(Assets assets){

        Texture t = assets.GREEN_DOT;

        colliionChecks = new Array<CollisionCheck>();

        for(int i = 0; i <= 6; i++) {

            for(int h = 0; h <= 8; h++) {

                int x = 12 + (i*6);

                int y = (h*6);

                if((x == 12 || x == 48) || (y == 0 || y == 48)) colliionChecks.add(new CollisionCheck(x, y, t));

            }

        }

    }

    public Vector calculateMovement(Vector movement, Position position, Level level) {

        int possibleX = (int) movement.x, possibleY = (int) movement.y;

        for(CollisionCheck check : colliionChecks) {

            Vector possible = check.calculateMovement(movement, position, level);

            if(possibleX < 0 && possible.x > possibleX) {
                possibleX = (int) possible.x;
            } else if(possibleX >= 0 && possible.x < possibleX) possibleX = (int) possible.x;

            if(possibleY< 0 && possible.y > possibleY) {
                possibleY = (int) possible.y;
            } else if(possibleY >= 0 && possible.y < possibleY) possibleY = (int) possible.y;

            if(possibleX == 0 && possibleY == 0) return new Vector(0,0);

        }

        return new Vector(possibleX, possibleY);

    }

    public boolean isOnGround(Position position, Level level) {

        Position newPos = new Position(position.x, position.y-1);

        for(CollisionCheck check : colliionChecks) {

            if(check.isInBlock(newPos,level)) return true;

        }

        return false;

    }

    public void render(SpriteBatch batch, Position position, Level level){

        colliionChecks.forEach(check -> check.render(batch, position, level));

    }

}
