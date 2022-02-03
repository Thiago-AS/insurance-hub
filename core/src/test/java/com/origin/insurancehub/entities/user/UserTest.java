package com.origin.insurancehub.entities.user;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.house.OwnershipStatus;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserTest {

    @Test
    void shouldCheckForNoVehicle() {
        final User user = User.builder()
                .vehicles(List.of())
                .houses(List.of(House.builder().id(1L)
                        .ownershipStatus(OwnershipStatus.OWNED)
                        .build()))
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();

        assertThat(user.hasNoVehicle(), is(true));
    }

    @Test
    void shouldCheckForNoHouse() {
        final User user = User.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of())
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();

        assertThat(user.hasNoHouse(), is(true));
    }

    @Test
    void shouldCheckForNoIncome() {
        final User user = User.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder()
                        .id(2L)
                        .ownershipStatus(OwnershipStatus.OWNED)
                        .build()))
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();

        assertThat(user.hasNoIncome(), is(true));
    }

    @Test
    void shouldCheckForDependents() {
        final User user = User.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder()
                        .id(2L)
                        .ownershipStatus(OwnershipStatus.OWNED)
                        .build()))
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(2L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();

        assertThat(user.hasDependents(), is(true));
    }

    @Test
    void shouldCheckForGreatIncome() {
        final User user = User.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder()
                        .id(2L)
                        .ownershipStatus(OwnershipStatus.OWNED)
                        .build()))
                .income(300_000L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();

        assertThat(user.hasGreatIncome(), is(true));
    }

    @Test
    void shouldCheckAgeRange() {
        final User user = User.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder()
                        .id(2L)
                        .ownershipStatus(OwnershipStatus.OWNED)
                        .build()))
                .income(300_000L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(35L)
                .build();

        assertThat(user.isYoungerThan30(), is(false));
        assertThat(user.isBetween30And40(), is(true));
        assertThat(user.isOlderThan60(), is(false));
    }
}
