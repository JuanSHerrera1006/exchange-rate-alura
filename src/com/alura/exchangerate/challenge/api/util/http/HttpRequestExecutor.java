package com.alura.exchangerate.challenge.api.util.http;

import java.io.IOException;

public interface HttpRequestExecutor {
    String executeGetRequest(String url) throws IOException, InterruptedException;
}
