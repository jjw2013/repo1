package com.example.doodle;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class platformGenerator implements IGameObject {


    private final MainScene scene;

    public platformGenerator(MainScene mainScene) {
        this.scene = mainScene;
        generate();
    }




    private void generate() {


        for (int i=0;i<60;i++){
            scene.add(MainScene.Layer.platform, new Platform(Metrics.width/2, 300 * i));
        }


        //Log.v(TAG, "Generating: wave " + wave + " : " + enemies.toString());
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

}