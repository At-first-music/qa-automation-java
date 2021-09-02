package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

public interface CreditCalcRepository {
    CreditResponse getCreditResponseFromUuid(String uuid);
    CreditResponse save(CreditRequest creditRequest);
}
