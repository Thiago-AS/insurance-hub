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

    @JsonProperty("houses")
    @Valid
    private List<House> houses;

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

    @JsonProperty("vehicles")
    @Valid
    private List<Vehicle> vehicles;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class House {

        @JsonProperty("id")
        @PositiveOrZero
        @NotNull
        private Long id;

        @JsonProperty("ownership_status")
        @Pattern(regexp = "^(owned|mortgaged)$")
        @NotNull
        private String ownershipStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vehicle {

        @JsonProperty("id")
        @PositiveOrZero
        @NotNull
        private Long id;

        @JsonProperty("year")
        @PositiveOrZero
        @NotNull
        private Long year;
    }
}
