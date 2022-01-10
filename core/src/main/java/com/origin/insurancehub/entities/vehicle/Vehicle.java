package com.origin.insurancehub.entities.vehicle;

import lombok.Data;
import java.util.Calendar;

@Data
public class Vehicle {

    private Long year;

    public boolean isRecentlyProduced() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.year < 5;
    }
}