package com.example.doodle;

import android.graphics.Canvas;
import android.view.MotionEvent;

import Platforms.Platform;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public abstract class Button extends Sprite implements IGameObject{

    public Button(int mipmapId) {
        super(mipmapId);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    public abstract void touchEvent();

    public boolean onTouch(MotionEvent event){
        float[] pts = Metrics.fromScreen(event.getX(), event.getY());
        if( pts[0]< super.dstRect.left )
            return false;
        if(pts[0]>super.dstRect.right)
            return false;
        if(pts[1] < super.dstRect.top)
            return false;
        if(pts[1] >super.dstRect.bottom)
            return false;

        touchEvent();

       return true;
    }
}
