package com.example.doodle;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class scoreButton extends Button{

    private static final float PLANE_WIDTH = 200f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 249 / 247;

    public scoreButton() {
        super(R.mipmap.highscore);
        setPosition(Metrics.width -PLANE_WIDTH/2 ,PLANE_HEIGHT/2,
                PLANE_WIDTH, PLANE_HEIGHT);

    }

    @Override
    public void touchEvent() {
        new ScoreScene().push();
    }
}