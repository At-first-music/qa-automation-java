package com.tinkoff.edu.app;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    /**
     * TODO Validates and logs request
     */
    CreditCalcLogger creditCalcLogger = new CreditCalcLogger();

    CreditCalcService creditCalcService = new CreditCalcService();

    public int createRequest(CreditRequest creditRequest) {
        creditCalcLogger.log(creditRequest);
        return creditCalcService.createRequest(creditRequest);
    }
}
