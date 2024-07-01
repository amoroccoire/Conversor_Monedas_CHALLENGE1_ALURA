package com.ConversorX.ManageCurrency;

import com.google.gson.annotations.SerializedName;

public record Transform(
        @SerializedName("result") String result,
        @SerializedName("base_code") String base_code,
        @SerializedName("target_code") String target_code,
        @SerializedName("conversion_result") double conversion_result) {
}
