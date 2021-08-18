package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.ResponseType.*;

/**
 * Credit calculation
 */
public class CreditCalcService {
    /**
     * TODO Loan calculation
     */
    public CreditResponse createRequest(CreditRequest creditRequest) {
        CreditCalcRepository creditCalcRepository = new CreditCalcRepository();
        return new CreditResponse(creditCalcRepository.save(), CONFIRM_REQUEST, creditRequest);
    }
}
