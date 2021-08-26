package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.CreditCalcController;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.repository.DefaultCreditCalcRepository;
import com.tinkoff.edu.app.service.DefaultCreditCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private CreditCalcController creditCalcController;
    private CreditRequest creditRequest;

    @BeforeEach
    public void init() {
        // Given
        creditCalcController = new CreditCalcController(new DefaultCreditCalcService(new DefaultCreditCalcRepository()));
    }

    @Test
    @DisplayName("Проверка значения requestId при первом вызове CreditCalcController")
    public void shouldGetId1WhenFirstCall() {
        // Given
        creditRequest = new CreditRequest(IP, 10, 100);

        // When
        int requestIdResult = creditCalcController.createRequest(creditRequest).getRequestId();

        // Then
        assertEquals(1, requestIdResult, "При первом вызове requestId должен быть = 1");
    }

    @Test
    @DisplayName("Проверка увеличения requestId при нескольких запросах")
    public void  shouldGetIncrementIdWithSeveralCalls() {
        // Given
        creditRequest = new CreditRequest(OOO, 4, 1_000);
        CreditRequest creditRequest1 = new CreditRequest(PERSON, 9, 800);

        // When
        creditCalcController.createRequest(creditRequest);
        int requestIdResult = creditCalcController.createRequest(creditRequest1).getRequestId();

        // Then
        assertEquals(2, requestIdResult, "При повторном вызове requestId  = 2");
    }
}
