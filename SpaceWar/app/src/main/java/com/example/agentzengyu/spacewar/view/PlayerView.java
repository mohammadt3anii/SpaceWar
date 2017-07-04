package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.entity.single.Bullet;

import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

public class PlayerView extends View {
    private int agility = 100;
    private final static int SCALE = 8;
    private int shieldColor = Color.parseColor("#30000099");
    private Paint paintShip, paintShield;
    private Bitmap bitmap;
    private float screenWidth = 0;
    private float screenHeight = 0;
    private float bitmapWidth = 0;
    private float bitmapHeight = 0;
    private float radius = 0;
    private float playerX = -1;
    private float playerY = -1;
    private boolean openShield = false;
    private ArrayList<Bullet> bullets =null;

    public PlayerView(Context context) {
        super(context);
        init();
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        screenWidth = getWidth();
        screenHeight = getHeight();

        if (playerX == -1 && playerY == -1) {
            playerX = (screenWidth - bitmapWidth) / 2;
            playerY = screenHeight - bitmapHeight;
        }
        canvas.drawBitmap(bitmap, playerX, playerY, paintShip);

        if (openShield) {
            paintShield.setColor(shieldColor);
            canvas.drawCircle(playerX + bitmapWidth / 2, playerY + bitmapHeight / 2, radius, paintShield);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        int normalId = R.mipmap.ic_launcher_round;
        bitmap = BitmapFactory.decodeResource(getResources(), normalId);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        radius = Math.max(bitmapWidth, bitmapHeight);
        paintShip = new Paint();
        paintShield = new Paint();
    }

    /**
     * 设置敏捷值
     *
     * @param agility 敏捷值
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * 左移
     */
    public void onLeft() {
        if (playerX == 0) return;
        playerX -= agility / SCALE;
        if (playerX < 0) {
            playerX = 0;
        }
        invalidate();
    }

    /**
     * 右移
     */
    public void onRight() {
        if (playerX == screenWidth - bitmap.getWidth()) return;
        playerX += agility / SCALE;
        if (playerX + bitmap.getWidth() > screenWidth) {
            playerX = screenWidth - bitmap.getWidth();
        }
        invalidate();
    }

    /**
     * 上移
     */
    public void onTop() {
        if (playerY == 0) return;
        playerY -= agility / SCALE;
        if (playerY < 0) {
            playerY = 0;
        }
        invalidate();
    }

    /**
     * 下移
     */
    public void onBottom() {
        if (playerY == screenHeight - bitmap.getHeight()) return;
        playerY += agility / SCALE;
        if (playerY + bitmap.getHeight() > screenHeight) {
            playerY = screenHeight - bitmap.getHeight();
        }
        invalidate();
    }

    /**
     * 护盾
     *
     * @param open 是否开启
     */
    public void shield(boolean open) {
        openShield = open;
    }

    /**
     * 破坏
     */
    public void destroy() {
        int destroyId = R.mipmap.ic_launcher;
        bitmap = BitmapFactory.decodeResource(getResources(), destroyId);
        invalidate();
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     * 获取玩家X坐标
     *
     * @return
     */
    public float getPlayerX() {
        return playerX;
    }

    /**
     * 获取玩家Y坐标
     *
     * @return
     */
    public float getPlayerY() {
        return playerY;
    }


}
