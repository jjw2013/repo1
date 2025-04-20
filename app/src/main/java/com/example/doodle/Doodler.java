package com.example.doodle;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import com.example.doodle.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite {

    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 80 / 72;

    private static final float speed = 300f;

    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height-200,PLANE_WIDTH, PLANE_HEIGHT);


    }

    public void update(){

    }

    public void draw(Canvas canvas){

        super.draw(canvas);




    }
}