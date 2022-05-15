package com.fc.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/15 18:09
 * @Version 1.0
 */

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //一池N线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <=20 ; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }

    }
}
