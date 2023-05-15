package com.xww.notes.learn_opengl.sample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xww.notes.learn_opengl.databinding.ActivityGlSurfaceBinding;
import com.xww.notes.learn_opengl.databinding.ActivityMainBinding;
import com.xww.notes.learn_opengl.sample.glsurfaceview.GLShapeActivity;

public class GLSurfaceActivity extends AppCompatActivity {

    private ActivityGlSurfaceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlSurfaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGlShape.setOnClickListener(v -> {
            startActivity(new Intent(GLSurfaceActivity.this, GLShapeActivity.class));
        });
    }
}