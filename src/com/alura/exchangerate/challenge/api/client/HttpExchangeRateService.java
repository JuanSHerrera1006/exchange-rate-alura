package com.alura.exchangerate.challenge.api.client;

import com.alura.exchangerate.challenge.api.model.ExchangeRateResponse;
import com.alura.exchangerate.challenge.api.model.SupportCodesResponse;
import com.alura.exchangerate.challenge.api.util.http.HttpRequestExecutor;
import com.google.gson.Gson;

import java.io.IOException;

public class HttpExchangeRateService implements ExchangeRateService {
    private final HttpRequestExecutor requestExecutor;
    private final Gson gson;
    private final String baseUrl;

    public HttpExchangeRateService(HttpRequestExecutor requestExecutor, Gson gson, String baseUrl) {
        this.requestExecutor = requestExecutor;
        this.gson = gson;
        this.baseUrl = baseUrl;
    }

    @Override
    public ExchangeRateResponse getExchangeRate(String baseCode, String targetCode) throws IOException, InterruptedException {
        String url = baseUrl + "/pair/" + baseCode + "/" + targetCode;
        String jsonResponse = requestExecutor.executeGetRequest(url);
        return gson.fromJson(jsonResponse, ExchangeRateResponse.class);
    }

    @Override
    public SupportCodesResponse getSupportCodes() throws IOException, InterruptedException {
            String url = baseUrl + "/codes";
        String jsonResponse = requestExecutor.executeGetRequest(url);
        return gson.fromJson(jsonResponse, SupportCodesResponse.class);
    }


}
