package com.origin.insurancehub.entities.user;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class MaritalStatusTest {

    @Test
    void shouldReturnCorrectStatusFromLabel() {
        assertThat(MaritalStatus.fromLabel("single"), is(MaritalStatus.SINGLE));
    }

    @Test
    void shouldReturnNullWhenNoStatusFoundFromLabel() {
        assertThat(MaritalStatus.fromLabel("not-existent"), is(nullValue()));
    }
}
