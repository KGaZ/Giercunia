package me.kgaz.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.kgaz.assets.Assets;

import java.io.*;
import java.util.Scanner;

public class Level {


    private TextureRegion foreGround;
    private TextureRegion backGround;
    private int[][] tileSet;

    private Tiles TILESET;

    public Level(Assets assets) {

        tileSet = new int[240][135];

        TILESET = assets.TILESET;

        backGround = assets.LEVEL_BACKGROUNDS.findRegion("plainsBackground");

        foreGround = null;

        for(int h = 0; h < 240; h++) {

            for(int i = 0; i < 135; i++) tileSet[h][i] = 0;

        }

        for(int h = 0; h < 240; h++) {

            for(int i = 0; i < 22; i++) {

                tileSet[h][i] = 1;

            }

        }
        save("xd");

    }

    @Deprecated
    public void save(String path) {


            // file.createNewFile();

            // FileWriter writer = new FileWriter(file);

            System.out.println("plainsBackground");
            System.out.println("null");
            for(int i = 0; i < 4; i++) System.out.println("null");
            for(int i = 0; i < 135; i++) {

                StringBuilder bath = new StringBuilder();

                for(int h = 0; h < 240; h++) {

                    bath.append(tileSet[h][i]).append(" ");

                }

                System.out.println(bath.toString());

            }

            //writer.flush();
            //writer.close();


    }

    public Level(Assets assets, String pathToFile){

        tileSet = new int[240][135];

        TILESET = assets.TILESET;

        File file = new File(pathToFile);

        try {

            Scanner scanner = new Scanner(file);

            int skip = 0;

            int row = 0;
            int column = 0;

            { // level data

                backGround = assets.LEVEL_BACKGROUNDS.findRegion(scanner.nextLine());
                foreGround = null;scanner.nextLine(); //TODO
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();


            }

            while(scanner.hasNextLine()) {

                String data = scanner.nextLine();

                column = 0;

                for(String line : data.split(" ")) {

                    if(column == 135) break;

                    tileSet[row][column] = Integer.parseInt(line);
                    column++;

                }

                row++;
            }

            if(row != 239) {

                System.exit(0);

            }

            scanner.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }


    }

    public void render(SpriteBatch batch){

        batch.draw(backGround, 0, 0, 1920, 1080);

        for(int x = 0; x < 240; x++) {

            for(int y = 0; y < 135; y++) {

                TILESET.tileSet[tileSet[x][y]].render(batch, x*Tile.TILE_SIZE, y*Tile.TILE_SIZE);

            }

        }

        if(foreGround != null) batch.draw(foreGround, 0, 0, 1920, 1080);


    }



}
