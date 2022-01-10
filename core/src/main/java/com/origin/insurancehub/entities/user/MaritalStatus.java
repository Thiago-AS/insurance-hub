package com.origin.insurancehub.entities.user;

import lombok.RequiredArgsConstructor;
import java.util.Arrays;

@RequiredArgsConstructor
public enum MaritalStatus {
    SINGLE("single"),
    MARRIED("married");

    private final String label;

    @Override
    public String toString() {
        return this.label;
    }

    public static MaritalStatus fromLabel(final String label) {
        return Arrays.stream(MaritalStatus.values()).filter(val -> val.label.equals(label)).findFirst().orElse(null);
    }
}
