package com.xww.notes.learn_opengl.utils;

import android.content.Context;
import android.opengl.GLES20;

import com.xww.notes.learn_opengl.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * create at : 11/03/2023 - 9:12 PM
 * author : xuweiwei
 * email : 122265264@qq.com
 * description : <功能说明>
 */
public class ShaderUtils {

    /**
     * 获取 raw 文件下的 .glsl
     *
     * @param context 全局环境
     * @param resId   raw id
     */
    public static String getRawShader(Context context, int resId) {
        InputStream inputStream = context.getResources().openRawResource(resId);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 创建一个 shader 程序
     *
     * @param vertexSource   顶点着色器
     * @param fragmentSource 片段着色器
     * @return program id
     */
    public static int createProgram(String vertexSource, String fragmentSource) {
        // 1、创建顶点着色器
        int vertexShader = createShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0) {
            LogUtils.e("创建程序失败：创建顶点着色器错误！");
            return 0;
        }
        // 2、创建片段着色器
        int fragmentShader = createShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (fragmentShader == 0) {
            LogUtils.e("创建程序失败：创建片段着色器错误！");
            return 0;
        }
        // 3、创建一个着色器程序，并返回新创建程序对象的ID引用
        int program = GLES20.glCreateProgram();
        if (program == 0) {
            LogUtils.e("创建程序失败");
            return 0;
        }

        // 把顶点着色器和片段着色器绑定到程序
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        // 绑定完成后，可以删除着色器
        GLES20.glDeleteShader(vertexShader);
        GLES20.glDeleteShader(fragmentShader);
        // 链接并创建一个可执行的 OpenGL 程序对象
        GLES20.glLinkProgram(program);
        // 检查链接状态
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) { // 编译失败
            LogUtils.e("链接 program 失败！错误：" + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteShader(program);
            return 0;
        }
        return program;
    }

    /**
     * 创建着色器
     *
     * @param type   类型
     * @param source 着色器 glsl
     * @return shader id
     */
    public static int createShader(int type, String source) {
        // glCreateShader 函数创建一个顶点着色器或者片段着色器,并返回新创建着色器的 ID 引用
        int shader = GLES20.glCreateShader(type);
        if (shader == 0) {
            LogUtils.e("创建 shader 失败!");
            return 0;
        }
        // 把着色器和代码关联,然后编译着色器
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        // 检查 shader 编译状态
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) { // 编译失败
            LogUtils.e("编译 shader 失败！错误：" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    /**
     * 创建顶点信息
     *
     * @param vertex 顶点数据
     */
    public static FloatBuffer createFloatBuffer(float[] vertex, int position) {
        // 初始化顶点坐标数据，坐标点的数目 * float所占字节
        ByteBuffer bb = ByteBuffer.allocateDirect(vertex.length * Float.BYTES);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        // 把坐标添加到FloatBuffer
        vertexBuffer.put(vertex);
        // 设置 buffer 的位置为起始点
        vertexBuffer.position(position);
        return vertexBuffer;
    }
}
