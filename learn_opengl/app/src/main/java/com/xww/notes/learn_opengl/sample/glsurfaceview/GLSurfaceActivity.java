package com.xww.notes.learn_opengl.sample.glsurfaceview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * create at : 04/03/2023 - 9:27 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 * GLSurfaceView 使用文档 : https://developer.android.com/training/graphics/opengl/environment?hl=zh-cn#java
 */
public class GLSurfaceActivity extends AppCompatActivity {
    private SampleGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new SampleGLSurfaceView(this);
        setContentView(glSurfaceView);
    }
}
