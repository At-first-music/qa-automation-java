package com.tinkoff.edu.app;

/**
 * Log calls Credit Controller
 */
public class CreditCalcLogger {
    /**
     * TODO make logs for Credit Controller calls
     * @param creditRequest - object with  ClientType, months and amount
     */
    public void log(CreditRequest creditRequest) {
        System.out.println("Log calls..." + creditRequest);
    }
}
