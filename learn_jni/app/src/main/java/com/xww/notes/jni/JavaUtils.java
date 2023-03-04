package com.xww.notes.jni;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * create at : 12/02/2023 - 5:23 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class JavaUtils {

    public static String getMD5(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        return new BigInteger(1, digest).toString(16);
    }

    public static String getSignature(Context context) {
        try {
            /** 通过包管理器获得指定包名包含签名的包信息 **/
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            /******* 通过返回的包信息获得签名数组 *******/
            Signature[] signatures = packageInfo.signatures;
            /******* 循环遍历签名数组拼接应用签名 *******/
            return signatures[0].toCharsString();
            /************** 得到应用签名 **************/
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
