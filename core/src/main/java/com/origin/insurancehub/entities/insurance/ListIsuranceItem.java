package com.origin.insurancehub.entities.insurance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListIsuranceItem {

    private Long id;

    private InsurancePlan plan;
}
