package me.kgaz.physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import me.kgaz.world.Level;

public class CollisionBox {

    private Array<CollisionCheck> xChecks;
    private Array<CollisionCheck> yChecks;
//0 -50
//12-48
    public CollisionBox(){

        xChecks = new Array<CollisionCheck>();
        yChecks = new Array<CollisionCheck>();

        {//x CHECKS

            xChecks.add(new CollisionCheck(30, 0));

            xChecks.add(new CollisionCheck(30, 50));

        }

        {//x CHECKS

            yChecks.add(new CollisionCheck(12, 25));

            yChecks.add(new CollisionCheck(48, 25));

        }

        {//Both Checks

            CollisionCheck lD = new CollisionCheck(12, 0);
            CollisionCheck lU = new CollisionCheck(12, 50);
            CollisionCheck pD = new CollisionCheck(48, 0);
            CollisionCheck pU = new CollisionCheck(48, 50);

            yChecks.add(lD);xChecks.add(lD);
            yChecks.add(lU);xChecks.add(lU);
            yChecks.add(pD);xChecks.add(pD);
            yChecks.add(pU);xChecks.add(pU);

        }

    }

    public Vector checkMovement(Vector movement, Position pos, Level level) {

        int moveX = (int) movement.x, moveY = (int) movement.y;

        if(moveX != 0) {

            if(moveX > 0) {

                for(CollisionCheck rightChecks : xChecks) {

                    if(rightChecks.relativeX == 48) {

                        int move = rightChecks.checkDistanceRight(moveX, pos, level);

                        if(moveX > move) moveX = move;

                        if(move == 0) break;

                    }

                }

            } else {

                for(CollisionCheck upChecks : xChecks) {

                    if(upChecks.relativeX == 12) {

                        int move = upChecks.checkDistanceLeft(-moveX, pos, level);

                        if(moveX < move) moveX = move;

                        if(move == 0) break;

                    }

                }

            }

        }

        if(moveY != 0) {

            if(moveY > 0) {

                for(CollisionCheck upChecks : yChecks) {

                    if(upChecks.relativeY == 50) {

                        int move = upChecks.checkDistanceUp(moveY, pos, level, movement);

                        if(moveY > move) moveY = move;

                        if(move == 0) break;

                    }

                }

            } else {

                for(CollisionCheck downChecks : yChecks) {

                    if(downChecks.relativeY == 0) {

                        int move = downChecks.checkDistanceDown(-moveY, pos, level, movement);

                        if(moveY < move) moveY = move;

                        if(move == 0) break;

                    }

                }

            }

        }

        return new Vector(moveX, moveY);

    }



}
