package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;
import com.tinkoff.edu.app.repository.CreditCalcRepository;
import com.tinkoff.edu.app.repository.FileBackendCreditCalcRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tinkoff.edu.app.enums.ResponseType.*;

/**
 * Credit calculation
 */
public class DefaultCreditCalcService implements CreditCalcService {
    private CreditCalcRepository creditCalcRepository;

    public DefaultCreditCalcService(CreditCalcRepository creditCalcRepository) {
        this.creditCalcRepository = creditCalcRepository;
    }

    @Override
    public List<CreditResponse> getCreditResponsesByClientType(ClientType clientType) throws IOException {
        List<CreditResponse> result = new ArrayList<>(List.of());
        List<String> filterOfCreditResponses = Files.lines(FileBackendCreditCalcRepository
                .fileWithCreditResponses
                .toPath())
                .filter(line-> line.contains(clientType.toString()))
                .collect(Collectors.toList());
        for (String line : filterOfCreditResponses) {
            String[] parts = line.split(",");
             result.add(new CreditResponse(
                    Integer.parseInt(parts[1]),
                    new CreditRequest(ClientType.valueOf(parts[3]),
                            Integer.parseInt(parts[4]),
                            Integer.parseInt(parts[5]),
                            parts[6]),
                    UUID.fromString(parts[0])).setResponseType(ResponseType.valueOf(parts[2])));
        }
        return result;
    }

    @Override
    public CreditResponse getCreditResponseFromUuid(String uuid) throws IOException {
        return creditCalcRepository.getCreditResponseByUuid(uuid);
    }

    /**
     * TODO Loan calculation
     * @return creditRequest with ResponseType
     */
    @Override
    public CreditResponse createRequest(CreditRequest creditRequest) throws IOException {

        switch (creditRequest.getClientType()) {
            case IP: return new CreditResponse(creditRequest).setResponseType(REJECTED);

            case PERSON: if (creditRequest.getAmount() <= 10_000 && creditRequest.getMonths() <= 12) {
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }

            case OOO: if (creditRequest.getAmount() <= 10_000) {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            } else if (creditRequest.getAmount() > 10_000 && creditRequest.getMonths() < 12) {
                return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
            } else {
                return new CreditResponse(creditRequest).setResponseType(REJECTED);
            }
        }

        return creditCalcRepository.save(creditRequest).setResponseType(CONFIRM_REQUEST);
    }
}
