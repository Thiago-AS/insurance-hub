package com.origin.insurancehub.entities.vehicle;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class VehicleTest {

    @Test
    void shouldCheckForRecentlyProduced() {
        final Vehicle vehicle = Vehicle.builder().year(2018L).build();

        assertThat(vehicle.isRecentlyProduced(), is(true));
    }
}
