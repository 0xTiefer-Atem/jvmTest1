package java_learn;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        MyResources myResources = new MyResources(lock);

        MyRun myRun1 = new MyRun(lock,myResources);
        MyRun myRun2 = new MyRun(lock,myResources);


        Thread t1 = new Thread(myRun1);
        Thread t2 = new Thread(myRun2);

        t1.start();
        t2.start();


    }
}


class MyResources{


    public MyResources(Lock l){
        this.l = l;
    }

    private Lock l = null;

    public void display(){
        l.lock();
        try {

            System.out.println("打印信息...");
            while (true) {

            }
        }finally {
            l.unlock();
        }
    }
}



class MyRun implements Runnable{

    private Lock l = null;
    private MyResources myResources = null;

    public MyRun(Lock l, MyResources myResources){
        this.l = l;
        this.myResources = myResources;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获得锁");
        if (l.tryLock()){
            System.out.println(Thread.currentThread().getName() + "成功获得锁");
            myResources.display();
        }
        System.out.println(Thread.currentThread().getName() + "没有获得锁");
    }
}
