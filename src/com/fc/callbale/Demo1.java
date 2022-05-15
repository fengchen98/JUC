package com.fc.callbale;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/15 9:07
 * @Version 1.0
 */

class  MyThread1 implements Runnable{
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable{
    @Override
    public Integer call() throws Exception {
        return 200;
    }
}

//比较callable接口和runnable接口
public class Demo1 {

    public static void main(String[] args) {
        new Thread(new MyThread1(),"AA").start();
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask1=new FutureTask<>(()->{
            return 1024;
        });
        new Thread(futureTask1,"BB").start();
    }
}
