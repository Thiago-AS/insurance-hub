package com.origin.insurancehub.entities.insurance;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class InsurancePlanTest {

    @Test
    void shouldMapResponsibleFromRiskValue() {
        assertThat(InsurancePlan.fromRiskValue(4), is(InsurancePlan.RESPONSIBLE));
    }

    @Test
    void shouldMapRegularFromRiskValue() {
        assertThat(InsurancePlan.fromRiskValue(2), is(InsurancePlan.REGULAR));
    }

    @Test
    void shouldMapEconomicFromRiskValue() {
        assertThat(InsurancePlan.fromRiskValue(-1), is(InsurancePlan.ECONOMIC));
    }

    @Test
    void shouldMapIneligibleFromRiskValue() {
        assertThat(InsurancePlan.fromRiskValue(null), is(InsurancePlan.INELIGIBLE));
    }
}
