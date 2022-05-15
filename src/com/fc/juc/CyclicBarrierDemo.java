package com.fc.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/15 10:36
 * @Version 1.0
 */

public class CyclicBarrierDemo {
    private static final int number=7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(number,()->{
            System.out.println("集齐七颗龙珠就可以召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"星被收集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
