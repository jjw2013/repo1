package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Background extends Sprite implements ILayerProvider, IGameObject {

    private static final float PLANE_WIDTH = Metrics.width;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 368 / 337;


    private RectF tempDstRect;
    public Background(){
        super(R.mipmap.note_background);
        setPosition(Metrics.width/2,PLANE_HEIGHT/2,PLANE_WIDTH, PLANE_HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        tempDstRect= dstRect;



        float camera_y_bottom = Camera.worldY - Metrics.height/2;
        float camera_y_top = Camera.worldY + Metrics.height/2;
        float current_bg_bottom = 0;
        float current_bg_top = PLANE_HEIGHT;

        int bg_cliping_count = 0;

        while(current_bg_bottom < camera_y_top){
            if(current_bg_top <camera_y_bottom ) {
                current_bg_bottom +=PLANE_HEIGHT;
                current_bg_top += PLANE_HEIGHT;
                bg_cliping_count++;
                continue;
            }

            setPosition(Metrics.width/2,
                    PLANE_HEIGHT/2 + PLANE_HEIGHT*bg_cliping_count,
                    PLANE_WIDTH, PLANE_HEIGHT);

            super.setDstRectWithCamera(Camera.getCameraY(y));
            super.draw(canvas);
            super.revertDstRect();

            current_bg_bottom +=PLANE_HEIGHT;
            current_bg_top += PLANE_HEIGHT;
            bg_cliping_count++;

        }

        setPosition(Metrics.width/2,PLANE_HEIGHT/2,PLANE_WIDTH, PLANE_HEIGHT);


    }

    @Override
    public Enum getLayer() {
        return MainScene.Layer.bg;
    }
}
