package com.origin.insurancehub.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestViolation {
    private final String fieldName;

    private final String message;
}
