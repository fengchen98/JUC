package com.fc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/6 16:54
 * @Version 1.0
 */

class ShareResource{
    private int flag=1;
    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void prtint5(int loop){
        lock.lock();
        try {
            while (flag!=1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+" 轮数："+loop);
            }
            flag=2;
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void prtint10(int loop){
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+" 轮数："+loop);
            }
            flag=3;
            c3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void prtint15(int loop){
        lock.lock();
        try {
            while (flag!=3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+" 轮数："+loop);
            }
            flag=1;
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource=new ShareResource();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.prtint5(i);
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.prtint10(i);
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.prtint15(i);
            }
        },"CC").start();
    }
}
