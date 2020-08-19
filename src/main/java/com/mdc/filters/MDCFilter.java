package com.mdc.filters;

import ch.qos.logback.classic.turbo.MatchingFilter;
import org.slf4j.MDC;
import org.slf4j.Marker;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;

public class MDCFilter extends MatchingFilter {

    String value;
    String MDCKey;


    @Override
    public void start() {
        int errorCount = 0;
        if (value == null) {
            addError("\'value\' parameter is mandatory. Cannot start.");
            errorCount++;
        }
        if (MDCKey == null) {
            addError("\'MDCKey\' parameter is mandatory. Cannot start.");
            errorCount++;
        }

        if (errorCount == 0)
            super.start();
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        String value = MDC.get(MDCKey);
        if (this.value.equals(value)) {
            return onMatch;
        }
        return onMismatch;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setMDCKey(String MDCKey) {
        this.MDCKey = MDCKey;
    }
}
