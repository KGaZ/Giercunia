package me.kgaz.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import me.kgaz.assets.Assets;
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

    public Vector calculateMovement(Vector movement, Position position, Level level) {

        float possibleX = movement.x;
        float possibleY = movement.y;

        // patrzymy co jest wieksze

        if(possibleX > possibleY) {

            // najpierw X, potem Y

            if(possibleX != 0) {

                if(possibleX > 0) {

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceRight(possibleX, level, position);

                        if(work < possibleX) possibleX = work;

                        if(work == 0) break;

                    }

                } else if (possibleX < 0){

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceLeft(-possibleX, level, position);

                        if(work > possibleX) possibleX = work;

                        if(work == 0) break;

                    }

                }

            }

            // liczymy Y

            if(possibleY != 0) {

                if(possibleY > 0) {

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceUp(possibleY, level, new Position(position.x + possibleX, position.y));

                        if(work < possibleY) possibleY = work;

                        if(work == 0) break;

                    }

                } else {

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceDown(-possibleY, level, new Position(position.x + possibleX, position.y));

                        if(work > possibleY) possibleY = work;

                        if(work == 0) break;

                    }

                }

            }

        } else {

            // najpierw Y, potem X

            if(possibleY != 0) {

                if(possibleY > 0) {

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceUp(possibleY, level, position);

                        if(work < possibleY) possibleY = work;

                        if(work == 0) break;

                    }

                } else {

                    for(CollisionCheck check : collisionChecks) {

                        float work = check.checkDistanceDown(-possibleY, level, position);

                        if(work > possibleY) possibleX = work;

                        if(work == 0) break;

                    }

                }

                if(possibleX != 0) {

                    if (possibleX > 0) {

                        for (CollisionCheck check : collisionChecks) {

                            float work = check.checkDistanceRight(possibleX, level, new Position(position.x, position.y + possibleY));

                            if (work < possibleX) possibleX = work;

                            if (work == 0) break;

                        }

                    } else {

                        for (CollisionCheck check : collisionChecks) {

                            float work = check.checkDistanceLeft(-possibleX, level, new Position(position.x, position.y + possibleY));

                            if (work > possibleX) possibleX = work;

                            if (work == 0) break;

                        }

                    }

                }

            }

        }
        return new Vector(possibleX, possibleY);

    }

    public boolean isOnGround(Position position, Level level) {

        for(CollisionCheck check : collisionChecks) {

            if(check.checkDistanceDown(1, level, position) == 0) return true;

        }

        return false;

    }

    public void render(SpriteBatch batch, Position position, Level level){
//
//        colliionChecks.forEach(check -> check.render(batch, position, level));

    }

}
