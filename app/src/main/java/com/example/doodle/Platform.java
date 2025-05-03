package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Platform extends Sprite implements IBoxCollidable , IRecyclable, ILayerProvider {

    private static final float PLATFORM_WIDTH = 175f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;

    protected RectF collisionRect = new RectF();

    public static Platform get(float x, float y) {
        return Scene.top().getRecyclable(Platform.class).init(x, y);
    }

    private Platform init(float x, float y) {
        setPosition(x, y,PLATFORM_WIDTH, PLATFORM_HEIGHT);
        return this;
    }


    public Platform() {
        super(R.mipmap.platform);
    }

    @Override
    public void update() {
        super.update();

        if(isOutOfCameraRange())
            Scene.top().remove(this);

    }

    private boolean isOutOfCameraRange(){

        if (this.y < Camera.worldY - Metrics.height/2 ) return true;

        return false;

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

    public MainScene.Layer getLayer() {
        return MainScene.Layer.platform;
    }

    @Override
    public void onRecycle() {

    }
}