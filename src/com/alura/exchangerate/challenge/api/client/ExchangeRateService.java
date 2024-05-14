package com.alura.exchangerate.challenge.api.client;

import com.alura.exchangerate.challenge.api.model.ExchangeRateResponse;
import com.alura.exchangerate.challenge.api.model.SupportCodesResponse;

import java.io.IOException;

public interface ExchangeRateService{
    ExchangeRateResponse getExchangeRate(String baseCode, String targetCode) throws IOException, InterruptedException;
    SupportCodesResponse getSupportCodes() throws IOException, InterruptedException;


}
