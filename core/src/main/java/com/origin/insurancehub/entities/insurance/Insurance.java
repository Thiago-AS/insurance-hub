package com.origin.insurancehub.entities.insurance;

import com.origin.insurancehub.entities.user.User;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Insurance {

    private final User user;

    private InsurancePlan life;

    private InsurancePlan disability;

    private InsurancePlan umbrella;

    private List<ListIsuranceItem> home;

    private List<ListIsuranceItem> auto;

    public boolean hasAnyEconomicInsurancePlan() {
        return life.equals(InsurancePlan.ECONOMIC)
                || disability.equals(InsurancePlan.ECONOMIC)
                || home.stream().anyMatch(homeItem -> homeItem.getPlan().equals(InsurancePlan.ECONOMIC)
                || auto.stream().anyMatch(autoItem -> autoItem.getPlan().equals(InsurancePlan.ECONOMIC)));
    }

    public static Insurance getIneligibleInsurances(final User user) {
        return Insurance.builder().user(user)
                .auto(user.getVehicles().stream().map(vehicle -> ListIsuranceItem.builder().id(
                        vehicle.getId()).plan(InsurancePlan.INELIGIBLE).build()).collect(Collectors.toList()))
                .home(user.getHouses().stream()
                        .map(house -> ListIsuranceItem.builder().id(house.getId()).plan(InsurancePlan.INELIGIBLE)
                                .build()).collect(Collectors.toList()))
                .umbrella(InsurancePlan.INELIGIBLE)
                .life(InsurancePlan.INELIGIBLE)
                .disability(InsurancePlan.INELIGIBLE)
                .build();
    }
}
