package com.fc.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName FengChen
 * @Description TODO
 * @date 2022/5/6 17:07
 * @Version 1.0
 */



public class ThreadDemo4 {
    public static void main(String[] args) {
        List<String> list= Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
