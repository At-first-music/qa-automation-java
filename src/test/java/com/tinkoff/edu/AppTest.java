package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.CreditCalcController;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.FileBackendCreditCalcRepository;
import com.tinkoff.edu.app.repository.MapBackendCreditCalcRepository;
import com.tinkoff.edu.app.service.DefaultCreditCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static com.tinkoff.edu.app.enums.ResponseType.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private CreditCalcController creditCalcController;
    private CreditRequest creditRequest;
    private String clientName;

    private Exception thrownExceptionFunction(CreditRequest creditRequest) {
        return assertThrows(
                Exception.class,
                () -> creditCalcController.createRequest(creditRequest)
        );
    }

    @BeforeEach
    public void init() {
        // Given
        creditCalcController = new CreditCalcController(new DefaultCreditCalcService(new FileBackendCreditCalcRepository()));
        clientName = "Petr Ilich Chaikovski";
    }

    @Test
    @DisplayName("Проверка значения requestId при первом вызове CreditCalcController")
    public void shouldGetId1WhenFirstCall() {
        creditRequest = new CreditRequest(PERSON, 10, 100, clientName);

        // Then
        assertEquals(1, creditCalcController.createRequest(creditRequest).getRequestId(), "При первом вызове requestId должен быть = 1");
    }

    @Test
    @DisplayName("Проверка увеличения requestId при нескольких запросах")
    public void  shouldGetIncrementIdWithSeveralCalls() {
        creditRequest = new CreditRequest(PERSON, 10, 100, clientName);
        creditCalcController.createRequest(creditRequest);

        // Then
        assertEquals(2, creditCalcController.createRequest(creditRequest).getRequestId(), "При повторном вызове requestId  = 2");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = IP")
    public void shouldRejectedResponseWithIPClientType() {
        creditRequest = new CreditRequest(IP, 10, 1_000, clientName);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и amount <= 10_000")
    public void shouldRejectedResponseWithOOOClientTypeAndIncorrectAmount() {
        creditRequest = new CreditRequest(OOO, 10, 10_000, clientName);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и  months >= 12")
    public void shouldRejectedResponseWithOOOClientTypeAndIncorrectMonths() {
        creditRequest = new CreditRequest(OOO, 12, 10_100, clientName);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = OOO и amount > 10_000 и months < 12")
    public void shouldApprovedResponseWithOOOClientType() {
        creditRequest = new CreditRequest(OOO, 11, 10_100, clientName);

        // Then
        assertEquals(CONFIRM_REQUEST, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть принята");
    }

    @Test
    @DisplayName("Проверка принятия заявки, если тип клиента = PERSON и amount <= 10_000 и months <= 12")
    public void shouldApprovedResponseWithPERSONClientType() {
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        // Then
        assertEquals(CONFIRM_REQUEST, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть принята");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = PERSON и amount > 10_000 и months > 12")
    public void shouldRejectedResponseWithPERSONClientTypeAndIncorrectMonths() {
        creditRequest = new CreditRequest(PERSON, 13, 10_000, clientName);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка отклонения заявки, если тип клиента = PERSON и amount > 10_000 и months > 12")
    public void shouldRejectedResponseWithPERSONClientTypeAndIncorrectAmount() {
        creditRequest = new CreditRequest(PERSON, 12, 100_000, clientName);

        // Then
        assertEquals(REJECTED, creditCalcController.createRequest(creditRequest).getResponseType(), "Заявка должна быть отклонена");
    }

    @Test
    @DisplayName("Проверка получения исключения, если creditRequest == null")
    public void shouldGetErrorWhenApplyNullRequest() {

        assertTrue(thrownExceptionFunction(null).getMessage().contains("creditRequest и clientType должны быть заполнены"));
    }

    @Test
    @DisplayName("Проверка получения исключения, если amount <= 0")
    public void shouldGetErrorWithIncorrectAmount() {
        creditRequest = new CreditRequest(PERSON, 12, 0, clientName);

        assertTrue(thrownExceptionFunction(creditRequest).getMessage().contains("amount и month должны быть больше 0"));
    }

    @Test
    @DisplayName("Проверка получения исключения, если moths <= 0")
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        creditRequest = new CreditRequest(PERSON, 0, 100, clientName);

        assertTrue(thrownExceptionFunction(creditRequest).getMessage().contains("amount и month должны быть больше 0"));
    }

    @Test
    @DisplayName("Проверка получения исключения, если clientType == null")
    public void shouldGetErrorWhenNullClientType() {
        creditRequest = new CreditRequest(null, 12, 100, clientName);

        assertTrue(thrownExceptionFunction(creditRequest).getMessage().contains("creditRequest и clientType должны быть заполнены"));
    }

    @Test
    @DisplayName("Тест создания UUID для creditRequest")
    public void shouldNotNullCreditRequestUUID() {
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        // Then
        assertNotNull(creditCalcController.createRequest(creditRequest).getCreditRequestId());
    }

    @Test
    @DisplayName("Проверка получения заявки по её UUID creditRequestId")
    public void shouldGetCreditRequestFromUUid() throws IOException {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        String firstCreditRequest = creditCalcController.createRequest(creditRequest).getCreditRequestId().toString();
        CreditResponse creditResponse = creditCalcController.getCreditResponseFromUuid(firstCreditRequest);

        // Then
        assertEquals(new CreditResponse(creditRequest, 1).setResponseType(CONFIRM_REQUEST), creditResponse);
    }

    @Test
    @DisplayName("Проверка получения заявки по её UUID creditRequestId")
    public void shouldGetResponseTypeOfCreditRequestFromUUid() throws IOException {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        String firstCreditRequest = creditCalcController.createRequest(creditRequest).getCreditRequestId().toString();
        ResponseType creditResponseType = creditCalcController.getCreditResponseFromUuid(firstCreditRequest).getResponseType();

        // Then
        assertEquals(CONFIRM_REQUEST, creditResponseType);
    }

    @Test
    @DisplayName("Проверка изменения статуса заявки по её UUID creditRequestId")
    public void shouldChangeCreditRequestResponseTypeToRejectedFromUUid() throws IOException {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        String firstCreditRequest = creditCalcController.createRequest(creditRequest).getCreditRequestId().toString();
        CreditResponse creditResponse = creditCalcController.getCreditResponseFromUuid(firstCreditRequest).setResponseType(REJECTED);

        // Then
        assertEquals(REJECTED, creditResponse.getResponseType());
    }

    @Test
    @DisplayName("Проверка изменения статуса заявки по её UUID creditRequestId")
    public void shouldChangeCreditRequestResponseTypeToInProgressFromUUid() throws IOException {
        // When
        creditRequest = new CreditRequest(PERSON, 12, 10_000, clientName);

        String firstCreditRequest = creditCalcController.createRequest(creditRequest).getCreditRequestId().toString();
        CreditResponse creditResponse = creditCalcController.getCreditResponseFromUuid(firstCreditRequest).setResponseType(IN_PROGRESS);

        // Then
        assertEquals(IN_PROGRESS, creditResponse.getResponseType());
    }

    @Test
    @DisplayName("Проверка получения исключения, если длина clientName < 10")
    public void shouldGetErrorWhenShortClientName() {
        creditRequest = new CreditRequest(PERSON, 12, 100, "Test");

        assertEquals("Длина ФИО клиента должна быть в пределах от 10 до 100 символов",
                thrownExceptionFunction(creditRequest).getMessage());
    }

    @Test
    @DisplayName("Проверка получения исключения, если длина clientName > 100")
    public void shouldGetErrorWhenLongClientName() {
        creditRequest = new CreditRequest(PERSON, 12, 100, "Test test very_long_Client_Name" +
                "and this Name has some different surnames like some Great Britain's knights");

        assertEquals("Длина ФИО клиента должна быть в пределах от 10 до 100 символов",
                thrownExceptionFunction(creditRequest).getMessage());
    }

    @Test
    @DisplayName("Проверка фильтрации creditResponses по clientType")
    public void shouldFilerCreditResponsesByClientType() {
        creditCalcController.createRequest(new CreditRequest(OOO, 11, 10_100, clientName));
        creditCalcController.createRequest(new CreditRequest(PERSON, 12, 10_000, clientName));
        creditCalcController.createRequest(new CreditRequest(OOO, 11, 10_100, clientName));

        assertEquals(2, creditCalcController.getCreditResponsesByClientType(OOO).size());
    }
}
