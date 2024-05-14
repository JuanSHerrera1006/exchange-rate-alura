package com.alura.exchangerate.challenge.api;

import com.alura.exchangerate.challenge.api.client.ExchangeRateService;
import com.alura.exchangerate.challenge.api.client.HttpExchangeRateService;
import com.alura.exchangerate.challenge.api.util.console.ConsolePrinter;
import com.alura.exchangerate.challenge.api.util.http.DefaultHttpRequestExecutor;
import com.alura.exchangerate.challenge.api.util.http.HttpRequestExecutor;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequestExecutor requestExecutor = new DefaultHttpRequestExecutor(httpClient);
        Gson gson = new Gson();
        String baseUrl = "https://v6.exchangerate-api.com/v6/77515c08de87ee8d8765dc55/";
        ExchangeRateService exchangeRateService = new HttpExchangeRateService(requestExecutor, gson, baseUrl);
        ConsolePrinter consolePrinter = new ConsolePrinter(exchangeRateService);
        try {
            consolePrinter.menu();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

