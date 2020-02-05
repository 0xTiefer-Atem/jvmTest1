package java_learn;

public class ProducerAndConsumerTest {
    public static void main(String[] args) {
        Breakfast breakfast = new Breakfast();
        new Thread(new Producer(breakfast)).start();
        new Thread(new Consumer(breakfast)).start();
    }
}


/**
 * 早餐基础类
 *
 * wait()
 * notify()
 * notifyAll()
 * 三个方法 需要放在同步代码块中执行 因为要获取对象锁
 */
class Breakfast{
    private  String food;

    private  String drink;

    private boolean flag = false;//flag = false 表示需要生产  flag = true 表示需要消费

    public synchronized  void  makeBreakfast(String food,String drink){
        System.out.println("生产者进入--->标志值为："+flag);
        if (flag){
            try {
                System.out.println("make---wait()暂停，释放对象锁");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.food = food;
        try {
            System.out.println("make---sleep()休眠,不释放对象锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.drink = drink;
        System.out.println("make---生产者制造东西完成----");
        this.flag = true;
        System.out.println("make---notify()唤醒，标志值为"+flag);
        notify();
    }


    public synchronized void eatBreakfast(){
        System.out.println("消费者进入--->标志值为："+flag);
        if(!flag){
            try {
                System.out.println("eat---wait()");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("eat---sleep()");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("吃东西---"+this.food+";喝东西---"+this.drink);
        this.flag = false;
        System.out.println("eat---notify()唤醒，标志值为"+flag);
        notify();
    }
}


class Producer implements Runnable{

    private Breakfast breakfast;

    public Producer(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public void run() {
        int i = 7;
        for (int i1 = 0; i1 < i; i1++) {
            if (i1 %2 == 0){
                this.breakfast.makeBreakfast("馒头","豆浆");
            }else {
                this.breakfast.makeBreakfast("面包","冷饮");
            }
        }
    }
}





class Consumer implements Runnable{

    private Breakfast breakfast;

    public Consumer(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public void run() {
        int i = 7;
        for (int i1 = 0; i1 < i; i1++) {
            System.out.println("星期"+(i1+1)+"---消费者要来吃东西了");
            this.breakfast.eatBreakfast();
        }
    }
}
