package com.tinkoff.edu.app.models;

import com.tinkoff.edu.app.enums.ResponseType;

import java.util.Objects;
import java.util.UUID;

public class CreditResponse {

    private int requestId;
    private ResponseType responseType;
    private CreditRequest creditRequest;
    private UUID creditRequestId;

    public CreditResponse(int requestId, CreditRequest creditRequest, UUID creditRequestId) {
        this.requestId = requestId;
        this.creditRequest = creditRequest;
        this.creditRequestId = creditRequestId;
    }

    public CreditResponse(CreditRequest creditRequest, int requestId) {
        this.requestId = requestId;
        this.creditRequest = creditRequest;
    }

    public CreditResponse(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public int getRequestId() {
        return requestId;
    }

    public CreditResponse setRequestId(int requestId) {
        this.requestId = requestId;
        return this;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public CreditResponse setResponseType(ResponseType responseType) {
        this.responseType = responseType;
        return this;
    }

    public UUID getCreditRequestId() {
        return creditRequestId;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    @Override
    public String toString() {
        return "CreditResponse {" +
                "requestId=" + requestId +
                ", responseType=" + responseType +
                ", CreditRequest=" + creditRequest +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditResponse that = (CreditResponse) o;
        return requestId == that.requestId &&
                responseType == that.responseType &&
                Objects.equals(creditRequest, that.creditRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, responseType, creditRequest);
    }
}
