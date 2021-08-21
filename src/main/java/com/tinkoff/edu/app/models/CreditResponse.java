package com.tinkoff.edu.app.models;

import com.tinkoff.edu.app.enums.ResponseType;

public class CreditResponse {

    private int requestId;
    private ResponseType responseType;
    private CreditRequest creditRequest;

    public CreditResponse(CreditRequest creditRequest) {
        ++this.requestId;
        this.creditRequest = creditRequest;
    }

    public CreditResponse setResponseType(ResponseType responseType) {
        this.responseType = responseType;
        return this;
    }

    @Override
    public String toString() {
        return "CreditResponse {" +
                "requestId=" + requestId +
                ", responseType=" + responseType +
                ", CreditRequest=" + creditRequest +
                " }";
    }
}
