package com.xww.notes.jni;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.xww.notes.jni.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnMd5.setOnClickListener(v -> {
            String test = "user=123&pwd=123456";
            String jniMd5 = JniUtils.md5Digest(test);
            String javaMd5 = JavaUtils.getMD5(test);
            Log.i("TAG", "java => md5 算法: " + javaMd5);
            Log.i("TAG", "jni => md5 算法: " + jniMd5);
            if (TextUtils.equals(jniMd5, javaMd5)) {
                Log.i("TAG", "算法执行结果一致");
            }
        });

        binding.btnSignature.setOnClickListener(v -> {
            // 先调用校验签名
            JniUtils.signatureVerify(MainActivity.this);
            String test = "user=123&pwd=123456";
            String jniMd5 = JniUtils.signatureParams(test);
            Log.i("TAG", "jni => 校验结果: " + jniMd5);
        });

    }
}