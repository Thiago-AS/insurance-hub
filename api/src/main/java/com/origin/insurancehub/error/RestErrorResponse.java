package com.origin.insurancehub.error;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RestErrorResponse {

    private final ZonedDateTime timestamp;

    private final int status;

    private final String error;

    private final String path;

    private final List<RestViolation> violations = new ArrayList<>();
}
