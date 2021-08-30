package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.service.CreditCalcService;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static com.tinkoff.edu.app.enums.ResponseType.*;
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
        if (creditRequest == null
                || creditRequest.getAmount() <= 0
                || creditRequest.getMonths() <= 0
                || creditRequest.getClientType() == null) {
            return new CreditResponse(creditRequest).setRequestId(-1).setResponseType(REJECTED);
        }

        if (creditRequest.getClientType() == IP) {
            log(creditRequest);
            return new CreditResponse(creditRequest).setResponseType(REJECTED);
        }

        if (creditRequest.getClientType() == OOO) {
            if (creditRequest.getAmount() <= 10_000) {
                log(creditRequest);
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            } else if (creditRequest.getAmount() > 10_000 && creditRequest.getMonths() < 12) {
                log(creditRequest);
                return creditCalcService.createRequest(creditRequest);
            } else {
                log(creditRequest);
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        if (creditRequest.getClientType() == PERSON) {
            if (creditRequest.getAmount() <= 10_000 && creditRequest.getMonths() <= 12) {
                log(creditRequest);
                return creditCalcService.createRequest(creditRequest);
            } else {
                log(creditRequest);
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        log(creditRequest);
        return creditCalcService.createRequest(creditRequest);
    }
}
