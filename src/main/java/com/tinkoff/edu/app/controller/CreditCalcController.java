package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.exceptions.WrongLengthOfClientName;
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

    public CreditResponse getCreditResponseFromUuid(String uuid) {
        return creditCalcService.getCreditResponseFromUuid(uuid);
    }

    /**
     * TODO Validates and logs request
     * @return creditRequest with ResponseType
     */
    public CreditResponse createRequest(CreditRequest creditRequest) throws WrongLengthOfClientName {
        if (creditRequest == null || creditRequest.getClientType() == null) {
            throw  new IllegalArgumentException("creditRequest и clientType должны быть заполнены");
        }

        if (creditRequest.getAmount() <= 0 || creditRequest.getMonths() <= 0) {
            throw new IllegalArgumentException("amount и month должны быть больше 0");
        }

        try {
            if (creditRequest.getClientName().length() < 10 || creditRequest.getClientName().length() > 100) {
                throw new WrongLengthOfClientName();
            }
            log(creditRequest);
            return creditCalcService.createRequest(creditRequest);
        } catch (RuntimeException lengthOfNameException) {
            throw new WrongLengthOfClientName("Длина ФИО клиента должна быть в пределах от 10 до 100 символов", lengthOfNameException);
        }
    }
}
