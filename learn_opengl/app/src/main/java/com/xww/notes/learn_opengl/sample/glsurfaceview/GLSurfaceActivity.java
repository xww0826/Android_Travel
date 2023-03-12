package com.xww.notes.learn_opengl.sample.glsurfaceview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xww.notes.learn_opengl.R;
import com.xww.notes.learn_opengl.databinding.ActivityGlSurfaceBinding;
import com.xww.notes.learn_opengl.sample.glsurfaceview.shape.ShapeEnum;

/**
 * create at : 04/03/2023 - 9:27 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 * GLSurfaceView 使用文档 : https://developer.android.com/training/graphics/opengl/environment?hl=zh-cn#java
 */
public class GLSurfaceActivity extends AppCompatActivity {

    private ActivityGlSurfaceBinding binding;
    private SampleGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlSurfaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        glSurfaceView = findViewById(R.id.gl_view);
        binding.btnTriangle.setOnClickListener(v -> glSurfaceView.changeShape(ShapeEnum.TRIANGLE));
        binding.btnRectangle.setOnClickListener(v -> glSurfaceView.changeShape(ShapeEnum.RECTANGLE));
        binding.btnCircle.setOnClickListener(v -> glSurfaceView.changeShape(ShapeEnum.CIRCLE));
        binding.btnRandomColor.setOnClickListener(v -> glSurfaceView.changeColor());
        binding.btnColors.setOnClickListener(v -> glSurfaceView.changeShape(ShapeEnum.COLORFUL_CIRCLE));

    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

}
