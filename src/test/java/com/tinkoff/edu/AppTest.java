package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.CreditCalcController;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.DefaultCreditCalcRepository;
import com.tinkoff.edu.app.service.DefaultCreditCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static com.tinkoff.edu.app.enums.ResponseType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private CreditCalcController creditCalcController;
    private CreditRequest creditRequest;
    private CreditResponse creditResponse;

    @BeforeEach
    public void init() {
        // Given
        creditCalcController = new CreditCalcController(new DefaultCreditCalcService(new DefaultCreditCalcRepository()));
    }

    @Test
    @DisplayName("Проверка значения requestId при первом вызове CreditCalcController")
    public void shouldGetId1WhenFirstCall() {
        // When
        creditRequest = new CreditRequest(PERSON, 10, 100);
        int requestIdResult = creditCalcController.createRequest(creditRequest).getRequestId();

        // Then
        assertEquals(1, requestIdResult, "При первом вызове requestId должен быть = 1");
    }

    @Test
    @DisplayName("Проверка увеличения requestId при нескольких запросах")
    public void  shouldGetIncrementIdWithSeveralCalls() {
        // When
        creditRequest = new CreditRequest(PERSON, 10, 100);
        creditCalcController.createRequest(creditRequest);
        int requestIdResult = creditCalcController.createRequest(creditRequest).getRequestId();

        // Then
        assertEquals(2, requestIdResult, "При повторном вызове requestId  = 2");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = IP")
    public void shouldRejectedResponseWithIPClientType() {
        // When
        creditRequest = new CreditRequest(IP, 10, 1_000);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и amount <= 10_000")
    public void shouldRejectedResponseWithOOOClientTypeAndIncorrectAmount() {
        // When
        creditRequest = new CreditRequest(OOO, 10, 10_000);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и  months >= 12")
    public void shouldRejectedResponseWithOOOClientTypeAndIncorrectMonths() {
        // When
        creditRequest = new CreditRequest(OOO, 12, 10_100);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и amount > 10_000 и months < 12")
    public void shouldApprovedResponseWithOOOClientType() {
        // When
        creditRequest = new CreditRequest(OOO, 11, 10_100);

        // Then
        assertEquals(CONFIRM_REQUEST, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть принята");
    }

    @Test
    @DisplayName("Проверка принятия заявки, если тип клиента = PERSON и amount <= 10_000 и months <= 12")
    public void shouldApprovedResponseWithPERSONClientType() {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 10_000);

        // Then
        assertEquals(CONFIRM_REQUEST, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть принята");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = PERSON и amount > 10_000 и months > 12")
    public void shouldRejectedResponseWithPERSONClientTypeAndIncorrectMonths() {
        // When
        creditRequest = new CreditRequest(PERSON, 13, 10_000);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = PERSON и amount > 10_000 и months > 12")
    public void shouldRejectedResponseWithPERSONClientTypeAndIncorrectAmount() {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 100_000);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка получения исключения, если creditRequest == null")
    public void shouldGetErrorWhenApplyNullRequest() {
        // When

        // Then
        assertEquals(-1, creditCalcController.createRequest(null).getRequestId(), "Заявка отклонена, requestId = -1");
    }

    @Test
    @DisplayName("Проверка получения исключения, если amount <= 0")
    public void shouldGetErrorWithIncorrectAmount() {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 0);

        // Then
        assertEquals(-1, creditCalcController.createRequest(creditRequest).getRequestId(), "Заявка отклонена, requestId = -1");
    }

    @Test
    @DisplayName("Проверка получения исключения, если moths <= 0")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        // When
        creditRequest = new CreditRequest(PERSON, 0, 100);

        // Then
        assertEquals(-1, creditCalcController.createRequest(creditRequest).getRequestId(), "Заявка отклонена, requestId = -1");
    }

    @Test
    @DisplayName("Проверка получения исключения, если clientType == null")
    public void shouldGetErrorWhenNullClientType() {
        // When
        creditRequest = new CreditRequest(null, 10, 100);

        // Then
        assertEquals(-1, creditCalcController.createRequest(creditRequest).getRequestId(), "Заявка отклонена, requestId = -1");
    }
}
