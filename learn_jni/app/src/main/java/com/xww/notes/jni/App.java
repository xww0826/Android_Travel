package com.xww.notes.jni;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * create at : 12/02/2023 - 3:24 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        System.loadLibrary("jni");
        Log.i("JNI", "static initializer: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
