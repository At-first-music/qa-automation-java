package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.service.CreditCalcService;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import static com.tinkoff.edu.app.logger.CreditCalcLogger.log;

/**
 * Controller for credit calculation
 */
public class CreditCalcController {
    private CreditCalcService creditCalcService;

    public CreditCalcController(CreditCalcService creditCalcService) {
        this.creditCalcService = creditCalcService;
    }

    /**
     * TODO Validates and logs request
     * @return creditRequest with ResponseType
     */
    public CreditResponse createRequest(CreditRequest creditRequest) {
        log(creditRequest);
        return creditCalcService.createRequest(creditRequest);
    }
}
