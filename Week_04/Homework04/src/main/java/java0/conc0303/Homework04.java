package java0.conc0303;


import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.*;

public class Homework04 {

    static int result8 = 0;

    public static void main(String[] args) throws Exception {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

//        int result = sum(); //这是得到的返回值
        Integer result;

//        1. FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() ->{

            return sum();
        });
        new Thread(futureTask).start();
        result = futureTask.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果1为："+ result);

//        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
//        2. Future
        start=System.currentTimeMillis();

        Integer result1 = Executors.newFixedThreadPool(1).submit(() ->{
            return sum();
        }).get();

        System.out.println("异步计算结果2为："+ result1);

//        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
//        3.
        new Thread(futureTask).start();
        int result2 = futureTask.get();
        System.out.println("异步计算结果3为："+ result2);
//        4.
        Integer result4 = CompletableFuture.supplyAsync(() ->{
            return sum();
        }).get();
        System.out.println("异步计算结果4为："+ result4);
//        5.
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        int[] result5 = {0};
        new Thread(() ->{
            try {
                result5[0] = sum();
                Thread.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        semaphore.acquire();
        System.out.println("异步计算结果5为："+ result5[0]);
//        6.
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int[] result6 = {0};
        new Thread(() ->{
            try {
                result6[0] = sum();
                Thread.sleep(2000);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        countDownLatch.await();
        System.out.println("异步计算结果6为："+ result6[0]);
//        7.
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        int[] result7 = {0};
        new Thread(() ->{
            try {
                result7[0] = sum();
                Thread.sleep(2000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        cyclicBarrier.await();
        System.out.println("异步计算结果7为："+ result7[0]);

        Thread t = new Thread(() ->{
            try {
                result8 = sum();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        t.join();
        System.out.println("异步计算结果8为："+ result8);

        // 然后退出main线程
        System.exit(0);
    }

    public static int sum() {
        return fibo(2);
    }

    private static int fibo(int a) {
        if (a < 2) {

            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }




}


