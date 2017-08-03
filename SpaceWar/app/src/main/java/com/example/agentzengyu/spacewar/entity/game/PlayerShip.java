package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 玩家飞船
 */
public class PlayerShip extends GameComponent {
    private Paint paintShield, paintLaser;
    private final static int colorShield = Color.parseColor("#881e90ff");
    private final static int colorLaser = Color.parseColor("#bbff0000");
    private boolean shield = false;
    private boolean laser = false;
    private float acceleratedX = 0.0f, acceleratedY = 0.0f;
    private float radius = 1.0f;
    private List<PlayerBullet> bullets = new ArrayList<>();

    public PlayerShip(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);

        radius = Math.max(objectWidth, objectHeight) * 2 / 3;

        paintShield = new Paint();
        paintShield.setAntiAlias(true);
        paintShield.setColor(colorShield);

        paintLaser = new Paint();
        paintLaser.setAntiAlias(true);
        paintLaser.setColor(colorLaser);
    }

    @Override
    public void setParams(float life, float defense, float power, float velocity) {
        super.setParams(life, defense, power, velocity);
    }

    @Override
    public void setScreenSize(float screenWidth, float screenHeight) {
        super.setScreenSize(screenWidth, screenHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        action();
        canvas.save();
        canvas.clipRect(coordX - objectWidth / 2, coordY - objectHeight / 2, coordX + objectWidth / 2, coordY + objectHeight / 2);
        canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY - objectHeight / 2, paint);
        canvas.restore();

        canvas.save();
        if (shield) {
            canvas.drawCircle(coordX, coordY, radius, paintShield);
        }
        if (laser) {
            canvas.drawRect(coordX - objectWidth / 3, 0.0f, coordX + objectWidth / 3, coordY - objectHeight / 2, paintLaser);
        }
        canvas.restore();

        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.onDraw(canvas);
            if (bullet.isOutOfScreen()) {
                bullets.remove(i);
                bullet.onDestroy();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (objectBitmap != null && objectBitmap.isRecycled()) {
            objectBitmap.recycle();
        }
        if (crashBitmap != null && crashBitmap.isRecycled()) {
            crashBitmap.recycle();
        }
        for (PlayerBullet bullet:bullets){
            bullet.onDestroy();
        }
    }

    @Override
    public boolean crash(GameComponent target) {
        return false;
    }

    @Override
    protected void action() {
        coordX += acceleratedX * velocity;
        if (coordX < 0) {
            coordX = 0;
        } else if (coordX > screenWidth) {
            coordX = screenWidth;
        }
        coordY += acceleratedY * velocity;
        if (coordY < 0) {
            coordY = 0;
        } else if (coordY > screenHeight) {
            coordY = screenHeight;
        }
    }

    @Override
    protected boolean isOutOfScreen() {
        return false;
    }

    /**
     * 设置加速度
     * @param X
     * @param Y
     */
    public void setAccelerated(float X, float Y) {
        acceleratedX = X;
        acceleratedY = Y;
    }

    /**
     * 射击敌人
     *
     * @param factory
     * @param objectResId
     */
    public void shootEnemy(GameComponentFactory factory, int objectResId) {
        bullets.add(factory.createPlayerBullet(objectResId, coordX, coordY));
    }

    /**
     * 开起护盾
     *
     * @param shield
     */
    public void openShield(boolean shield) {
        this.shield = shield;
    }

    /**
     * 发射激光
     *
     * @param laser
     */
    public void openLaser(boolean laser) {
        this.laser = laser;
    }
}
