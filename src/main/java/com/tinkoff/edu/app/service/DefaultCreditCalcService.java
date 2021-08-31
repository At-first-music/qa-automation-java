package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.CreditCalcRepository;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static com.tinkoff.edu.app.enums.ResponseType.*;
import static com.tinkoff.edu.app.logger.CreditCalcLogger.log;

/**
 * Credit calculation
 */
public class DefaultCreditCalcService implements CreditCalcService {
    private CreditCalcRepository creditCalcRepository;

    public DefaultCreditCalcService(CreditCalcRepository creditCalcRepository) {
        this.creditCalcRepository = creditCalcRepository;
    }

    /**
     * TODO Loan calculation
     * @return creditRequest with ResponseType
     */
    @Override
    public CreditResponse createRequest(CreditRequest creditRequest) {

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
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                log(creditRequest);
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        if (creditRequest.getClientType() == PERSON) {
            if (creditRequest.getAmount() <= 10_000 && creditRequest.getMonths() <= 12) {
                log(creditRequest);
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                log(creditRequest);
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
    }
}
