package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolSellTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 35; i++) {
                threadPool.execute(() -> {
                    ticket.sellTicket();
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}

class Ticket {
    private int ticket = 30;
    private Lock lock = new ReentrantLock();

    public void sellTicket() {
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖了第" + (ticket--) + "张票，剩余" + ticket + "张票");
            }
        } finally {
            lock.unlock();
        }
    }
}