package com.example.doodle;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Camera implements IGameObject {

    private Doodler doodler;

    public static float realWorldX, realWorldY;

    public Camera(Doodler doodler){
        this.doodler = doodler;
    }

    public void update() {
        realWorldX = doodler.getX();
        realWorldY = doodler.getY();
    }

    public static float getCameraY(float y){
        return y + (Metrics.height/2) - realWorldY;
    }

    @Override
    public void draw(Canvas canvas) {


    }
}