//
// Created by xww on 12/02/2023.
//

#include <string>
#include <jni.h>

#ifndef LEARN_JNI_APP_H
#define LEARN_JNI_APP_H

namespace toolkit {

    class APP {

    public:

        static std::string get_package_name(JNIEnv *env, jclass clazz, jobject obj);

        static std::string get_signature(JNIEnv *env, jclass clazz, jobject obj);
    };

}

#endif
