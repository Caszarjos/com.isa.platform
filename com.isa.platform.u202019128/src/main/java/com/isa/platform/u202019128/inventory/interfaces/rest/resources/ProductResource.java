package com.isa.platform.u202019128.inventory.interfaces.rest.resources;


public record ProductResource(
        Long id,
        String brand,
        String model,
        String monitoringLevel,
        String serialNumber
) {
}
