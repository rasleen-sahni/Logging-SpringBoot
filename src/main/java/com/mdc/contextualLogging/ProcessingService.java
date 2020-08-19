package com.mdc.contextualLogging;

/**
 * A fake processing service simulating an actual one
 */
public abstract class ProcessingService {

    public void process(String account) {

        beforeProcessing(account);
        try {

            // Pausing randomly to simulate an actual service
            Thread.sleep((long) (500 + Math.random() * 500));

        } catch (InterruptedException e) {
            // should never happen
        }
        afterProcessing(account);
    }

    abstract protected void beforeProcessing(String amount);

    abstract protected void afterProcessing(String amount);
}
