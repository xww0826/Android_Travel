package com.xww.notes.jni;

import android.content.Context;
import android.util.Log;

/**
 * create at : 12/02/2023 - 3:27 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class JniUtils {

    static {
        System.loadLibrary("jni");
        Log.i("JNI", "static initializer: ");
    }

    /**
     * 签名校验
     */
    public static native void signatureVerify(Context context);

    /**
     * 对网络请求参数进行加密算法
     *
     * @param params 请求参数
     * @return 返回加密结果
     */
    public static native String signatureParams(String params);

    /**
     * md5 信息摘要算法
     *
     * @param input 输入内容
     * @return 返回结果
     */
    public static native String md5Digest(String input);
}
