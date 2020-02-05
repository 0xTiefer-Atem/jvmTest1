package java_learn;

import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread {

    public static void main(String[] args) {
        Resources resources = new Resources();


        MyRunnable1 myRunnable1 = new MyRunnable1(resources);
        MyRunnable1 myRunnable2 = new MyRunnable1(resources);
        MyRunnable1 myRunnable3 = new MyRunnable1(resources);

        Thread t1 = new Thread(myRunnable1);
        Thread t2 = new Thread(myRunnable2);
        Thread t3 = new Thread(myRunnable3);

        t1.start();
        t2.start();
        t3.start();

    }
}

class MyRunnable1 implements Runnable {
    public static boolean flag = false;
    private Resources resources = null;

    public MyRunnable1(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        while (!flag) {
            try {
                resources.incCount();
                System.out.println("当前线程是:  " + Thread.currentThread().getName() + "  当前计数： " + this.resources.display() + "  ,睡上1秒");
                Thread.currentThread().sleep(1000);
            } catch (Exception e) {
            }
        }
        System.out.println("标志改变，当前线程  " + Thread.currentThread().getName() + "  结束");
    }
}


class Resources{
    private int count = 0;

    public synchronized void incCount(){
            count++;
    }

    public int display(){
        return count;
    }
}
