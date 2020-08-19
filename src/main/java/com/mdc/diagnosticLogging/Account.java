package com.mdc.diagnosticLogging;

public class Account {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static String getAccount() {
        return threadLocal.get();
    }
    public static void setAccount(String account) {
        threadLocal.set(account);
    }
}
