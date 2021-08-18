package com.tinkoff.edu.app;

public class CreditResponse {

    private int requestId;
    private ResponseType responseType;
    private CreditRequest creditRequest;

    public CreditResponse(int requestId, ResponseType responseType, CreditRequest creditRequest) {
        this.requestId = requestId;
        this.responseType = responseType;
        this.creditRequest = creditRequest;
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
