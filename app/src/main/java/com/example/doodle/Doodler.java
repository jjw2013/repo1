package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite implements IBoxCollidable {

    private static final float GRAVITY = 500f;
    private boolean isFalling= true;

    private boolean stomp = false;

    protected RectF collisionRect= new RectF();

    private float speed = 1000f;
    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 185 / 227;




    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = speed;

    }

    public boolean isFalling(){
        return isFalling;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void stomped(){
        stomp= true;
    }

    public void addJumpSpeed(){
        isFalling= false;
        stomp= false;

        dy = speed;

    }

    @Override
    public void update() {
        super.update();


        if(stomp)
            addJumpSpeed();

        if(!isFalling && dy < 0) {
            isFalling = true;
        }

        if ( y < 0 )
            stomped();

        dy -= GRAVITY * GameView.frameTime;



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