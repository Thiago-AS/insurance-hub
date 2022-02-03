package com.origin.insurancehub.calculateinsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
public class RestCalculateInsuranceResponse {

    @JsonProperty("auto")
    private final List<InsuranceResponseItem> auto;

    @JsonProperty("disability")
    private final String disability;

    @JsonProperty("home")
    private final List<InsuranceResponseItem> home;

    @JsonProperty("life")
    private final String life;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InsuranceResponseItem {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("plan")
        private String plan;
    }
}
