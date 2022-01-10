package com.origin.insurancehub.calculateinsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
public class RestCalculateInsuranceRequest {
    @JsonProperty("age")
    @PositiveOrZero
    @NotNull
    private final Long age;

    @JsonProperty("dependents")
    @PositiveOrZero
    @NotNull
    private final Long dependents;

    @JsonProperty("house")
    @Valid
    private final House house;

    @JsonProperty("income")
    @PositiveOrZero
    @NotNull
    private final Long income;

    @JsonProperty("marital_status")
    @Pattern(regexp = "^(single|married)$")
    @NotNull
    private final String maritalStatus;

    @JsonProperty("risk_questions")
    @NotNull
    private final List<Integer> riskQuestions;

    @JsonProperty("vehicle")
    @Valid
    private final Vehicle vehicle;

    @Data
    public static class House {

        @JsonProperty("ownership_status")
        @Pattern(regexp = "^(owned|mortgaged)$")
        @NotNull
        private String ownershipStatus;
    }

    @Data
    public static class Vehicle {

        @JsonProperty("year")
        @PositiveOrZero
        @NotNull
        private Long year;
    }
}
