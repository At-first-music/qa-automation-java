package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

/**
 * Save results of calculation
 */
public class DefaultCreditCalcRepository implements CreditCalcRepository {
    private int requestId;

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
