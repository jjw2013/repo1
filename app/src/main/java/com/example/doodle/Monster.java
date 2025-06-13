package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;

import Items.Item;
import Platforms.MovingPlatform;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;


public class Monster extends Sprite implements ILayerProvider, IBoxCollidable, IRecyclable {

    protected RectF collisionRect = new RectF();

    public boolean deleteMark = false;

    private static final float PLATFORM_WIDTH = 200f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 162 / 288;

    private int dir = 1;
    private float speed = 200f;
    private float degree = 0;
    public boolean remove_this = false;

    public Monster(){
        super(R.mipmap.mob2);
    }


    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.enemy;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {

        if( x-width/2 < 0 || x+width/2> Metrics.width)
            dir = dir * -1;

        degree = degree + 20*GameView.frameTime;
        dy = (float) Math.sin(degree) * 300f;

        dx = dir*speed;

        super.update();


        if(isOutOfCameraRange() || remove_this)
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

    public static Monster get(float x, float y) {
        return Scene.top().getRecyclable(Monster.class).init(x, y);
    }

    public Monster init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        remove_this=false;
        return this;
    }



}