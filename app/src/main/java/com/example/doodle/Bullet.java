package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;


public class Bullet extends Sprite implements ILayerProvider, IBoxCollidable, IRecyclable {

    protected RectF collisionRect = new RectF();

    public boolean deleteMark = false;

    private static final float PLATFORM_WIDTH = 30f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 41 / 41;


    private float speed = 1000f;
    private float dir = 0;
    public boolean remove_this = false;

    private float timer =2;

    public Bullet(float dir){
        super(R.mipmap.shell);
    }


    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.bullet;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {


        dx = (float)Math.cos(dir) * speed *GameView.frameTime;
        dy = (float)Math.sin(dir) * speed *GameView.frameTime;
        timer -= GameView.frameTime;

        super.update();


        if(timer <0 || remove_this)
            Scene.top().remove(this);

    }

    private boolean isOutOfCameraRange(){

        if (this.y < Camera.worldY - Metrics.height/2 ) return true;

        return false;

    }


    public void updateCollisionRect(){
        collisionRect.set(dstRect);
    }

    @Override
    public void onRecycle() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
        updateCollisionRect();
    }

    public static Bullet get(float x, float y) {
        return Scene.top().getRecyclable(Bullet.class).init(x, y);
    }

    public Bullet init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        remove_this=false;
        timer=2;
        return this;
    }



}
