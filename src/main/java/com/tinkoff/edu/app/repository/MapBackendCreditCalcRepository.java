package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MapBackendCreditCalcRepository implements CreditCalcRepository {
    private int requestId;
    private HashMap<String, CreditResponse> mapOfCreditResponses;

    public MapBackendCreditCalcRepository() {
        this.mapOfCreditResponses = new HashMap<>();
    }

    @Override
    public List<CreditResponse> getCreditResponsesByClientType(ClientType clientType) {
        return mapOfCreditResponses.values()
                .stream()
                .filter(creditResponse -> creditResponse.getCreditRequest().getClientType().equals(clientType))
                .collect(Collectors.toList());
    }

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
