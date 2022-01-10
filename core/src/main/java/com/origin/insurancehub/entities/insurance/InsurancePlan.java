package com.origin.insurancehub.entities.insurance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum InsurancePlan {
    INELIGIBLE("ineligible"),
    ECONOMIC("economic"),
    REGULAR("regular"),
    RESPONSIBLE("responsible");

    private final String label;

    public static InsurancePlan fromRiskValue(final Integer value) {
        return Optional.ofNullable(value).map(val -> {
            if (val >= 3) {
                return InsurancePlan.RESPONSIBLE;
            }
            if (val == 1 || val == 2) {
                return InsurancePlan.REGULAR;
            }

            return InsurancePlan.ECONOMIC;
        }).orElse(InsurancePlan.INELIGIBLE);
    }

    @Override
    public String toString() {
        return this.label;
    }
}
