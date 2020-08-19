package com.mdc.contextualLogging;

/*
This class provides the request Id per request
 */
public class Request {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static Long getRequest() {
        return threadLocal.get();
    }
    public static void setRequest(Long request) {
        threadLocal.set(request);
    }
}
