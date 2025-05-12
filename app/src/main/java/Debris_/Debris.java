package Debris_;

import android.graphics.Canvas;

import com.example.doodle.Camera;
import com.example.doodle.MainScene;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public abstract class Debris extends Sprite implements IRecyclable, ILayerProvider {


    public Debris (int mipmapId) {
        super(mipmapId);
    }
    public abstract Debris init(float x , float y);

    private static final float GRAVITY = 800f;

    @Override
    public void draw(Canvas canvas) {

        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
    }

    @Override
    public void update() {
        super.update();

        if(isOutOfCameraRange())
            Scene.top().remove(this);
        dy -= GRAVITY * GameView.frameTime;
        dx -= 50f;

    }

    private boolean isOutOfCameraRange(){

        if (this.y < Camera.worldY - Metrics.height/2 ) return true;

        return false;

    }

    public MainScene.Layer getLayer() {
        return MainScene.Layer.debris;
    }

    @Override
    public void onRecycle() {

    }
}
