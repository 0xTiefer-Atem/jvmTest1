package org.wq.jvm.test;



/*
* 当一个接口在初始化时，并不要求其父接口也完成初始化
* 只有在真正使用到父接口的时(如直接引用接口中所定义的常量时)，才会初始化
*
* */
public class JvmTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}


interface MyParent5{
    public static int a = 5;
}

interface MyChild5 extends MyParent5{
    public static int b = 6;
}