package com.tinkoff.edu.app.models;

import com.tinkoff.edu.app.enums.ClientType;

public class CreditRequest {

    public ClientType getClientType() {
        return clientType;
    }

    private ClientType clientType;
    private final int months;
    private final int amount;

    public CreditRequest (ClientType clientType, int months, int amount) {
        this.clientType = clientType;
        this.months = months;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public String toString() {
        return "CreditRequest {" +
                "clientType=" + clientType +
                ", months=" + this.getMonths() +
                ", amount=" + this.getAmount() +
                " }";
    }
}
