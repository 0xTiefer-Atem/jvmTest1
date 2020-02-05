package org.wq.jvm.test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class JvmTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.str);
    }
}


class MyParent1{
    public static String str = "hello world";
    static {
        System.out.println("MyParent1 class");
    }
}


class MyChild1 extends MyParent1{
    public static String str2 = "welcome";
    static {
        System.out.println("MyChild1 class");
    }
}
