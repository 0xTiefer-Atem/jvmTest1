package org.wq.jvm.test;



/*
* 加入final之后常量再编译阶段会存入到调用这个常量的方法所在的类的常量池中
* 本质上，调用类并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
* 注意:这里指的是将常量存放到了JvmTest2的常量池中，之后JvmTest2与MyParent2就没有关系了
*甚至可以将MyParent2的class文件删除
* 反编译JvmTest2.class文件
* Compiled from "JvmTest2.java"
public class org.wq.jvm.test.JvmTest2 {
  public org.wq.jvm.test.JvmTest2();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #4                  // String hello world
       5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

*
*
* 助记符：
* ldc 表示将int，float或是String类型的常量值从常量池推送至栈顶
* bipush表示将单字节(-128 ~ 127)的常量值推送至栈顶
* sipush表示将一个短整型常量值(-32768 ~ 32767)推送至栈顶
* iconst_m表示将int类型的数字m推送至栈顶(m最多-1到5)，超过5就用bipush
* */

import com.sun.org.apache.bcel.internal.generic.ICONST;

public class JvmTest2 {
    public static void main(String[] args) {
        ICONST iconst = new ICONST(6);

//        System.out.println(MyParent2.str);
    }
}


class MyParent2{
    public static final String str = "hello world";
    static {
        System.out.println("MyParent2 static block");
    }
}
