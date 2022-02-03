package com.origin.insurancehub.entities.risk;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskItem {

    private Long id;

    private Integer score;
}
