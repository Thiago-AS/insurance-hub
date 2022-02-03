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

    private List<ListIsuranceItem> home;

    private List<ListIsuranceItem> auto;
}
