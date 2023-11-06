package com.pichincha.accounts.application.queries;

import com.pichincha.accounts.application.dtos.AccountStatusResponse;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ReadAccountStatusQuery implements Request<List<AccountStatusResponse>> {
    private String clientId;

    public ReadAccountStatusQuery(String clientId) {
        this.clientId = clientId;
    }
}
