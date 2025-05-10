package com.example.doodle;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Background extends Sprite implements ILayerProvider, IGameObject {

    private static final float PLANE_WIDTH = Metrics.width;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 368 / 337;


    public Background(){
        super(R.mipmap.note_background);
        setPosition(Metrics.width/2,PLANE_HEIGHT/2,PLANE_WIDTH, PLANE_HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
    }

    @Override
    public Enum getLayer() {
        return MainScene.Layer.bg;
    }
}
