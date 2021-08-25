package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.CreditCalcController;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.DefaultCreditCalcRepository;
import com.tinkoff.edu.app.service.DefaultCreditCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.enums.ClientType.*;
import static com.tinkoff.edu.app.enums.ResponseType.CONFIRM_REQUEST;
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
    public void shouldCorrectRequest() {
        // Given
        creditRequest = new CreditRequest(IP, 10, 100);

        //When

        //Then
        assertEquals("CreditResponse {" +
              "requestId=" + 1 +
              ", responseType=" + CONFIRM_REQUEST +
              ", CreditRequest=" + creditRequest +
              " }", String.valueOf(creditCalcController.createRequest(creditRequest)));
    }
}
