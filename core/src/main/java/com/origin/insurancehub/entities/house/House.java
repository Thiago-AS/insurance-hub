package com.origin.insurancehub.entities.house;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class House {

    private OwnershipStatus ownershipStatus;
}
