package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Credit Calc Test
 */
public class CreditCalcTest {
    public static void main(String... args) {
        CreditCalcController creditCalcController = new CreditCalcController();

        CreditRequest creditRequest = new CreditRequest(ClientType.IP, 10, 100);

        int requestId = creditCalcController.createRequest(creditRequest);

        CreditResponse creditResponse = new CreditResponse(requestId, ResponseType.CONFIRM_REQUEST, creditRequest);

        System.out.println(creditResponse);
    }
}
