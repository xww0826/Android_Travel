package com.xww.notes.learn_opengl.sample.glsurfaceview.shape;

import android.opengl.GLES30;

import com.xww.notes.learn_opengl.utils.ShaderUtils;

import java.nio.FloatBuffer;

public class Rectangle extends ShaderProgram {

    private FloatBuffer vertexBuffer;

    // 矩形四个点的坐标值(逆时针方向,在 3D 坐标系中,方向决定了哪面是正面)
    private static final float RECTANGLE_VERTEXES[] = {
            -0.5f, -0.5f, 0.0f, // 左下顶点
            0.5f, -0.5f, 0.0f,  // 右下顶点
            -0.5f, 0.5f, 0.0f, // 左上顶点
            0.5f, 0.5f, 0.0f // 右上顶点
    };

    public Rectangle(String vertShader, String fragShader) {
        super(vertShader, fragShader);
    }

    @Override
    public void draw() {
        // 激活着色器程序,把程序添加到 OpenGL 环境
        GLES30.glUseProgram(mProgram);
        // 获取顶点着色器中的 vPosition 变量(因为之前已经编译过着色器代码,所以可以从着色器程序中获取)
        int position = GLES30.glGetAttribLocation(mProgram, "vPosition");
        GLES30.glEnableVertexAttribArray(position);
        // 将顶点数据传递给 position 指向的 vPosition 变量,将顶点属性与顶点缓冲对象关联
        GLES30.glVertexAttribPointer(position, PER_VERTEX, GLES30.GL_FLOAT, false, PER_VERTEX * FLOAT_BYTE, vertexBuffer);
        // 获取片段着色器中的 vColor 变量
        int color = GLES30.glGetUniformLocation(mProgram, "vColor");
        // 通过 color 设置绘制的颜色值
        GLES30.glUniform4fv(color, 1, fillColor, 0);
        // 绘制顶点数组
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, RECTANGLE_VERTEXES.length / PER_VERTEX);
        // 操作完后,取消允许操作顶点对象 position
        GLES30.glDisableVertexAttribArray(position);
    }

    @Override
    public void initShader() {
        vertexBuffer = ShaderUtils.createFloatBuffer(RECTANGLE_VERTEXES, 0);
    }
}
    