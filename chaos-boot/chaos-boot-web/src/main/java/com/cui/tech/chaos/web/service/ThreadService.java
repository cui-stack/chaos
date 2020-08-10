package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.model.DTO;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author G.G
 * @date 2020/6/1 11:28
 */
public class ThreadService {
    public void startThread(DTO t, Consumer<DTO> c) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                c.accept(t);
                super.run();
            }
        };
        thread.start();
    }

    public void startRunnable(DTO t, Consumer<DTO> c) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.accept(t);
            }
        });
        thread1.start();
    }

    public void startExecutor(DTO t, Consumer<DTO> c) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                c.accept(t);
                return "";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void daemonThread() {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("i am alive");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("finally block");
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        //确保main线程结束前能给daemonThread能够分到时间片
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
