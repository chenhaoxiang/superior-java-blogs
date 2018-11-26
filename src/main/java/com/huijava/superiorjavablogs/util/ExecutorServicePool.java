package com.huijava.superiorjavablogs.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/17.
 * Time: 下午 3:36.
 * Explain: ExecutorService线程池 -固定线程
 */
public class ExecutorServicePool {
    private static ExecutorService pool;

    static {
        pool = Executors.newFixedThreadPool(50);
    }

    public static ExecutorService getExecutorService() {
        return pool;
    }

    public static Object runCallableAndBack(Callable callable) throws ExecutionException, InterruptedException {
        return pool.submit(callable).get().toString();
    }

    public static Future runCallable(Callable callable) {
        return pool.submit(callable);
    }
}
