package Platforms;

import com.example.doodle.R;

import Items.Item;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class FakePlatform extends Platform {


    private static final float PLATFORM_WIDTH = 100f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;
    private boolean stomped = false;

    public FakePlatform() {
        super(R.mipmap.platform_fake);
    }

    @Override
    public void update() {
        if(stomped)
            Scene.top().remove(this);

        else
            super.update();


    }

    public void stompedByDoodler(){
        stomped=true;
    }

    public static FakePlatform get(float x, float y, Item itemx) {

        FakePlatform platform = Scene.top().getRecyclable(FakePlatform.class).init(x, y);


        if (itemx != null) {
            Scene.top().add((ILayerProvider<?>) itemx);
            platform.setItem(itemx);
        }

        return platform;

    }


    public FakePlatform init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        stomped=false;
        return this;
    }


}