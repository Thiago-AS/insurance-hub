package com.origin.insurancehub.calculateinsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestCalculateInsuranceResponse {

    @JsonProperty("auto")
    private final String auto;

    @JsonProperty("disability")
    private final String disability;

    @JsonProperty("home")
    private final String home;

    @JsonProperty("life")
    private final String life;
}
