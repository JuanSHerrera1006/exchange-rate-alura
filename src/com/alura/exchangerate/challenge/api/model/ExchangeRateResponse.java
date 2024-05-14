package com.alura.exchangerate.challenge.api.model;

import com.google.gson.annotations.SerializedName;

public record ExchangeRateResponse(
        String result,
        @SerializedName("base_code")
        String baseCode,
        @SerializedName("target_code")
        String targetCode,
        @SerializedName("conversion_rate")
        double conversionRate
){}
