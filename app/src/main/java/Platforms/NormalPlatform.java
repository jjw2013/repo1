package Platforms;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.doodle.Camera;
import com.example.doodle.MainScene;
import com.example.doodle.R;

import Items.Item;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class NormalPlatform extends Platform {

    private static final float PLATFORM_WIDTH = 120f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;




    public NormalPlatform() {
        super(R.mipmap.platform);
    }

    public static NormalPlatform get(float x, float y, Item itemx) {

        NormalPlatform platform = Scene.top().getRecyclable(NormalPlatform.class).init(x, y);


        if (itemx != null) {
            Scene.top().add((ILayerProvider<?>) itemx);
            platform.setItem(itemx);
        }

        return platform;

    }


    public NormalPlatform init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        return this;
    }


}