package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import Debris_.EmptyRocketCanister;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite implements IBoxCollidable {


    Sprite equip;
    private static final float EQUIP_PLANE_WIDTH = 40f;
    private static final float EQUIP_PLANE_HEIGHT = EQUIP_PLANE_WIDTH * 125 / 35;

    private static final float GRAVITY = 800f;
    private boolean isFalling= true;
    private boolean stomped = false;

    public boolean rocketmode =false;
    private float rockettimer=5;


    protected RectF collisionRect= new RectF();

    private float jumpSpeed = 1000f;
    private static final float PLANE_WIDTH = 175f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 210 / 251;

    public float targetX;


    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = jumpSpeed;

        equip = new Sprite(R.mipmap.rocket_part);

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

    public boolean stomp_something(){
        if(isFalling) {
            stomped = true;
            addJumpSpeed();
            return true;
        }
        return false;
    }

    public void addJumpSpeed(){
        isFalling= false;
        stomped = false;
        dy = jumpSpeed;

    }

    public void checkFalling(){
        if(!isFalling && dy < 0)
            isFalling = true;
    }

    @Override
    public void update() {
        super.update();
        checkFalling();

        if(rocketmode) {
            dy = jumpSpeed*2;

            rockettimer -= GameView.frameTime;

            if(rockettimer<0){
                rocketmode= false;
                IGameObject canister;
                Scene.top().add(EmptyRocketCanister.get(x-68f,y-15f));
            }
        }

        else
            dy -= GRAVITY * GameView.frameTime;


        if( y< Camera.deadline)
            Camera.unlock_camera_y_with_deadline();



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

        if(rocketmode){
            equip.setPosition(x-68f,y-15f,EQUIP_PLANE_WIDTH,EQUIP_PLANE_HEIGHT);
            equip.setDstRectWithCamera(Camera.getCameraY(y));
            equip.draw(canvas);
            equip.revertDstRect();
        }

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

    public void use_item_spring() {
        if(stomp_something())
            dy += jumpSpeed;

    }

    public void use_item_rocket() {
        rocketmode = true;
        rockettimer=5;
    }
}