package Platforms;

import android.graphics.Canvas;

import com.example.doodle.MainScene;

import java.util.Random;

import Items.Item;
import Items.Rocket;
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
            float min = Metrics.width * 0.21f;
            float max = Metrics.width - min;
            int x = randG.nextInt((int)(max - min + 1)) + (int)min;




            int chance = randG.nextInt(100) + 1;

            Item tempItem = null;
            if(chance < 10)
                tempItem= Spring.get(x,300*i+50f);
            else if (chance <20)
                tempItem= Rocket.get(x,300*i+50f);


            int type = randG.nextInt(101) +1;

            if(type<60)
                scene.add(NormalPlatform.get(x, 300 * i, tempItem));
            else if(type<80)
                scene.add(MovingPlatform.get(x, 300 * i, tempItem));
            else if (type<95)
                scene.add(CloudPlatform.get(x, 300 * i, tempItem));
            else
                scene.add(FakePlatform.get(x, 300 * i, tempItem));

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