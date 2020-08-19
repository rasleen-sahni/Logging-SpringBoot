package com.mdc.diagnosticLogging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import com.mdc.diagnosticLogging.slf4j.Slf4jRunnable;

import static java.util.concurrent.TimeUnit.MINUTES;

public class DiagnosticLoggerDemo {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DiagnosticLoggerDemo.class);
    final static LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    public static void main(String[] args) {

        String[] accounts = {"1001", "1002", "1003"};
        String[] requests = {"1", "2", "3"};

        ExecutorService executor = new ThreadPoolExecutor(3, 3, 0, MINUTES,
          new LinkedBlockingQueue<>(), Thread::new, new AbortPolicy());

        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(loggerContext);
            loggerContext.reset();
            configurator.doConfigure("/Users/rasleen.sahni/appdy/codebase/gs-spring-boot/initial/src/main/resources/diagnosticLogback.xml");
        } catch (JoranException je) {
            je.printStackTrace();
        }

        for (int i = 0; i < accounts.length; i++) {
            Account.setAccount(accounts[i]);
            Request.setRequest(requests[i]);
            Model model = new Model(Account.getAccount(), Request.getRequest());
            logger.info("Running the thread for account {} and request {} ", Account.getAccount(), Request.getRequest());
            Runnable task = new Slf4jRunnable(logger, loggerContext, model);
            executor.submit(task);
        }
        executor.shutdown();
    }
}