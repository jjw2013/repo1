package com.example.doodle;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class playButton extends Button{

    private static final float PLANE_WIDTH = 300f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 123 / 340;

    public playButton() {
        super(R.mipmap.play);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);

    }

    @Override
    public void touchEvent() {
        new MainScene().push();
    }
}
