package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.io.IOException;
import java.util.List;

public interface CreditCalcService {
    List<CreditResponse> getCreditResponsesByClientType(ClientType clientType);
    CreditResponse getCreditResponseFromUuid(String uuid) throws IOException;
    CreditResponse createRequest(CreditRequest creditRequest) throws IOException;
}
