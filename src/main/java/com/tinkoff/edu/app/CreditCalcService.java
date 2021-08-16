package com.tinkoff.edu.app;

/**
 * Credit calculation
 */
public class CreditCalcService {
    /**
     * TODO Loan calculation
     */

    CreditCalcRepository creditCalcRepository = new CreditCalcRepository();

    public int createRequest(CreditRequest creditRequest) {
        return creditCalcRepository.save(creditRequest);
    }
}
