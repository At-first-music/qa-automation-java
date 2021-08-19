package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.ResponseType.*;

/**
 * Credit calculation
 */
public class StaticCreditCalcService implements CreditCalcService{
    private CreditCalcRepository creditCalcRepository;

    public StaticCreditCalcService(CreditCalcRepository creditCalcRepository) {
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
