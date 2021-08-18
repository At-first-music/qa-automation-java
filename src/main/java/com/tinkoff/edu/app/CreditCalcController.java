package com.tinkoff.edu.app;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    /**
     * TODO Validates and logs request
     */
    CreditCalcLogger creditCalcLogger = new CreditCalcLogger();

    public CreditResponse createRequest(CreditRequest creditRequest) {
        CreditCalcService creditCalcService = new CreditCalcService();

        CreditCalcLogger.log(creditRequest);
        return creditCalcService.createRequest(creditRequest);
    }
}
