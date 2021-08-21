package com.tinkoff.edu.app.logger;

import com.tinkoff.edu.app.models.CreditRequest;

/**
 * Log calls Credit Controller
 */
public class CreditCalcLogger {
    /**
     * TODO make logs for Credit Controller calls
     * @param creditRequest - object with  ClientType, months and amount
     */
    public static void log(CreditRequest creditRequest) {
        System.out.println("Log calls..." + creditRequest);
    }
}
