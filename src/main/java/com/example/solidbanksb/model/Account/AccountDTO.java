package com.example.solidbanksb.model.Account;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class AccountDTO implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("clientId")
    private int clientId;
    @JsonProperty("balance")
    private double balance;
    @JsonProperty("withdrawAllowed")
    private boolean withdrawAllowed;
}
