package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.CreditCalcRepository;

import java.util.List;

import static com.tinkoff.edu.app.enums.ResponseType.*;

/**
 * Credit calculation
 */
public class DefaultCreditCalcService implements CreditCalcService {
    private CreditCalcRepository creditCalcRepository;

    public DefaultCreditCalcService(CreditCalcRepository creditCalcRepository) {
        this.creditCalcRepository = creditCalcRepository;
    }

    @Override
    public List<CreditResponse> getCreditResponsesByClientType(ClientType clientType) {
        return creditCalcRepository.getCreditResponsesByClientType(clientType);
    }

    @Override
    public CreditResponse getCreditResponseFromUuid(String uuid) {
        return creditCalcRepository.getCreditResponseByUuid(uuid);
    }

    /**
     * TODO Loan calculation
     * @return creditRequest with ResponseType
     */
    @Override
    public CreditResponse createRequest(CreditRequest creditRequest) {

        switch (creditRequest.getClientType()) {
            case IP: return new CreditResponse(creditRequest).setResponseType(REJECTED);

            case PERSON: if (creditRequest.getAmount() <= 10_000 && creditRequest.getMonths() <= 12) {
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }

            case OOO: if (creditRequest.getAmount() <= 10_000) {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            } else if (creditRequest.getAmount() > 10_000 && creditRequest.getMonths() < 12) {
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
    }
}
