package com.mdc.diagnosticLogging.slf4j;

import ch.qos.logback.classic.LoggerContext;
import com.mdc.diagnosticLogging.Model;
import org.slf4j.MDC;
import org.slf4j.Logger;

public class Slf4jRunnable implements Runnable {

    Logger logger;
    LoggerContext loggerContext;
    Model model;

    public Slf4jRunnable(Logger logger, LoggerContext loggerContext, Model model) {
        this.logger = logger;
        this.loggerContext = loggerContext;
        this.model = model;
    }

    @Override
    public void run() {

        MDC.put("accountId", model.getAccount());
        MDC.put("requestId", model.getRequest());

        logger.trace("Filtered Trace Request for account {} and request {}",  model.getAccount(), model.getRequest());

        MDC.clear();
    }
}