package com.origin.insurancehub.entities.user;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import lombok.Data;
import java.util.List;

@Data
public class User {

    private Long age;

    private Long dependents;

    private House house;

    private Long income;

    private MaritalStatus maritalStatus;

    private List<Boolean> riskQuestions;

    private Vehicle vehicle;
}
