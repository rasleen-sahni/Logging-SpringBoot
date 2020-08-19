package com.mdc.diagnosticLogging;

public class Model {

    private String account;
    private String request;

    public Model(String account, String request) {
        this.account = account;
        this.request = request;
    }

    public String getAccount() {
        return account;
    }

    public String getRequest() {
        return request;
    }
}
