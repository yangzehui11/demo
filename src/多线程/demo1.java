package 多线程;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.locks.Lock;

public class demo1 {
    //ForkJoinPool
    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<?> submit = pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        });
        //submit.invoke();
        //submit.invoke();
    }


}
