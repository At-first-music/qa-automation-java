package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public interface CreditCalcRepository {
    HashMap<String, CreditResponse> mapOfCreditResponses = new HashMap<>();
    CreditResponse getCreditResponseByUuid(String uuid) throws IOException;
    CreditResponse save(CreditRequest creditRequest) throws IOException;
}
