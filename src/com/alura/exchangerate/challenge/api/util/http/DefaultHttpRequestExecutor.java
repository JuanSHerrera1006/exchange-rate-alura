package com.alura.exchangerate.challenge.api.util.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultHttpRequestExecutor implements HttpRequestExecutor {
    private final HttpClient httpClient;

    public DefaultHttpRequestExecutor(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String executeGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
