package com.origin.insurancehub.usecases.calculateinsurance;

import com.origin.insurancehub.entities.insurance.Insurance;

public interface CalculateInsurancePresenter {

    void error();

    void success(Insurance insurance);
}
