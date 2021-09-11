package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.List;

public interface CreditCalcRepository {
    List<CreditResponse> getCreditResponsesByClientType(ClientType clientType);
    CreditResponse getCreditResponseByUuid(String uuid);
    CreditResponse save(CreditRequest creditRequest);
}
