package com.origin.insurancehub.entities.vehicle;

import lombok.Builder;
import lombok.Data;
import java.util.Calendar;

@Data
@Builder
public class Vehicle {

    private Long year;

    public boolean isRecentlyProduced() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.year < 5;
    }
}
