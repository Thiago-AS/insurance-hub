package com.origin.insurancehub.calculateinsurance;

import com.origin.insurancehub.entities.insurance.Insurance;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsurancePresenter;
import lombok.extern.slf4j.Slf4j;

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
                .auto(this.insurance.getAuto().getLabel())
                .disability(this.insurance.getDisability().getLabel())
                .home(this.insurance.getHome().getLabel())
                .life(this.insurance.getLife().getLabel())
                .build();
    }
}
