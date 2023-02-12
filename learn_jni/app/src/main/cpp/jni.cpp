#include <jni.h>
#include <android/log.h>
#include "MD5.h"
#include "APP.h"

#define TAG "JNI-LOG"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#define SALT "Test"

using namespace std;
using namespace toolkit;

int sign_verification = 0;
char *apk_sign = "308202e4308201cc020101300d06092a864886f70d010105050030373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b30090603550406130255533020170d3231313030323038323534355a180f32303531303932353038323534355a30373116301406035504030c0d416e64726f69642044656275673110300e060355040a0c07416e64726f6964310b300906035504061302555330820122300d06092a864886f70d01010105000382010f003082010a0282010100931c07301f32439e2716accde9e5c4a02e118282662a40c21895ee2de8f3838bdc7ec807154891b051f06cd35af837b6f5ca74b298be5ca72f8a67e9c4e1d0b9929a92ac4174e9695b5dae300808ef6b538c61d7f8ea93399edfb2c4d80f90d227b7f1d6fd069f100d4d0b109a8c973db40acaef70ae2c0b4522675c84c1cd25a8bd8876c153d9ebeb8d2609ccd47d491eafbe057e6624f945905108acb50d1185a1e0c8003e11fc9cefa77234aeed448627f890fe633819434b2c02583bb95b11b1246a57bc0694818e1b98fc2f17d3d2de51a7c2e4e6aa58d0b2e642aca3209e2f1d84cd644d0d01c19ae50c1de5e6126599441a15add674e25507852489130203010001300d06092a864886f70d0101050500038201010067df61639c78f68518c952577d811f57d6f79072d51051ab6b336b6e3c4d5c1cb86f2c96f777e17cb05263d22cab6374f95df79adcbdde75f91019691627247a0e4e0b37d42f3f4cea03584c4bcdf1a30f695f8ba306b32029dd190e1e1565ca0ba13bf70fee7e32a225145e81b7e69d4755c9d3dd5dfba57c6988be9bb1c1b5467964d410861312f03af945bd9b008b2e7cbb532a8b906dd078a3d7406fbcb1b72d895200bfa094ce07ef9400436158ee05c6ff46bc63bfb67fc1f804d39edc2992eabc03f8f897bb976ad18c03fe6e52ca65e11ab57a2e571ec5bbd5e42d5fdd888a6797b95b2805b9b9cfcf1a3a1b1722aeb3881bdd30265c26c8fa53d816";

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xww_notes_jni_JniUtils_signatureParams(JNIEnv *env, jclass clazz, jstring params) {
    if (sign_verification == 0) {
        return env->NewStringUTF("signature err.");
    }

    const char *j_params = env->GetStringUTFChars(params, 0);

    LOGI("jni => signatureParams 原来 = %s", j_params);

    string variant(j_params);
    variant.insert(variant.length(), SALT);
    string md5_str = MD5(variant).hexdigest();


    LOGI("jni => signatureParams md5 加密 = %s", md5_str.c_str());

    env->ReleaseStringUTFChars(params, j_params);
    return env->NewStringUTF("signature pass.");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xww_notes_jni_JniUtils_md5Digest(JNIEnv *env, jclass clazz, jstring input) {
    const char *text = env->GetStringUTFChars(input, 0);
    string md5_str = MD5(text).hexdigest();
    env->ReleaseStringUTFChars(input, text);
    return env->NewStringUTF(md5_str.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xww_notes_jni_JniUtils_signatureVerify(JNIEnv *env, jclass clazz, jobject context) {
    string pkg = APP::get_package_name(env, clazz, context);
    string signature = APP::get_apk_signature(env, clazz, context);
    LOGI("jni => signatureVerify 包名 = %s", pkg.c_str());
    LOGI("jni => signatureVerify apk 签名 = %s", signature.c_str());
    if (strcmp(signature.c_str(), apk_sign) == 0) {
        sign_verification = 1;
        LOGI("jni => signatureVerify apk 签名一致");
    }
}