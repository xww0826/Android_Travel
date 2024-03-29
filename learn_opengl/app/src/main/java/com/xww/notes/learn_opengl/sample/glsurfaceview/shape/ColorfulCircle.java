package com.xww.notes.learn_opengl.sample.glsurfaceview.shape;

import android.opengl.GLES30;

import com.xww.notes.learn_opengl.utils.ShaderUtils;
import com.xww.notes.learn_opengl.utils.ShapeUtils;

import java.nio.FloatBuffer;

public class ColorfulCircle extends ShaderProgram {

    private FloatBuffer vertexBuffer;
    private FloatBuffer colorsBuffer;

    public ColorfulCircle(String vertShader, String fragShader) {
        super(vertShader, fragShader);
    }

    @Override
    public void draw() {
        // 激活着色器程序,把程序添加到 OpenGL 环境
        GLES30.glUseProgram(mProgram);
        // 获取顶点着色器中的 vPosition 变量(因为之前已经编译过着色器代码,所以可以从着色器程序中获取)
        int position = GLES30.glGetAttribLocation(mProgram, "vPosition");
        // 获取顶点着色器中的 aColor 变量
        int color = GLES30.glGetAttribLocation(mProgram, "aColor");
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);
        // 将顶点数据传递给 position 指向的 vPosition 变量,将顶点属性与顶点缓冲对象关联
        GLES30.glVertexAttribPointer(position, PER_VERTEX, GLES30.GL_FLOAT, false, 0, vertexBuffer);
        GLES30.glVertexAttribPointer(color, PER_VERTEX, GLES30.GL_FLOAT, false, 0, colorsBuffer);
        // 绘制顶点数组
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN, 0, 45);
        // 操作完后,取消允许操作顶点对象 position
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
    }

    @Override
    public void initShader() {
        float[] points = ShapeUtils.getCirclePoints(0f, 0f, 0.75f, 45);
        float[] vertexes = ShapeUtils.adjustCoord(points, width, height);
        vertexBuffer = ShaderUtils.createFloatBuffer(vertexes, 0);
        colorsBuffer = ShaderUtils.createFloatBuffer(fillColors, 1);
    }
}
    