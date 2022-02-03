package com.origin.insurancehub.entities.insurance;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class InsuranceTest {

    @Test
    void shouldCheckIfHaveAnyEconomicInsurancePlan() {
        final Insurance insurance = Insurance.builder()
                .disability(InsurancePlan.ECONOMIC)
                .life(InsurancePlan.INELIGIBLE)
                .home(List.of())
                .auto(List.of())
                .build();

        assertThat(insurance.hasAnyEconomicInsurancePlan(), is(true));

    }
}
