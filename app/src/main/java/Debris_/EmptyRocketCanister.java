package Debris_;

import com.example.doodle.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class EmptyRocketCanister extends Debris{

    private static final float PLATFORM_WIDTH = 40f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 125 / 35;

    public EmptyRocketCanister(){
        super(R.mipmap.rocket_part);
    }

    public static EmptyRocketCanister get(float x, float y) {
        return Scene.top().getRecyclable(EmptyRocketCanister.class).init(x, y);

    }


    public EmptyRocketCanister init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        return this;
    }
}
