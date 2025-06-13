package com.example.doodle;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class backButton extends Button{

    private static final float PLANE_WIDTH = 300f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 123 / 340;

    public backButton() {
        super(R.mipmap.back);
        setPosition(Metrics.width -PLANE_WIDTH/2 ,PLANE_HEIGHT/2,
                PLANE_WIDTH, PLANE_HEIGHT);

    }

    @Override
    public void touchEvent() {
        Scene.pop();
    }
}