package Platforms;

import android.graphics.Canvas;

import com.example.doodle.Doodler;
import com.example.doodle.MainScene;

import java.util.Random;

import Items.Item;
import Items.Rocket;
import Items.Spring;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PlatformGenerator implements IGameObject {


    private final MainScene scene;
    private Doodler doodler;
    private float last_platform_generated_Y=0;
    private boolean ready_to_generate_platform =true;

    Random randG = new Random();

    public PlatformGenerator(MainScene mainScene, Doodler doodler) {
        this.scene = mainScene;

        this.doodler = doodler;
    }





    private void generate() {



        float min = Metrics.width * 0.21f;
        float max = Metrics.width - min;
        int x = randG.nextInt((int) (max - min + 1)) + (int) min;


        int chance = randG.nextInt(100) + 1;

        Item tempItem = null;
        if (chance < 15)
            tempItem = Spring.get(x, last_platform_generated_Y + 50f);
        else if (chance < 20)
            tempItem = Rocket.get(x, last_platform_generated_Y + 50f);



        int type = randG.nextInt(101) + 1;

        if (type < 60)
            scene.add(NormalPlatform.get(x, last_platform_generated_Y, tempItem));
        else if (type < 80)
            scene.add(MovingPlatform.get(x, last_platform_generated_Y, tempItem));
        else if (type < 95)
            scene.add(CloudPlatform.get(x, last_platform_generated_Y, tempItem));
        else
            scene.add(FakePlatform.get(x, last_platform_generated_Y, tempItem));


        ready_to_generate_platform=false;


        //Log.v(TAG, "Generating: wave " + wave + " : " + enemies.toString());
    }

    private boolean generated = false;

    @Override
    public void update() {
        if(doodler.getY()+Metrics.height - last_platform_generated_Y >233){
            last_platform_generated_Y +=233;
            ready_to_generate_platform=true;
            generate();
        }


    }

    @Override
    public void draw(Canvas canvas) {

    }

}