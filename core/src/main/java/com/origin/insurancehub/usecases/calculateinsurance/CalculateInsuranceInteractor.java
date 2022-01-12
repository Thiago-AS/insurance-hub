package com.origin.insurancehub.usecases.calculateinsurance;

import com.origin.insurancehub.entities.house.OwnershipStatus;
import com.origin.insurancehub.entities.insurance.Insurance;
import com.origin.insurancehub.entities.insurance.InsurancePlan;
import com.origin.insurancehub.entities.user.MaritalStatus;
import com.origin.insurancehub.entities.user.User;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class CalculateInsuranceInteractor {

    private final CalculateInsurancePresenter presenter;

    public void execute(final CalculateInsuranceRequest calculateInsuranceRequest) {
        final User user = User.builder()
                .age(calculateInsuranceRequest.getAge())
                .dependents(calculateInsuranceRequest.getDependents())
                .house(calculateInsuranceRequest.getHouse())
                .income(calculateInsuranceRequest.getIncome())
                .maritalStatus(calculateInsuranceRequest.getMaritalStatus())
                .riskQuestions(calculateInsuranceRequest.getRiskQuestions())
                .vehicle(calculateInsuranceRequest.getVehicle())
                .build();

        final Insurance insurance = calculateInsurance(user);
        this.presenter.success(insurance);
    }

    private Insurance calculateInsurance(final User user) {
        return Insurance.builder()
                .user(user)
                .auto(calculateAutoRisk(user).map(InsurancePlan::fromRiskValue).orElse(InsurancePlan.INELIGIBLE))
                .home(calculateHomeRisk(user).map(InsurancePlan::fromRiskValue).orElse(InsurancePlan.INELIGIBLE))
                .disability(calculateDisabilityRisk(user).map(InsurancePlan::fromRiskValue)
                        .orElse(InsurancePlan.INELIGIBLE))
                .life(calculateLifeRisk(user).map(InsurancePlan::fromRiskValue)
                        .orElse(InsurancePlan.INELIGIBLE))
                .build();
    }

    private Optional<Integer> calculateAutoRisk(final User user) {
        if (user.hasNoVehicle()) {
            return Optional.empty();
        }

        int risk = user.getRiskQuestions().stream().mapToInt(Integer::intValue).sum();
        if (user.isYoungerThan30()) {
            risk -= 2;
        }
        if (user.isBetween30And40()) {
            risk -= 1;
        }
        if (user.hasGreatIncome()) {
            risk -= 1;
        }
        if (user.getVehicle().isRecentlyProduced()) {
            risk += 1;
        }

        return Optional.of(risk);
    }

    private Optional<Integer> calculateHomeRisk(final User user) {
        if (user.hasNoHouse()) {
            return Optional.empty();
        }

        int risk = user.getRiskQuestions().stream().mapToInt(Integer::intValue).sum();
        if (user.isYoungerThan30()) {
            risk -= 2;
        }
        if (user.isBetween30And40()) {
            risk -= 1;
        }
        if (user.hasGreatIncome()) {
            risk -= 1;
        }
        if (user.getHouse().getOwnershipStatus() == OwnershipStatus.MORTGAGED) {
            risk += 1;
        }

        return Optional.of(risk);
    }

    private Optional<Integer> calculateDisabilityRisk(final User user) {
        if (user.hasNoIncome() || user.isOlderThan60()) {
            return Optional.empty();
        }

        int risk = user.getRiskQuestions().stream().mapToInt(Integer::intValue).sum();
        if (user.isYoungerThan30()) {
            risk -= 2;
        }
        if (user.isBetween30And40()) {
            risk -= 1;
        }
        if (user.hasGreatIncome()) {
            risk -= 1;
        }
        if (Optional.ofNullable(user.getHouse())
                .map(house -> house.getOwnershipStatus() == OwnershipStatus.MORTGAGED).orElse(false)) {
            risk += 1;
        }
        if (user.hasDependents()) {
            risk += 1;
        }
        if (user.getMaritalStatus() == MaritalStatus.MARRIED) {
            risk -= 1;
        }

        return Optional.of(risk);
    }

    private Optional<Integer> calculateLifeRisk(final User user) {
        if (user.isOlderThan60()) {
            return Optional.empty();
        }

        int risk = user.getRiskQuestions().stream().mapToInt(Integer::intValue).sum();
        if (user.isYoungerThan30()) {
            risk -= 2;
        }
        if (user.isBetween30And40()) {
            risk -= 1;
        }
        if (user.hasGreatIncome()) {
            risk -= 1;
        }
        if (user.hasDependents()) {
            risk += 1;
        }
        if (user.getMaritalStatus() == MaritalStatus.MARRIED) {
            risk += 1;
        }

        return Optional.of(risk);
    }
}
