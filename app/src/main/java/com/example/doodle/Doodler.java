package com.example.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import Debris_.EmptyRocketCanister;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Doodler extends Sprite implements IBoxCollidable {


    Sprite equip;
    Sprite equip2;
    Sprite equip3;
    Sprite equip4;

    Sprite equip5;
    private static final float EQUIP_PLANE_WIDTH = 30f;
    private static final float EQUIP_PLANE_HEIGHT = EQUIP_PLANE_WIDTH * 125 / 35;


    private static final float GRAVITY = 2000f;
    private boolean isFalling= true;
    private boolean stomped = false;
    public boolean invisible = false;

    public boolean hit =false;

    public boolean rocketmode =false;
    private float rockettimer=3;

    public boolean shieldmode =false;
    private float shieldtimer=10;

    public boolean propellermode =false;
    private float propellertimer=3;
    private boolean filp_propeller_image =true;

    public boolean hult = false;


    public int max_height=0;

    protected RectF collisionRect= new RectF();

    private float jumpSpeed = 1200f;
    private static final float PLANE_WIDTH = 120f;
    private static final float PLANE_HEIGHT = PLANE_WIDTH * 210 / 251;

    public float targetX;


    public NumberPainter score;
    public Doodler(NumberPainter num){



        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = jumpSpeed;
        score = num;
        isFalling = false;

        equip = new Sprite(R.mipmap.rocket_part);
        equip2 = new Sprite(R.mipmap.shield);
        equip3 = new Sprite(R.mipmap.propeller_hat);
        equip4 = new Sprite(R.mipmap.propeller_hat2);
        equip5 = new Sprite(R.mipmap.stun_star);

    }

    public Doodler(){

        super(R.mipmap.doodler);
        setPosition(Metrics.width/2,Metrics.height/2,PLANE_WIDTH, PLANE_HEIGHT);
        dy = jumpSpeed;

        equip = new Sprite(R.mipmap.rocket_part);
        equip2 = new Sprite(R.mipmap.shield);
        equip3 = new Sprite(R.mipmap.propeller_hat);
        equip4 = new Sprite(R.mipmap.propeller_hat2);
        equip5 = new Sprite(R.mipmap.stun_star);
    }

    public boolean isFalling(){
        return isFalling;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public boolean stomp_something(){
        if (hit)
            return false;

        if(isFalling) {
            stomped = true;
            addJumpSpeed();
            return true;
        }
        return false;
    }

    public void addJumpSpeed(){
        isFalling= false;
        stomped = false;
        dy = jumpSpeed;

    }

    public void hit_mob(){
        if(rocketmode)
            return;
        if(propellermode)
            propellermode=false;

        hit=true;
        dy=0;
    }

    public void checkFalling(){
        if(!isFalling && dy < 0)
            isFalling = true;
    }

    public void use_item_spring() {
        if(stomp_something())
            dy += jumpSpeed;

    }

    public void use_item_rocket() {
        rocketmode = true;
        rockettimer=3;
    }

    public void use_item_shield() {
        shieldmode = true;
        shieldtimer=10;
    }

    public void use_item_propeller() {
        propellermode = true;
        propellertimer=3;
    }


    float tiltX = 0;
    public void ontiltevent(float tiltX) {
        this.tiltX = tiltX;

    }

    @Override
    public void update() {
        if(hult)
            return;

        super.update();
        checkFalling();


        if(rocketmode) {
            dy = jumpSpeed*2;

            rockettimer -= GameView.frameTime;

            if(rockettimer<0){
                rocketmode= false;
                IGameObject canister;
                Scene.top().add(EmptyRocketCanister.get(x-48f,y-15f));
            }
        }
        else if(propellermode){
            dy = jumpSpeed*0.8f;

            propellertimer -= GameView.frameTime;

            if(propellertimer<0){
                propellermode= false;
            }
        }
        else
            dy -= GRAVITY * GameView.frameTime;



        if(shieldmode){
            shieldtimer -= GameView.frameTime;

            if(shieldtimer<0) {
                shieldmode = false;
            }
        }





        if(!hit)
            dx = tiltX* -400f;

        if(x<0)
            x= Metrics.width;
        if(x>Metrics.width)
            x=0;



        if(max_height < getY()) {
            max_height= (int)getY();
            score.set_score(max_height/10);
        }


        if( y< Camera.deadline) {
            Camera.unlock_camera_y_with_deadline();
            Context context = GameView.view.getContext();
            ScoreManager.getInstance(context).addScore(score.score);
            Scene.pop();

        }



    }


    private void setTargetX(float x) {
        //targetX = Math.max(radius, Math.min(x, Metrics.width - radius));
        targetX = x;
        setPosition(x,y,PLANE_WIDTH, PLANE_HEIGHT);
    }

    public boolean onTouch(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                //setTargetX(pts[0]);
                return true;

        }
        return false;

    }

    @Override
    public void draw(Canvas canvas) {

        if(invisible)
            return;


        super.setDstRectWithCamera(Camera.getCameraY(y));
        super.draw(canvas);
        super.revertDstRect();


        updateCollisionRect();

        if(rocketmode){
            equip.setPosition(x-48f,y-15f,EQUIP_PLANE_WIDTH,EQUIP_PLANE_HEIGHT);
            equip.setDstRectWithCamera(Camera.getCameraY(y-15f));
            equip.draw(canvas);
            equip.revertDstRect();
        }



        if(propellermode){
            if(filp_propeller_image) {
                equip3.setPosition(x, y + 55f, 93, 79);
                equip3.setDstRectWithCamera(Camera.getCameraY(y + 55f));
                equip3.draw(canvas);
                equip3.revertDstRect();
                filp_propeller_image= false;
            }
            else{
                equip4.setPosition(x, y + 55f, 93, 79);
                equip4.setDstRectWithCamera(Camera.getCameraY(y + 55f));
                equip4.draw(canvas);
                equip4.revertDstRect();
                filp_propeller_image= true;
            }

        }

        if(hit){
            equip5.setPosition(x, y + 55f, 100f, 100f * 100/186);
            equip5.setDstRectWithCamera(Camera.getCameraY(y + 55f));
            equip5.draw(canvas);
            equip5.revertDstRect();
        }


        if(shieldmode){
            equip2.setPosition(x,y,200f,200f);
            equip2.setDstRectWithCamera(Camera.getCameraY(y));
            equip2.draw_with_alpha(canvas);
            equip2.revertDstRect();
        }

    }

    protected static Paint bboxPaint;



    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    public void updateCollisionRect(){
        collisionRect.set(dstRect.left,
                dstRect.top,
                dstRect.right,
                dstRect.bottom);

        collisionRect.set(
                collisionRect.left,
                collisionRect.top +PLANE_HEIGHT*0.9f,
                collisionRect.right,
                collisionRect.bottom);
    }


}