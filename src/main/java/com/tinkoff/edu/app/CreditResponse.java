package com.tinkoff.edu.app;

public class CreditResponse {

    private int requestId;

    private ResponseType responseType;

    CreditRequest creditRequest;

    public CreditResponse(int requestId, ResponseType responseType, Object creditRequest) {
        this.requestId = requestId;
        this.responseType = responseType;
        this.creditRequest = (CreditRequest) creditRequest;
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
