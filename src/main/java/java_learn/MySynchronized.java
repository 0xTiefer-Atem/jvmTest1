package java_learn;

public class MySynchronized {
    public static void main(String[] args) throws InterruptedException {
        MyMethod myMethod = new MyMethod();


        MyRunnable myRunnable = new MyRunnable(myMethod);
        MyRunnable2 myRunnable2 = new MyRunnable2(myMethod);
        MyRunnable3 myRunnable3 = new MyRunnable3(myMethod);


        Thread t = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable2);
        Thread t3 = new Thread(myRunnable3);




        t.start();
        t2.start();
        t3.start();
    }
}

class MyMethod {
    public void display1() {
        synchronized (this) {
            System.out.println("调用 display1方法");
            while (true) {

            }
        }
    }

    public void display2() {
        synchronized (this) {
            try {
                Thread.currentThread().sleep(2000);
                System.out.println("调用 display2方法");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public  void display3() {
        synchronized (this) {
            try {
                Thread.currentThread().sleep(2000);
                System.out.println("调用 display3方法");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class MyRunnable implements Runnable{

    private MyMethod myMethod = null;

    public MyRunnable(MyMethod myMethod) {
        this.myMethod = myMethod;
    }

    @Override
    public void run() {
        myMethod.display1();
    }
}

class MyRunnable2 implements Runnable{

    private MyMethod myMethod = null;

    public MyRunnable2(MyMethod myMethod){
        this.myMethod = myMethod;
    }

    @Override
    public void run() {
        myMethod.display2();
    }
}

class MyRunnable3 implements Runnable{

    private MyMethod myMethod = null;

    public MyRunnable3(MyMethod myMethod){
        this.myMethod = myMethod;
    }

    @Override
    public void run() {
        myMethod.display3();
    }
}
