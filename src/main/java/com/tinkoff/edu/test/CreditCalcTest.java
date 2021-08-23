package com.tinkoff.edu.test;

import com.tinkoff.edu.app.controller.CreditCalcController;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.repository.DefaultCreditCalcRepository;
import com.tinkoff.edu.app.service.DefaultCreditCalcService;

import static com.tinkoff.edu.app.enums.ClientType.*;

/**
 * Credit Calc Test
 */
public class CreditCalcTest {
    public static void main(String... args) {
        CreditCalcController creditCalcController = new CreditCalcController(new DefaultCreditCalcService(new DefaultCreditCalcRepository()));

        CreditRequest creditRequest = new CreditRequest(IP, 10, 100);

        System.out.println(creditCalcController.createRequest(creditRequest));
    }
}
