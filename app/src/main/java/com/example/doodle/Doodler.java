package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite implements IBoxCollidable {

    private static final float GRAVITY = 180f;
    private boolean isFalling= false;

    protected RectF collisionRect= new RectF();

    private float speed = 500f;
    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 185 / 227;




    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = speed;

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    @Override
    public void update() {
        super.update();


        dy -= GRAVITY * GameView.frameTime;

        if(!isFalling && dy<=0) isFalling= true;

        if(isFalling && dy>0) isFalling= false;

        if ( y <0){
            dy = speed;
        }



    }

    @Override
    public void draw(Canvas canvas) {
        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();

        updateCollisionRect();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    public void updateCollisionRect(){
        collisionRect.set(dstRect);
    }
}