package Items;

import android.graphics.RectF;

import com.example.doodle.Doodler;
import com.example.doodle.MainScene;
import com.example.doodle.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.ILayerProvider;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class Spring extends Sprite implements Item, ILayerProvider, IBoxCollidable {

    protected RectF collisionRect = new RectF();
    public Spring(){
        super(R.mipmap.platform_fake_piece_left);
    }

    @Override
    public void applyItemTo(Doodler doodler) {
        doodler.use_item_spring();
        
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


    public void updateCollisionRect(){
        collisionRect.set(dstRect);
    }
}
