package com.example.doodle;

import android.view.MotionEvent;

import java.util.ArrayList;

import Platforms.Platform;
import Platforms.PlatformGenerator;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MenuScene extends Scene {


    private final Doodler doodler;
    private final Camera camera;


    public enum Layer {
        bg, enemy, bullet, platform,item, debris ,doodler, ui, controller, image;
        public static final int COUNT = values().length;
    }

    public MenuScene(){


        initLayers(Layer.COUNT);

        add(Layer.bg, new Background());



        this.doodler= new Doodler();
        doodler.hult=true;
        add(Layer.doodler, doodler);

        this.camera = new Camera(doodler);
        add(Layer.controller, camera);

        add(Layer.ui, new playButton());
        add(Layer.ui, new scoreButton());
        add(Layer.image, new ImagePainter(R.mipmap.logo, (int)Metrics.width/4,(int)Metrics.height/4,
                500,594,267));




    }

    // Game Loop Functions


    // Overridables
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        ArrayList<IGameObject> buttons = objectsAt(MenuScene.Layer.ui);


        for (int e = buttons.size() - 1; e >= 0; e--) {
            Button b = (Button) buttons.get(e);
            b.onTouch(event);
        }

        return false;
    }
}