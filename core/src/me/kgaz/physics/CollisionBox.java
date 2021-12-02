package me.kgaz.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import me.kgaz.assets.Assets;
import me.kgaz.player.Player;
import me.kgaz.world.Level;

public class CollisionBox {

    public Array<CollisionCheck> collisionChecks;

    public CollisionBox(Assets assets){

        Texture t = assets.GREEN_DOT;

        collisionChecks = new Array<CollisionCheck>();

        for(int i = 0; i <= 6; i++) {

            for(int h = 0; h <= 8; h++) {

                int x = 12 + (i*6);

                int y = (h*6);

                if((x == 12 || x == 48) || (y == 0 || y == 48)) collisionChecks.add(new CollisionCheck(x, y));

            }

        }

    }

    public Vector calculateMovement(Player player, Vector movement, Position position, Level level) {

        for(CollisionCheck check : collisionChecks) {

            CollisionData.CollisionType collisionType = check.canMove(player, movement, position, level);

            if(collisionType != CollisionData.CollisionType.NO_COLLISION) {

                switch(collisionType) {

                    case HIT_WALL_LEFT:

                        player.debugCollision(CollisionData.CollisionType.HIT_WALL_LEFT);

                        if(Math.abs(movement.x) > 5 && !player.isOnGround()) {

                            player.bounced();

                            return new Vector(-movement.x*0.7f, movement.y*0.9f - 0.5f);

                        }

                        return new Vector(0, 0);

                    case HIT_WALL_RIGHT:

                        player.debugCollision(CollisionData.CollisionType.HIT_WALL_RIGHT);

                        if(Math.abs(movement.x) > 5 && !player.isOnGround()) {

                            player.bounced();

                            return new Vector(-movement.x*0.7f, movement.y*0.9f - 0.5f);

                        }

                        return new Vector(0, 0);

                    case HIT_WALL_DOWN:

                        player.debugCollision(CollisionData.CollisionType.HIT_WALL_DOWN);

                        if(movement.y < -30) {

                            player.fellDown();

                        }

                        return new Vector(0, 0);

                    case HIT_WALL_UP:

                        player.debugCollision(CollisionData.CollisionType.HIT_WALL_UP);

                        player.bounced();

                        return new Vector(movement.x*0.8f, movement.y*-0.5f);


                }

            }

        }

        return movement;

    }

    private static final Vector vectorGround = new Vector(0, -1);

    public boolean isOnGround(Player player, Position position, Level level) {

        for(CollisionCheck check : collisionChecks) {

            if(check.canMove(player, vectorGround, position, level) != CollisionData.CollisionType.NO_COLLISION) return true;

        }

        return false;

    }

    public void render(SpriteBatch batch, Position position, Level level){
//
//        colliionChecks.forEach(check -> check.render(batch, position, level));

    }

}
