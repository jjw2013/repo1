package Items;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.doodle.Camera;
import com.example.doodle.Doodler;
import com.example.doodle.MainScene;
import com.example.doodle.R;

import Platforms.NormalPlatform;
import Platforms.Platform;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class Spring extends Sprite implements Item, ILayerProvider, IBoxCollidable, IRecyclable {

    protected RectF collisionRect = new RectF();

    public boolean deleteMark = false;

    private static final float PLATFORM_WIDTH = 50f;
    private static final float PLATFORM_HEIGHT = PLATFORM_WIDTH * 53 / 46;


    public Spring(){
        super(R.mipmap.spring);
    }

    @Override
    public void applyItemTo(Doodler doodler) {
        doodler.use_item_spring();
        deleteMark =true;

    }

    @Override
    public boolean isMarked() {
        return deleteMark;
    }

    @Override
    public MainScene.Layer getLayer() {
        return MainScene.Layer.item;
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {
        super.update();


    }

    public void deleteThis(){
        deleteMark=false;
        Scene.top().remove(this);
    }


    public void updateCollisionRect(){
        collisionRect.set(dstRect);
    }

    @Override
    public void onRecycle() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
        updateCollisionRect();
    }

    public static Spring get(float x, float y) {
        return Scene.top().getRecyclable(Spring.class).init(x, y);
    }

    public Spring init(float x, float y) {
        setPosition(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        return this;
    }
}
