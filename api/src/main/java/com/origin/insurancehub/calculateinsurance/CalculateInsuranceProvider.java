package com.origin.insurancehub.calculateinsurance;

import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculateInsuranceProvider {

    public RestCalculateInsurancePresenter getPresenter() {
        return new RestCalculateInsurancePresenter();
    }

    public CalculateInsuranceInteractor getInteractor(final RestCalculateInsurancePresenter presenter) {
        return new CalculateInsuranceInteractor(presenter);
    }
}
