package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.Enemy;

import java.io.Serializable;
import java.util.Map;

/**
 * 敌人库
 */
public class EnemyLibrary implements Serializable {
    //普通敌人
    private Map<String, Enemy> normalEnemies = null;
    //boss
    private Map<String, Enemy> bossEnemies = null;

    public EnemyLibrary() {

    }

    public boolean setNormalEnemies(Map<String, Enemy> normalEmenys) {
        if (normalEmenys == null) {
            return false;
        } else {
            this.normalEnemies = normalEmenys;
            return true;
        }
    }

    public boolean setBossEnemies(Map<String, Enemy> bossEmenys) {
        if (bossEmenys == null) {
            return false;
        } else {
            this.bossEnemies = bossEmenys;
            return true;
        }
    }

    public Map<String, Enemy> getNormalEnemies() {
        return normalEnemies;
    }

    public Map<String, Enemy> getBossEnemies() {
        return bossEnemies;
    }
}
