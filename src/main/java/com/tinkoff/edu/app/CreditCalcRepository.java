package com.tinkoff.edu.app;

/**
 * Save results of calculation
 */
public class CreditCalcRepository {
    private static int requestId;

    /**
     *  TODO make persists request
     * @return Request Id
     */
    public static int save() {
        return ++requestId;
    }
}