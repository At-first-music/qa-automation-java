package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.CreditCalcLogger.log;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    /**
     * TODO Validates and logs request
     * @return creditRequest with ResponseType
     */
    public CreditResponse createRequest(CreditRequest creditRequest) {

        log(creditRequest);
        return new CreditCalcService().createRequest(creditRequest);
    }
}
