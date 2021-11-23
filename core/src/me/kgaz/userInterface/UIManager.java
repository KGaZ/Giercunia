package me.kgaz.userInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import me.kgaz.garbage.Dispose;

public class UIManager implements Dispose {

    private Array<UI> uiElements;

    public UIManager(){

        uiElements = new Array<UI>();

    }

    public void addUiElement(UI element) {

        uiElements.add(element);

    }


    public void update(float mouseX, float mouseY){

        uiElements.forEach(ui -> {

            ui.update((int) mouseX, (int) mouseY);

        });

    }

    public void render(SpriteBatch batch){

        uiElements.forEach(ui -> {

            ui.render(batch);

        });

    }

    public void dispose() {

        uiElements.forEach(UI::dispose);

    }


}
