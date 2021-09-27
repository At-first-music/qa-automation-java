package com.tinkoff.edu.app.models;

import com.tinkoff.edu.app.enums.ClientType;

import java.util.Objects;
import java.util.UUID;

public class CreditRequest {

    private ClientType clientType;
    private final int months;
    private final int amount;
    private final String clientName;

    public CreditRequest(ClientType clientType, int months, int amount, String clientName) {
        this.clientType = clientType;
        this.months = months;
        this.amount = amount;
        this.clientName = clientName;
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

    @Override
    public String toString() {
        return "CreditRequest{" +
                "clientType=" + clientType +
                ", months=" + months +
                ", amount=" + amount +
                ", clientName='" + clientName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditRequest that = (CreditRequest) o;
        return months == that.months &&
                amount == that.amount &&
                clientType == that.clientType &&
                Objects.equals(clientName, that.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientType, months, amount, clientName);
    }
}
