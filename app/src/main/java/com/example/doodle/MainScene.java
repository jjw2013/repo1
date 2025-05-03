package com.example.doodle;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {


    Random randG = new Random();
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

        add(Layer.controller, new platformGenerator(this));
        add(Layer.controller, new CollisionChecker(this, doodler));






    }

    // Game Loop Functions


    // Overridables
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return doodler.onTouch(event);
    }
}