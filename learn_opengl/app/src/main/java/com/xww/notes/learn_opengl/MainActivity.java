package com.xww.notes.learn_opengl;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xww.notes.learn_opengl.databinding.ActivityMainBinding;
import com.xww.notes.learn_opengl.sample.glsurfaceview.GLSurfaceActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGlSurfaceView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GLSurfaceActivity.class));
        });
    }
}