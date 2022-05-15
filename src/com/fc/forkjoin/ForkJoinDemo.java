package com.fc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/15 21:22
 * @Version 1.0
 */

class MyTask extends RecursiveTask<Integer>{
    private static final Integer VALUE=10;
    private int begin;
    private int end;
    private int res;


    public MyTask(int begin,int end){
        this.begin=begin;
        this.end=end;
    }

    @Override
    protected Integer compute() {
        //判断相加的两个数差值是否大于10
        if ((end-begin)<=VALUE){
            for (int i = begin; i <=end ; i++) {
                res+=i;
            }
        }else {
            int mid=(begin+end)/2;
            //拆分
            MyTask task1=new MyTask(begin,mid);
            MyTask task2=new MyTask(mid+1,end);
            task1.fork();
            task2.fork();
            //合并
            res=res+task1.join()+task2.join();
        }
        return null;
    }

}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask=new MyTask(0,100);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Integer> forkjoin = forkJoinPool.submit(myTask);
        System.out.println(forkjoin.get());
        forkJoinPool.shutdown();
    }
}
