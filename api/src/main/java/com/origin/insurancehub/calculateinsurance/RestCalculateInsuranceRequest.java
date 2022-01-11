package com.origin.insurancehub.calculateinsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestCalculateInsuranceRequest {
    @JsonProperty("age")
    @PositiveOrZero
    @NotNull
    private Long age;

    @JsonProperty("dependents")
    @PositiveOrZero
    @NotNull
    private Long dependents;

    @JsonProperty("house")
    @Valid
    private House house;

    @JsonProperty("income")
    @PositiveOrZero
    @NotNull
    private Long income;

    @JsonProperty("marital_status")
    @Pattern(regexp = "^(single|married)$")
    @NotNull
    private String maritalStatus;

    @JsonProperty("risk_questions")
    @NotNull
    private List<Integer> riskQuestions;

    @JsonProperty("vehicle")
    @Valid
    private Vehicle vehicle;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class House {

        @JsonProperty("ownership_status")
        @Pattern(regexp = "^(owned|mortgaged)$")
        @NotNull
        private String ownershipStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vehicle {

        @JsonProperty("year")
        @PositiveOrZero
        @NotNull
        private Long year;
    }
}
