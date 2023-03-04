package com.xww.notes.learn_opengl.sample.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * create at : 04/03/2023 - 9:24 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class SampleGLSurfaceView extends GLSurfaceView {

    private SampleGLRenderer renderer;

    public SampleGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public SampleGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        // 创建 OpenGL ES 2.0 上下文
        setEGLContextClientVersion(2);
        // 创建 GLSurfaceView 的渲染器
        renderer = new SampleGLRenderer();
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
}
