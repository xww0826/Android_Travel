package com.xww.notes.learn_opengl.sample.glsurfaceview.shape;

import com.xww.notes.learn_opengl.utils.ShaderUtils;

/**
 * create at : 04/03/2023 - 10:15 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public abstract class ShaderProgram {
    // float 所占的字节数
    protected static final int FLOAT_BYTE = 4;

    protected int mProgram;

    protected ShaderProgram(String vertShader, String fragShader) {
        // 创建 shader 程序
        mProgram = ShaderUtils.createProgram(vertShader, fragShader);
    }

    public abstract void draw();

}
