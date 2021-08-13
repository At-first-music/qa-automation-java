package com.tinkoff.edu.app;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    /**
     * TODO Validates and logs request
     */
    public static int createRequest() {
        CreditCalcLogger.log();
        return CreditCalcService.createRequest();
    }
}
