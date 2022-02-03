package com.origin.insurancehub.usecases.calculateinsurance;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.house.OwnershipStatus;
import com.origin.insurancehub.entities.insurance.Insurance;
import com.origin.insurancehub.entities.insurance.InsurancePlan;
import com.origin.insurancehub.entities.insurance.ListIsuranceItem;
import com.origin.insurancehub.entities.risk.RiskItem;
import com.origin.insurancehub.entities.user.MaritalStatus;
import com.origin.insurancehub.entities.user.User;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CalculateInsuranceInteractor {

    private final CalculateInsurancePresenter presenter;

    public void execute(final CalculateInsuranceRequest calculateInsuranceRequest) {
        final User user = User.builder()
                .age(calculateInsuranceRequest.getAge())
                .dependents(calculateInsuranceRequest.getDependents())
                .houses(calculateInsuranceRequest.getHouses())
                .income(calculateInsuranceRequest.getIncome())
                .maritalStatus(calculateInsuranceRequest.getMaritalStatus())
                .riskQuestions(calculateInsuranceRequest.getRiskQuestions())
                .vehicles(calculateInsuranceRequest.getVehicles())
                .build();

        final Insurance insurance = calculateInsurance(user);
        this.presenter.success(insurance);
    }

    private Insurance calculateInsurance(final User user) {
        final Insurance insurance = Insurance.builder()
                .user(user)
                .auto(calculateAllAutoRisks(user).stream()
                        .map(autoRisk -> ListIsuranceItem.builder().id(autoRisk.getId())
                                .plan(InsurancePlan.fromRiskValue(autoRisk.getScore())).build()
                        ).collect(Collectors.toList()))
                .home(calculateAllHomeRisk(user).stream()
                        .map(homeRisk -> ListIsuranceItem.builder().id(homeRisk.getId())
                                .plan(InsurancePlan.fromRiskValue(homeRisk.getScore())).build()
                        ).collect(Collectors.toList()))
                .disability(calculateDisabilityRisk(user).map(InsurancePlan::fromRiskValue)
                        .orElse(InsurancePlan.INELIGIBLE))
                .life(calculateLifeRisk(user).map(InsurancePlan::fromRiskValue)
                        .orElse(InsurancePlan.INELIGIBLE))
                .build();
        insurance.setUmbrella(calculateUmbrellaRisk(user, insurance).map(InsurancePlan::fromRiskValue)
                .orElse(InsurancePlan.INELIGIBLE));
        return insurance;
    }

    private Integer calculateBaseRisk(final User user) {
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

        return risk;
    }

    private Optional<Integer> calculateUmbrellaRisk(final User user, final Insurance insurance) {
        if (insurance.hasAnyEconomicInsurancePlan()) {
            return Optional.of(calculateBaseRisk(user));
        }

        return Optional.empty();
    }

    private List<RiskItem> calculateAllAutoRisks(final User user) {
        if (user.hasNoVehicle()) {
            return List.of();
        }

        final Integer baseRisk = this.calculateBaseRisk(user);
        return user.getVehicles().stream()
                .map(vehicle -> RiskItem.builder().id(vehicle.getId()).score(this.calculateAutoRisk(vehicle, baseRisk))
                        .build())
                .collect(Collectors.toList());
    }

    private Integer calculateAutoRisk(final Vehicle vehicle, final int baseRisk) {
        int risk = baseRisk;
        if (vehicle.isRecentlyProduced()) {
            risk += 1;
        }

        return risk;
    }

    private List<RiskItem> calculateAllHomeRisk(final User user) {
        if (user.hasNoHouse()) {
            return List.of();
        }

        final Integer baseRisk = this.calculateBaseRisk(user);
        return user.getHouses().stream()
                .map(house -> RiskItem.builder().id(house.getId()).score(this.calculateHomeRisk(house, baseRisk))
                        .build()).collect(Collectors.toList());
    }

    private Integer calculateHomeRisk(final House house, final int baseRisk) {
        int risk = baseRisk;
        if (house.getOwnershipStatus() == OwnershipStatus.MORTGAGED) {
            risk += 1;
        }

        return risk;
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
        if (user.getHouses().stream().anyMatch(house -> house.getOwnershipStatus() == OwnershipStatus.MORTGAGED)) {
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
