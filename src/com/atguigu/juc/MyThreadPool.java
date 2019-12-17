package com.atguigu.juc;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <= 12; i++) {
            int tempI = i;
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+":正在办理业务,客人号码是:"+tempI);
            });
        }
    }
}
