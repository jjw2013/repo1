package Platforms;

import android.graphics.Canvas;

import com.example.doodle.MainScene;

import java.util.Random;

import Items.Item;
import Items.Spring;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PlatformGenerator implements IGameObject {


    private final MainScene scene;

    Random randG = new Random();

    public PlatformGenerator(MainScene mainScene) {
        this.scene = mainScene;

    }




    private void generate() {


        for (int i=0;i<60;i++){
            float min = Metrics.width * 0.13f;
            float max = Metrics.width - min;

            int x = randG.nextInt((int)(max - min + 1)) + (int)min;

            int type = randG.nextInt(2) +1;


            Item tempItem = Spring.get(x,300*i+25f);

            switch (type){
                case 1:
                    scene.add(NormalPlatform.get(x, 300 * i, tempItem));
                    break;

                case 2:
                    scene.add(MovingPlatform.get(x, 300 * i, tempItem));

                    break;

                case 3:
                    scene.add(CloudPlatform.get(x, 300 * i, null));
                    break;

                case 4:
                    scene.add(FakePlatform.get(x, 300 * i, null));
                    break;
            }
        }


        //Log.v(TAG, "Generating: wave " + wave + " : " + enemies.toString());
    }

    private boolean generated = false;

    @Override
    public void update() {
        if(!generated) {
            generate();
            generated=true;
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}