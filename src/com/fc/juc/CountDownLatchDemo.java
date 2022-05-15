package com.fc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/15 10:22
 * @Version 1.0
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了教室");
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门");
    }
}
