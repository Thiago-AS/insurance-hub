package com.origin.insurancehub.entities.house;

import lombok.RequiredArgsConstructor;
import java.util.Arrays;

@RequiredArgsConstructor
public enum OwnershipStatus {
    OWNED("owned"),
    MORTGAGED("mortgaged");

    private final String label;

    @Override
    public String toString() {
        return this.label;
    }

    public static OwnershipStatus fromLabel(final String label) {
        return Arrays.stream(OwnershipStatus.values()).filter(val -> val.label.equals(label)).findFirst().orElse(null);
    }
}
