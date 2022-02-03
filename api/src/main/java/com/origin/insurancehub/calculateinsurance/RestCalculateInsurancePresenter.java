package com.origin.insurancehub.calculateinsurance;

import com.origin.insurancehub.entities.insurance.Insurance;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsurancePresenter;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;

@Slf4j
public class RestCalculateInsurancePresenter implements CalculateInsurancePresenter {

    private Insurance insurance;

    @Override
    public void error() {
        log.info("Error calculating user insurances");
    }

    @Override
    public void success(final Insurance insurance) {
        this.insurance = insurance;
    }

    public RestCalculateInsuranceResponse buildPayload() {
        return RestCalculateInsuranceResponse.builder()
                .auto(this.insurance.getAuto().stream()
                        .map(autoPlan -> new RestCalculateInsuranceResponse.InsuranceResponseItem(autoPlan.getId(),
                                autoPlan.getPlan().getLabel())).collect(Collectors.toList()))
                .disability(this.insurance.getDisability().getLabel())
                .home(this.insurance.getHome().stream()
                        .map(homePlan -> new RestCalculateInsuranceResponse.InsuranceResponseItem(homePlan.getId(),
                                homePlan.getPlan().getLabel())).collect(Collectors.toList()))
                .life(this.insurance.getLife().getLabel())
                .build();
    }
}
