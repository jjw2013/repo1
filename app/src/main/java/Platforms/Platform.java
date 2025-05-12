package Platforms;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doodle.Camera;
import com.example.doodle.Doodler;
import com.example.doodle.MainScene;
import com.example.doodle.R;

import Items.Item;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public abstract class Platform extends Sprite implements IBoxCollidable, ILayerProvider, IRecyclable {

    protected RectF collisionRect = new RectF();


    protected Item item;

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public Platform(int mipmapId) {
        super(mipmapId);
    }

    public abstract Platform init(float x , float y);

    @Override
    public void update() {
        super.update();

        if(isOutOfCameraRange())
            Scene.top().remove(this);

        if(item !=null){
            if(item.isMarked()) {
                item.deleteThis();
                item=null;
            }
        }

    }

    private boolean isOutOfCameraRange(){

        if (this.y < Camera.worldY - Metrics.height/2 ) return true;

        return false;

    }

    @Override
    public void draw(Canvas canvas) {

        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();
        updateCollisionRect();
    }


    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    public void updateCollisionRect(){
        collisionRect.set(dstRect);

    }

    public MainScene.Layer getLayer() {
        return MainScene.Layer.platform;
    }

    @Override
    public void onRecycle() {


        if( item !=null)
            item.deleteThis();


        item = null;
    }


}