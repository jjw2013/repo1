package Platforms;

import android.health.connect.datatypes.Metadata;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doodle.R;

import Items.Item;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MovingPlatform extends Platform {


    private static final float PLATFORM_WIDTH = 120f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;

    private float speed = 100f;
    private int dir = 1;

    public MovingPlatform() {
        super(R.mipmap.platform_move);
    }

    @Override
    public void update() {



        if( x-width/2 < 0 || x+width/2> Metrics.width)
            dir = dir * -1;

        dx = dir*speed;

        super.update();

        if (item != null) {

            Sprite itemx = (Sprite) item;
            itemx.setPosition(x,y +20f );
        }

    }

    public static MovingPlatform get(float x, float y, Item itemx) {

        MovingPlatform platform = Scene.top().getRecyclable(MovingPlatform.class).init(x, y);


        if (itemx != null) {
            Scene.top().add((ILayerProvider<?>) itemx);
            platform.setItem(itemx);
        }

        return platform;

    }


    public MovingPlatform init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        return this;
    }


}