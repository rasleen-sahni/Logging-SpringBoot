package com.mdc.contextualLogging;

public class Payload {

    private String request;
    private String account;
    private String customer;

    public Payload(String request, String account, String customer) {
        this.request = request;
        this.account = account;
        this.customer = customer;
    }

    public String getRequest() {
        return request;
    }

    public String getAccount() {
        return account;
    }

    public String getCustomer() {
        return customer;
    }

}
