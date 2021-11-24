package me.kgaz.userInterface.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.kgaz.userInterface.UI;

public class Button extends UI {

    private final Texture buttonTexture;

    private int widthExtended;
    private int heightExtended;

    private int heightDiff;
    private int widthDiff;

    private Runnable clickAction;

    public Button(int x, int y, int width, int height, String pathToTexture) {
        super(x, y, width, height);

        clickAction = null;

        widthExtended = (int) (width*1.1);
        heightExtended = (int) (height*1.1);

        heightDiff = heightExtended - height;
        widthDiff = widthExtended - width;

        buttonTexture = new Texture(pathToTexture);
    }

    @Override
    public void onClick() {

        if(clickAction != null) clickAction.run();

    }

    public void setClickAction(Runnable run) {

        this.clickAction = run;
    }

    @Override
    public void onHover() {

    }

    @Override
    public void onUnHover() {

    }

    @Override
    public void render(SpriteBatch batch) {

        if(super.isHovered()) {

            batch.draw(buttonTexture, x - (widthDiff/2), y - (heightDiff/2), widthExtended, heightExtended);

        } else batch.draw(buttonTexture, x, y, width, height);

    }

    @Override
    public void dispose() {

        buttonTexture.dispose();

    }

    @Override
    public int getX(){

        if(isHovered()) {

            return x - (widthDiff/2);

        }

        return x;

    }

    @Override
    public int getY(){

        if(isHovered()) {

            return y - (heightDiff/2);

        }

        return y;

    }

    @Override
    public int getWidth(){

        if(isHovered()) return widthExtended;

        return width;

    }

    @Override
    public int getHeight() {

        if(isHovered()) return heightExtended;

        return height;

    }

}
