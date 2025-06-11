package com.example.doodle;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class NumberPainter extends Sprite {
    private static final int digitWidth = 60;
    private static final int digitHeight = 100;

    private static final float PLANE_WIDTH = 50f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 100/60;

    public NumberPainter(int x, int y) {
        super(R.mipmap.number);
        super.x=x;
        super.y=y;
    }


    int score=0;
    int digits=1;
    public void set_score(int x){
        score = x;
        digits = String.valueOf(score).length();
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);

        String scoreStr = String.valueOf(score);

        for (int i = 0; i < scoreStr.length(); i++) {
            int digit = scoreStr.charAt(i) - '0';

            Rect srcRectx = new Rect(
                    digit * digitWidth,
                    0,
                    (digit + 1) * digitWidth,
                    digitHeight);

            RectF dstRectx = new RectF(
                    super.x  + i*PLANE_WIDTH ,
                    super.y ,
                    super.x  + (i+1)*PLANE_WIDTH,
                    super.y+ PLANE_HEIGHT
            );

            canvas.drawBitmap(bitmap, srcRectx, dstRectx, null);
        }






    }
}
