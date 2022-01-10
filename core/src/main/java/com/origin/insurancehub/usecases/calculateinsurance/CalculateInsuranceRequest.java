package com.origin.insurancehub.usecases.calculateinsurance;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.user.MaritalStatus;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CalculateInsuranceRequest {

    private final Long age;

    private final Long dependents;

    private final House house;

    private final Long income;

    private final MaritalStatus maritalStatus;

    private final List<Integer> riskQuestions;

    private final Vehicle vehicle;
}
