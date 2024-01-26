package com.example.solidbanksb.model;

import com.example.solidbanksb.model.TransactionTransfer.TransferResult;
import com.example.solidbanksb.model.TransactionTransfer.TransferType;

public interface TransferOperationCLIUI {

    TransferResult processTransfer(TransferType transferType, String clientId, double transferAmount);

    TransferType requestTransferType();
}
