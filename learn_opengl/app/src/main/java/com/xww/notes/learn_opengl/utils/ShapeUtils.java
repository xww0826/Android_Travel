package com.xww.notes.learn_opengl.utils;

import java.util.Arrays;

/**
 * create at : 12/03/2023 - 12:50 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class ShapeUtils {

    /**
     * 获取圆周上散点坐标
     *
     * @param centerX  圆心 x
     * @param centerY  圆心 y
     * @param radius   半径
     * @param pointNum 散点数量
     */
    public static float[] getCirclePoints(float centerX, float centerY, float radius, int pointNum) {
        float unit = (float) (2 * Math.PI / pointNum);
        float[] coords = new float[pointNum * 3];
        for (int i = 0; i < pointNum; i++) {
            coords[i * 3] = (float) (centerX + radius * Math.cos(unit * i));
            coords[i * 3 + 1] = (float) (centerY + radius * Math.sin(unit * i));
            coords[i * 3 + 2] = 0;
        }
        return coords;
    }

    //调整坐标
    public static float[] adjustCoord(float[] coords, int width, int height) {
        float ratio = width > height ? (1.0f * height / width) : (1.0f * width / height);
        int start = width > height ? 0 : 1;
        float[] tempCoord = Arrays.copyOf(coords, coords.length);
        int num = tempCoord.length / 3;
        for (int i = 0; i < num; i++) {
            tempCoord[start + i * 3] *= ratio;
        }
        return tempCoord;
    }

}
