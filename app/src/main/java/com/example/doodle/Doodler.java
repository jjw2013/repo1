package com.example.doodle;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite {

    private static final float GRAVITY = 180f;
    private boolean isFalling= false;

    private float speed = 500f;
    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 185 / 227;


    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height-200,PLANE_WIDTH, PLANE_HEIGHT);
        dy = -speed;

    }

    @Override
    public void update() {
        super.update();

        dy +=GRAVITY*GameView.frameTime;

        if(y>Metrics.height){
            dy = -speed;
        }



    }
}