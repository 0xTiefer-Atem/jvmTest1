package java_learn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        int i = 1;
        while (i > 0){
            i++;
        }
        System.out.println(Math.abs(i+2020));


//        MyCallable myCallable = new MyCallable();
//        FutureTask f = new FutureTask(myCallable);
//        Thread t = new Thread(f);
//        t.start();
//        Object o = f.get();
//        System.out.println(o.toString());
    }
}

class MyCallable implements Callable{
    private People people;

    @Override
    public Object call() throws Exception {
        people = new People("as",10);
        return people;
    }
}

class People{
    private String name;
    private int age;

    public People(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
