package com.xww.notes.learn_opengl.sample.glsurfaceview.shape;

import com.xww.notes.learn_opengl.utils.ShaderUtils;

/**
 * create at : 04/03/2023 - 10:15 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public abstract class ShaderProgram {

    public int width;

    public int height;

    // 每个顶点的坐标数量
    protected static final int PER_VERTEX = 3;

    // 纯色填充(r,g,b,a)
    protected float fillColor[] = {0.8f, 0.0f, 0.11f, 1.0f};

    // 混合色填充
    protected float fillColors[] = {
            0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f
    };

    // float 所占的字节数
    protected static final int FLOAT_BYTE = 4;

    protected int mProgram;

    protected ShaderProgram(String vertShader, String fragShader) {
        // 创建 shader 程序
        mProgram = ShaderUtils.createProgram(vertShader, fragShader);
    }

    public void changeColor(float[] color) {
        fillColor = color;
    }

    public abstract void draw();

    public abstract void initShader();

    public void sizeChange(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
