package me.kgaz.utils;

public class LevelBuilder {

    private int[][] work;

    public LevelBuilder() {

        work = new int[240][135];

    }

    public LevelBuilder setGroundLevel(int level) {

        for (int i = 0; i < level; i++) {

            for(int h = 0; h < 240; h++) work[h][i] = 1;

        }

        return this;

    }

    public LevelBuilder empty(int x, int y, int width, int height) {

        for(int i = x; i < x+width; i++) {

            for(int h = y; h < y+height; h++) {

                work[i][h] = 0;

            }

        }

        return this;

    }

    public LevelBuilder fillBlock(int x, int y, int width, int height) {

        for(int i = x; i < x+width; i++) {

            for(int h = y; h < y+height; h++) {

                work[i][h] = 1;

            }

        }

        return this;

    }

    public int[][] build(){
        return work;
    }

}
