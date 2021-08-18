package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.ResponseType.*;

/**
 * Credit calculation
 */
public class CreditCalcService {
    /**
     * TODO Loan calculation
     */

    CreditCalcRepository creditCalcRepository = new CreditCalcRepository();

    public CreditResponse createRequest(CreditRequest creditRequest) {
        return new CreditResponse(creditCalcRepository.save(creditRequest), CONFIRM_REQUEST, creditRequest);
    }
}
