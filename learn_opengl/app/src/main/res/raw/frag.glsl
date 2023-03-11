// 片段着色器（GLSL 语言编写）
// 1、Uniform 是 GLSL 的关键字，是一种从 CPU 中的应用向 GPU 中的着色器发送数据的方式；
//   与普通 attribute 不同的是，uniform 是全局的(Global)，即 uniform 变量在每个着色器程序对象中都是独一无二的，
//   而且它可以被着色器程序的任意着色器在任意阶段访问。无论你把 uniform 值设置成什么，uniform 会一直保存它们的数据，直到它们被重置或更新。
// 2、vColor 是开发者自定义的变量名
// 3、gl_FragColor 是 GLSL 的内建变量：片段着色器对象，这里把我们自定义的 vColor 赋值过去，后面我们会在着色器程序中取出来并进行操作；

precision mediump float;

uniform vec4 vColor;

void main(){

    gl_FragColor = vColor;
}