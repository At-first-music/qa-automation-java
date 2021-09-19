package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.util.HashMap;

public interface CreditCalcRepository {
    HashMap<String, CreditResponse> mapOfCreditResponses = new HashMap<>();
    CreditResponse getCreditResponseByUuid(String uuid);
    CreditResponse save(CreditRequest creditRequest);
}
