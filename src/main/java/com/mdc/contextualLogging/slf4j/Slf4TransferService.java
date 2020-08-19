package com.mdc.contextualLogging.slf4j;

import com.mdc.contextualLogging.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Slf4TransferService extends ProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(Slf4TransferService.class);

    @Override
    protected void beforeProcessing(String account) {
        logger.info("Sent a request to the service");
    }

    @Override
    protected void afterProcessing(String account) {
        logger.info("Processed the payload for the request ");
    }
}