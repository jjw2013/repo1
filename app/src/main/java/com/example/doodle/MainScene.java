package com.example.doodle;

import android.os.Bundle;

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

    public MainScene(){

        for (int i = 0; i<40; i++) {

            add(new Platform(randG.nextFloat() * Metrics.width, 100f * i));
        }

        this.doodler= new Doodler();
        add(doodler);

        this.camera = new Camera(doodler);
        add(camera);






    }

    // Game Loop Functions
    @Override
    public void update() {
        super.update();
        checkCollision();
    }

    private void checkCollision() {


        int count = gameObjects.size();
        for(int i=count-1; i>=0 ; i--){
            count = gameObjects.size();
            if(CollisionHelper.collides())
        }

    }
}