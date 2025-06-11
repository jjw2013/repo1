package com.example.doodle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;

import Platforms.PlatformGenerator;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class MainScene extends Scene  {


    private final Doodler doodler;
    private final Camera camera;

    private final NumberPainter score;

    public enum Layer {
        bg, enemy, bullet, platform,item, debris ,doodler, ui, controller;
        public static final int COUNT = values().length;
    }

    public MainScene(){

        Camera.onEnter();




        initLayers(Layer.COUNT);

        add(Layer.bg, new Background());


        this.score = new NumberPainter(0,0);
        add(MainScene.Layer.ui, score);

        this.doodler= new Doodler(score);
        add(Layer.doodler, doodler);

        this.camera = new Camera(doodler);
        add(Layer.controller, camera);







        add(Layer.controller, new PlatformGenerator(this, doodler));
        add(Layer.controller, new CollisionChecker(this, doodler));











    }

    // Game Loop Functions
    // Overridables


    private float tiltX = 0;
    public void onTiltEvent(float x){

        this.tiltX= x;
        if(doodler != null){
            doodler.ontiltevent(tiltX);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return doodler.onTouch(event);
   }



}