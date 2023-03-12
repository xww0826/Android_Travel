package com.xww.notes.learn_opengl.sample.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.xww.notes.learn_opengl.R;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.Circle;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.ColorfulCircle;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.Rectangle;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.ShaderProgram;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.ShapeEnum;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.Triangle;
import com.xww.notes.learn_opengl.utils.ShaderUtils;

import java.util.Random;

/**
 * create at : 04/03/2023 - 9:24 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class SampleGLSurfaceView extends GLSurfaceView {

    private SampleGLRenderer renderer;
    private Random random;

    public SampleGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public SampleGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        random = new Random();

        // 创建 OpenGL ES 2.0 上下文
        setEGLContextClientVersion(2);
        // 创建 GLSurfaceView 的渲染器
        renderer = new SampleGLRenderer(getContext());
        // 设置渲染器
        setRenderer(renderer);
        /**
         * 设置渲染模式
         * 1、GLSurfaceView.RENDERMODE_WHEN_DIRTY （渲染器仅在创建表面或调用 requestRender 时渲染）
         *
         * 2、GLSurfaceView.RENDERMODE_CONTINUOUSLY （不断调用渲染器以重新渲染场景，一般系统是每 16ms 刷新会调用一次）
         */
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void changeShape(ShapeEnum shape) {
        String vertShader = ShaderUtils.getRawShader(getContext(), R.raw.vert);
        String fragShader = ShaderUtils.getRawShader(getContext(), R.raw.frag);
        // 转 GL 线程才能调用刷新
        queueEvent(() -> {
            ShaderProgram shader = null;
            switch (shape) {
                case TRIANGLE:
                    shader = new Triangle(vertShader, fragShader);
                    break;
                case RECTANGLE:
                    shader = new Rectangle(vertShader, fragShader);
                    break;
                case CIRCLE:
                    shader = new Circle(vertShader, fragShader);
                    break;
                case COLORFUL_CIRCLE:
                    shader = new ColorfulCircle(vertShader, fragShader);
                    break;
            }
            renderer.setShader(shader);
            shader.initShader();
            requestRender();
        });
    }

    public void changeColor() {
        queueEvent(() -> {
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            ShaderProgram shader = renderer.getShader();
            shader.changeColor(new float[]{r, g, b, 1.0f});
            requestRender();
        });
    }

}
