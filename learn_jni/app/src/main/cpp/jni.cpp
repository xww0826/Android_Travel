#include <jni.h>
#include <android/log.h>
#include "MD5.h"

#define TAG "NDK-LOG"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

using namespace std;
using namespace toolkit;

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xww_notes_jni_JniUtils_signatureParams(JNIEnv *env, jclass clazz, jstring params) {
    const char *jParams = env->GetStringUTFChars(params, 0);

    LOGI("jni => signatureParams 原来 = %s", jParams);

    string s = MD5(jParams).hexdigest();

    LOGI("jni => signatureParams md5 加密 = %s", s.c_str());

    env->ReleaseStringUTFChars(params, jParams);
    return nullptr;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xww_notes_jni_JniUtils_md5Digest(JNIEnv *env, jclass clazz, jstring input) {
    const char *text = env->GetStringUTFChars(input, 0);
    string s = MD5(text).hexdigest();
    env->ReleaseStringUTFChars(input, text);
    return env->NewStringUTF(s.c_str());
}