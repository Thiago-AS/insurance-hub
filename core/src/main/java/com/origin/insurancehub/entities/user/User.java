package com.origin.insurancehub.entities.user;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class User {

    private Long age;

    private Long dependents;

    private House house;

    private Long income;

    private MaritalStatus maritalStatus;

    private List<Integer> riskQuestions;

    private Vehicle vehicle;

    public boolean hasNoVehicle() {
        return this.vehicle == null;
    }

    public boolean hasNoHouse() {
        return this.house == null;
    }

    public boolean hasNoIncome() {
        return this.income == 0;
    }

    public boolean hasDependents() {
        return this.dependents > 0;
    }

    public boolean hasGreatIncome() {
        return this.income > 200_000;
    }

    public boolean isYoungerThan30() {
        return this.age < 30;
    }

    public boolean isBetween30And40() {
        return this.age < 40 && this.age >= 30;
    }

    public boolean isOlderThan60() {
        return this.age > 60;
    }
}
