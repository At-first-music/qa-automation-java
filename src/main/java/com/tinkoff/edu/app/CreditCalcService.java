package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.ResponseType.*;

/**
 * Credit calculation
 */
public class CreditCalcService {
    /**
     * TODO Loan calculation
     * @return creditRequest with ResponseType
     */
    public CreditResponse createRequest(CreditRequest creditRequest) {
        return new CreditCalcRepository().save(creditRequest).setResponseType(CONFIRM_REQUEST);
    }
}
