package com.tinkoff.edu.test;

import com.tinkoff.edu.app.CreditCalcController;

/**
 * Credit Calc Test
 */
public class CreditCalcTest {
    public static void main(String... args) {
        int requestId = CreditCalcController.createRequest();
        System.out.println(requestId + " must be 1");
    }
}
