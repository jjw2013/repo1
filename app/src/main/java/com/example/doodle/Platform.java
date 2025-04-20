package com.example.doodle;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class Platform extends Sprite {

    private static final float PLATFORM_WIDTH = 175f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;

    public Platform(float rand_x, float rand_y){
        super(R.mipmap.platform);

        setPosition(rand_x,rand_y,PLATFORM_WIDTH, PLATFORM_HEIGHT);

    }

    @Override
    public void draw(Canvas canvas) {

        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
    }
}