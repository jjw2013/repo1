package Platforms;

import android.health.connect.datatypes.Metadata;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doodle.R;

import Items.Item;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class CloudPlatform extends Platform {


    private static final float PLATFORM_WIDTH = 175f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 23 / 105;
    private boolean stomped = false;

    public CloudPlatform() {
        super(R.mipmap.platform_cloud);
    }

    @Override
    public void update() {
        if(stomped)
            Scene.top().remove(this);

        else
            super.update();


    }

    public static CloudPlatform get(float x, float y, Item item) {
        return Scene.top().getRecyclable(CloudPlatform.class).init(x, y, item);
    }

    public CloudPlatform init(float x, float y, Item item) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.item= item;
        return this;
    }


    public void stompedByDoodler(){
        stomped=true;
    }


}