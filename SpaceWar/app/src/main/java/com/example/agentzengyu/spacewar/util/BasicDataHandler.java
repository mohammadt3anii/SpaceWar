package com.example.agentzengyu.spacewar.util;

import android.util.Log;
import android.util.Xml;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.entity.ShopItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/21.
 */

/**
 * 基础数据处理类
 */
public class BasicDataHandler {
    private BasicData data = null;
    private File file = null;
    private InputStream inputStream = null;

    /**
     * 设置处理的数据参数
     *
     * @param data 基础数据对象
     * @param file 文件
     * @return
     */
    private BasicDataHandler setResource(BasicData data, File file) {
        this.data = data;
        this.file = file;
        return this;
    }

    public BasicDataHandler setResource(BasicData data, InputStream inputStream) {
        this.data = data;
        this.inputStream = inputStream;
        return this;
    }

    /**
     * 存档
     *
     * @param callBack 消息回调
     * @return
     */
//    private void save(final DataHandlerCallBack callBack) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    XmlSerializer xmlSerializer = Xml.newSerializer();
//                    xmlSerializer.setOutput(fos, "utf-8");
//                    xmlSerializer.startDocument("utf-8", true);
//                    xmlSerializer.startTag(null, Config.TAG_SHOP);
//
//                    //战舰库开始
//                    xmlSerializer.startTag(null, Config.TAG_SPACESHIP);
//
//                    //生命库
//                    for (ShopItem life : data.getLifes()) {
//                        xmlSerializer.startTag(null, Config.TAG_LIFE);
//                        setAttributes(xmlSerializer, life);
//                        xmlSerializer.endTag(null, Config.TAG_LIFE);
//                    }
//
//                    //防御库
//                    for (ShopItem defense : data.getDefenses()) {
//                        xmlSerializer.startTag(null, Config.TAG_DEFENSE);
//                        setAttributes(xmlSerializer, defense);
//                        xmlSerializer.endTag(null, Config.TAG_DEFENSE);
//                    }
//
//                    //敏捷库
//                    for (ShopItem agility : data.getAgilities()) {
//                        xmlSerializer.startTag(null, Config.TAG_AGILITY);
//                        setAttributes(xmlSerializer, agility);
//                        xmlSerializer.endTag(null, Config.TAG_AGILITY);
//                    }
//
//                    //护盾库
//                    for (ShopItem shield : data.getShields()) {
//                        xmlSerializer.startTag(null, Config.TAG_SHIELD);
//                        setAttributes(xmlSerializer, shield);
//                        xmlSerializer.endTag(null, Config.TAG_SHIELD);
//                    }
//
//                    //战舰库结束
//                    xmlSerializer.endTag(null, Config.TAG_SPACESHIP);
//
//                    //武器库开始
//                    xmlSerializer.startTag(null, Config.TAG_WEAPON);
//
//                    //力量库
//                    for (ShopItem power : data.getPowers()) {
//                        xmlSerializer.startTag(null, Config.TAG_POWER);
//                        setAttributes(xmlSerializer, power);
//                        xmlSerializer.endTag(null, Config.TAG_POWER);
//                    }
//
//                    //速度库
//                    for (ShopItem speed : data.getSpeeds()) {
//                        xmlSerializer.startTag(null, Config.TAG_SPEED);
//                        setAttributes(xmlSerializer, speed);
//                        xmlSerializer.endTag(null, Config.TAG_SPEED);
//                    }
//
//                    //范围库
//                    for (ShopItem range : data.getRanges()) {
//                        xmlSerializer.startTag(null, Config.TAG_RANGE);
//                        setAttributes(xmlSerializer, range);
//                        xmlSerializer.endTag(null, Config.TAG_RANGE);
//                    }
//
//                    //核弹库
//                    for (ShopItem nuclear : data.getNuclears()) {
//                        xmlSerializer.startTag(null, Config.TAG_NUCLEAR);
//                        setAttributes(xmlSerializer, nuclear);
//                        xmlSerializer.endTag(null, Config.TAG_NUCLEAR);
//                    }
//
//                    //武器库结束
//                    xmlSerializer.endTag(null, Config.TAG_WEAPON);
//
//                    xmlSerializer.endTag(null, Config.TAG_SHOP);
//
//                    xmlSerializer.endDocument();
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 存档时设置属性
//     *
//     * @param xmlSerializer 解析器对象
//     * @param item          存档item
//     */
//    private void setAttributes(XmlSerializer xmlSerializer, ShopItem item) {
//        try {
//            xmlSerializer.attribute(null, Config.TAG_NAME, item.getName());
//            xmlSerializer.attribute(null, Config.TAG_DETAIL, ""+item.getDetail());
//            xmlSerializer.attribute(null, Config.TAG_LEVEL, "" + item.getLevel());
//            xmlSerializer.attribute(null, Config.TAG_FEE, "" + item.getFee());
//            xmlSerializer.attribute(null, Config.TAG_IMAGE, "" + item.getImage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 读档
     *
     * @param callBack 消息回调
     */
    public void read(final DataHandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParser xmlPullParser = Xml.newPullParser();
                    xmlPullParser.setInput(inputStream, "utf-8");
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                callBack.onStart("Start to read basic data:");
                                break;
                            case XmlPullParser.START_TAG:
                                String tagName = xmlPullParser.getName();
                                Log.e("tagName", tagName);
                                ShopItem item = new ShopItem();
                                switch (tagName) {
                                    case Config.TAG_LIFE:
                                        getAttributes(xmlPullParser, data.getLifes(), item, tagName);
                                        break;
                                    case Config.TAG_DEFENSE:
                                        getAttributes(xmlPullParser, data.getDefenses(), item, tagName);
                                        break;
                                    case Config.TAG_AGILITY:
                                        getAttributes(xmlPullParser, data.getAgilities(), item, tagName);
                                        break;
                                    case Config.TAG_SHIELD:
                                        getAttributes(xmlPullParser, data.getShields(), item, tagName);
                                        break;
                                    case Config.TAG_POWER:
                                        getAttributes(xmlPullParser, data.getPowers(), item, tagName);
                                        break;
                                    case Config.TAG_SPEED:
                                        getAttributes(xmlPullParser, data.getSpeeds(), item, tagName);
                                        break;
                                    case Config.TAG_RANGE:
                                        getAttributes(xmlPullParser, data.getRanges(), item, tagName);
                                        break;
                                    case Config.TAG_NUCLEAR:
                                        getAttributes(xmlPullParser, data.getNuclears(), item, tagName);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case XmlPullParser.TEXT:
                                Log.e("BasicDataHandler", xmlPullParser.getText() + "");
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                    callBack.onSuccess("Read basic data successful.");
                } catch (FileNotFoundException e) {
                    callBack.onFailure("Read basic data abortively.", e);
                } catch (XmlPullParserException e) {
                    callBack.onFailure("Read basic data abortively.", e);
                } catch (IOException e) {
                    callBack.onFailure("Read basic data abortively.", e);
                }
            }
        }).start();
    }

    /**
     * 读档时获取属性
     *
     * @param xmlPullParser 解析器
     * @param items         item数组
     * @param item          item对象
     */
    private void getAttributes(XmlPullParser xmlPullParser, ArrayList<ShopItem> items, ShopItem item, String endTagName) {
        try {
            xmlPullParser.next();
            String tagName;
            while (!endTagName.equals((tagName = xmlPullParser.getName()))) {
                Log.e("tagName", tagName + "  ");
                String value = xmlPullParser.nextText();
                Log.e("value", value);
                if (!"".equals(value)) {
                    switch (tagName) {
                        case Config.TAG_NAME:
                            item.setName(value);
                            break;
                        case Config.TAG_DETAIL:
                            item.setDetail(Integer.parseInt(value));
                            break;
                        case Config.TAG_LEVEL:
                            item.setLevel(Integer.parseInt(value));
                            break;
                        case Config.TAG_FEE:
                            item.setFee(Integer.parseInt(value));
                            break;
                        case Config.TAG_IMAGE:
                            item.setImage(Integer.parseInt(value));
                            break;
                        default:
                            break;
                    }
                }
                xmlPullParser.next();
            }
            items.add(item);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}