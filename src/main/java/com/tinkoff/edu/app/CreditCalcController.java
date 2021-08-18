package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.CreditCalcLogger.log;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    /**
     * TODO Validates and logs request
     */
    public CreditResponse createRequest(CreditRequest creditRequest) {
        CreditCalcService creditCalcService = new CreditCalcService();

        log(creditRequest);
        return creditCalcService.createRequest(creditRequest);
    }
}
