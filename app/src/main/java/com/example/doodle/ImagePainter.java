package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class ImagePainter extends Sprite {

    private float PLANE_WIDTH;
    private float PLANE_HEIGHT;

    public ImagePainter(int mipmapId, int x, int y , float scale, float width, float height) {
        super(mipmapId);
        PLANE_WIDTH = scale;
        PLANE_HEIGHT = PLANE_WIDTH * height / width;

        setPosition(x, y, PLANE_WIDTH, PLANE_HEIGHT );

    }


    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        RectF dstRectx = new RectF(
                super.x  ,
                super.y ,
                super.x  + PLANE_WIDTH,
                super.y+ PLANE_HEIGHT
        );

            canvas.drawBitmap(bitmap, srcRect, dstRectx, null);







    }


}
