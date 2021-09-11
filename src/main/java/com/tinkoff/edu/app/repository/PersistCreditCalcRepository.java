package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.List;

public class PersistCreditCalcRepository implements CreditCalcRepository {
    private int requestId;

    private CreditResponse[] creditResponses;

    public PersistCreditCalcRepository() {
        creditResponses = new CreditResponse[100_000];
    }

    @Override
    public List<CreditResponse> getCreditResponsesByClientType(ClientType clientType) {
        throw new  UnsupportedOperationException("This class have not filter responses");
    }

    @Override
    public CreditResponse getCreditResponseByUuid(String uuid) {
        for (CreditResponse creditResponse : creditResponses) {
            if (creditResponse != null && creditResponse.getCreditRequestId().toString().equals(uuid)) {
                return creditResponse;
            }
        }
        return null;
    }

    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        CreditResponse creditResponse = new CreditResponse(++requestId, creditRequest, creditRequest.getCreditRequestId());

        creditResponses[requestId] = creditResponse;
        return creditResponse;
    }
}
