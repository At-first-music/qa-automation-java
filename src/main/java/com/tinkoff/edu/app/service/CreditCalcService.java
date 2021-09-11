package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.List;

public interface CreditCalcService {
    List<CreditResponse> getCreditResponsesByClientType(ClientType clientType);
    CreditResponse getCreditResponseFromUuid(String uuid);
    CreditResponse createRequest(CreditRequest creditRequest);
}
