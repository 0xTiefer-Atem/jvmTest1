package java_learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;



/**
 *简单的线程池demo
 *
 * */
public class MyThreadPoolTest {
    public static void main(String[] args) throws IOException {
        MyThreadPool myThreadPool = new MyThreadPool();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String command = null;
        System.out.println("请输入想要执行的命令(退出请输入quit)");
        while ( !(command= bfr.readLine()).equals("quit")){
            myThreadPool.myExecute(new MyRunnableTask(command));
            System.out.println("请输入想要执行的命令(退出请输入quit)");
        }
//        for (int i = 0; i < 9; i++) {
//            myThreadPool.myExecute(new MyRunnableTask("taskName-"+(i+1)));
//        }
        try {
//            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("停止线程池");
        myThreadPool.stopMyThreadPool();

    }
}


class MyThreadPool {
    //默认线程大小
    private int defaultThreadNum = 5;

    //默认最大任务数量
    private int maxTaskNum = 10;

    //线程池队列
    private ArrayList<MyThread> threadList;

    //任务队列
    private ArrayBlockingQueue<Runnable> runnableQueue;

    //初始化线程池
    public MyThreadPool() {
        myInitPool();
    }

    public MyThreadPool(int threadNum, int maxTaskNum) {
        if (threadNum <= 0) {
            this.defaultThreadNum = threadNum;
        }
        if (maxTaskNum <= 0) {
            this.maxTaskNum = maxTaskNum;
        }
        myInitPool();
    }


    private void myInitPool() {
        //初始化任务队列
        runnableQueue = new ArrayBlockingQueue<Runnable>(this.maxTaskNum);

        //初始化线程队列
        threadList = new ArrayList<MyThread>(this.defaultThreadNum);

        //初始化MyThread线程并放入线程队列中运行
        for (int i = 0; i < defaultThreadNum; i++) {
            MyThread myThread = new MyThread();
            threadList.add(myThread);
            myThread.start();
        }
    }


    //提交任务
    public void myExecute(Runnable runnableTask) {
        try {
            this.runnableQueue.put(runnableTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void stopMyThreadPool() {
        destroyThreadPool();
    }

    private void destroyThreadPool() {
        runnableQueue.clear();
        runnableQueue = null;
        Iterator iterator = threadList.iterator();
        while (iterator.hasNext()){
           Thread thread= (Thread) iterator.next();
           thread.interrupt();
           iterator.remove();
        }
        threadList = null;
    }


    class MyThread extends Thread {
        Runnable runnableTask = null;
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    //只要该线程不被中断就可以一直重复使用

                    //若任务队列里面没有任务则会阻塞在这里
                    runnableTask = runnableQueue.take();
                    runnableTask.run();
                    runnableTask = null;
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "  被中断");
                    break;
                }
            }
        }
    }


}


class MyRunnableTask implements Runnable {

    private String taskName;

    public MyRunnableTask() {

    }

    public MyRunnableTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(taskName + "  由线程 " + Thread.currentThread().getName() + " 开始执行,时间戳:  " + sdf.format(new Date()));
//            Thread.currentThread().sleep(2000);
            System.out.println(taskName + "  由线程 " + Thread.currentThread().getName() + " 执行完毕,时间戳:  " + sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



