package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

public interface CreditCalcRepository {
    CreditResponse save(CreditRequest creditRequest);
}
