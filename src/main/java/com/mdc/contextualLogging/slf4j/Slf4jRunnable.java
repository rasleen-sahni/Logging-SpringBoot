package com.mdc.contextualLogging.slf4j;

import com.mdc.contextualLogging.Payload;
import org.slf4j.MDC;

public class Slf4jRunnable implements Runnable {
    private final Payload payload;

    public Slf4jRunnable(Payload payload) {
        this.payload = payload;
    }

    public void run() {

        MDC.put("accountId", payload.getAccount());
        MDC.put("requestId", payload.getRequest());
        MDC.put("customerId", payload.getCustomer());

        new Slf4TransferService().process(payload.getAccount());
    }
}