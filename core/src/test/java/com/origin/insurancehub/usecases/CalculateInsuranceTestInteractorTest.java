package com.origin.insurancehub.usecases;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.house.OwnershipStatus;
import com.origin.insurancehub.entities.insurance.Insurance;
import com.origin.insurancehub.entities.insurance.InsurancePlan;
import com.origin.insurancehub.entities.insurance.ListIsuranceItem;
import com.origin.insurancehub.entities.user.MaritalStatus;
import com.origin.insurancehub.entities.user.User;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceInteractor;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsurancePresenter;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CalculateInsuranceTestInteractorTest {

    @Mock
    private CalculateInsurancePresenter presenter;

    private CalculateInsuranceInteractor interactor;

    @BeforeEach
    void setUp() {
        this.interactor = new CalculateInsuranceInteractor(this.presenter);
    }

    @Test
    void shouldReturnAllIneligibleInsurances() {
        final CalculateInsuranceRequest request = CalculateInsuranceRequest.builder()
                .vehicles(List.of())
                .houses(List.of())
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(0L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(65L)
                .build();
        final Insurance insurance = Insurance.builder()
                .user(buildUserFromRequest(request))
                .life(InsurancePlan.INELIGIBLE)
                .home(List.of())
                .auto(List.of())
                .disability(InsurancePlan.INELIGIBLE)
                .umbrella(InsurancePlan.INELIGIBLE)
                .build();

        this.interactor.execute(request);

        verify(this.presenter).success(insurance);
    }

    @Test
    void shouldReturnCalculatedInsurances() {
        final CalculateInsuranceRequest request = CalculateInsuranceRequest.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder().id(2L).ownershipStatus(OwnershipStatus.OWNED).build()))
                .income(0L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(2L)
                .maritalStatus(MaritalStatus.MARRIED)
                .age(35L)
                .build();
        final Insurance insurance = Insurance.builder()
                .user(buildUserFromRequest(request))
                .life(InsurancePlan.REGULAR)
                .home(List.of(ListIsuranceItem.builder().id(2L).plan(InsurancePlan.ECONOMIC).build()))
                .auto(List.of(ListIsuranceItem.builder().id(1L).plan(InsurancePlan.REGULAR).build()))
                .disability(InsurancePlan.INELIGIBLE)
                .umbrella(InsurancePlan.ECONOMIC)
                .build();

        this.interactor.execute(request);

        verify(this.presenter).success(insurance);
    }

    @Test
    void shouldCountSecondRiskQuestionInDisabilityInsurance() {
        final CalculateInsuranceRequest request = CalculateInsuranceRequest.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder().id(2L).ownershipStatus(OwnershipStatus.OWNED).build()))
                .income(100_000L)
                .riskQuestions(List.of(0, 1, 0))
                .dependents(2L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(35L)
                .build();
        final Insurance insurance = Insurance.builder()
                .user(buildUserFromRequest(request))
                .life(InsurancePlan.REGULAR)
                .home(List.of(ListIsuranceItem.builder().id(2L).plan(InsurancePlan.ECONOMIC).build()))
                .auto(List.of(ListIsuranceItem.builder().id(1L).plan(InsurancePlan.REGULAR).build()))
                .disability(InsurancePlan.RESPONSIBLE)
                .umbrella(InsurancePlan.ECONOMIC)
                .build();

        this.interactor.execute(request);

        verify(this.presenter).success(insurance);
    }

    @Test
    void shouldReturnAllInsurancesIneligible() {
        final CalculateInsuranceRequest request = CalculateInsuranceRequest.builder()
                .vehicles(List.of(Vehicle.builder().id(1L).year(2018L).build()))
                .houses(List.of(House.builder().id(2L).ownershipStatus(OwnershipStatus.OWNED).build()))
                .income(20_000L)
                .riskQuestions(List.of(0, 0, 0))
                .dependents(2L)
                .maritalStatus(MaritalStatus.SINGLE)
                .age(35L)
                .build();
        final Insurance insurance = Insurance.builder()
                .user(buildUserFromRequest(request))
                .life(InsurancePlan.INELIGIBLE)
                .home(List.of(ListIsuranceItem.builder().id(2L).plan(InsurancePlan.INELIGIBLE).build()))
                .auto(List.of(ListIsuranceItem.builder().id(1L).plan(InsurancePlan.INELIGIBLE).build()))
                .disability(InsurancePlan.INELIGIBLE)
                .umbrella(InsurancePlan.INELIGIBLE)
                .build();

        this.interactor.execute(request);

        verify(this.presenter).success(insurance);
    }

    private User buildUserFromRequest(final CalculateInsuranceRequest request) {
        return User.builder()
                .age(request.getAge())
                .dependents(request.getDependents())
                .houses(request.getHouses())
                .income(request.getIncome())
                .maritalStatus(request.getMaritalStatus())
                .riskQuestions(request.getRiskQuestions())
                .vehicles(request.getVehicles())
                .build();
    }
}
