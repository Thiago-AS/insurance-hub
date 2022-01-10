package com.origin.insurancehub.entities.insurance;

import com.origin.insurancehub.entities.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Insurance {

    private final User user;

    private InsurancePlan life;

    private InsurancePlan disability;

    private InsurancePlan home;

    private InsurancePlan auto;
}
