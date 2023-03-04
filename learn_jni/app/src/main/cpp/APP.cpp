//
// Created by xww on 12/02/2023.
//

#include "APP.h"

namespace toolkit {

    std::string APP::get_package_name(JNIEnv *env, jclass clazz, jobject obj) {
        jclass clz = env->GetObjectClass(obj);
        jmethodID pkg_name_method = env->GetMethodID(clz, "getPackageName", "()Ljava/lang/String;");
        jstring pkg_name = static_cast<jstring>(env->CallObjectMethod(obj, pkg_name_method));
        return env->GetStringUTFChars(pkg_name, 0);
    }

    std::string APP::get_signature(JNIEnv *env, jclass clazz, jobject obj) {
        jclass clz = env->GetObjectClass(obj);
        jmethodID pm_method = env->GetMethodID(clz, "getPackageManager","()Landroid/content/pm/PackageManager;");
        // 获得 PackageManager 对象
        jobject pkg_obj = env->CallObjectMethod(obj, pm_method);
        // 获得 PackageManager 类
        jclass pm_clz = env->GetObjectClass(pkg_obj);
        jmethodID pkg_info_method = env->GetMethodID(pm_clz, "getPackageInfo","(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
        // 获得应用包的信息
        jmethodID pkg_name_method = env->GetMethodID(clz, "getPackageName", "()Ljava/lang/String;");
        jstring pkg_name = static_cast<jstring>(env->CallObjectMethod(obj, pkg_name_method));

        // 获得 PackageInfo 对象
        jobject pkg_info_obj = env->CallObjectMethod(pkg_obj, pkg_info_method, pkg_name, 64);
        // 获得 PackageInfo 对象
        jclass pkg_info_clz = env->GetObjectClass(pkg_info_obj);
        // 获得签名数组属性的字段
        jfieldID signatures_field = env->GetFieldID(pkg_info_clz, "signatures","[Landroid/content/pm/Signature;");
        // 获取签名数组对象
        jobject signatures_obj = env->GetObjectField(pkg_info_obj, signatures_field);
        jobjectArray signatures_arr = (jobjectArray) signatures_obj;
        // 获取数组的第 0 个签名对象
        jobject signature_obj = env->GetObjectArrayElement(signatures_arr, 0);
        jclass signature_clz = env->GetObjectClass(signature_obj);
        jmethodID to_char_method = env->GetMethodID(signature_clz, "toCharsString","()Ljava/lang/String;");
        // 得到签名
        jstring signature = static_cast<jstring>(env->CallObjectMethod(signature_obj,to_char_method));
        return env->GetStringUTFChars(signature, 0);
    }
}