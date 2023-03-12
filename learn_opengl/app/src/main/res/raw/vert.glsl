// 顶点着色器（GLSL 语言编写）
// 1、attribute 是的关键字表示声明一个属性
// 2、vec4 是 GLSL 的数据类型关键字，包含 4 个 float 分量的默认向量
// 3、vPosition 是开发者自定义的变量名
// 4、gl_Position 是 GLSL 的内建变量：顶点着色器输出向量，这里把我们自定义的 vPosition 赋值过去，
//    后面我们会在着色器程序中取出来操作顶点着色器中的数据

attribute vec4 vPosition;
attribute vec4 aColor;
varying vec4 vColor;

void main(){
    gl_Position = vPosition;
    vColor = aColor;
}