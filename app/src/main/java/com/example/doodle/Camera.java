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

    public static float worldX, worldY;
    public static float deadline;

    private float speed;

    public static boolean lock_camera_y_with_deadline = true;

    public Camera(Doodler doodler){
        this.doodler = doodler;
    }

    public void update() {
        worldX = doodler.getX();
        worldY = doodler.getY();

        if(worldY < Metrics.height/2)
            worldY= Metrics.height/2;

        if(lock_camera_y_with_deadline){
            if(worldY - Metrics.height/2 <deadline)
                worldY = deadline + Metrics.height/2;
        }

        deadline= worldY-Metrics.height/2;
    }

    public static void unlock_camera_y_with_deadline(){
        lock_camera_y_with_deadline =false;
    }

    public static float getCameraY(float y){
        return y + (Metrics.height/2) - worldY;
    }

    @Override
    public void draw(Canvas canvas) {


    }
}