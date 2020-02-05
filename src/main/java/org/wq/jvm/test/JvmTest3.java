package org.wq.jvm.test;

import java.util.UUID;



/*
* 当一个常量的值在编译期不能确定，那么其值就不会放到调用类的常量池中
* 这时在运行程序，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
* */
public class JvmTest3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.uuid);
    }
}

class MyParent3{
    public static final String uuid = UUID.randomUUID().toString();
    static {
        System.out.println("MyParent3 static block");
    }
}
