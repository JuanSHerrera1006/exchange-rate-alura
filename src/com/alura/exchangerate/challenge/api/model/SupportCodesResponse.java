package com.alura.exchangerate.challenge.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public record SupportCodesResponse(
       String result,
       @SerializedName("supported_codes")
       ArrayList<ArrayList<String>> supportCodes
) {
}
