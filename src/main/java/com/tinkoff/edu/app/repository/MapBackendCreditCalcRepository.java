package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

public class MapBackendCreditCalcRepository implements CreditCalcRepository {
    private int requestId;

    @Override
    public CreditResponse getCreditResponseByUuid(String uuid) {
        return mapOfCreditResponses.get(uuid);
    }

    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        CreditResponse creditResponse = new CreditResponse(++requestId, creditRequest, creditRequest.getCreditRequestId());
        mapOfCreditResponses.put(creditResponse.getCreditRequestId().toString(), creditResponse);

        return creditResponse;
    }
}
