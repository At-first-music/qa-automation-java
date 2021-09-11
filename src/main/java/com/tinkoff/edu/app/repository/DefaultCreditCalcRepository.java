package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.List;

/**
 * Save results of calculation
 */
public class DefaultCreditCalcRepository implements CreditCalcRepository {
    private int requestId;

    @Override
    public List<CreditResponse> getCreditResponsesByClientType(ClientType clientType) {
        throw new UnsupportedOperationException("This class not saving response and can't be filtered");
    }

    @Override
    public CreditResponse getCreditResponseByUuid(String uuid) {
        throw new UnsupportedOperationException("This class have not saving responses");
    }

    /**
     * @return creditRequest
     */
    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        requestId++;
        return new CreditResponse(creditRequest, requestId);
    }
}
