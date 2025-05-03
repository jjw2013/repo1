package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite implements IBoxCollidable {

    private static final float GRAVITY = 800f;
    private boolean isFalling= true;

    private boolean stomp = false;

    protected RectF collisionRect= new RectF();

    private float jumpSpeed = 1000f;
    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 185 / 227;

    public float targetX;


    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = jumpSpeed;

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
        if(isFalling())
            stomp= true;
    }

    public void addJumpSpeed(){
        isFalling= false;
        stomp= false;

        dy = jumpSpeed;

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


    private void setTargetX(float x) {
        //targetX = Math.max(radius, Math.min(x, Metrics.width - radius));
        targetX = x;
        setPosition(x,y,PLANE_WIDTH, PLANE_HEIGHT);
    }

    public boolean onTouch(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                setTargetX(pts[0]);
                return true;

        }
        return false;

    }

    @Override
    public void draw(Canvas canvas) {


        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();


        updateCollisionRect();


    }

    protected static Paint bboxPaint;



    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    public void updateCollisionRect(){
        collisionRect.set(dstRect.left,
                dstRect.top,
                dstRect.right,
                dstRect.bottom);

        collisionRect.set(
                collisionRect.left,
                collisionRect.top +PLANE_HEIGHT*0.9f,
                collisionRect.right,
                collisionRect.bottom);
    }
}