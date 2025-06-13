package com.example.doodle;

import android.content.Context;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Platforms.Platform;
import Platforms.PlatformGenerator;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class ScoreScene extends Scene {


    private final Doodler doodler;
    private final Camera camera;

    private List<NumberPainter> scores;


    public enum Layer {
        bg, enemy, bullet, platform,item, debris ,doodler, ui, controller, image;
        public static final int COUNT = values().length;
    }

    public ScoreScene(){


        initLayers(Layer.COUNT);

        add(Layer.bg, new Background());



        this.doodler= new Doodler();
        doodler.hult=true;
        doodler.invisible=true;
        add(Layer.doodler, doodler);

        this.camera = new Camera(doodler);
        add(Layer.controller, camera);

        add(Layer.ui, new backButton());


        List<Integer> top5score = ScoreManager.getInstance(GameView.view.getContext()).getScore();

        Collections.sort(top5score,Collections.reverseOrder());

        scores= new ArrayList<>();
        for(int i=0; i<top5score.size(); i++){

            if(i==0) {

                add(Layer.image, new ImagePainter(R.mipmap.m1st,
                        (int) Metrics.width/3 - 100,i * (int) Metrics.height/9 + 300,
                        70f,
                        159, 256
                ));
            }

            if(i==1) {

                add(Layer.image, new ImagePainter(R.mipmap.m2nd,
                        (int) Metrics.width/3 - 100,i * (int) Metrics.height/9 + 300,
                        70f,
                        159, 256
                ));
            }
            if(i==2) {

                add(Layer.image, new ImagePainter(R.mipmap.m3rd,
                        (int) Metrics.width/3 - 100,i * (int) Metrics.height/9 + 300,
                        70f,
                        159, 256
                ));
            }





            NumberPainter np = new NumberPainter((int) Metrics.width/3,i * (int) Metrics.height/9 + 300);
            np.set_score(top5score.get(i));
            scores.add(np);
        }

        for(NumberPainter np : scores){
            add(Layer.controller, np);
        }





    }

    // Game Loop Functions


    // Overridables
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        ArrayList<IGameObject> buttons = objectsAt(ScoreScene.Layer.ui);


        for (int e = buttons.size() - 1; e >= 0; e--) {
            Button b = (Button) buttons.get(e);
            b.onTouch(event);
        }

        return false;
    }
}