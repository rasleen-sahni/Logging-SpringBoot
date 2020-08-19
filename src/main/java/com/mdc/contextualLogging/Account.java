package com.mdc.contextualLogging;

/*
This class provides the account Id per request
 */
public class Account {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static Long getAccount() {
        return threadLocal.get();
    }
    public static void setAccount(Long account) {
        threadLocal.set(account);
    }
}
