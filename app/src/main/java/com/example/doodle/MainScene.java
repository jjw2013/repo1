package com.example.doodle;

import android.view.MotionEvent;

import Platforms.PlatformGenerator;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class MainScene extends Scene {


    private final Doodler doodler;
    private final Camera camera;


    public enum Layer {
        bg, enemy, bullet, platform ,doodler, ui, controller;
        public static final int COUNT = values().length;
    }

    public MainScene(){


        initLayers(Layer.COUNT);



        this.doodler= new Doodler();
        add(Layer.doodler, doodler);

        this.camera = new Camera(doodler);
        add(Layer.controller, camera);



        add(Layer.controller, new PlatformGenerator(this));
        add(Layer.controller, new CollisionChecker(this, doodler));






    }

    // Game Loop Functions


    // Overridables
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return doodler.onTouch(event);
    }
}