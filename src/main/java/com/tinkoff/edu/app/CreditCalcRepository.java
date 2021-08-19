package com.tinkoff.edu.app;

/**
 * Save results of calculation
 */
public class CreditCalcRepository {

    /**
     *  TODO make persists request
     * @return creditRequest
     */
    public CreditResponse save(CreditRequest creditRequest) {
        return new CreditResponse(creditRequest);
    }
}
