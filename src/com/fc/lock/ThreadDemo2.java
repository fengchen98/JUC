package com.fc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/6 16:36
 * @Version 1.0
 */

class Share{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void incre() throws InterruptedException {
        lock.lock();

        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decre() throws InterruptedException {
        lock.lock();

        try {
            while (number!=1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share=new Share();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decre();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"dd").start();
    }


}
