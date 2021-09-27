package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.ClientType;
import com.tinkoff.edu.app.enums.ResponseType;
import com.tinkoff.edu.app.models.CreditRequest;
import com.tinkoff.edu.app.models.CreditResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tinkoff.edu.app.enums.ResponseType.*;
import static java.nio.file.StandardOpenOption.*;

public class FileBackendCreditCalcRepository implements CreditCalcRepository {
    private int requestId;
    private File fileWithCreditResponses = new File("FileWithCreditResponses.txt");

    @Override
    public CreditResponse getCreditResponseByUuid(String uuid) throws IOException {
        List<String> filterList = Files.lines(fileWithCreditResponses.toPath()).filter(lines -> lines.contains(uuid)).collect(Collectors.toList());
        String[] parts = filterList.get(0).split(",");

        return new CreditResponse(
                Integer.parseInt(parts[1]),
                new CreditRequest(ClientType.valueOf(parts[3]),
                        Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5]),
                        parts[6]),
                UUID.fromString(parts[0])).setResponseType(ResponseType.valueOf(parts[2]));
    }

    @Override
    public CreditResponse save(CreditRequest creditRequest) {
        CreditResponse creditResponse = new CreditResponse(++requestId, creditRequest, creditRequest.getCreditRequestId()).setResponseType(CONFIRM_REQUEST);
        try {
            Files.write(fileWithCreditResponses.toPath(),
                    List.of(creditResponse.getCreditRequestId().toString()+","+
                            creditResponse.getRequestId()+","+
                            creditResponse.getResponseType().toString()+","+
                            creditRequest.getClientType().toString()+","+
                            creditRequest.getMonths()+","+
                            creditRequest.getAmount()+","+
                            creditRequest.getClientName()), CREATE, APPEND, WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return creditResponse;
    }
}
