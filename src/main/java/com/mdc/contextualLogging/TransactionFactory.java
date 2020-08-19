package com.mdc.contextualLogging;

import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.random;

public class TransactionFactory {

    private static String[] customers = { "Bofa", "Chase", "IBM", "USBank"};

    public Payload newInstance() {

        String account = String.valueOf(Account.getAccount());
        String request = String.valueOf(Request.getRequest());
        String customer = customers[(int) floor(random() * customers.length)];

        Payload payload = new Payload(request, account, customer);
        return payload;
    }

}
