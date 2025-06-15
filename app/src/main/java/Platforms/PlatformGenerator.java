package Platforms;

import android.graphics.Canvas;

import com.example.doodle.Doodler;
import com.example.doodle.MainScene;
import com.example.doodle.Monster;

import java.util.Random;

import Items.Item;
import Items.PropellerHat;
import Items.Rocket;
import Items.Shield;
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
        if(last_platform_generated_Y >2000) {
            if (chance < 3)
                tempItem = Spring.get(x, last_platform_generated_Y + 25f);
            else if (chance < 4)
                tempItem = Rocket.get(x, last_platform_generated_Y + 25f);
            else if (chance < 5)
                tempItem = Shield.get(x, last_platform_generated_Y + 25f);
            else if (chance < 6)
                tempItem = PropellerHat.get(x, last_platform_generated_Y + 25f);
        }


        int type = randG.nextInt(101) + 1;

        if ( interval == 50 || type < 60)
            scene.add(NormalPlatform.get(x, last_platform_generated_Y, tempItem));
        else if (type < 80)
            scene.add(MovingPlatform.get(x, last_platform_generated_Y, tempItem));
        else if (type < 95)
            scene.add(CloudPlatform.get(x, last_platform_generated_Y, tempItem));
        else
            scene.add(FakePlatform.get(x, last_platform_generated_Y, tempItem));




        int monster_type = randG.nextInt(101) + 1;
        if(last_platform_generated_Y > 20000) {
            if(monster_type<25)
                scene.add(Monster.get(x, last_platform_generated_Y));
        }
        else if(last_platform_generated_Y > 10000) {
            if(monster_type<15)
                scene.add(Monster.get(x, last_platform_generated_Y));
        }
        else if(last_platform_generated_Y >3000) {
            if(monster_type<5)
                scene.add(Monster.get(x, last_platform_generated_Y));
        }



        ready_to_generate_platform=false;


        //Log.v(TAG, "Generating: wave " + wave + " : " + enemies.toString());
    }

    private boolean generated = false;

    private int interval =50;
    @Override
    public void update() {
        if(doodler.getY()+Metrics.height - last_platform_generated_Y >interval){
            last_platform_generated_Y +=interval;
            ready_to_generate_platform=true;
            generate();
        }

        if(last_platform_generated_Y>3000)
            interval=100;

        else if(last_platform_generated_Y>10000)
            interval = 150;


    }

    @Override
    public void draw(Canvas canvas) {

    }

}