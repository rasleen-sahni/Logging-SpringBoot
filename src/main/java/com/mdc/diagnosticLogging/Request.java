package com.mdc.diagnosticLogging;

public class Request {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static String getRequest() {
        return threadLocal.get();
    }
    public static void setRequest(String request) {
        threadLocal.set(request);
    }
}
