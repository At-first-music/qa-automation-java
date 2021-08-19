package com.tinkoff.edu.app;

public interface CreditCalcRepository {
    CreditResponse save(CreditRequest creditRequest);
}
