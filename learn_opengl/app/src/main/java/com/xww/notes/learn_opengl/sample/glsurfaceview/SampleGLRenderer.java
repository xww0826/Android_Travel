package com.xww.notes.learn_opengl.sample.glsurfaceview;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.xww.notes.learn_opengl.LogUtils;
import com.xww.notes.learn_opengl.R;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.Rectangle;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.ShaderProgram;
import com.xww.notes.learn_opengl.utils.ShaderUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * create at : 04/03/2023 - 9:25 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class SampleGLRenderer implements GLSurfaceView.Renderer {

    private ShaderProgram shader;
    private Context context;

    public SampleGLRenderer(Context context) {
        this.context = context;
    }

    private void initShader() {
        String vertShader = ShaderUtils.getRawShader(context, R.raw.vert);
        String fragShader = ShaderUtils.getRawShader(context, R.raw.frag);
        shader = new Rectangle(vertShader, fragShader);
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    /**
     * 调用一次以设置视图的 OpenGL ES 环境
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        LogUtils.d("onSurfaceCreated");
        // 设置绘图背景
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        initShader();
    }

    /**
     * 当视图的几何图形发生变化（例如当设备的屏幕方向发生变化）时调用
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        LogUtils.d("onSurfaceChanged : width = " + width + " , height = " + height);
        // 设置 GL 绘制的视图大小
        GLES20.glViewport(0, 0, width, height);
    }

    /**
     * 每次重新绘制视图时调用
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        LogUtils.d("onDrawFrame");
        // 首先清理屏幕
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        shader.draw();
    }

    public ShaderProgram getShader() {
        return shader;
    }
}
