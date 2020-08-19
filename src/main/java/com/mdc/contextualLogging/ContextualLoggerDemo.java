package com.mdc.contextualLogging;

import com.mdc.contextualLogging.pool.MdcAwareThreadPoolExecutor;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import com.mdc.contextualLogging.slf4j.Slf4jRunnable;

import static java.util.concurrent.TimeUnit.MINUTES;

class SimpleThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
public class ContextualLoggerDemo {

    public static void main(String[] args) {
        Long[] accounts = {1000L, 1001L, 1002L, 1003L};

        ExecutorService executor = new MdcAwareThreadPoolExecutor(3, 3, 0, MINUTES,
          new LinkedBlockingQueue<>(), new SimpleThreadFactory(), new AbortPolicy());

        TransactionFactory transactionFactory = new TransactionFactory();

        for (int i = 0; i < 4; i++) {
            Account.setAccount(accounts[i]);
            Request.setRequest(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            Payload payload = transactionFactory.newInstance();
            Runnable task = new Slf4jRunnable(payload);
            executor.submit(task);
        }
        executor.shutdown();
    }
}