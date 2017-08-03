package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

/**
 * 游戏组件
 */
abstract public class GameComponent {
    protected float coordX;
    protected float coordY;
    protected float life = 0;
    protected float defense = 0;
    protected float power = 0;
    protected float velocity = 0;
    protected float objectWidth;
    protected float objectHeight;
    protected float screenWidth;
    protected float screenHeight;
    protected Paint paint;
    protected Bitmap objectBitmap;
    protected Bitmap crashBitmap;

    private GameComponent() {

    }

    /**
     * 构造方法
     *
     * @param resources
     * @param objectResId
     * @param crashResId
     */
    public GameComponent(Resources resources, int objectResId, int crashResId) {
        paint = new Paint();
        paint.setAntiAlias(true);
        if (objectResId != 0) {
            objectBitmap = BitmapFactory.decodeResource(resources, objectResId);
            objectWidth = objectBitmap.getWidth();
            objectHeight = objectBitmap.getHeight();
        }
        if (crashResId != 0) {
            crashBitmap = BitmapFactory.decodeResource(resources, crashResId);
        }
    }

    /**
     * 设置屏幕尺寸
     * @param screenWidth
     * @param screenHeight
     */
    public void setScreenSize(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * 设置参数
     * @param life
     * @param defense
     * @param power
     * @param velocity
     */
    public void setParams(float life, float defense, float power, float velocity) {
        this.life = life;
        this.defense = defense;
        this.power = power;
        this.velocity = velocity;
    }

    /**
     * 绘图
     * @param canvas
     */
    public abstract void onDraw(Canvas canvas);

    /**
     * 销毁
     */
    public abstract void onDestroy();

    /**
     * 碰撞
     * @param target
     * @return
     */
    public abstract boolean crash(GameComponent target);

    /**
     * 动作
     */
    protected abstract void action();

    /**
     * 超出屏幕
     * @return
     */
    protected abstract boolean isOutOfScreen();
}
