package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

import static com.tinkoff.edu.app.ClientType.*;

/**
 * Credit Calc Test
 */
public class CreditCalcTest {
    public static void main(String... args) {
        CreditCalcController creditCalcController = new CreditCalcController();

        CreditRequest creditRequest = new CreditRequest(IP, 10, 100);

        System.out.println(creditCalcController.createRequest(creditRequest));
    }
}
