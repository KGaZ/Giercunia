package me.kgaz.garbage;

import com.badlogic.gdx.utils.Array;

public class DisposeManager {

    private Array<Dispose> disposables;

    public DisposeManager(){

        disposables = new Array<Dispose>();

    }

    public void dispose(){

        disposables.forEach(Dispose::dispose);

        disposables.clear();

    }


    public void registerDisposable(Dispose dispose) {

        disposables.add(dispose);

    }


}
