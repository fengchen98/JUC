package com.fc.sync;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/6 11:21
 * @Version 1.0
 */

class Ticket{
    private int number=30;
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出："+number-- +"剩下"+number);
        }
    }

}
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        //创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"bb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"cc").start();
    }

}
