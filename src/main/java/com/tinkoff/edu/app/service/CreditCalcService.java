package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

public interface CreditCalcService {
    CreditResponse createRequest(CreditRequest creditRequest);
}
