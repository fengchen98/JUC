package com.fc.lock;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/7 8:29
 * @Version 1.0
 */

public class DeadLock {
    static Object a=new Object();
    static Object b=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "持有锁A，试图获取锁B");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取锁B");
                }
            }
        },"A").start();


        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "持有锁B，试图获取锁A");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取锁A");
                }
            }
        },"B").start();

    }
}
