package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.CreditCalcRepository;

import static com.tinkoff.edu.app.enums.ResponseType.*;

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
        return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
    }
}
