package com.example.doodle;

import android.graphics.Canvas;

import java.util.ArrayList;

import Items.Item;
import Platforms.CloudPlatform;
import Platforms.FakePlatform;
import Platforms.Platform;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
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
            Platform platform = (Platform) platforms.get(e);  // 바로 Platform으로 캐스팅 가능

            if (doodler.isFalling() && CollisionHelper.collides(doodler, platform)) {

                if(platform instanceof CloudPlatform)
                    ((CloudPlatform) platform).stompedByDoodler();

                else if(platform instanceof FakePlatform) {
                    ((FakePlatform) platform).stompedByDoodler();
                    continue;

                }
                doodler.stomp_something();
            }
        }

        ArrayList<IGameObject> items = scene.objectsAt(MainScene.Layer.item);
        for (int e = items.size() - 1; e >= 0; e--) {
            Item item = (Item) items.get(e);

            if(CollisionHelper.collides(doodler, (IBoxCollidable) item)){
                item.applyItemTo(doodler);
            }


        }

        ArrayList<IGameObject> mobs = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = mobs.size() - 1; e >= 0; e--) {
            Monster mob = (Monster) mobs.get(e);

            if(CollisionHelper.collides(doodler, (IBoxCollidable) mob)){
                if(!doodler.hit && !doodler.isFalling())
                    doodler.hit_mob();

                if(!doodler.hit && doodler.isFalling()) {
                    doodler.stomp_something();
                    mob.remove_this= true;

                }
            }


        }

    }

    @Override
    public void draw(Canvas canvas) {}
}
