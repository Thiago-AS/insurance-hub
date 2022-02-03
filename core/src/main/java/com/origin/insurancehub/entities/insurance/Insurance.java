package com.origin.insurancehub.entities.insurance;

import com.origin.insurancehub.entities.user.User;
import lombok.Builder;
import lombok.Data;
import java.util.List;

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
}
