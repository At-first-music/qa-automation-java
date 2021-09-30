package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.UUID;

public class MapBackendCreditCalcRepository implements CreditCalcRepository {
    private int requestId;

    @Override
    public CreditResponse getCreditResponseByUuid(String uuid) {
        return mapOfCreditResponses.get(uuid);
    }

    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        UUID creditRequestId = UUID.randomUUID();
        CreditResponse creditResponse = new CreditResponse(++requestId, creditRequest, creditRequestId);
        mapOfCreditResponses.put(creditResponse.getCreditRequestId().toString(), creditResponse);

        return creditResponse;
    }
}
