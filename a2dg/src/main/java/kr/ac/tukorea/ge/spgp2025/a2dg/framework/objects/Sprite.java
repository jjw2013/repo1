package kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Sprite implements IGameObject {
    protected Bitmap bitmap;
    protected Rect srcRect = null;
    public  RectF dstRect = new RectF();
    protected float x, y, dx, dy;
    protected float width, height, radius;

    public Sprite(int mipmapId) {
        if (mipmapId != 0) {
            bitmap = BitmapPool.get(mipmapId);
        }
    }

    public void setPosition(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.width = this.height = 2 * radius;
        RectUtil.setRect(dstRect, x,Metrics.height-y, radius);

    }
    public void setPosition(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        radius = Math.min(width, height) / 2;
        RectUtil.setRect(dstRect, x, Metrics.height-y, width, height);
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        RectUtil.setRect(dstRect, x, Metrics.height-y, width, height);
        Log.d("DEBUG_TAG", "아이템이 Sprite 객체가 아닙니다.");
    }

    public void setDstRectWithCamera(float camera_y){
        RectUtil.setRect(dstRect, x, Metrics.height-camera_y, width, height);
    }

    public void revertDstRect(){
        RectUtil.setRect(dstRect, x, Metrics.height-y, width, height);
    }

    public void resetDstRectWithRect(RectF rect){
        dstRect=rect;

    }


    @Override
    public void update() {
        float timedDx = dx * GameView.frameTime;
        float timedDy = dy * GameView.frameTime;
        x += timedDx;
        y += timedDy;
        dstRect.offset(timedDx, -timedDy);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }


    protected static Paint bboxPaint;
}


