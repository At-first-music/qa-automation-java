package com.tinkoff.edu.app;

/**
 * Save results of calculation
 */
public class StaticCreditCalcRepository implements CreditCalcRepository {

    /**
     *  TODO make persists request
     * @return creditRequest
     */
    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        return new CreditResponse(creditRequest);
    }
}
