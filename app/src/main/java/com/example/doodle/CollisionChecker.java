package com.example.doodle;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final MainScene scene;
    public final Doodler doodler;

    public CollisionChecker(MainScene mainScene, Doodler doodlerz) {

        this.scene = mainScene;
        this.doodler= doodlerz;
    }

    @Override
    public void update() {
        ArrayList<IGameObject> platforms = scene.objectsAt(MainScene.Layer.platform);
        for (int e = platforms.size() - 1; e >= 0; e--) {
            Platform platform = (Platform) platforms.get(e);

            if(CollisionHelper.collides(doodler, platform)){
                doodler.stomped();
            }

        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
