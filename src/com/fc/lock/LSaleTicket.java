package com.fc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/6 14:55
 * @Version 1.0
 */
class LTicket{
    private int number=30;
    private final ReentrantLock lock=new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出"+ number-- + "剩余"+number);
            }
        }finally {
            lock.unlock();
        }

    }
}

public class LSaleTicket {
    public static void main(String[] args) {
        LTicket lTicket=new LTicket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"bb").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"cc").start();
    }

}
