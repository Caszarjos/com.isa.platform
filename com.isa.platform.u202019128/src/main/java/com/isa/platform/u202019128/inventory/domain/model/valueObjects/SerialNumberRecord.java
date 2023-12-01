package com.isa.platform.u202019128.inventory.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record SerialNumberRecord(String serialNumber) {
    public SerialNumberRecord() {
        this(UUID.randomUUID().toString());
    }

    public SerialNumberRecord {
        if (serialNumber == null || serialNumber.isBlank()){
            throw new IllegalArgumentException("Id cannot be null or blank");
        }
    }
}
