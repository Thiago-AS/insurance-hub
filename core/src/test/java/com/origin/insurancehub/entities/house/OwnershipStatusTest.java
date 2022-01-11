package com.origin.insurancehub.entities.house;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class OwnershipStatusTest {

    @Test
    void shouldReturnCorrectStatusFromLabel() {
        assertThat(OwnershipStatus.fromLabel("mortgaged"), is(OwnershipStatus.MORTGAGED));
    }

    @Test
    void shouldReturnNullWhenNoStatusFoundFromLabel() {
        assertThat(OwnershipStatus.fromLabel("not-existent"), is(nullValue()));
    }
}
