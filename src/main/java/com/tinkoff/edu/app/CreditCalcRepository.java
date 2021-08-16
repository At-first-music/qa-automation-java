package com.tinkoff.edu.app;

/**
 * Save results of calculation
 */
public class CreditCalcRepository {
    private static int requestId;

    /**
     *  TODO make persists request
     * @return Request Id
     * @param creditRequest - object with  ClientType, months and amount
     */
    public int save(CreditRequest creditRequest) {
        return ++requestId;
    }
}
