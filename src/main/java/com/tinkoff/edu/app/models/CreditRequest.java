package com.tinkoff.edu.app.models;

import com.tinkoff.edu.app.enums.ClientType;

import java.util.UUID;

public class CreditRequest {

    private ClientType clientType;
    private final int months;
    private final int amount;
    private final String clientName;
    private UUID creditRequestId;

    public CreditRequest(ClientType clientType, int months, int amount, String clientName) {
        this.clientType = clientType;
        this.months = months;
        this.amount = amount;
        this.clientName = clientName;
        creditRequestId = UUID.randomUUID();
    }

    public int getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getClientName() {
        return clientName;
    }

    public UUID getCreditRequestId() {
        return creditRequestId;
    }

    @Override
    public String toString() {
        return "CreditRequest {" +
                "clientType=" + clientType +
                ", months=" + this.getMonths() +
                ", amount=" + this.getAmount() +
                ", clientName=" + this.clientName +
                " }";
    }
}
